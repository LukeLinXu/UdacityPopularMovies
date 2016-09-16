package com.example.lukelin.udacitypopularmovies.restfulclient;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by lukelin on 2016-09-15.
 */
public class MovieAPIFactory {

    private static final String API_BASE_URL = "https://api.themoviedb.org/";
    private OkHttpClient httpClient;
    private static MovieAPI movieAPI;

    public MovieAPIFactory() {
        this.httpClient = new OkHttpClient.Builder()
                .build();
    }

    public MovieAPI createAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        return retrofit.create(MovieAPI.class);
    }

    public static MovieAPI getMovieAPI(){
        if(movieAPI != null) return movieAPI;
        MovieAPIFactory apiFactory = new MovieAPIFactory();
        movieAPI = apiFactory.createAPI();
        return movieAPI;
    }

}
