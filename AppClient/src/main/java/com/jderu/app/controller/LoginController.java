package com.jderu.app.controller;

import com.jfoenix.controls.*;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.jderu.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.jderu.services.AppServiceException;
import com.jderu.services.IAppObserver;
import com.jderu.services.IAppServices;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class LoginController extends UnicastRemoteObject implements Initializable, IAppObserver, Serializable {

    public JFXTextField logInUsername;
    public JFXPasswordField logInPassword;
    public JFXButton logInButton;
    public StackPane rootPane;
    public HBox menuTable;
    private IAppServices loginService;
    private Stage primaryStage;
    private AppController appController;

    public LoginController() throws RemoteException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setService(IAppServices loginService) {
        this.loginService = loginService;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
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

    public void login(ActionEvent actionEvent) {
        String username = logInUsername.getText();
        String rawPassword = logInPassword.getText();

        User user = null;
        try {
            user = loginService.login(username, rawPassword, this);
        } catch (AppServiceException e) {
            showError("Login error", e.getMessage());
        }
        if (user == null)
            failedLogin();
        else
            successfulLogin(user);
    }

    private void failedLogin() {
        logInUsername.getStyleClass().add("wrong-credentials");
        logInPassword.getStyleClass().add("wrong-credentials");
    }

    private void successfulLogin(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AppView.fxml"));
            StackPane rootLayout = loader.load();
            appController = loader.getController();

            appController.setService(loginService, user);

            primaryStage.setMinWidth(800);
            primaryStage.setMinHeight(500);
            primaryStage.setScene(new Scene(rootLayout));
            primaryStage.setOnCloseRequest(event -> {
                appController.logout();
                System.exit(0);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {

    }

    @Override
    public void updateWindows(String destinationName, Timestamp departure, int seatNumber, String clientName) {
        appController.updateWindows(destinationName, departure, seatNumber, clientName);
    }
}
