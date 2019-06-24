package com.example.recipe_app;

import android.content.Intent;
import android.mtp.MtpConstants;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class UploadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
    }

    // Navigating from UploadActivity back to SearchActivity
    public void tomenuClicked(View view) {
        Intent tomenu = new Intent(UploadActivity.this, SearchActivity.class);
        startActivity(tomenu);
    }

    // This method is called when the user wants to upload the recipe
    public void uploadrecipeClicked(View view) {

        // Saving all Edittexts and getting uploaded title, name, email, recipe and ingredients
        EditText title = findViewById(R.id.title);
        String user_title = title.getText().toString();
        EditText email = findViewById(R.id.email);
        String user_email = email.getText().toString();
        EditText name = findViewById(R.id.name);
        String user_name = name.getText().toString();
        EditText ingredients = findViewById(R.id.ingredients);
        String user_ingredients = ingredients.getText().toString();
        EditText recipe = findViewById(R.id.recipe);
        String user_recipe = recipe.getText().toString();

        // Making sure the user filled in all the Edit texts or the app will show a toast message
        if((user_title.equals("") || user_title == null)){
            Toast.makeText(this, "Fill in a Title", Toast.LENGTH_LONG).show();
        }
        else if((user_email.equals("") || user_email == null)){
            Toast.makeText(this, "Fill in an Email", Toast.LENGTH_LONG).show();
        }
        else if((user_name.equals("") || user_name == null)){
            Toast.makeText(this, "Fill in a Name", Toast.LENGTH_LONG).show();
        }
        else if((user_recipe.equals("") || user_recipe == null)){
            Toast.makeText(this, "Fill in a Recipe", Toast.LENGTH_LONG).show();
        }
        else if((user_ingredients.equals("") || user_ingredients == null)){
            Toast.makeText(this, "Fill in a Title", Toast.LENGTH_LONG).show();
        }
        else{
            uploadRecipe(user_title, user_email, user_name, user_recipe, user_ingredients);
        }
    }

    // This method will upload the users recipe parameters to the database
    private void uploadRecipe(final String title, final String email, final String name,
                              final String recipe, final String ingredients){

        //      TODO Standaard plaatje voor users recipes
        String url = "https://ide50-lvanderlinde.legacy.cs50.io:8080/searchRecipe";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
        new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyRecipes = new HashMap<>();
                MyRecipes.put("title", title);
                MyRecipes.put("email", email);
                MyRecipes.put("name", name);
                MyRecipes.put("recipe", recipe);
                MyRecipes.put("ingredients", ingredients);
                return MyRecipes;
            }
        };
        // Performing the database request to add a user recipe
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

        // This toastmessage is shown when the user has uploaded a complete recipe
        Toast.makeText(this, "Succesfully uploaded your recipe!", Toast.LENGTH_LONG).show();
    }
}
