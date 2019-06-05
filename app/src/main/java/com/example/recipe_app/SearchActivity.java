package com.example.recipe_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    // Navigating from SearchActivity to MainActivity
    public void backtomenu_clicked(View view) {

        Intent menu = new Intent(SearchActivity.this, MainActivity.class);
        startActivity(menu);
    }

    // Navigating from SearchActivity to ResultsActivity
    public void recipesearch_clicked(View view) {

        Intent results = new Intent(SearchActivity.this, ResultsActivity.class);
        startActivity(results);

    }
}
