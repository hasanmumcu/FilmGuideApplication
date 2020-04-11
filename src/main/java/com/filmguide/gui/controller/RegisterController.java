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
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.filmguide.model.user.Registration;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import com.filmguide.MainApp;

public class RegisterController{

    @FXML private JFXTextField registerSceneUsernameTextField;
    @FXML private JFXTextField registerSceneEmailTextField;
    @FXML private JFXPasswordField registerScenePasswordField;
    @FXML private JFXButton registerSceneRegisterButton;
    @FXML private Label registerStatusLabel;
    @FXML private StackPane parentContainer;
    @FXML private VBox mainVbox;

    @FXML
    public void register(){

        if(registerSceneRegisterButton.isDisabled()){
            return;
        }

        String username = registerSceneUsernameTextField.getText();
        String email = registerSceneEmailTextField.getText();
        String password = registerScenePasswordField.getText();
        

        final AsyncRegistration asyncRegistration = new AsyncRegistration(new Registration(username, password, email));
        //registerStatusLabel.textProperty().bind(asyncRegistration.messageProperty());

        asyncRegistration.setOnRunning(new EventHandler<WorkerStateEvent>() {

            public void handle(WorkerStateEvent event) {
                registerSceneRegisterButton.setDisable(true);
                if(registerSceneRegisterButton.getScene() != null)
                    registerSceneRegisterButton.getScene().setCursor(Cursor.WAIT);
            }            
        });

        asyncRegistration.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            public void handle(WorkerStateEvent event) {
                if((Boolean)asyncRegistration.getValue().get(0) == true){
                    registerStatusLabel.setText(asyncRegistration.getValue().get(1).toString());
                    registerStatusLabel.setTextFill(Paint.valueOf("#25BF1A"));
                    registerStatusLabel.setVisible(true);
                }
                else{
                    registerStatusLabel.setText(asyncRegistration.getValue().get(1).toString());
                    registerStatusLabel.setTextFill(Paint.valueOf("#DD2B2B"));
                    registerStatusLabel.setVisible(true);
                }
                registerSceneRegisterButton.setDisable(false);
                if(registerSceneRegisterButton.getScene() != null)
                    registerSceneRegisterButton.getScene().setCursor(Cursor.DEFAULT);
            }
        });

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(asyncRegistration);
        executorService.shutdown();

    }

    @FXML
    public void goToLogin(){
        
        if(registerSceneRegisterButton.getScene() != null)
            registerSceneRegisterButton.getScene().setCursor(Cursor.DEFAULT);

        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
         
            root.translateYProperty().set(MainApp.windowHeight * -1);
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
        }
        catch(Exception ex){
        
            System.out.println("Excetion message: " + ex.getMessage());
        }
 

        /*Parent loginParent = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene);*/
    }
}