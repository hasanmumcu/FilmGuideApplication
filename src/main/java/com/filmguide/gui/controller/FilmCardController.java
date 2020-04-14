package com.filmguide.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

public class FilmCardController implements Initializable{

    @FXML private Polygon polygon;
    @FXML private AnchorPane infoPane;
    @FXML private Text voteAverage;
    @FXML private Text imdbText;

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
    }
    
    @FXML
    public void onMouseEnter(){
        System.out.println("enter");
        this.infoPane.setVisible(true);
        this.polygon.setLayoutY(-130);
        this.imdbText.setLayoutY(188);
        this.voteAverage.setLayoutY(188);
    }

    @FXML
    public void onMouseExit(){
        this.infoPane.setVisible(false);
        this.polygon.setLayoutY(60);
        this.imdbText.setLayoutY(382);
        this.voteAverage.setLayoutY(382);
        System.out.println("exit");
    }

}