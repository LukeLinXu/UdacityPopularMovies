package com.example.lukelin.udacitypopularmovies.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lukelin.udacitypopularmovies.Extras;
import com.example.lukelin.udacitypopularmovies.R;
import com.example.lukelin.udacitypopularmovies.pojos.Movie;
import com.example.lukelin.udacitypopularmovies.restfulclient.MovieAPIFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by lukelin on 2016-09-15.
 */
public class MovieDetailFragment extends ClickToRefreshFragmentBase{
    private ImageView poster;
    private TextView title, status, releaseDate, voteAverage, popularity, description;

    @Override
    protected void initView(View spView) {
        poster = (ImageView) spView.findViewById(R.id.fragment_movie_detail_poster);
        title = (TextView) spView.findViewById(R.id.fragment_movie_detail_title);
        status = (TextView) spView.findViewById(R.id.fragment_movie_detail_status);
        releaseDate = (TextView) spView.findViewById(R.id.fragment_movie_detail_release_date);
        voteAverage = (TextView) spView.findViewById(R.id.fragment_movie_detail_vote_average);
        popularity = (TextView) spView.findViewById(R.id.fragment_movie_detail_popularity);
        description = (TextView) spView.findViewById(R.id.fragment_movie_detail_description);
    }

    @Override
    protected Observable<Object> doRefresh() {
        return Observable.create(new Observable.OnSubscribe<Object>(){
            @Override
            public void call(final Subscriber<? super Object> subscriber) {
                Call<Movie> call = MovieAPIFactory.getMovieAPI().getMovieById(getArguments().getString(DetailActivity.ID), Extras.API_KEY);
                call.enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        subscriber.onNext(response.body());
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        subscriber.onError(t);
                    }
                });
            }
        });
    }

    @Override
    protected void refreshUI(RelativeLayout mainContent, Object object) {
        if(!(object instanceof Movie)) return;
        Movie movie = (Movie) object;
        Glide.with(getActivity())
                .load(movie.getPoster_path())
                .fitCenter()
                .into(poster);
        title.setText(movie.getTitle());
        status.setText(getString(R.string.fragment_movie_detail_status, movie.getStatus()));
        releaseDate.setText(getString(R.string.fragment_movie_detail_release_date, movie.getRelease_date()));
        voteAverage.setText(getString(R.string.fragment_movie_detail_vote_average, Double.toString(movie.getVote_average())));
        popularity.setText(getString(R.string.fragment_movie_detail_popularity, Double.toString(movie.getPopularity())));
        description.setText(movie.getOverview());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie_detail;
    }
}
