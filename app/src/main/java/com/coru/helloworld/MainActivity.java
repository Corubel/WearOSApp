package com.coru.helloworld;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.coru.helloworld.controller.JokeController;
import com.coru.helloworld.model.Joke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends WearableActivity {

    private TextView mTextView;
    private Button jokeButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.tvJoke);

        jokeButton = (Button) findViewById(R.id.tvButton);

        Retrofit retrofitApi = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https:/ " + "/icanhazdadjoke.com/")
                .build();

        final JokeController jokeController = retrofitApi.create(JokeController.class);

        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<Joke> call = jokeController.getJokes();
                call.enqueue(new Callback<Joke>() {
                    @Override
                    public void onResponse(Call<Joke> call, Response<Joke> response) {
                        if (response.isSuccessful()){
                            mTextView.setText(response.body().getJoke());
                        }
                        else{
                            mTextView.setText("Response Unsuccesful");
                        }
                    }

                    @Override
                    public void onFailure(Call<Joke> call, Throwable t) {
                        mTextView.setText("Call has failed");
                    }
                });

            }
        });


        // Enables Always-on
        setAmbientEnabled();
    }
}