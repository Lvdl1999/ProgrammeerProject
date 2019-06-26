/**
 * UserGetRequest
 * This class handles the GetRequest which is performed for more details about a recipe
 * Created by Levy van der Linde (june 2019)
 * Minor Programmeren
 */


package com.example.recipe_app;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserGetRequest implements Response.Listener<JSONObject>, Response.ErrorListener {


    private Context context;
    private Callback callback;
    private ArrayList ingredients;

    // Describes what the callback should get back and it's methods will show how
    public interface Callback{
        void gotUsersRec (GetRecipe userRecipe);
        void gotUsersRecError (String message);
    }

    // Constructor for UserGetRequest that accepts a Context type parameter
    UserGetRequest(Context context){
        this.context = context;
    }
    public void getUsersRecipe(UserGetRequest.Callback callback, String id){
        this.callback = callback;
        String url = "https://ide50-lvanderlinde.legacy.cs50.io:8080/searchRecipe/"+id;

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
    }

    // This method is called when the request goes as expected
    @Override
    public void onResponse(JSONObject response) {

        // Getting back a response from the DB and saving the data into Strings and an Arraylist
        try {
                String title = response.getString("title");
                String id = response.getString("id");
                String recipe = response.getString("recipe");
                String ingredients = response.getString("ingredients");

                String[] recipe_seperate = ingredients.split(",");
                ArrayList<String> ingredients_array = new ArrayList<>();
                for(int j =0; j< recipe_seperate.length; j++){
                    ingredients_array.add(recipe_seperate[j]);
                }

                String user_image = "http://static.food2fork.com/chickenandcashewnuts_89299_16x9986b.jpg";
                GetRecipe user_recipe = new GetRecipe(title, id, user_image, recipe, ingredients_array);
                callback.gotUsersRec(user_recipe);

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // This method is called when something about the request goes wrong
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotUsersRecError(error.getMessage());

        error.printStackTrace();
    }
}
