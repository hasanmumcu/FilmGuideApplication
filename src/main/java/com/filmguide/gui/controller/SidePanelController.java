package com.filmguide.gui.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.filmguide.Config;
import com.jfoenix.controls.JFXHamburger;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class SidePanelController implements Initializable {

    @FXML private VBox sidePanelVbox;
    @FXML private AnchorPane anchorPane;
    @FXML private JFXHamburger hamburgerButton;
 

    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void quit() {
        System.exit(0);
    }

    @FXML
    public void logout() {
        File file = new File("storage/authtoken.ser");
        file.delete();

        FXMLLoader loader = new FXMLLoader();
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(getClass().getResource("/fxml/login.fxml"));

        Parent root;
        try {
            root = loader.load();
            root.translateXProperty().set(-Config.windowWidth);
            final StackPane parentContainer = (StackPane)sidePanelVbox.getParent().getParent().getParent().getParent().getParent();
            final VBox mainVBox = (VBox)sidePanelVbox.getParent().getParent().getParent().getParent();
            parentContainer.getChildren().add(root);
            
    
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(150), kv);
            timeline.setOnFinished(new EventHandler<ActionEvent>() {
    
                public void handle(ActionEvent event) {
                   parentContainer.getChildren().remove(mainVBox);
                }
            });
            timeline.getKeyFrames().add(kf);
            timeline.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JFXHamburger getHamburgerButton(){
        return this.hamburgerButton;
    }


}