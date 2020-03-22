package controller;

import domain.BookedTripDTO;
import domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.AppService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

public class TripDetailsController implements Initializable {
    public TableView<BookedTripDTO> bookedTripDTOTable;
    public TableColumn<BookedTripDTO, String> tableName;
    public TableColumn<BookedTripDTO, Integer> tableSeatNumber;
    private AppService appService;
    private User user;
    private ObservableList<BookedTripDTO> entities;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        tableSeatNumber.setCellValueFactory(new PropertyValueFactory<>("seatNumber"));
    }

    public void setService(AppService appService, User user, List<BookedTripDTO> result) {
        this.appService = appService;
        this.user = user;
        postInitialization(result);
    }


    private void postInitialization(List<BookedTripDTO> result) {
        List<BookedTripDTO> temporary = new Vector<>(18);
        for(int i = 0; i < 18; i++)
            temporary.add(new BookedTripDTO(-1, "-", i + 1));
        for(var a : result)
            temporary.set(a.getSeatNumber(), a);
        entities = FXCollections.observableList(temporary);
        bookedTripDTOTable.setItems(entities);
    }
}
