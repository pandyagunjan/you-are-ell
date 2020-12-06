package controllers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.tools.packager.IOUtils;
import models.Id;
import okhttp3.*;
import okhttp3.MediaType;
import views.IdTextView;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private OkHttpClient client;
    private MediaType JSON;
    IdController idController;
    public TransactionController() throws IOException {
        client = new OkHttpClient().newBuilder()
        .connectTimeout(5, TimeUnit.MINUTES)
        .readTimeout(5, TimeUnit.MINUTES)
        .writeTimeout(5, TimeUnit.MINUTES)
        .build();
     //   mediaType = MediaType.parse("application/json");

    }
    public String get(String path) throws IOException {
        Request request = new Request.Builder()
                .url(rootURL + path)
                .method("GET", null)
                //.addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    //post - posting something new

    public void post(String path) throws IOException {

        JSON = MediaType.parse("application/json; charset=utf-8");
        String body="{\n" +
                "        \"userid\": \"-\",\n" +
                "        \"name\": \"Pandya Gunjan\",\n" +
                "        \"github\": \"HubHub\"\n" +
                "    }";
        RequestBody json = RequestBody.create(JSON, body);
        Request request = new Request.Builder()
                .url(rootURL + path)
                .post(json)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
    public void put(String path ,String body) throws IOException {
        JSON = MediaType.parse("application/json; charset=utf-8");
       // String body=idsList.get(4).toString();
        RequestBody json = RequestBody.create(JSON, body);
        Request request = new Request.Builder()
                .url(rootURL + path)
                .put(json)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
//        ObjectMapper objectMapper = new ObjectMapper();
//        Id id = objectMapper.readValue(response.body().string() ,Id.class);
//         System.out.println(id);
    }




    public String getMessages(String path) throws IOException {
        Request request = new Request.Builder()
                .url(rootURL + path)
                .method("GET", null)
                //.addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}