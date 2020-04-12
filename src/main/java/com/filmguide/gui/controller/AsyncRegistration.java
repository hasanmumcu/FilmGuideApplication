package com.filmguide.gui.controller;

import java.util.ArrayList;
import java.util.List;

import com.filmguide.model.user.Registration;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javafx.concurrent.Task;

import com.filmguide.MainApp;

public class AsyncRegistration extends Task<List<Object>>{

    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private Registration registration;

    public AsyncRegistration(Registration registration){
        this.registration = registration;
    }

    @Override
    protected List<Object> call() throws Exception {
        
        List<Object> returnValues = new ArrayList<Object>();

        HttpPost request = new HttpPost(MainApp.baseURL + "/auth/register");
        request.addHeader("Content-Type", "application/json");
        
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("username", this.registration.getUsername());
        requestBody.addProperty("email", this.registration.getEmail());
        requestBody.addProperty("password", this.registration.getPassword());
        requestBody.addProperty("recaptchaToken", this.registration.getToken());
        
        try{
            request.setEntity(new StringEntity(requestBody.toString()));
            HttpResponse response = httpClient.execute(request);
            JsonObject responseBody = JsonParser.parseString(EntityUtils.toString(response.getEntity(), "UTF-8")).getAsJsonObject();
            
            if(responseBody.get("success") != null && responseBody.get("success").getAsBoolean() == true){
                
                returnValues.add(true);
                returnValues.add(responseBody.get("message").getAsString());
                return returnValues;
            }
            else if(responseBody.get("success") != null && responseBody.get("success").getAsBoolean() == false){
                
                returnValues.add(false);
                returnValues.add(responseBody.get("message").getAsString());
                return returnValues;
            }
            else{
                returnValues.add(false);
                returnValues.add("Unexpected error occured!");
                return returnValues;
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            returnValues.add(false);
            returnValues.add("Connection could not be established!");
            return returnValues;
        }
        finally{
            httpClient.close();
        }
    }
}