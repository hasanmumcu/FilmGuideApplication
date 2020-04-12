package com.filmguide.model.user;

public class Registration extends User{

    private String email;
    private String token;


    public Registration() {
    }

    public Registration(String username, String password, String email, String token) {
        super(username, password);
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Registration email(String email) {
        this.email = email;
        return this;
    }

    public Registration token(String token) {
        this.token = token;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " email='" + getEmail() + "'" +
            ", token='" + getToken() + "'" +
            "}";
    }
}