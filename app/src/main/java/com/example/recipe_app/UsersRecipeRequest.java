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

    public interface Callback{
        void gotUsersRecipe (ArrayList<SearchRecipe> GetRecipe);
        void gotUsersRecipeError (String message);
    }
    UsersRecipeRequest(Context context){
        this.context = context;
    }
    public void getUsersRecipe(Callback callback){
        this.callback = callback;
        // String url.... van server
        String url = "https://ide50-lvanderlinde.legacy.cs50.io:8080/searchRecipe";

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, this, this);
        queue.add(jsonArrayRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotUsersRecipeError(error.getMessage());
    }

    @Override
    public void onResponse(JSONArray response) {

        ArrayList arrayList = new ArrayList();

        try {
            JSONObject jsonObject;

            for (int i = 0; i < response.length(); i++){
                jsonObject = response.getJSONObject(i);
                String title = jsonObject.getString("title");
                String id = jsonObject.getString("id");

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
}
