package com.filmguide.gui.controller;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.filmguide.Config;
import com.filmguide.gui.asynctask.AsyncLogin;
import com.filmguide.model.user.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;


public class LoginController implements Initializable {

    @FXML
    private JFXTextField loginSceneUsernameTextField;
    @FXML
    private JFXPasswordField loginScenePasswordField;
    @FXML
    private JFXButton loginSceneLoginButton;
    @FXML
    private StackPane parentContainer;
    @FXML
    private Label loginStatusLabel;
    @FXML
    private VBox mainVbox;
  
  
    public void initialize(URL location, ResourceBundle resources) {
        
        Parent root = this.loginSceneLoginButton.getParent();

        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    login();
                }
                else if(event.getCode() == KeyCode.ESCAPE) {
                    System.exit(0);
                }
            }
        });
    }
 

    @FXML
    public void login() {

        if (loginSceneLoginButton.isDisabled()) {
            return;
        }

        String username = loginSceneUsernameTextField.getText();
        String password = loginScenePasswordField.getText();

        if (!Config.usernamePattern.matcher(username).matches()
                || !Config.passwordPattern.matcher(password).matches()) {
            loginStatusLabel.setText("Check your username or password!");
            loginStatusLabel.setTextFill(Config.ErrorTextColor);
            loginStatusLabel.setVisible(true);
            loginScenePasswordField.setText("");
            loginSceneUsernameTextField.setText("");
            return;
        }

        final AsyncLogin asyncLogin = new AsyncLogin(new User(username, password));

        asyncLogin.setOnRunning(new EventHandler<WorkerStateEvent>() {

            public void handle(WorkerStateEvent event) {
                loginSceneLoginButton.setDisable(true);
                if (loginSceneLoginButton.getScene() != null) {
                    loginSceneLoginButton.getScene().setCursor(Cursor.WAIT);
                }
            }
        });

        asyncLogin.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            public void handle(WorkerStateEvent event) {
                if ((Boolean) asyncLogin.getValue().get(0) == true) {
                    loginStatusLabel.setText(asyncLogin.getValue().get(1).toString());
                    loginStatusLabel.setTextFill(Config.SuccessTextColor);
                    loginStatusLabel.setVisible(true);

                    try {
                        goToMainPage();
                    } catch (IOException ex) {
                        Config.log.info(ex.getMessage());
                    }
                } else {
                    loginStatusLabel.setText(asyncLogin.getValue().get(1).toString());
                    loginStatusLabel.setTextFill(Config.ErrorTextColor);
                    loginStatusLabel.setVisible(true);
                    loginScenePasswordField.setText("");
                    loginSceneUsernameTextField.setText("");
                }
                loginSceneLoginButton.setDisable(false);
                if (loginSceneLoginButton.getScene() != null)
                    loginSceneLoginButton.getScene().setCursor(Cursor.DEFAULT);
            }
        });

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(asyncLogin);
        executorService.shutdown();

    }

    @FXML
    public void goToRegister(ActionEvent event) throws Exception {

        Scene scene = loginSceneLoginButton.getScene();

        FXMLLoader loader = new FXMLLoader();
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(getClass().getResource("/fxml/register.fxml"));

        Parent root = loader.load();

        root.translateYProperty().set(scene.getHeight());
        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.millis(150), kv);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                parentContainer.getChildren().remove(mainVbox);
            }
        });
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    public void goToMainPage() throws IOException {

        Scene scene = loginSceneLoginButton.getScene();

        FXMLLoader loader = new FXMLLoader();
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(getClass().getResource("/fxml/mainPage.fxml"));

        Parent root = loader.load();

        root.translateXProperty().set(scene.getWidth());
        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.millis(150), kv);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                parentContainer.getChildren().remove(mainVbox);
            }
        });
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

  

}