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

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RegisterController{

    @FXML private JFXTextField registerSceneUsernameTextField;
    @FXML private JFXTextField registerSceneEmailTextField;
    @FXML private JFXPasswordField registerScenePasswordField;
    @FXML private JFXButton registerSceneRegisterButton;
    @FXML private StackPane parentContainer;
    @FXML private VBox mainVbox;

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    @FXML
    public void register(){

        registerSceneRegisterButton.setDisable(true);

        HttpPost request = new HttpPost(MainApp.baseURL + "/auth/register");
        request.addHeader("Content-Type", "application/json");
        
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("username", registerSceneUsernameTextField.getText());
        requestBody.addProperty("email", registerSceneEmailTextField.getText());
        requestBody.addProperty("password", registerScenePasswordField.getText());
        
        try{
            request.setEntity(new StringEntity(requestBody.toString()));
            HttpResponse response = httpClient.execute(request);
            JsonObject responseBody = JsonParser.parseString(EntityUtils.toString(response.getEntity(), "UTF-8")).getAsJsonObject();
            
            if(responseBody.get("success") != null && responseBody.get("success").getAsBoolean() == true){
                System.out.println(responseBody.get("message").getAsString());   
            }
            else{
                System.out.println(responseBody);
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        registerSceneRegisterButton.setDisable(false);
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