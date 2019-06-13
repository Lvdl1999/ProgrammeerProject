package com.example.recipe_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity implements APISearchRequest.Callback  {
    private ArrayList recipe;
    private String search_word;
    private int page = 0;
    private GridView recipe_GridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        search_word = (String) intent.getStringExtra("search_word");

        APISearchRequest RecipeRequest = new APISearchRequest(this);
        RecipeRequest.getAPIRecipes(this, search_word, page);

        recipe_GridView = (GridView) findViewById(R.id.recipeGridView);

        recipe_GridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the GridView selected/clicked item text

                SearchRecipe clickedrecipe = (SearchRecipe) recipe.get(position);
                 String recipeId = clickedrecipe.getId();
                System.out.println("testttttttt voor id");
                 System.out.println(recipeId);

                 //GetRecipe clickedRecipe = (GetRecipe) parent.getItemAtPosition(position);
                 //String recipe_id = clickedRecipe.getId();
                // System.out.println(recipe_id);

                 Intent intent = new Intent(ResultsActivity.this, RecipeActivity.class);
                 intent.putExtra("recipe_id", recipeId);
                 startActivity(intent);
            }
        });
    }

    // Navigating from ResultsActivity to SearchActivity
    public void back_clicked(View view) {

        Intent back = new Intent(ResultsActivity.this, SearchActivity.class);
        startActivity(back);
    }

    public void next_clicked(View view) {

//    When a user clicks on this button, the next 30 results will show.
//    The API searchmethod only returns max 30 results at a time.
//    To get the next 30 results, the same request can be used but use page 2 instead of 1

    }

    // GotRecipe so name, id and image are printed from the recipe
    @Override
    public void gotRecipe(ArrayList<SearchRecipe> recipe) {

        this.recipe = recipe;

//        for(int i = 0; i < recipe.size(); i ++) {
//
//            SearchRecipe recipe_info = (SearchRecipe) this.recipe.get(i);
//
//            System.out.println(recipe_info.getName() + "\n" + recipe_info.getId() + "\n"+
//                    recipe_info.getImage() + "\n");
//        }
        ResultsAdapter resultsAdapter = new ResultsAdapter(this, R.id.recipe_layout, recipe);
        recipe_GridView.setAdapter(resultsAdapter);

    }

    // Returning a toast message if request went wrong
    @Override
    public void gotRecipeError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


}
