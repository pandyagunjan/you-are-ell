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
    private String rootURL = "http://zipcode.rocks:8085";
    String IdsURL = rootURL + "/ids";
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
        ArrayList<Id> idList = objectMapper.readValue(response, new TypeReference<ArrayList<Id>>() {
        });
        return idList; //returns an ID object to IdTextView
    }

    public Id postId(Id id) throws IOException {
        //add your github id / name to be registered
        JSON = MediaType.parse("application/json; charset=utf-8");

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
        Id IdObjWithUserId = ConvertJSONToObject(response);
        return IdObjWithUserId;

    }

    private Id ConvertJSONToObject(Response response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Id IdObjWithUserId = objectMapper.readValue(response.body().bytes(), Id.class);
        return IdObjWithUserId;
    }

    public Id putId(Id id) {
        //change the name linked to your githubID
//        JSON = MediaType.parse("application/json; charset=utf-8");
//        RequestBody json = RequestBody.create(JSON, putBody);
//        Request request = new Request.Builder()
//                .url(IdsURL)
//                .put(json)
//                .addHeader("Content-Type", "application/json")
//                .build();
//        Response responseAfterPut = client.newCall(request).execute();
//        return responseAfterPut.body().string();
        return null;
    }




}