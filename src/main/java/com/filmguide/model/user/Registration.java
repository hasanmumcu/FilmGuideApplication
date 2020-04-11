package com.filmguide.model.user;

public class Registration extends User{

    private String email;

    public Registration(){
    }

    public Registration(String username, String password, String email){
        super(username, password);
        this.email = email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    @Override
    public String toString() {
        return "{" +
            " username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", email='" + email + "'" +
            "}";
    }

}