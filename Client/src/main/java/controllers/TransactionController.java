package controllers;
import okhttp3.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private OkHttpClient client;
    private MediaType mediaType;
    public TransactionController() throws IOException {
        client = new OkHttpClient().newBuilder()
        .connectTimeout(5, TimeUnit.MINUTES)
        .readTimeout(5, TimeUnit.MINUTES)
        .writeTimeout(5, TimeUnit.MINUTES)
        .build();
        mediaType = MediaType.parse("application/json");

    }
    public String get(String path,String method) throws IOException {
        Request request = new Request.Builder()
                .url(rootURL + path)
                .method(method, null)
                //.addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    //post - posting something new
    //put - updating
}