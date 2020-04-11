package com.filmguide.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.events.JFXDrawerEvent;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class MainPageController implements Initializable{

    @FXML private VBox mainVbox;

    @FXML private JFXDrawer sidePanel;

	public void initialize(URL location, ResourceBundle resources) {
        
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(getClass().getResource("/fxml/sidePanelContent.fxml"));

            VBox sidePanelBox = loader.load();
            final SidePanelController sidePanelController = loader.getController();
            sidePanel.setSidePane(sidePanelBox);
            final HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(sidePanelController.getHamburgerButton());
            burgerTask.setRate(-1);
            sidePanelController.getHamburgerButton().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    sidePanel.toggle();
                }
            });
            sidePanel.setOnDrawerOpening(new EventHandler<JFXDrawerEvent>() {

                public void handle(JFXDrawerEvent event) {
                    
                    burgerTask.setRate(burgerTask.getRate() * -1);
                    burgerTask.play();
                }
            
            });
            sidePanel.setOnDrawerClosing(new EventHandler<JFXDrawerEvent>() {

                public void handle(JFXDrawerEvent event){

                    burgerTask.setRate(burgerTask.getRate() * -1);
                    burgerTask.play();
                }
            });
        }catch(IOException ex){
            System.out.println("Exception: " + ex.getMessage());
        }   
    }
    
}