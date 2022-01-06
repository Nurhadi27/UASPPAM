package com.example.sqliteandroidstudiojava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqliteandroidstudiojava.databse.SqliteDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.item_list)
    RecyclerView mRecyclerView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    FoodListAdapter mFoodListAdapter;

    GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Recycler();


        mFab.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout_item:
                Toast.makeText(this, "Berhasil Logout!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LoginAct.class);
                startActivity(intent);
                this.finish();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    public void Recycler() {

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mLayoutManager = new GridLayoutManager(this, 2);
        } else {
            mLayoutManager = new GridLayoutManager(this, 1);
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mFoodListAdapter = new FoodListAdapter(new ArrayList<>());

        Content();
    }

    private void Content() {

        SqliteDatabase mDatabase = new SqliteDatabase(this);
        List<FoodList> mFoodList = mDatabase.listAnime();


        if (mFoodList.size() > 0) {
            mFoodListAdapter = new FoodListAdapter(mFoodList);
        } else {
            ArrayList<FoodList> foodListEmpty = new ArrayList<>();
            mFoodListAdapter.addItems(foodListEmpty);
        }

        mRecyclerView.setAdapter(mFoodListAdapter);

    }

}
