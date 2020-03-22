package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import domain.TripDTO;
import domain.User;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    public TableView<TripDTO> tripDTOTable;
    public TableColumn<TripDTO, String> tableDestination;
    public TableColumn<TripDTO, Timestamp> tableDeparture;
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
        tableDeparture.setCellValueFactory(new PropertyValueFactory<>("departure"));
        tableFreeSeats.setCellValueFactory(new PropertyValueFactory<>("freeSeats"));
        searchByTime.setEditable(false);
        searchByTime.set24HourView(true);
        searchByTime.setConverter(new LocalTimeStringConverter(FormatStyle.SHORT, Locale.GERMANY));
        entities = FXCollections.emptyObservableList();
    }

    public void setService(AppService appService, User user) {
        this.appService = appService;
        this.user = user;
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
        var result = appService.search(destination, Timestamp.valueOf(LocalDateTime.of(date, time)));
        if (result.size() > 0) {
            StackPane parent;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TripDetailsView.fxml"));
                parent = loader.load();
                TripDetailsController controller = loader.getController();
                controller.setService(appService, user, result);
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
