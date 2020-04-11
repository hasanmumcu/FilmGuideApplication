package com.filmguide;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static final String baseURL = "http://localhost:8080";
    
    public static final int windowWidth = 1600;
    public static final int windowHeight = 900;

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {


        log.info("Starting Hello JavaFX and Maven demonstration application");

        String fxmlFile = "/fxml/login.fxml";
        log.debug("Loading FXML for main view from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode, windowWidth, windowHeight);
        scene.getStylesheets().add("/styles/styles.css");
        
        stage.setTitle("Film Guide");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
