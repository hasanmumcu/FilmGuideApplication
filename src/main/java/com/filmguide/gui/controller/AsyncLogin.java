package com.filmguide.gui.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.filmguide.Config;
import com.filmguide.model.AuthenticationToken;
import com.filmguide.model.user.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javafx.concurrent.Task;

public class AsyncLogin extends Task<List<Object>> {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private User user;

    public AsyncLogin(User user){
        this.user = user;
    }

    @Override
    protected List<Object> call() throws Exception {
        
        List<Object> returnValues = new ArrayList<Object>();

        HttpPost request = new HttpPost(Config.baseURL + "/auth/signin");
        request.addHeader("Content-Type", "application/json");

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("username", this.user.getUsername());
        requestBody.addProperty("password", this.user.getPassword());

        try{
            request.setEntity(new StringEntity(requestBody.toString()));
            HttpResponse response = httpClient.execute(request);
            JsonObject responseBody = JsonParser.parseString(EntityUtils.toString(response.getEntity(), "UTF-8")).getAsJsonObject();
            if(responseBody.get("token") != null){

                AuthenticationToken token = new AuthenticationToken("Bearer " + responseBody.get("token").getAsString());
                try{
                    FileOutputStream file = new FileOutputStream("storage/authtoken.ser");
                    ObjectOutputStream out = new ObjectOutputStream(file);

                    out.writeObject(token);
                    out.close();
                    file.close();

                    Config.log.info("Token object has been serialized.");
                }
                catch(IOException ex){
                    System.out.println(ex.getMessage());
                }

                returnValues.add(true);
                returnValues.add("Login successful!");
                return returnValues;
            }
            else{
                returnValues.add(false);
                returnValues.add("Check your username or password!");
                return returnValues;
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            returnValues.add(false);
            returnValues.add("Connection could not been established!");
            return returnValues;
        }
        finally{
            httpClient.close();
        }
    }

}