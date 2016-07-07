package com.epicodus.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.bSearch) Button mSearchButton;
    @Bind(R.id.etSearch) EditText mSearchTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = mSearchTitle.getText().toString();
                Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                intent.putExtra("userInput", userInput);
                startActivity(intent);
                mSearchTitle.setText("");
            }
        });
    }
}
