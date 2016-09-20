package com.example.lukelin.udacitypopularmovies.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lukelin.udacitypopularmovies.R;
import com.example.lukelin.udacitypopularmovies.Utils;
import com.example.lukelin.udacitypopularmovies.pojos.Review;

import java.util.List;

/**
 * Created by lukelin on 2016-09-16.
 */
public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<Review> mValues;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView mContent;
        public final TextView mName;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContent = (TextView) view.findViewById(R.id.review_list_item_content);
            mName = (TextView) view.findViewById(R.id.review_list_item_name);
        }

    }

    public ReviewListAdapter(Context context, List<Review> items) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_list_item, parent, false);
        view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mName.setText(mValues.get(position).getAuthor());
        holder.mContent.setText(mValues.get(position).getContent());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.showDialog(v.getContext(), mValues.get(position).getContent(), mValues.get(position).getAuthor(), null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}