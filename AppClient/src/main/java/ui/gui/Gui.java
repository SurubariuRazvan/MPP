package ui.gui;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import network.rpcprotocol.AppProxyService;
import services.IAppServices;

public class Gui extends Application {
    public static void run(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Application Login");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/LoginView.fxml"));
        StackPane rootLayout = loader.load();
        LoginController loginController = loader.getController();

        String serverIP = "localhost";
        int serverPort = 55555;

        IAppServices server = new AppProxyService(serverIP, serverPort, loginController);
        loginController.setService(server);
        loginController.setPrimaryStage(primaryStage);

        primaryStage.setOnCloseRequest(we -> loginController.close());
        primaryStage.setMinWidth(650);
        primaryStage.setMinHeight(450);

        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.show();
    }
}
