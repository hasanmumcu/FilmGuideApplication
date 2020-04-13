package com.filmguide;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        String fxmlFile = "/fxml/login.fxml";
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
