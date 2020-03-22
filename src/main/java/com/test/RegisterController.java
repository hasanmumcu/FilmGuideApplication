package com.test;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

public class RegisterController{

    @FXML private JFXTextField registerSceneUsernameTextField;
    @FXML private JFXTextField registerSceneEmailTextField;
    @FXML private JFXPasswordField registerScenePasswordField;
    @FXML private JFXButton registerSceneRegisterButton;
    @FXML private StackPane parentContainer;
    @FXML private VBox mainVbox;

    @FXML
    public void register(){
        System.out.println(registerSceneUsernameTextField.getText());
        System.out.println(registerSceneEmailTextField.getText());
        System.out.println(registerScenePasswordField.getText());
    }

    @FXML
    public void goToLogin(ActionEvent event) throws Exception{
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Scene scene = registerSceneEmailTextField.getScene();

        root.translateYProperty().set(-scene.getHeight());
        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.millis(150), kv);
        timeline.setOnFinished(new EventHandler<ActionEvent>(){
        
            public void handle(ActionEvent arg0) {
                parentContainer.getChildren().remove(mainVbox);
            }
        });
        timeline.getKeyFrames().add(kf);
        timeline.play();

        /*Parent loginParent = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene);*/
    }
}