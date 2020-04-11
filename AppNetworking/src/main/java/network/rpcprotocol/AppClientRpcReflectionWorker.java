package network.rpcprotocol;

import domain.BookedTrip;
import domain.BookedTripDTO;
import domain.TripDTO;
import domain.User;
import network.dto.*;
import services.AppServiceException;
import services.IAppObserver;
import services.IAppServices;
import services.LoginServiceException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Timestamp;
import java.util.List;


public class AppClientRpcReflectionWorker implements Runnable, IAppObserver {
    private IAppServices server;
    private Socket connection;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile boolean connected;

    public AppClientRpcReflectionWorker(IAppServices server, Socket connection) {
        this.server = server;
        this.connection = connection;
        try {
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            connected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while(connected) {
            try {
                Object request = input.readObject();
                Response response = handleRequest((Request) request);
                if (response != null) {
                    sendResponse(response);
                }
            } catch (SocketException e) {
                connected = false;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            input.close();
            output.close();
            connection.close();
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }

    private Response handleRequest(Request request) {
        Response response = null;
        String handlerName = "handle" + (request).type();
        System.out.println("HandlerName " + handlerName);
        try {
            Method method = this.getClass().getDeclaredMethod(handlerName, Request.class);
            response = (Response) method.invoke(this, request);
            System.out.println("Method " + handlerName + " invoked");
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return response;
    }

    private Response handleLOGIN(Request request) {
        System.out.println("Login request ..." + request.type());
        UserDTO userDTO = (UserDTO) request.data();
        try {
            User user = server.login(userDTO.getUsername(), userDTO.getPassword(), this);
            return new Response.Builder().type(ResponseType.OK).data(user).build();

        } catch (AppServiceException | LoginServiceException e) {
            connected = false;
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    private Response handleLOGOUT(Request request) {
        System.out.println("LOGOUT Request ..." + request.type());
        Integer userID = (Integer) request.data();
        try {
            server.logout(userID);
            connected = false;
            return new Response.Builder().type(ResponseType.LOGOUT).build();
        } catch (AppServiceException | LoginServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    private Response handleGET_TRIPS(Request request) {
        System.out.println("GET_TRIPS Request ..." + request.type());
        try {
            List<TripDTO> trips = server.showTrips();
            return new Response.Builder().type(ResponseType.GET_TRIPS).data(trips).build();
        } catch (AppServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    private Response handleGET_BOOKED_TRIPS(Request request) {
        System.out.println("GET_BOOKED_TRIPS Request ..." + request.type());
        NetTripDTO netTripDTO = (NetTripDTO) request.data();
        try {
            List<BookedTripDTO> trips = server.search(netTripDTO.getDestinationName(), netTripDTO.getDeparture());
            return new Response.Builder().type(ResponseType.GET_BOOKED_TRIPS).data(trips).build();
        } catch (AppServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    private Response handleGET_BOOKED_TRIP(Request request) {
        System.out.println("GET_BOOKED_TRIP Request ..." + request.type());
        NetBookedTripDTO netTripDTO = (NetBookedTripDTO) request.data();
        try {
            BookedTrip trip = server.findClientID(netTripDTO.getTripID(), netTripDTO.getSeatNumber());
            return new Response.Builder().type(ResponseType.GET_BOOKED_TRIP).data(trip).build();
        } catch (AppServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    private Response handleGET_BOOKED_TRIP_ID(Request request) {
        System.out.println("GET_BOOKED_TRIP_ID Request ..." + request.type());
        NetTripDTO netTripDTO = (NetTripDTO) request.data();
        try {
            Integer id = server.getTripIDByDestinationAndDeparture(netTripDTO.getDestinationName(), netTripDTO.getDeparture());
            return new Response.Builder().type(ResponseType.GET_BOOKED_TRIP_ID).data(id).build();
        } catch (AppServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    private Response handleRESERVE_SEAT(Request request) {
        System.out.println("RESERVE_SEAT Request ..." + request.type());
        NetReservedDTO netReservedDTO = (NetReservedDTO) request.data();
        try {
            server.reserve(netReservedDTO.getTripID(), netReservedDTO.getClientName(), netReservedDTO.getSeatNumber());
            return new Response.Builder().type(ResponseType.RESERVE_SEAT).build();
        } catch (AppServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    private void sendResponse(Response response) throws IOException {
        System.out.println("sending response " + response);
        output.writeObject(response);
        output.flush();
    }

    @Override
    public void updateWindows(String destinationName, Timestamp departure, int seatNumber, String clientName) {
        Response resp = new Response.Builder().type(ResponseType.RESERVED).data(new NetUpdateDTO(destinationName, departure, seatNumber, clientName)).build();
        System.out.println("Update windows from reflection worker ");
        try {
            sendResponse(resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
