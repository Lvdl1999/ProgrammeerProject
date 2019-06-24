package com.example.recipe_app;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UsersRecipeRequest implements Response.Listener<JSONArray>, Response.ErrorListener {

    private Context context;
    private Callback callback;

    // Describes what the callback should get back and it's methods will show how
    public interface Callback{
        void gotUsersRecipe (ArrayList<SearchRecipe> GetRecipe);
        void gotUsersRecipeError (String message);
    }

    // Constructor for UserGetRequest that accepts a Context type parameter
    UsersRecipeRequest(Context context){
        this.context = context;
    }
    public void getUsersRecipe(Callback callback, String id){
        this.callback = callback;
        String url = "https://ide50-lvanderlinde.legacy.cs50.io:8080/searchRecipe";
        //String url = "https://ide50-lvanderlinde.legacy.cs50.io:8080/searchRecipe?ingredients=" +id;

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, this, this);
        queue.add(jsonArrayRequest);
    }

    // This method is called when the request goes as expected
    @Override
    public void onResponse(JSONArray response) {

        ArrayList arrayList = new ArrayList();

        try {
            JSONObject jsonObject;
            if (response.length() == 0){
                callback.gotUsersRecipeError("Couldn't find this ingredient in the user DataBase");
            }
            for (int i = 0; i < response.length(); i++){
                jsonObject = response.getJSONObject(i);
                String title = jsonObject.getString("title");
                String id = jsonObject.getString("id");

                // An users recipe goes with the tag 'isAPI' being false to underestimate
                Boolean recipetag = false;

                //String image = jsonObject.getString("image_url");
                SearchRecipe recipe = new SearchRecipe(title, id, "http://static.food2fork.com/chickenandcashewnuts_89299_16x9986b.jpg", recipetag);
                arrayList.add(recipe);
            }
            callback.gotUsersRecipe(arrayList);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // This method is called when something about the request goes wrong
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotUsersRecipeError(error.getMessage());
    }
}
