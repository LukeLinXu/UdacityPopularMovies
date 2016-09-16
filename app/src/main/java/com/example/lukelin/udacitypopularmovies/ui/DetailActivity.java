package com.example.lukelin.udacitypopularmovies.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.lukelin.udacitypopularmovies.R;
import com.example.lukelin.udacitypopularmovies.Utils;

/**
 * Created by lukelin on 2016-09-15.
 */
public class DetailActivity extends AppCompatActivity {

    public static final String ID = "info";
    public static final String TITLE = "title";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra(TITLE));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        movieDetailFragment.setArguments(getIntent().getExtras());
        Utils.switchFragment(getSupportFragmentManager(), movieDetailFragment, R.id.fragment_container);
    }

}
