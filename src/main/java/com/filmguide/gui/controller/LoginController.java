package com.filmguide.gui.controller;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

public class LoginController{

    @FXML private JFXTextField loginSceneUsernameTextField;
    @FXML private JFXPasswordField loginScenePasswordField;
    @FXML private JFXButton loginSceneLoginButton;
    @FXML private StackPane parentContainer;
    @FXML private VBox mainVbox;

    @FXML
    public void goToRegister(ActionEvent event) throws Exception {               
        
        Scene scene = loginSceneLoginButton.getScene();

        FXMLLoader loader = new FXMLLoader();
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(getClass().getResource("/fxml/register.fxml"));

        Parent root = loader.load();
        final RegisterController registerController = loader.getController();

        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    registerController.register();
                }
                else if(event.getCode() == KeyCode.ESCAPE){
                    registerController.goToLogin();
                }
            }
        });

        root.translateYProperty().set(scene.getHeight());
        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.millis(150), kv);
        timeline.setOnFinished(new EventHandler<ActionEvent>(){

            public void handle(ActionEvent event) {
               parentContainer.getChildren().remove(mainVbox);
            }       
        });
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

}