/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.udacity.jokeproject.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.jokeproject.udacity.com",
                ownerName = "backend.jokeproject.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public GetJokeBean sayHi(@Named("name") String name) {
        GetJokeBean response = new GetJokeBean();
        response.setData("Hi, " + name);

        return response;
    }

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayJoke")
    public GetJokeBean sayJoke() {
        GetJokeBean response = new GetJokeBean();
        String theJoke = response.getJokeData().get(0); // get the first joke
        response.setJoke(theJoke);

        return response;
    }

}
