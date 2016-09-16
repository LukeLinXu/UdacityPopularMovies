package com.example.lukelin.udacitypopularmovies.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lukelin.udacitypopularmovies.Extras;
import com.example.lukelin.udacitypopularmovies.R;
import com.example.lukelin.udacitypopularmovies.pojos.Movie;
import com.example.lukelin.udacitypopularmovies.pojos.PopularResult;
import com.example.lukelin.udacitypopularmovies.restfulclient.MovieAPIFactory;

import java.util.List;

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
    protected Observable<Object> doRefresh() {
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(final Subscriber<? super Object> subscriber) {
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

    public static class SimpleRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleRecyclerViewAdapter.ViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        private List<Movie> mValues;

        public static class ViewHolder extends RecyclerView.ViewHolder {

            public final View mView;
            public final ImageView mImageView;
            public final TextView mName;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mImageView = (ImageView) view.findViewById(R.id.list_item_image);
                mName = (TextView) view.findViewById(R.id.list_item_title);
            }

        }

        public SimpleRecyclerViewAdapter(Context context, List<Movie> items) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            view.setBackgroundResource(mBackground);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mName.setText(mValues.get(position).getTitle());
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(DetailActivity.ID, mValues.get(position).getId());
                    intent.putExtra(DetailActivity.TITLE, mValues.get(position).getTitle());
                    context.startActivity(intent);
                }
            });
            Glide.with(holder.mImageView.getContext())
                    .load(mValues.get(position).getPoster_path())
                    .fitCenter()
                    .into(holder.mImageView);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }
}