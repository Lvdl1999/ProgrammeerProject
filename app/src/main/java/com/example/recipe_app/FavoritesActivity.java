package com.example.recipe_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
    }

    // Navigating from FavoritesActivity to MainActivity
    public void menubutton_clicked(View view) {
        Intent menu = new Intent(FavoritesActivity.this, MainActivity.class);
        startActivity(menu);
    }
}
