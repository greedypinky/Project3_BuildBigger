package com.udacity.jokeproject.backend;

import java.util.ArrayList;
import com.joke.JokesLibraryClass;

/**
 * The object model for the data we are sending through endpoints
 */
public class GetJokeBean {

    private String myData;
    private String myJoke;

    public String getData() {
        return myData;
    }
    public ArrayList<String> getJokeData() {

        ArrayList<String> jokeListFromJavaLib = JokesLibraryClass.getJokes();

        return jokeListFromJavaLib;
    }

    public void setJoke(String joke)
    {
        myJoke = joke;
    }

    public void setData(String data)
    {
        myData = data;
    }
}