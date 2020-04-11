package com.filmguide.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXHamburger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class SidePanelController implements Initializable {


    @FXML
    private VBox sidePanelVbox;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXHamburger hamburgerButton;


    public void initialize(URL location, ResourceBundle resources) {

    }

    public JFXHamburger getHamburgerButton(){
        return this.hamburgerButton;
    }
}