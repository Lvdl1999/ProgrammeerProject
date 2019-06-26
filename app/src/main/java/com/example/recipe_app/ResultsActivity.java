/**
 * ResultsActivity
 * This activity does an API- and UserSearchRequest and shows all the corresponding results.
 * Created by Levy van der Linde (june 2019)
 * Minor Programmeren
 */

package com.example.recipe_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity implements APISearchRequest.Callback, UserSearchRequest.Callback  {
    private ArrayList recipe;
    private ArrayList GetRecipe;
    private String search_word;
    private int page;
    private GridView recipe_GridView;
    private ArrayList arrayList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Receiving the search word of the user through the intent
        Intent intent = getIntent();
        search_word = (String) intent.getStringExtra("search_word");

        // Performing an API request for the next 30 results because it gives 30 per request
        // The 'previous' button will be invisible in case the app is at page 0
        page = (int) intent.getIntExtra("page", 0);
        if(page == 0) {
            // Performing an UserSearchRequest only on page 0
            UserSearchRequest userRecipeRequest = new UserSearchRequest(this);
            userRecipeRequest.getUsersRecipe(this, search_word);
            Button previous = (Button) findViewById(R.id.button3);
            previous.setVisibility(View.GONE);
        }
        else{
            APISearchRequest RecipeRequest = new APISearchRequest(this);
            RecipeRequest.getAPIRecipes(this, search_word, page);
        }

        // Given the results, the user can click on a recipe to get more details
        recipe_GridView = (GridView) findViewById(R.id.recipeGridView);
        recipe_GridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get the GridView selected/clicked item text
                SearchRecipe clickedrecipe = (SearchRecipe) recipe.get(position);
                String recipeId = clickedrecipe.getId();
                Boolean tag = clickedrecipe.getRecipetag();

                // The recipe id and tag is put within the intent to RecipeActivity for the API getRequest
                Intent intent = new Intent(ResultsActivity.this, RecipeActivity.class);

                // https://stackoverflow.com/questions/22918333/pass-multiple-variable-with-putextra
                Bundle extras = new Bundle();
                extras.putBoolean("tag", tag);
                extras.putString("recipe_id", recipeId);

                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }

    // Navigating from ResultsActivity to SearchActivity
    public void backClicked(View view) {
        Intent back = new Intent(ResultsActivity.this, SearchActivity.class);
        startActivity(back);
    }

    // This method is called when the user presses the 'previous' button
    public void previousClicked(View view) {
        // The resultsActivity will show the previous 30 results of the API recipes
        page--;
        Intent results = new Intent(ResultsActivity.this, ResultsActivity.class);
        results.putExtra("search_word", search_word);
        results.putExtra("page", page);
        startActivity(results);
    }

    // This method is called when the user presses the 'next' button
    public void nextClicked(View view) {
        // The resultsActivity will show the next 30 results of the API recipes
        page ++;
        Intent results = new Intent(ResultsActivity.this, ResultsActivity.class);
        results.putExtra("search_word", search_word);
        results.putExtra("page", page);
        startActivity(results);
    }

    // Navigating from ResultsActivity to UploadActivity
    public void uploadClicked(View view) {
        Intent back = new Intent(ResultsActivity.this, UploadActivity.class);
        startActivity(back);
    }

    // This method is called when the request goes as expected
    @Override
    public void gotRecipe(ArrayList<SearchRecipe> recipe) {

        // Getting all data from the corresponding recipe and putting in an ArrayList
        for (int j = 0; j < recipe.size(); j++) {
            SearchRecipe searchRecipe2 = (SearchRecipe) recipe.get(j);
            SearchRecipe recipe2 = new SearchRecipe(searchRecipe2.getName(), searchRecipe2.getId(),
                    searchRecipe2.getImage(), searchRecipe2.getRecipetag());
            arrayList.add(recipe2);
        }
        this.recipe = arrayList;
        ResultsAdapter resultsAdapter = new ResultsAdapter(this, R.id.recipe_layout, this.recipe);
        recipe_GridView.setAdapter(resultsAdapter);
    }

    // This method is called when something about the request goes wrong
    @Override
    public void gotRecipeError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // This method is called when the request goes as expected
    @Override
    public void gotUsersRecipe (ArrayList<SearchRecipe> GetRecipe) {
        this.GetRecipe = GetRecipe;

        // Getting all data from the corresponding recipe and putting in an ArrayList
        for (int i = 0; i < GetRecipe.size(); i++) {
            SearchRecipe searchRecipe = (SearchRecipe) GetRecipe.get(i);
            SearchRecipe recipe = new SearchRecipe(searchRecipe.getName(), searchRecipe.getId(),
                    searchRecipe.getImage(), searchRecipe.getRecipetag());
            arrayList.add(recipe);
        }
        // The search word and page are redirected to the APISearchRequest
        // Performing an APISearchRequest
        APISearchRequest RecipeRequest = new APISearchRequest(this);
        RecipeRequest.getAPIRecipes(this, search_word, page);
    }

    // This method is called when something about the request goes wrong
    @Override
    public void gotUsersRecipeError (String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
