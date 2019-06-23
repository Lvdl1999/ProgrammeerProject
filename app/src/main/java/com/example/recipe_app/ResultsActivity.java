package com.example.recipe_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity implements APISearchRequest.Callback, UsersRecipeRequest.Callback  {
    private ArrayList recipe;
    private ArrayList GetRecipe;
    private String search_word;
    private int page;
    private GridView recipe_GridView;
    private ArrayList arrayList = new ArrayList();
    private Boolean tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Receiving the search word of the user through the intent
        Intent intent = getIntent();
        search_word = (String) intent.getStringExtra("search_word");

//        page = (int) intent.getIntExtra("page", 0);
//        if(page == 0) {
//            UsersRecipeRequest userRecipeRequest = new UsersRecipeRequest(this);
//            userRecipeRequest.getUsersRecipe(this, search_word);
//            Button previous = (Button) findViewById(R.id.button3);
//            previous.setVisibility(View.GONE);
//        }

//        TODO waarom zit deze request in oncreate?
        // Performing a UsersRecipeRequest
        UsersRecipeRequest userRecipeRequest = new UsersRecipeRequest(this);
        userRecipeRequest.getUsersRecipe(this);

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

        Bundle extras = new Bundle();
        extras.putBoolean("tag", tag);
        extras.putString("recipe_id", recipeId);

        intent.putExtras(extras);
        startActivity(intent);

            }
        });
    }

    // Navigating from ResultsActivity to SearchActivity
    public void back_clicked(View view) {
        Intent back = new Intent(ResultsActivity.this, SearchActivity.class);
        startActivity(back);
    }

    public void previous_clicked(View view) {
        page--;
        Intent results = new Intent(ResultsActivity.this, ResultsActivity.class);
        results.putExtra("search_word", search_word);
        results.putExtra("page", page);
        startActivity(results);

    }

    public void next_clicked(View view) {

//        TODO next 30 results
//    When a user clicks on this button, the next 30 results will show.
//    The API searchmethod only returns max 30 results at a time.
//    To get the next 30 results, the same request can be used but use page 2 instead of 1

        page ++;
        Intent results = new Intent(ResultsActivity.this, ResultsActivity.class);
        results.putExtra("search_word", search_word);
        results.putExtra("page", page);
        startActivity(results);
    }

    // This method is called when the request goes as expected
    @Override
    public void gotRecipe(ArrayList<SearchRecipe> recipe) {

        for (int i2 = 0; i2 < recipe.size(); i2++) {
            SearchRecipe searchRecipe2 = (SearchRecipe) recipe.get(i2);
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
