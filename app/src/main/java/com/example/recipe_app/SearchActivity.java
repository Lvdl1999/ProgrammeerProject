package com.example.recipe_app;

import android.content.Intent;
import android.net.Uri;
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

    // Navigating from SearchActivity to UploadActivity
    public void toUploadClicked(View view) {
        Intent upload = new Intent(SearchActivity.this, UploadActivity.class);
        startActivity(upload);
    }

    // Navigating from SearchActivity to ResultsActivity
    public void recipesearchClicked(View view) {

        // Saving the users given searchword that was typed in the search bar
        EditText search = (EditText) findViewById(R.id.search_bar);
        String search_word =  search.getText().toString();
        Intent results = new Intent(SearchActivity.this, ResultsActivity.class);

        // The users searchword is put in the intent to perform a GetRequest in the ResultsActivity
        results.putExtra("search_word", search_word);
        startActivity(results);
    }

    public void instagramClicked(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/urfridgeapp/"));
        startActivity(browserIntent);
    }
}

