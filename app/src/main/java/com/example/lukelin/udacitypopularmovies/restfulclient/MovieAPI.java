package com.example.lukelin.udacitypopularmovies.restfulclient;

import com.example.lukelin.udacitypopularmovies.pojos.Movie;
import com.example.lukelin.udacitypopularmovies.pojos.PopularResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lukelin on 2016-09-15.
 */
public interface MovieAPI {

    @GET("/3/movie/popular")
    Call<PopularResult> getPopular(@Query("api_key") String apikey);

    @GET("/3/movie/top_rated")
    Call<PopularResult> getTopRated(@Query("api_key") String apikey);

    @GET("/3/movie/{id}")
    Call<Movie> getMovieById(@Path("id") String id, @Query("api_key") String apikey);
}
