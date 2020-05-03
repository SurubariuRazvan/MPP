package com.jderu.app.controller;

import com.jfoenix.controls.*;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.jderu.TripDTO;
import com.jderu.User;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.converter.LocalTimeStringConverter;
import com.jderu.services.AppServiceException;
import com.jderu.services.IAppObserver;
import com.jderu.services.IAppServices;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class AppController implements Initializable, IAppObserver {

    public TableView<TripDTO> tripDTOTable;
    public TableColumn<TripDTO, String> tableDestination;
    public TableColumn<TripDTO, String> tableDeparture;
    public TableColumn<TripDTO, Integer> tableFreeSeats;
    public JFXTextField searchByDestination;
    public JFXButton searchButton;
    public JFXDatePicker searchByDate;
    public JFXTimePicker searchByTime;
    public StackPane rootPane;
    public BorderPane menuTable;
    protected ObservableList<TripDTO> entities;
    private IAppServices appService;
    private User user;
    private TripDetailsController controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableDestination.setCellValueFactory(new PropertyValueFactory<>("destinationName"));
        tableDeparture.setCellValueFactory(line -> new ReadOnlyObjectWrapper<>(new SimpleDateFormat("MM/dd/yyyy HH:mm").format(line.getValue().getDeparture())));
        tableFreeSeats.setCellValueFactory(new PropertyValueFactory<>("freeSeats"));

        tripDTOTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                searchByDestination.setText(newValue.getDestinationName());
                Timestamp time = newValue.getDeparture();
                searchByDate.setValue(time.toLocalDateTime().toLocalDate());
                searchByTime.setValue(time.toLocalDateTime().toLocalTime());
            }

        });
    }

    public void setService(IAppServices appService, User user) {
        this.appService = appService;
        this.user = user;
        entities = FXCollections.emptyObservableList();
        searchByTime.setEditable(false);
        searchByTime.set24HourView(true);
        searchByTime.setConverter(new LocalTimeStringConverter(FormatStyle.SHORT, Locale.GERMANY));
        postInitialization();
    }

    private void postInitialization() {
        try {
            entities = FXCollections.observableList(appService.showTrips());
        } catch (AppServiceException | RemoteException e) {
            e.printStackTrace();
        }
        tripDTOTable.setItems(entities);
    }

    protected void showError(String title, String message) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXButton button = new JFXButton("OK");
        JFXDialog dialog = new JFXDialog(this.rootPane, dialogLayout, JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> dialog.close());

        dialogLayout.setHeading(new Label(title));
        dialogLayout.getStyleClass().add("errorHeading");
        dialogLayout.setBody(new Label(message));
        dialogLayout.setActions(button);
        dialog.show();
        BoxBlur blur = new BoxBlur(3, 3, 2);
        this.menuTable.setEffect(blur);
        dialog.setOnDialogClosed((JFXDialogEvent event) -> this.menuTable.setEffect(null));
    }

    public void searchByDestinationAndDate() throws AppServiceException, RemoteException {
        String destination = searchByDestination.getText();
        LocalDate date = searchByDate.getValue();
        LocalTime time = searchByTime.getValue();
        if (destination == null)
            showError("Unfilled field", "type a name for the destination");
        if (date == null)
            showError("Unfilled field", "select a date");
        if (time == null)
            showError("Unfilled field", "select a time");

        if (destination != null && date != null && time != null) {
            var tripID = appService.getTripIDByDestinationAndDeparture(destination, Timestamp.valueOf(LocalDateTime.of(date, time)));
            if (tripID != null) {
                StackPane parent;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TripDetailsView.fxml"));
                    parent = loader.load();
                    controller = loader.getController();
                    controller.setService(appService, this, tripID, user, destination, Timestamp.valueOf(LocalDateTime.of(date, time)));
                    Stage stage = new Stage();
                    stage.setTitle("Trip details");
                    stage.setScene(new Scene(parent));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void logout() {
        try {
            appService.logout(user.getId());
        } catch (AppServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateWindows(String destinationName, Timestamp departure, int seatNumber, String clientName) {
        for (var e : entities)
            if (e.getDeparture().equals(departure) && e.getDestinationName().equals(destinationName)) {
                e.setFreeSeats(e.getFreeSeats() - 1);
                break;
            }
        tripDTOTable.refresh();
        if (controller != null && controller.departure.equals(departure) && controller.destination.equals(destinationName))
            controller.updateWindows(destinationName, departure, seatNumber, clientName);
    }
}
