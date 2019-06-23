package com.example.recipe_app;
import android.content.Context;
import android.telecom.Call;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.recipe_app.GetRecipe;
import com.example.recipe_app.ResultsActivity;
import com.example.recipe_app.UsersRecipeRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserGetRequest implements Response.Listener<JSONArray>, Response.ErrorListener {


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
    public void getUsersRecipe(UserGetRequest.Callback callback){
        this.callback = callback;

        String url = "https://ide50-lvanderlinde.legacy.cs50.io:8080/searchRecipe";
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, this, this);
        queue.add(jsonArrayRequest);
    }

    // This method is called when the request goes as expected
    @Override
    public void onResponse(JSONArray response) {

        try {
            JSONObject jsonObject;

            for (int i = 0; i < response.length(); i++){
                jsonObject = response.getJSONObject(i);
                String title = jsonObject.getString("title");
                String id = jsonObject.getString("id");
                String recipe = jsonObject.getString("recipe");

                System.out.println("test recept = " + recipe);

//         TODO ingredients krijg je in string maar moet als lijst door worden gegeven.
                ArrayList ingredients = null;

//              Arraylist op [0] is het recept ? Of niet, anders is dat je string

                String user_image = "http://static.food2fork.com/chickenandcashewnuts_89299_16x9986b.jpg";
                GetRecipe user_recipe = new GetRecipe(title, id, user_image, recipe, ingredients);
                callback.gotUsersRec(user_recipe);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // This method is called when something about the request goes wrong
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotUsersRecError(error.getMessage());
    }
}
