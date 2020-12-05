package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import models.Id;
import models.Message;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MessageController {

    private HashSet<Message> messagesSeen;
    // why a HashSet??

    public ArrayList<Message> getMessages() throws IOException {
        String rootURL = "http://zipcode.rocks:8085/messages";
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        // RequestBody body = RequestBody.create(mediaType, data);
        Request request = new Request.Builder()
                .url(rootURL)
                // .method("POST", body)
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        } else {

            //  ArrayList<Id> response Ids =
            String responseInString = response.body().toString();


            return null;
        }
    }
    public ArrayList<Message> getMessagesForId(Id Id) {
        return null;
    }
    public Message getMessageForSequence(String seq) {
        return null;
    }
    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        return null;
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        return null;
    }
 
}