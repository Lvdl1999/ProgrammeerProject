package com.example.recipe_app;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class APIGetRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Callback callback;
    private Context context;

    // Describes what the callback should get back and it's methods will show how
    public interface Callback {

        void gotRecipe(ArrayList<GetRecipe> recipe);
        void gotRecipeError(String message);

    }

    // Constructor
    APIGetRequest(Context context){
        this.context = context;
    }

    public void getRecipes(APIGetRequest.Callback callback, String id){

        this.callback = callback;
        String url = "https://www.food2fork.com/api/get?key=8413a4deaec24af1f8da381c9f6719a3&rId="+id;

        RequestQueue requestRecipe = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        requestRecipe.add(jsonObjectRequest);
    }



    // Something went wrong --> Errormessage
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotRecipeError(error.getMessage());
    }

    // Everything went well, JSON object was returned
    @Override
    public void onResponse(JSONObject response) {

        System.out.println("get request gelukt");

    }

}
