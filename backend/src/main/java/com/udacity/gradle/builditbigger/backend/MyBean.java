package com.udacity.gradle.builditbigger.backend;

import com.example.android.jokelib.JokeCreator;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private String myData;

    public String getData() {
        JokeCreator joke = new JokeCreator();
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }
}
