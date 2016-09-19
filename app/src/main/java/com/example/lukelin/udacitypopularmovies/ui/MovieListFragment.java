package com.example.lukelin.udacitypopularmovies.ui;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.lukelin.udacitypopularmovies.Extras;
import com.example.lukelin.udacitypopularmovies.R;
import com.example.lukelin.udacitypopularmovies.pojos.Movie;
import com.example.lukelin.udacitypopularmovies.pojos.PopularResult;
import com.example.lukelin.udacitypopularmovies.restfulclient.MovieAPIFactory;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by lukelin on 2016-09-15.
 */
public class MovieListFragment extends ClickToRefreshFragmentBase{
    private RecyclerView recyclerView;
    private int sortOption = MainActivity.SORT_POPULAR;

    @Override
    protected void initView(View spView) {
        recyclerView = (RecyclerView) spView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    protected Observable<Object> doRefresh() {
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(final Subscriber<? super Object> subscriber) {
                if(sortOption == MainActivity.SORT_FAVORITE){
                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<Movie> all = realm.where(Movie.class).findAll();
                    RealmList<Movie> list = new RealmList<Movie>();
                    for(int i = 0; i < all.size(); i++){
                        list.add(all.get(i));
                    }
                    PopularResult popularResult = new PopularResult(0, list, list.size(), 1);
                    subscriber.onNext(popularResult);
                }else {
                    Call<PopularResult> call;
                    if(sortOption == MainActivity.SORT_POPULAR){
                        call = MovieAPIFactory.getMovieAPI().getPopular(Extras.API_KEY);
                    }else {
                        call = MovieAPIFactory.getMovieAPI().getTopRated(Extras.API_KEY);
                    }
                    call.enqueue(new Callback<PopularResult>() {
                        @Override
                        public void onResponse(Call<PopularResult> call, Response<PopularResult> response) {
                            subscriber.onNext(response.body());
                        }

                        @Override
                        public void onFailure(Call<PopularResult> call, Throwable t) {
                            subscriber.onError(t);
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void refreshUI(RelativeLayout mainContent, Object object) {
        if(!(object instanceof PopularResult)) return;
        PopularResult searchResponse = (PopularResult) object;
        recyclerView.setAdapter(new SimpleRecyclerViewAdapter(getActivity(), searchResponse.getResults()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie_list;
    }

    public void setSortOption(int sortOption) {
        if(this.sortOption == sortOption) return;
        this.sortOption = sortOption;
        refresh();
    }
}
