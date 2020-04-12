package com.filmguide.gui.controller;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;

import javax.swing.SwingUtilities;

import com.filmguide.model.user.Registration;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import com.filmguide.MainApp;
import com.sun.webkit.WebPage;
import netscape.javascript.JSObject;

public class RegisterController implements Initializable {

    @FXML private JFXTextField registerSceneUsernameTextField;
    @FXML private JFXTextField registerSceneEmailTextField;
    @FXML private JFXPasswordField registerScenePasswordField;
    @FXML private JFXButton registerSceneRegisterButton;
    @FXML private Label registerStatusLabel;
    @FXML private StackPane parentContainer;
    @FXML private VBox mainVbox;
    @FXML private WebView webView;
    private JavaScriptBridge bridge;

    public void initialize(URL location, ResourceBundle resources) {
        
        this.bridge = new JavaScriptBridge(this.webView);
        final WebEngine webEngine = webView.getEngine();

        webEngine.setUserAgent("use required / intended UA string");
        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            
            public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {
                if (newState == Worker.State.SUCCEEDED) {
                    JSObject window = (JSObject) webEngine.executeScript("window");
                    window.setMember("javaApp", bridge);
                    webEngine.executeScript("initListener();");
                }
            }
        });
        
        webEngine.load(MainApp.baseURL + "/recaptcha.html");

        try {
            
            Field field = webEngine.getClass().getDeclaredField("page");
            field.setAccessible(true);
            final WebPage page = (WebPage) field.get(webEngine);
            SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					page.setBackgroundColor(new java.awt.Color(255, 255, 255, 0).getRGB());	
				}
            });
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @FXML
    public void register() {

        String token = null;

        if (registerSceneRegisterButton.isDisabled()) {
            return;
        }

        try{
            token = webView.getEngine().executeScript("grecaptcha.getResponse()").toString();
        }
        catch(Exception ex){   
            System.out.println(ex.getMessage());
        }

        String username = registerSceneUsernameTextField.getText();
        String email = registerSceneEmailTextField.getText();
        String password = registerScenePasswordField.getText();

        if(!MainApp.usernamePattern.matcher(username).matches()){
            registerStatusLabel.setText("Invalid username!");
            registerStatusLabel.setTextFill(Paint.valueOf("#DD2B2B"));
            registerStatusLabel.setVisible(true);
            registerSceneUsernameTextField.setText("");
            return;
        }

        if(!MainApp.passwordPattern.matcher(password).matches()){
            registerStatusLabel.setText("Invalid password! Minimum six characters, at least one letter and one number.");
            registerStatusLabel.setTextFill(Paint.valueOf("#DD2B2B"));
            registerStatusLabel.setVisible(true);
            registerScenePasswordField.setText("");
            return;
        }

        if(!MainApp.emailPattern.matcher(email).matches()){
            registerStatusLabel.setText("Invalid email!");
            registerStatusLabel.setTextFill(Paint.valueOf("#DD2B2B"));
            registerStatusLabel.setVisible(true);
            registerSceneEmailTextField.setText("");
            return;
        }

        if(token == null || token.equals("")){
            registerStatusLabel.setText("Are you human? I am not sure.");
            registerStatusLabel.setTextFill(Paint.valueOf("#DD2B2B"));
            registerStatusLabel.setVisible(true);
            return;
        }

        final AsyncRegistration asyncRegistration = new AsyncRegistration(new Registration(username, password, email, token));
        // registerStatusLabel.textProperty().bind(asyncRegistration.messageProperty());

        asyncRegistration.setOnRunning(new EventHandler<WorkerStateEvent>() {

            public void handle(WorkerStateEvent event) {
                registerSceneRegisterButton.setDisable(true);
                if (registerSceneRegisterButton.getScene() != null)
                    registerSceneRegisterButton.getScene().setCursor(Cursor.WAIT);
            }
        });

        asyncRegistration.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            public void handle(WorkerStateEvent event) {
                if ((Boolean) asyncRegistration.getValue().get(0) == true) {
                    registerStatusLabel.setText(asyncRegistration.getValue().get(1).toString());
                    registerStatusLabel.setTextFill(Paint.valueOf("#25BF1A"));
                    registerStatusLabel.setVisible(true);
                    registerSceneUsernameTextField.setText("");
                    registerScenePasswordField.setText("");
                    registerSceneEmailTextField.setText("");
                    webView.getEngine().reload();
                } else {
                    registerStatusLabel.setText(asyncRegistration.getValue().get(1).toString());
                    registerStatusLabel.setTextFill(Paint.valueOf("#DD2B2B"));
                    registerStatusLabel.setVisible(true);
                    registerSceneUsernameTextField.setText("");
                    registerScenePasswordField.setText("");
                    registerSceneEmailTextField.setText("");
                    webView.getEngine().reload();
                }
                registerSceneRegisterButton.setDisable(false);
                if (registerSceneRegisterButton.getScene() != null)
                    registerSceneRegisterButton.getScene().setCursor(Cursor.DEFAULT);
            }
        });

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(asyncRegistration);
        executorService.shutdown();

    }

    @FXML
    public void goToLogin() {

        webView.getEngine().reload();

        if (registerSceneRegisterButton.getScene() != null)
            registerSceneRegisterButton.getScene().setCursor(Cursor.DEFAULT);

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));

            root.translateYProperty().set(MainApp.windowHeight * -1);
            parentContainer.getChildren().add(root);

            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(150), kv);
            timeline.setOnFinished(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent arg0) {
                    parentContainer.getChildren().remove(mainVbox);
                }
            });
            timeline.getKeyFrames().add(kf);
            timeline.play();
        } catch (Exception ex) {
            System.out.println("Excetion message: " + ex.getMessage());
        }
    }

    public class JavaScriptBridge{

        private WebView webView;

        public JavaScriptBridge(WebView webView){
            this.webView = webView;
        }

        public void setStatus(String status){
            if(status.equals("visible")){
                webView.setLayoutX(160);
                webView.setLayoutY(108);
                webView.setPrefWidth(473);
                webView.setPrefHeight(547);
            }
            else if(status.equals("hidden")){
                webView.setLayoutX(160);
                webView.setLayoutY(568);
                webView.setPrefWidth(473);
                webView.setPrefHeight(87);
            }
        }
    }
}
