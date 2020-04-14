package com.filmguide.model;

import java.io.Serializable;

public class AuthenticationToken implements Serializable{

    private static final long serialVersionUID = 1231765093548L;
    
    private String token;


    public AuthenticationToken() {
    }

    public AuthenticationToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthenticationToken token(String token) {
        this.token = token;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " token='" + getToken() + "'" +
            "}";
    }

    
}