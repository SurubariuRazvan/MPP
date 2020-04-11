package network.rpcprotocol;

import domain.BookedTrip;
import domain.BookedTripDTO;
import domain.TripDTO;
import domain.User;
import network.dto.*;
import services.AppServiceException;
import services.IAppObserver;
import services.IAppServices;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AppProxyService implements IAppServices {
    private String host;
    private int port;

    private IAppObserver client;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;

    private BlockingQueue<Response> qresponses;
    private volatile boolean finished;

    public AppProxyService(String host, int port, IAppObserver client) {
        this.host = host;
        this.port = port;
        this.client = client;
        qresponses = new LinkedBlockingQueue<>();
        initializeConnection();
    }


    public List<BookedTripDTO> search(String destinationName, Timestamp departure) throws AppServiceException {
        Request req = new Request.Builder().type(RequestType.GET_BOOKED_TRIPS).data(new NetTripDTO(destinationName, departure)).build();
        sendRequest(req);
        Response response = readResponse();
        if (response.type() == ResponseType.ERROR) {
            String err = response.data().toString();
            throw new AppServiceException(err);
        }
        return (List<BookedTripDTO>) response.data();
    }

    public BookedTrip findClientID(Integer tripID, Integer seatNumber) throws AppServiceException {
        Request req = new Request.Builder().type(RequestType.GET_BOOKED_TRIP).data(new NetBookedTripDTO(tripID, seatNumber)).build();
        sendRequest(req);
        Response response = readResponse();
        if (response.type() == ResponseType.ERROR)
            throw new AppServiceException(response.data().toString());
        return (BookedTrip) response.data();
    }

    public void reserve(int tripID, String clientName, int seatNumber) throws AppServiceException {
        Request req = new Request.Builder().type(RequestType.RESERVE_SEAT).data(new NetReservedDTO(tripID, clientName, seatNumber)).build();
        sendRequest(req);
        Response response = readResponse();
        if (response.type() == ResponseType.ERROR)
            throw new AppServiceException(response.data().toString());
    }

    public List<TripDTO> showTrips() throws AppServiceException {
        Request req = new Request.Builder().type(RequestType.GET_TRIPS).data(null).build();
        sendRequest(req);
        Response response = readResponse();
        if (response.type() == ResponseType.ERROR) {
            String err = response.data().toString();
            throw new AppServiceException(err);
        }
        return (List<TripDTO>) response.data();
    }

    public Integer getTripIDByDestinationAndDeparture(String destination, Timestamp departure) throws AppServiceException {
        Request req = new Request.Builder().type(RequestType.GET_BOOKED_TRIP_ID).data(new NetTripDTO(destination, departure)).build();
        sendRequest(req);
        Response response = readResponse();
        if (response.type() == ResponseType.ERROR) {
            String err = response.data().toString();
            throw new AppServiceException(err);
        }
        return (Integer) response.data();
    }

    private void closeConnection() {
        finished = true;
        try {
            input.close();
            output.close();
            connection.close();
            client = null;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendRequest(Request request) throws AppServiceException {
        try {
            output.writeObject(request);
            output.flush();
        } catch (IOException e) {
            throw new AppServiceException("Error sending object " + e);
        }
    }

    private Response readResponse() {
        Response response = null;
        try {
            response = qresponses.take();
        } catch (InterruptedException | ClassCastException e) {
            e.printStackTrace();
        }
        return response;
    }

    private void initializeConnection() {
        try {
            connection = new Socket(host, port);
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            finished = false;
            startReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startReader() {
        Thread tw = new Thread(new ReaderThread());
        tw.start();
    }


    private void handleUpdate(Response response) {
        if (response.type() == ResponseType.RESERVED) {
            NetUpdateDTO netUpdateDTO = (NetUpdateDTO) response.data();
            System.out.println("updatewindow why crash?");
            client.updateWindows(netUpdateDTO.getDestinationName(), netUpdateDTO.getDeparture(), netUpdateDTO.getSeatNumber(), netUpdateDTO.getClientName());
        }
    }

    private boolean isUpdate(Response response) {
        return response.type() == ResponseType.RESERVED;
    }

    @Override
    public User login(String username, String password, IAppObserver client) throws AppServiceException {
        initializeConnection();
        Request req = new Request.Builder().type(RequestType.LOGIN).data(new UserDTO(username, password)).build();
        sendRequest(req);
        Response response = readResponse();
        if (response.type() == ResponseType.OK) {
            this.client = client;
            return (User) response.data();
        }
        closeConnection();
        throw new AppServiceException(response.data().toString());
    }

    @Override
    public void logout(Integer userID) throws AppServiceException {
        Request req = new Request.Builder().type(RequestType.LOGOUT).data(userID).build();
        sendRequest(req);
        Response response = readResponse();
        closeConnection();
        if (response.type() == ResponseType.ERROR)
            throw new AppServiceException(response.data().toString());
    }

    private class ReaderThread implements Runnable {
        public void run() {
            while(!finished) try {
                Object response = input.readObject();
                System.out.println("response received " + response.toString());
                if (isUpdate((Response) response)) handleUpdate((Response) response);
                else try {
                    qresponses.put((Response) response);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (IOException | ClassNotFoundException | ClassCastException e) {
                System.out.println("Reading error " + e);
            }
        }
    }
}
