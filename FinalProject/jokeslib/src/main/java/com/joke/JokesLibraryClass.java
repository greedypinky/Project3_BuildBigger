package com.joke;

import java.util.ArrayList;
import java.util.HashMap;

public class JokesLibraryClass {

    private static ArrayList<String> jokeList = new ArrayList<String>();
    String[] defaultJokes = {"joke1","joke2","joke3","joke4","joke5","joke6","joke7","joke8","joke9","joke10"};

    static {

        jokeList.add("joke1");
        jokeList.add("joke2");
        jokeList.add("joke3");
        jokeList.add("joke4");
        jokeList.add("joke5");
        jokeList.add("joke6");
        jokeList.add("joke7");
        jokeList.add("joke8");
        jokeList.add("joke9");
        jokeList.add("joke10");


    }


    public static void addMoreJoke(String newJoke) {

        jokeList.add(newJoke);
    }

    public static void resetJoke() {
        jokeList.clear();
    }

    public static ArrayList<String> getJokes() {
        return jokeList;
    }


}
