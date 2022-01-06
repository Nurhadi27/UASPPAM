package com.example.sqliteandroidstudiojava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sqliteandroidstudiojava.databse.SqliteDatabase;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FoodListAdapter extends RecyclerView.Adapter<ViewHolder>{


    private final List<FoodList> mFoodListList;

    public FoodListAdapter(List<FoodList> foodListList) {
        mFoodListList = foodListList;
    }

    @Override
    public void onBindViewHolder(com.example.sqliteandroidstudiojava.ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @NonNull
    @Override
    public com.example.sqliteandroidstudiojava.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.anime_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (mFoodListList != null & mFoodListList.size() > 0) {
            return mFoodListList.size();
        } else {
            return 0;
        }
    }

    public void addItems(List<FoodList> foodListList) {
        mFoodListList.addAll(foodListList);
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        if (mFoodListList != null & mFoodListList.size() > 0) {
            mFoodListList.remove(position);
        }
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public class ViewHolder extends com.example.sqliteandroidstudiojava.ViewHolder {

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.animeImageView)
        ImageView mAnimeImageView;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.animeCardView)
        CardView mAnimeCardView;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.nameTextView)
        TextView mNameTextView;

        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.scoreTextView)
        TextView mScoreEditText;

        SqliteDatabase dataBase;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
            mAnimeImageView.setImageDrawable(null);
            mNameTextView.setText("");
            mScoreEditText.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            FoodList mFoodList = mFoodListList.get(position);
            dataBase = new SqliteDatabase(itemView.getContext());

            if (mFoodList.getUrl() != null) {
                Glide.with(itemView.getContext())
                        .load(mFoodList.getUrl())
                        .into(mAnimeImageView);
            }

            if (mFoodList.getName() != null) {
                mNameTextView.setText(mFoodList.getName());
            }

            mScoreEditText.setText(String.valueOf(mFoodList.getScore()));

            mAnimeCardView.setOnClickListener(v -> {
                Intent intent=new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra("id",  mFoodList.getId());
                itemView.getContext().startActivity(intent);
            });
        }
    }

}