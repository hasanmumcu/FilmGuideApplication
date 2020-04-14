package com.filmguide;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.paint.Paint;

public class Config {

    public static final String baseURL = "http://localhost:8080";
    public static final int windowWidth = 1600;
    public static final int windowHeight = 900;
    public static final Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9]{5,15}$");
    public static final Pattern passwordPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$");
    public static final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    public static final Logger log = LoggerFactory.getLogger(MainApp.class);
    public static final Paint ErrorTextColor = Paint.valueOf("#DD2B2B");
    public static final Paint SuccessTextColor = Paint.valueOf("#25BF1A");


}