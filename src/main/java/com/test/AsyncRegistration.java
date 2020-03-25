package com.test;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javafx.concurrent.Task;

public class AsyncRegistration extends Task<List<Object>>{

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    private String username;
    private String email;
    private String password;

    public AsyncRegistration(String username, String email, String password){
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
    }

    @Override
    protected List<Object> call() throws Exception {
        
        List<Object> returnValues = new ArrayList<Object>();

        HttpPost request = new HttpPost(MainApp.baseURL + "/auth/register");
        request.addHeader("Content-Type", "application/json");
        
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("username", this.getUsername());
        requestBody.addProperty("email", this.getEmail());
        requestBody.addProperty("password", this.getPassword());
        
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

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}