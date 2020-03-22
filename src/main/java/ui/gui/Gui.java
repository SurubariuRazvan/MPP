package ui.gui;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.LoginService;

public class Gui extends Application {
    public static void run(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Application Login");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/LoginView.fxml"));
        HBox rootLayout = loader.load();
        LoginController loginController = loader.getController();

        ApplicationContext context = new ClassPathXmlApplicationContext("App.xml");
        LoginService loginService = context.getBean(LoginService.class);
        loginController.setService(loginService);
        loginController.setPrimaryStage(primaryStage);

        primaryStage.setOnCloseRequest(we -> loginController.close());
        primaryStage.setMinWidth(650);
        primaryStage.setMinHeight(450);

        primaryStage.setScene(new Scene(rootLayout));
        primaryStage.show();
    }
}
