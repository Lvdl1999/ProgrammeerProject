/**
 * RecipeActivity
 * This activity shows the details of a recipe that the user has clicked on by doing a GetRequest
 * Created by Levy van der Linde (june 2019)
 * Minor Programmeren
 */

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
    private String currentString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        // This activity receives the clicked recipes id to do a GetResearch
        Intent intent = getIntent();
        recipe_id = (String) intent.getSerializableExtra("recipe_id");
        tag = (Boolean) intent.getSerializableExtra("tag");

        // Set bigger image and closing button invisible
        findViewById(R.id.image_big).setVisibility(View.INVISIBLE);
        findViewById(R.id.closebutton).setVisibility(View.INVISIBLE);

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

    // This method makes it possible to look closer at the recipes image
    public void bigImageClicked(View view) {
        // Set bigger image and closing button invisible
        findViewById(R.id.image_big).setVisibility(View.VISIBLE);
        findViewById(R.id.closebutton).setVisibility(View.VISIBLE);

        // Setting image_url to corresponding image view
        ImageView recipe_image = findViewById(R.id.image_big);
        Picasso.with(this).load(this.currentString).into(recipe_image);
    }

    // By clicking this button the big image can be closed again to go back to it's original size
    public void closeImageClicked(View view) {
        // Set bigger image and closing button back to invisible
        findViewById(R.id.image_big).setVisibility(View.INVISIBLE);
        findViewById(R.id.closebutton).setVisibility(View.INVISIBLE);
    }

    // Navigating from RecipeActivity to MainActivity
    public void gobackToMenu(View view) {
        Intent menu = new Intent(RecipeActivity.this, SearchActivity.class);
        startActivity(menu);
    }

    // Clicking this button will redirect to the source url of the recipe
    public void browseRecipe(View view) {
        // I used this tutorial: https://www.youtube.com/watch?v=9-3OCc5g5oE
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

        //Set recipe adapter to load recipe into textview
        RecipeAdapter adapter = new RecipeAdapter(RecipeActivity.this, R.layout.ingredients_adapter, ingredients_text);
        ingredients.setAdapter(adapter);

        // Before the url can be set to the image, it is updated from a 'http' to a 'https' link
        currentString = image_url;
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
        ListView ingredients = findViewById(R.id.ingredients_text);
        TextView title = findViewById(R.id.title);
        TextView recipe = findViewById(R.id.recipe_text);

        String users_title = userRecipe.getName();
        String recipe_text = userRecipe.getSource();
        ArrayList ingredients_text = userRecipe.getIngredients();

        // Set title, ingredients(adapter) and recipe to corresponding textviews in RecipeActivity
        RecipeAdapter adapter = new RecipeAdapter(RecipeActivity.this, R.layout.ingredients_adapter, ingredients_text);
        ingredients.setAdapter(adapter);

        title.setText(users_title);
        recipe.setText(recipe_text);
    }

    // This method is called when something about the request goes wrong
    @Override
    public void gotUsersRecError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}


