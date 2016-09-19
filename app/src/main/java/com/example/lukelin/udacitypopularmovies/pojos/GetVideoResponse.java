package com.example.lukelin.udacitypopularmovies.pojos;

import java.util.List;

/**
 * Created by lukelin on 2016-09-19.
 */
public class GetVideoResponse {
    private String id;
    private List<Video> results;

    public String getId() {
        return id;
    }

    public List<Video> getResults() {
        return results;
    }
}
