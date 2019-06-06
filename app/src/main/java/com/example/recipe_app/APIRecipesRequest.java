package com.example.recipe_app;

import android.content.Context;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class APIRecipesRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    private Callback callback;
    private Context context;

    // describes what the callback should get back and it's methods will show how
    public interface Callback {

        void gotRecipe(ArrayList<SearchRecipe> recipe);
        void gotRecipeError (String message);

    }


// constructor that accepts ... parameter

    APIRecipesRequest(Context context){
        this.context = context;
    }



}