package com.example.recipe_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent intent = getIntent();
        GetRecipe getRecipe = (GetRecipe) intent.getSerializableExtra("clicked_recipe");

    }

    // Navigating from RecipeActivity to MainActivity
    public void buttonback_clicked(View view) {
        Intent menu = new Intent(RecipeActivity.this, MainActivity.class);
        startActivity(menu);
    }

    public void addfavorites_clicked(View view) {
        // When the user looks at a recipe it's possible to add to favorites through this method.
        // The user has to click this button and the recipe will be added to their favorites list.
        // Which is also a listview in FavoritesActivity.
    }

    public void showgrocery_clicked(View view) {
        Intent grocery = new Intent(RecipeActivity.this, GroceryActivity.class);
        startActivity(grocery);
    }
}
