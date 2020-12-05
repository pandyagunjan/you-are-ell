package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import sun.lwawt.macosx.CSystemTray;

import java.io.File;
public class IdController {
    Id myId;
   // String roolURL="http://zipcode.rocks:8085/ids";


    public ArrayList<Id> getIds(String getIdsURL) throws IOException {
         String[] rootURL = getIdsURL.split(" ");

//        HttpClient client = HttpClientBuilder.create().build();
//        HttpGet request = new HttpGet(rootURL);
//
//        HttpResponse response = client.execute(request);
//        HttpEntity entity = response.getEntity();
//     // Read the contents of an entity and return it as a String.
//        String content = EntityUtils.toString(entity);
//       // System.out.println(content);
//        List<Id> idList = content.jsonPath().getList("records.id");
        ArrayList<Id> sendId;
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        // RequestBody body = RequestBody.create(mediaType, data);
        Request request = new Request.Builder()
                .url(rootURL[0])
                // .method("POST", body)
                .method(rootURL[1],null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        } else {

          //  ArrayList<Id> response Ids =
          //  String responseInString = response.body(.

            ObjectMapper mapper = new ObjectMapper();

            String json = response.body().string();
            System.out.println(json);


           sendId= (ArrayList<Id>) mapper.readValue(json, new TypeReference<List<Id>>() {});
           // System.out.println(sendId);
           // System.out.println(response.body().toString());
        }

               //   .jsonPath().getList("records.id");
 //parse response and get Id's
        return sendId;
    }

    public Id postId(Id id) {
        return null;
    }

    public Id putId(Id id) {
        return null;
    }
 
}