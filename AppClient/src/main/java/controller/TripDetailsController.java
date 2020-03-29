package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.events.JFXDialogEvent;
import domain.BookedTripDTO;
import domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
    public StackPane rootPane;
    public VBox menuTable;
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

    public void setService(AppService appService, Integer tripID, User user, List<BookedTripDTO> result) {
        this.appService = appService;
        this.user = user;
        this.tripID = tripID;
        postInitialization(result);
    }


    private List<BookedTripDTO> createList(List<BookedTripDTO> result) {
        List<BookedTripDTO> temporary = new Vector<>(18);
        for(int i = 0; i < 18; i++)
            temporary.add(new BookedTripDTO(-1, "-", i + 1));
        for(var a : result)
            temporary.set(a.getSeatNumber() - 1, a);
        return temporary;
    }

    private void postInitialization(List<BookedTripDTO> result) {
        entities = FXCollections.observableList(createList(result));
        bookedTripDTOTable.setItems(entities);
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

    public void bookTrip() {
        int seatNumber = addSeatNumber.getValue();
        String name = addName.getText();
        try {
            appService.reserve(tripID, name, seatNumber);
            entities.set(seatNumber - 1, new BookedTripDTO(appService.findClientID(tripID, seatNumber).getClientID(), name, seatNumber));
        } catch (AppServiceException e) {
            showError("Reserving error", e.getMessage());
        }
    }
}
