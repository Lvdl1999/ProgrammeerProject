package com.example.recipe_app;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;

public class UsersRecipeRequest implements Response.Listener<JSONArray>, Response.ErrorListener {

    private Context context;
    private Callback callback;

    public interface Callback{
        void gotUsersRecipe (ArrayList<String> GetRecipe);
        void gotUsersRecipeError (String message);
    }


    public void getUsersRecipe(Callback callback){
        this.callback = callback;
//        String url.... van server
        String url = "nog niks";

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

    }
}
