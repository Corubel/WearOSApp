package com.coru.helloworld.controller;

import com.coru.helloworld.model.Joke;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface JokeController {

    @Headers("Accept: aplication/json")
    @GET("/")
    Call<Joke> getJokes();
}
