package controllers;
import models.Id;
import okhttp3.*;
import okhttp3.MediaType;
import java.io.*;

import java.util.ArrayList;

import java.util.concurrent.TimeUnit;


public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private OkHttpClient client;
    private MediaType JSON;
    private IdController idCtrl = new IdController();
    Console console = new Console();
    private String selectId;
    private String updateName;

    public TransactionController() throws IOException {
        client = new OkHttpClient().newBuilder()
        .connectTimeout(5, TimeUnit.MINUTES)
        .readTimeout(5, TimeUnit.MINUTES)
        .writeTimeout(5, TimeUnit.MINUTES)
        .build();

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

    public String post(String path ,String postName,String gitHub) throws IOException {
       // String postName=console.getStringInput("\u001B[34m Please enter Name to POST:\u001B[34m");
       // String gitHub = console.getStringInput("\u001B[34m Please enter the GIT Hub:\u001B[34m");
        Id postId= new Id(postName, "-",gitHub);
        JSON = MediaType.parse("application/json; charset=utf-8");
        String body = "\n{" +
                "\n\t\"userid\": \"" + postId.getUserid() + "\"," +
                "\n\t\"name\": \"" + postId.getName() + "\"," +
                "\n\t\"github\": \"" + postId.getGithub() + "\"" +
                "\n}";

        RequestBody json = RequestBody.create(JSON, body);
        Request request = new Request.Builder()
                .url(rootURL + path)
                .post(json)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    public String put(String path,String putBody) throws IOException {
//        String response = this.get(path); // get the response of /ids
//        ArrayList<Id> idsList = idCtrl.getIds(response); // Convert all response to ArrayList
//        String putBody = "";
//        selectId = console.getStringInput("\u001B[34m Please enter Id of person to modify:\u001B[34m");
//        updateName = console.getStringInput("\u001B[34m Please enter the modified name:\u001B[34m");
//        for (int i = 0; i < idsList.size(); i++) {
//            if(idsList.get(i).getGithub().equalsIgnoreCase(selectId))
//            {
//                idsList.get(i).setName(updateName);
//                putBody= idsList.get(i).toString();
//                break;
//            }
//        }
        JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody json = RequestBody.create(JSON, putBody);
        Request request = new Request.Builder()
                .url(rootURL + path)
                .put(json)
                .addHeader("Content-Type", "application/json")
                .build();
        Response responseAfterPut = client.newCall(request).execute();
        return responseAfterPut.body().string();
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