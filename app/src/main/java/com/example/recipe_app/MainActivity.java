package com.example.recipe_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Navigating from MainActivity to SearchActivity
    public void gotosearch_clicked(View view) {

        Intent search = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(search);
    }

    // Navigating from MainActivity to FavoritesActivity
    public void gotofavorites_clicked(View view) {

        Intent favorites = new Intent(MainActivity.this, FavoritesActivity.class);
        startActivity(favorites);
    }

    // Navigating from MainActivity to UploadActivity
    public void upload_clicked(View view) {
        Intent uploadrecipe = new Intent(MainActivity.this, UploadActivity.class);
        startActivity(uploadrecipe);
    }
}
