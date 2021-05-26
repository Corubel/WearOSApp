package com.coru.helloworld.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Joke implements Serializable {
    String id;
    String joke;
    int status;



    public String getJoke() {
        return joke;
    }





}
