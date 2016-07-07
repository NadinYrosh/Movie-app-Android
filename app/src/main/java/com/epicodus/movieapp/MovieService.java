package com.epicodus.movieapp;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieService {

    public static void findMovies(String userInput, Callback callback){
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.Base_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.Search_Query_Paramater, userInput);
        urlBuilder.addQueryParameter(Constants.Api_Key_Parameter, Constants.Key);

        String url = urlBuilder.build().toString();

        OkHttpClient client = new OkHttpClient.Builder().build();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Movie> processResults(Response response) {
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject tmdbJSON = new JSONObject(jsonData);
                JSONArray resultsJSON = tmdbJSON.getJSONArray("results");
                for (int i = 0; i < resultsJSON.length(); i++) {
                    JSONObject movieJSON = resultsJSON.getJSONObject(i);
                    String name = movieJSON.getString("title");
                    String poster = movieJSON.getString("poster_path");
                    String overview = movieJSON.getString("overview");

                    Movie movie = new Movie(name, poster, overview);
                    movies.add(movie);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
