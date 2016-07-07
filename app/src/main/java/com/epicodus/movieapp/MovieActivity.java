package com.epicodus.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;

public class MovieActivity extends AppCompatActivity {
    private static final String TAG = MovieActivity.class.getSimpleName();
    @Bind(R.id.tInput) TextView mTinput;
    @Bind(R.id.listView) ListView mListView;

    public ArrayList<Movie> mMovies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String userInput = intent.getStringExtra("userInput");
        mTinput.setText("User input is : " + userInput);

        getMovies(userInput);

    }


    private void getMovies(String userInput) {
        final MovieService movieService = new MovieService();
        MovieService.findMovies(userInput, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

            }

            @Override
            public void onResponse(Call call, Response response) {
                mMovies = movieService.processResults(response);

                MovieActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] movieNames = new String[mMovies.size()];
                        for (int i = 0; i < movieNames.length; i++) {
                            movieNames[i] = mMovies.get(i).getName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(MovieActivity.this,
                                android.R.layout.simple_expandable_list_item_1, movieNames);
                        mListView.setAdapter(adapter);

                        for (Movie movie : mMovies) {
                            Log.d(TAG, "Name: " + movie.getName());
                            Log.d(TAG, "Overview: " + movie.getOverview());
                        }
                    }
                });
            }
        });
    }
}
