package com.example.recipe_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UploadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
    }

    public void tomenu_clicked(View view) {

        Intent tomenu = new Intent(UploadActivity.this, MainActivity.class);
        startActivity(tomenu);
    }

    public void uploadrecipe_clicked(View view) {

        // Make a check that all boxes are filled in
        // When the user had filled in a recipe, they can upload it and with this button it will
        // be added to the database.
    }
}
