package com.example.lukelin.udacitypopularmovies.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lukelin.udacitypopularmovies.R;
import com.example.lukelin.udacitypopularmovies.Utils;

/**
 * Created by lukelin on 2016-09-15.
 */
public class MainActivity extends AppCompatActivity {

    public static final int SORT_POPULAR = 0;
    public static final int SORT_TOPRATED = 1;
    public static final int SORT_FAVORITE = 2;
    private int currentSortOption = SORT_POPULAR;
    private MovieListFragment movieListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        movieListFragment = new MovieListFragment();
        Utils.switchFragment(getSupportFragmentManager(), movieListFragment, R.id.fragment_container);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sort:
                showSortingOptions(MainActivity.this, currentSortOption);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void handleSortOptionsChange(int which) {
        currentSortOption = which;
        if(movieListFragment != null) movieListFragment.setSortOption(currentSortOption);
    }

    private void showSortingOptions(final MainActivity context, final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.pick_sorting_option)
                .setSingleChoiceItems(R.array.sorting_options_array, position, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which != position){
                            context.handleSortOptionsChange(which);
                        }
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
