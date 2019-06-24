package com.example.recipe_app;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity implements APIGetRequest.Callback, UserGetRequest.Callback {

    private String recipe_id;
    private String source_url;
    private Boolean tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        // This activity receives the clicked recipes id to do a GetResearch
        Intent intent = getIntent();
        recipe_id = (String) intent.getSerializableExtra("recipe_id");
        tag = (Boolean) intent.getSerializableExtra("tag");
        System.out.println("BOOLEAN IS BINNEN MET INTENT" + tag);

        // Depending on the kind of recipe the user clicked, there will be a different request
        // It's an API recipe if the tag boolean is 'true'
        if (tag){
            // Performing an APIGetRequest
            APIGetRequest GetRequest = new APIGetRequest(this);
            GetRequest.getRecipes(this, recipe_id);
        }
        else {
            // Performing a UsersGetRequest
            UserGetRequest userGetRequest = new UserGetRequest(this);
            userGetRequest.getUsersRecipe(this, recipe_id);

            // If it's an user recipe there is no browse button needed to redirect to online recipe
            // so in this case the button won't show
            findViewById(R.id.browse_button).setVisibility(View.INVISIBLE);
        }
    }

    // Navigating from RecipeActivity to SearchActivity
    public void buttonbackClicked(View view) {
        Intent menu = new Intent(RecipeActivity.this, SearchActivity.class);
        startActivity(menu);
    }

    // Navigating from RecipeActivity to MainActivity
    public void gobackToMenu(View view) {
        Intent menu = new Intent(RecipeActivity.this, SearchActivity.class);
        startActivity(menu);
    }

    // TODO Adding current recipe to favorites
    public void addfavoritesClicked(View view) {
        // When the user looks at a recipe it's possible to add to favorites through this method.
        // The user has to click this button and the recipe will be added to their favorites list.
        // Which is also a listview in FavoritesActivity.
    }

    // Navigating from RecipeActivity to GroceryActivity
    public void showgroceryClicked(View view) {
        Intent grocery = new Intent(RecipeActivity.this, GroceryActivity.class);
        startActivity(grocery);
    }

    // Clicking this button will redirect to the source url of the recipe
    public void browseRecipe(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(this.source_url));
        startActivity(browserIntent);
    }

    // This method is called when the request goes as expected
    @Override
    public void gotRecipe(GetRecipe getRecipe) {

        // Saving textviews and imageview that will show a recipe
        ImageView recipe_image = findViewById(R.id.image_recipe);
        ListView ingredients = findViewById(R.id.ingredients_text);
        TextView title = findViewById(R.id.title);

        // Get image_url, name, source_url and ingredients from the clicked recipe
        String image_url = getRecipe.getImage();
        String recipeName = getRecipe.getName();
        this.source_url = getRecipe.getSource();
        ArrayList ingredients_text = getRecipe.getIngredients();

        // Set title and ingredients to textviews in RecipeActivity
        title.setText(recipeName);
        //Set adapter
        RecipeAdapter adapter = new RecipeAdapter(RecipeActivity.this, R.layout.ingredients_adapter, ingredients_text);
        ingredients.setAdapter(adapter);
//        TODO ingredients uit lijst in textview zien te krijgen

        // Before the url can be set to the image, it is updated from a 'http' to a 'https' link
        String currentString = image_url;
        String[] seperated = currentString.split(":");
        currentString = seperated[0] + "s:" + seperated[1];

        // Setting image_url to corresponding image view
        Picasso.with(this).load(currentString).into(recipe_image);
    }

    // This method is called when something about the request goes wrong
    @Override
    public void gotRecipeError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // This method is called when the request goes as expected
    @Override
    public void gotUsersRec(GetRecipe userRecipe) {

        // Saving textviews and imageview that will be used to show a recipe
        ImageView image_recipe = findViewById(R.id.image_recipe);
        ListView ingredients = findViewById(R.id.ingredients_text);
        TextView title = findViewById(R.id.title);
        TextView recipe = findViewById(R.id.recipe_text);

        String users_title = userRecipe.getName();
        String recipe_text = userRecipe.getSource();
        String image_url = userRecipe.getImage();
        ArrayList ingredients_text = userRecipe.getIngredients();


        RecipeAdapter adapter = new RecipeAdapter(RecipeActivity.this, R.layout.ingredients_adapter, ingredients_text);
        ingredients.setAdapter(adapter);

        // Set title, ingredients and recipe to corresponding textviews in RecipeActivity
        title.setText(users_title);
        recipe.setText(recipe_text);

        // Before the url can be set to the image, it is updated from a 'http' to 'https' link
        String currentString = image_url;
        String[] seperated = currentString.split(":");
        currentString = seperated[0] + "s:" + seperated[1];

        // Setting image_url to image view
        Picasso.with(this).load(currentString).into(image_recipe);
    }

    // This method is called when something about the request goes wrong
    @Override
    public void gotUsersRecError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}


