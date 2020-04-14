package com.filmguide;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.filmguide.model.AuthenticationToken;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {



    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        Config.log.info("Starting Filmguide Application");

        File storage = new File("storage");
        storage.mkdir();

        String fxmlFile = "/fxml/login.fxml";
        
        //Deserialization
        AuthenticationToken token = null;
        
        try{
            FileInputStream file = new FileInputStream("storage/authtoken.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            token = (AuthenticationToken)in.readObject();
            
            in.close();
            file.close();

            System.out.println("Token object has been deserialized");
        }
        catch(IOException ex){
            System.out.println("Authentication token file is not found in the storage.");
        }
        catch(ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        catch(ClassCastException ex){
            System.out.println(ex.getMessage());
        }

        if(token != null){
            System.out.println("Authentication token is being validated");
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost request = new HttpPost(Config.baseURL + "/me");
            request.addHeader("Authorization", token.getToken());
            try{
                HttpResponse response = httpClient.execute(request);
                if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                    JsonObject responseBody = JsonParser.parseString(EntityUtils.toString(response.getEntity(), "UTF-8")).getAsJsonObject();
                    System.out.println("Welcome " + responseBody.get("username").getAsString());
                    fxmlFile = "/fxml/mainPage.fxml";
                }                
            }
            catch(ClientProtocolException ex){
                System.out.println(ex.getMessage());
            } 
            catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }

        Config.log.debug("Loading FXML for main view from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        Config.log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode, Config.windowWidth, Config.windowHeight);
        scene.getStylesheets().add("/styles/styles.css");
        
        stage.setTitle("Film Guide");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
