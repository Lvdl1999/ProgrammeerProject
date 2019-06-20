package com.example.recipe_app;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity implements APIGetRequest.Callback, UserGetRequest.Callback {

    private String recipe_id;
    private String source_url;
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        // This activity receives the clicked recipes id to do a GetResearch
        Intent intent = getIntent();
        recipe_id = (String) intent.getSerializableExtra("recipe_id");

        System.out.println("WELKE DING GEDRUKT?:" + recipe_id);

        // Depending on the kind of recipe the user clicked, there will be a different request
        // API recipes get the tag "API recipe" and user recipes "user recipe"
        if (this.tag == "API recipe"){
            // APIGetRequest using the recipes id

            System.out.println("kan zoeken met tag");
            APIGetRequest GetRequest = new APIGetRequest(this);
            GetRequest.getRecipes(this, recipe_id);
        }
        else {
            UserGetRequest userGetRequest = new UserGetRequest(this);
            userGetRequest.getUsersRecipe(this);

        // In case it's an user recipe there is no browse button needed to redirect to online recipe
//            TODO ff checken of dit knopje onzichtbaar is
            findViewById(R.id.browse_button).setVisibility(View.INVISIBLE);
        }

    }

    // Navigating from RecipeActivity to SearchActivity
    public void buttonback_clicked(View view) {
        Intent menu = new Intent(RecipeActivity.this, SearchActivity.class);
        startActivity(menu);
    }

    // Navigating from RecipeActivity to MainActivity
    public void gobackmenu(View view) {
        Intent menu = new Intent(RecipeActivity.this, MainActivity.class);
        startActivity(menu);
    }

    // TODO Adding current recipe to favorites
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

    // Clicking this button will redirect to the source url of the recipe
    public void browse_recipe(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(this.source_url));
        startActivity(browserIntent);
    }

    @Override
    public void gotRecipe(GetRecipe getRecipe) {

        // Saving textviews and imageview that will show a recipe
        ImageView recipe_image = findViewById(R.id.image_recipe);
        TextView ingredients = findViewById(R.id.ingredients);
        TextView title = findViewById(R.id.title);

        // Get image_url, name, source_url and ingredients from the clicked recipe
        String image_url = getRecipe.getImage();
        String recipeName = getRecipe.getName();
        this.source_url = getRecipe.getSource();
        ArrayList ingredients_text = getRecipe.getIngredients();

        this.tag = getRecipe.getRecipetag();

        // Set title and ingredients to textviews in RecipeActivity
        title.setText(recipeName);

//        TODO ingredients uit lijst in textview

        // Before the url can be set to the image, it is updated from a 'http' to 'https' link
        String currentString = image_url;
        String[] seperated = currentString.split(":");
        currentString = seperated[0] + "s:" + seperated[1];

        // Setting image_url to image view
        Picasso.with(this).load(currentString).into(recipe_image);
    }

    @Override
    public void gotRecipeError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void gotUsersRec(GetRecipe userRecipe) {

        System.out.println("NOG EEN CCECKKK");

        // Saving textviews and imageview that will show a recipe
        ImageView image_recipe = findViewById(R.id.image_recipe);
        TextView ingredients = findViewById(R.id.ingredients);
        TextView title = findViewById(R.id.title);
        TextView recipe = findViewById(R.id.recipe_text);

        // Get image_url, name, recipe and ingredients from the clicked userrecipe
        // TODO toelichten van gebruik getSource voor recipe?
        String users_title = userRecipe.getName();
        String recipe_text = userRecipe.getSource();
        String image_url = userRecipe.getImage();
//        TODO ingredienten opvragen en vullen.

        System.out.println("receptje:" + recipe_text);

        // Set title, ingredients and recipe to textviews in RecipeActivity
        title.setText(users_title);
        recipe.setText(recipe_text);

        // Before the url can be set to the image, it is updated from a 'http' to 'https' link
        String currentString = image_url;
        String[] seperated = currentString.split(":");
        currentString = seperated[0] + "s:" + seperated[1];

        // Setting image_url to image view
        Picasso.with(this).load(currentString).into(image_recipe);
    }

    @Override
    public void gotUsersRecError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}


