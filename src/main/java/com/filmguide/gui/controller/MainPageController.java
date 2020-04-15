package com.filmguide.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.events.JFXDrawerEvent;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainPageController implements Initializable {

    @FXML private VBox mainVbox;
    @FXML private StackPane parentContainer;
    @FXML private JFXDrawer sidePanel;
    @FXML private AnchorPane film1;

    private boolean sidePanelOpen = false;

    public void initialize(URL location, ResourceBundle resources) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(getClass().getResource("/fxml/sidePanelContent.fxml"));

            VBox sidePanelBox = loader.load();
            final SidePanelController sidePanelController = loader.getController();
            sidePanel.setSidePane(sidePanelBox);
            final HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(
                    sidePanelController.getHamburgerButton());
            burgerTask.setRate(-1);
            sidePanelController.getHamburgerButton().addEventHandler(MouseEvent.MOUSE_CLICKED,
                    new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent event) {
                            sidePanel.toggle();
                            if(!sidePanelOpen) sidePanel.toFront();
                            sidePanelOpen = !sidePanelOpen;
                        }
                    });
            sidePanel.setOnDrawerOpening(new EventHandler<JFXDrawerEvent>() {

                public void handle(JFXDrawerEvent event) {
            
                    burgerTask.setRate(burgerTask.getRate() * -1);
                    burgerTask.play();
                }

            });
            sidePanel.setOnDrawerClosing(new EventHandler<JFXDrawerEvent>() {

                public void handle(JFXDrawerEvent event) {
                    
                    burgerTask.setRate(burgerTask.getRate() * -1);
                    burgerTask.setOnFinished(new EventHandler<ActionEvent>() {

                        public void handle(ActionEvent event) {
                            if(!sidePanelOpen) film1.toFront();
                        }
                        
                    });
                    burgerTask.play();
                }
            });

            
            loader = new FXMLLoader();
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(getClass().getResource("/fxml/filmCard.fxml"));
            film1.getChildren().add((Node) loader.load());
    
        }catch(IOException ex){
            System.out.println("Exception: " + ex.getMessage());
        }   
    }
    
}