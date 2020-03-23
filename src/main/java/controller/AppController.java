package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import domain.TripDTO;
import domain.User;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.converter.LocalTimeStringConverter;
import service.AppService;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    public TableView<TripDTO> tripDTOTable;
    public TableColumn<TripDTO, String> tableDestination;
    public TableColumn<TripDTO, String> tableDeparture;
    public TableColumn<TripDTO, Integer> tableFreeSeats;
    public JFXTextField searchByDestination;
    public JFXButton searchButton;
    public JFXDatePicker searchByDate;
    public JFXTimePicker searchByTime;
    protected ObservableList<TripDTO> entities;
    private AppService appService;
    private User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableDestination.setCellValueFactory(new PropertyValueFactory<>("destinationName"));
        tableDeparture.setCellValueFactory(line -> new ReadOnlyObjectWrapper<>(new SimpleDateFormat("MM/dd/yyyy HH:mm").format(line.getValue().getDeparture())));
        tableFreeSeats.setCellValueFactory(new PropertyValueFactory<>("freeSeats"));

        tripDTOTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            searchByDestination.setText(newValue.getDestinationName());
            Timestamp time = newValue.getDeparture();
            searchByDate.setValue(time.toLocalDateTime().toLocalDate());
            searchByTime.setValue(time.toLocalDateTime().toLocalTime());
        });
    }

    public void setService(AppService appService, User user) {
        this.appService = appService;
        this.user = user;
        entities = FXCollections.emptyObservableList();
        searchByTime.setEditable(false);
        searchByTime.set24HourView(true);
        searchByTime.setConverter(new LocalTimeStringConverter(FormatStyle.SHORT, Locale.GERMANY));

        postInitialization();
    }

    private void postInitialization() {
        entities = FXCollections.observableList(appService.showTrips());
        tripDTOTable.setItems(entities);
    }


    public void addTripDTO() {
        appService.addTripDTO();
        entities.setAll(appService.showTrips());
    }

    public void searchByDestinationAndDate() {
        String destination = searchByDestination.getText();
        LocalDate date = searchByDate.getValue();
        LocalTime time = searchByTime.getValue();
        var tripID = appService.getTripIDByDestinationAndDeparture(destination, Timestamp.valueOf(LocalDateTime.of(date, time)));
        if (tripID != null) {
            var result = appService.search(destination, Timestamp.valueOf(LocalDateTime.of(date, time)));
            StackPane parent;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TripDetailsView.fxml"));
                parent = loader.load();
                TripDetailsController controller = loader.getController();
                controller.setService(appService, tripID, user, result);
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
