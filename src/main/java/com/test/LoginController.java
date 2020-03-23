package com.test;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    public void test(){
        System.out.println(loginSceneUsernameTextField.getText() + " " + loginScenePasswordField.getText());
    }

    @FXML
    public void goToRegister(ActionEvent event) throws Exception {               
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
        Scene scene = loginSceneLoginButton.getScene();

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
        /*Parent registerParent = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
        Scene registerScene = new Scene(registerParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(registerScene);
        window.show();*/
    }

}