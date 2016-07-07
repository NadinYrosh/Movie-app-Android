package com.epicodus.movieapp;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MovieService {

    public static void findMovies(String userInput, Callback callback){
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.Movie_App_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.Movie_Search_Query_Paramater, userInput);
        String url = urlBuilder.build().toString();

        OkHttpClient client = new OkHttpClient.Builder().build();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
