package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import domain.BookedTripDTO;
import domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import service.AppService;
import service.AppServiceException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

public class TripDetailsController implements Initializable {
    public TableView<BookedTripDTO> bookedTripDTOTable;
    public TableColumn<BookedTripDTO, String> tableName;
    public TableColumn<BookedTripDTO, Integer> tableSeatNumber;
    public JFXButton addButton;
    public JFXTextField addName;
    public Spinner<Integer> addSeatNumber;
    private AppService appService;
    private User user;
    private int tripID;
    private ObservableList<BookedTripDTO> entities;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        tableSeatNumber.setCellValueFactory(new PropertyValueFactory<>("seatNumber"));
        addSeatNumber.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 18, 1));
        addSeatNumber.getEditor().setAlignment(Pos.CENTER);
        addSeatNumber.getEditor().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER)
                try {
                    Integer.parseInt(addSeatNumber.getEditor().textProperty().get());
                } catch (NumberFormatException e) {
                    addSeatNumber.getEditor().textProperty().set("1");
                }
        });
    }

    public void setService(AppService appService,Integer tripID,  User user, List<BookedTripDTO> result) {
        this.appService = appService;
        this.user = user;
        this.tripID = tripID;
        postInitialization(result);
    }


    private void postInitialization(List<BookedTripDTO> result) {
        List<BookedTripDTO> temporary = new Vector<>(18);
        for(int i = 0; i < 18; i++)
            temporary.add(new BookedTripDTO(-1, "-", i + 1));
        for(var a : result)
            temporary.set(a.getSeatNumber() - 1, a);
        entities = FXCollections.observableList(temporary);
        bookedTripDTOTable.setItems(entities);
    }

    public void bookTrip() {
        int seatNumber = addSeatNumber.getValue();
        String name = addName.getText();
        try {
            appService.reserve(tripID, name, seatNumber);
        } catch (AppServiceException e) {
            e.printStackTrace();
        }
    }
}
