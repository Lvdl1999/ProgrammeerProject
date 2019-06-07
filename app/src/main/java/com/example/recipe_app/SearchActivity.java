package com.example.recipe_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity implements APIRecipesRequest.Callback {

    private ArrayList recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        APIRecipesRequest RecipeRequest = new APIRecipesRequest(this);
        RecipeRequest.getAPIRecipes(this);

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

    // GotRecipe so name, id and image are printed from the recipe
    @Override
    public void gotRecipe(ArrayList<SearchRecipe> recipe) {

        System.out.println("got recept");
        this.recipe = recipe;

        for(int i = 0; i < recipe.size(); i ++) {

            SearchRecipe recipe_info = (SearchRecipe) this.recipe.get(i);

            System.out.println(recipe_info.getName() + "\n" + recipe_info.getId() + "\n"+
                    recipe_info.getImage() + "\n");
        }

    }

    // Returning a toast message if request went wrong
    @Override
    public void gotRecipeError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}

