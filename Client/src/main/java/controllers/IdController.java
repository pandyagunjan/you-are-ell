package controllers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import okhttp3.*;

import javax.swing.text.TabableView;

public class IdController {
    Id myId;
     //ids api url is: "/ids/"
    // GET, POST, PUT
    // CONVERT JSON STRING INTO JAVA ID OBJECT:
     private String rootURL = "http://zipcode.rocks:8085";
     private OkHttpClient client;
     private MediaType JSON;
     private TransactionController tCtrl;
    {
        try {
            tCtrl = new TransactionController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Id> getIds(String response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Id> idList = objectMapper.readValue(response, new TypeReference<ArrayList<Id>>(){});
        return idList; //returns an ID object to IdTextView
    }
    public Id postId(Id id) throws IOException {
        //add your github id / name to be registered
        JSON = MediaType.parse("application/json; charset=utf-8");
        String IdsURL=rootURL + "/ids";
        String body = "\n{" +
                "\n\t\"userid\": \"" + id.getUserid() + "\"," +
                "\n\t\"name\": \"" + id.getName() + "\"," +
                "\n\t\"github\": \"" + id.getGithub() + "\"" +
                "\n}";
        RequestBody json = RequestBody.create(JSON, body);
        Request request = new Request.Builder()
                .url(IdsURL)
                .post(json)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = tCtrl.getClient().newCall(request).execute();
        ObjectMapper objectMapper = new ObjectMapper();
        Id IdObjWithUserId= objectMapper.readValue(response.body().bytes(),Id.class);
        return IdObjWithUserId;

    }
    public Id putId(Id id) {
        //change the name linked to your githubID
        return null;
    }




}