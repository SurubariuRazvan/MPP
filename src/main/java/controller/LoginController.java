package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.AppService;
import service.LoginService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public JFXTextField logInUsername;
    public JFXPasswordField logInPassword;
    public JFXButton logInButton;
    private LoginService loginService;
    private Stage primaryStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setService(LoginService loginService) {
        this.loginService = loginService;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void login(ActionEvent actionEvent) {
//        userRepo.save(new User("student", User.encodePassword("student"), CleranceLevel.Student, 2));
//        userRepo.save(new User("professor", User.encodePassword("professor"), CleranceLevel.Professor, 3));
//        userRepo.save(new User("admin", User.encodePassword("admin"), CleranceLevel.Admin, 1));
        String username = logInUsername.getText();
        String rawPassword = logInPassword.getText();

        User user = loginService.login(username, rawPassword);
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
            AppController appController = loader.getController();

            ApplicationContext context = new ClassPathXmlApplicationContext("App.xml");
            AppService appService = context.getBean(AppService.class);
            appController.setService(appService, user);

            primaryStage.setMinWidth(800);
            primaryStage.setMinHeight(500);
            primaryStage.setScene(new Scene(rootLayout));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
    }
}
