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

public class UserRecipeRequest implements Response.Listener<JSONArray>, Response.ErrorListener {

    private Context context;
    private Callback callback;

    public interface Callback{
        void gotUsersRecipe (ArrayList<SearchRecipe> GetRecipe);
        void gotUsersRecipeError (String message);
    }


    UserRecipeRequest(Context context){
        this.context = context;
    }
    public void searchUsersRecipe(Callback callback){
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
                String image_url = "http://static.food2fork.com/chickenandcashewnuts_89299_16x9986b.jpg";
                SearchRecipe recipe = new SearchRecipe(title, id, image_url);
                arrayList.add(recipe);
            }


            callback.gotUsersRecipe(arrayList);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
