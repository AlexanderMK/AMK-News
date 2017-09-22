package com.amknews.amknews;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.amknews.amknews.model.GetArticlesResponse;
import com.amknews.amknews.networking.NewsAPI;
import com.mindorks.placeholderview.PlaceHolderView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private PlaceHolderView mDrawerView;
    private DrawerLayout mDrawer;
    private Toolbar mToolbar;
    private PlaceHolderView mGalleryView;

    private RecyclerView newsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawer = (DrawerLayout)findViewById(R.id.drawerLayout);
        mDrawerView = (PlaceHolderView)findViewById(R.id.drawerView);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mGalleryView = (PlaceHolderView)findViewById(R.id.galleryView);
        setupDrawer();

        newsRecyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerview);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<GetArticlesResponse> call = NewsAPI.getApi().getArticles("reuters", "top");
        call.enqueue(new Callback<GetArticlesResponse>() {
            @Override
            public void onResponse(Call<GetArticlesResponse> call, Response<GetArticlesResponse> response) {
                GetArticlesResponse getArticlesResponse = response.body();
                NewsStore.setNewsArticles(getArticlesResponse.getArticles());
                Toast.makeText(MainActivity.this, "Response received", Toast.LENGTH_SHORT).show();
                HomeNewsAdapter homeNewsAdapter = new HomeNewsAdapter(getArticlesResponse.getArticles());
                newsRecyclerView.setAdapter(homeNewsAdapter);
            }

            @Override
            public void onFailure(Call<GetArticlesResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error received", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupDrawer() {
        mDrawerView
                .addView(new DrawerHeader())
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_PROFILE))
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_REQUESTS))
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_MESSAGE))
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_GROUPS))
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_NOTIFICATIONS))
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_TERMS))
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_SETTINGS))
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_LOGOUT));

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.open_drawer, R.string.close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

    }
}
