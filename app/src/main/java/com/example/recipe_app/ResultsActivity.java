package com.example.recipe_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
    }

    // Navigating from ResultsActivity to SearchActivity
    public void back_clicked(View view) {

        Intent back = new Intent(ResultsActivity.this, SearchActivity.class);
        startActivity(back);
    }

    public void next_clicked(View view) {

//    When a user clicks on this button, the next 30 results will show.
//    The API searchmethod only returns max 30 results at a time.

    }
}
