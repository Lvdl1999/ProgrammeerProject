package com.example.recipe_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity implements APIGetRequest.Callback {

    private String recipe_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent intent = getIntent();
        recipe_id = (String) intent.getSerializableExtra("recipe_id");

        APIGetRequest GetRequest = new APIGetRequest(this);
        GetRequest.getRecipes(this, recipe_id);

    }

    // Navigating from RecipeActivity to MainActivity
    public void buttonback_clicked(View view) {
        Intent menu = new Intent(RecipeActivity.this, SearchActivity.class);
        startActivity(menu);
    }

    // Adding current recipe to favorites
    public void addfavorites_clicked(View view) {
        // When the user looks at a recipe it's possible to add to favorites through this method.
        // The user has to click this button and the recipe will be added to their favorites list.
        // Which is also a listview in FavoritesActivity.
    }

    // Navigating from RecipeActivity to GroceryActivity
    public void showgrocery_clicked(View view) {
        Intent grocery = new Intent(RecipeActivity.this, GroceryActivity.class);
        startActivity(grocery);
    }


    @Override
    public void gotRecipe(ArrayList<GetRecipe> recipe) {
        System.out.println("got recipe");
    }

    @Override
    public void gotRecipeError(String message) {

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }
}
