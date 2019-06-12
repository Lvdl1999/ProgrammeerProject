package com.example.recipe_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


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

        EditText search = (EditText) findViewById(R.id.search_bar);
        String search_word =  search.getText().toString();
        Intent results = new Intent(SearchActivity.this, ResultsActivity.class);

        results.putExtra("search_word", search_word);
        startActivity(results);

    }





}

