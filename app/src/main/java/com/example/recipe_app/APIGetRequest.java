package com.example.recipe_app;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;

public class APIGetRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Callback callback;
    private Context context;


    // Describes what the callback should get back and it's methods will show how
    public interface Callback {

        void gotRecipe(GetRecipe get_recipe);
        void gotRecipeError(String message);
    }

    // Constructor for APIGetRequest that accepts a Context type parameter
    APIGetRequest(Context context){
        this.context = context;
    }
    // This method attempts to retrieve categories from the API
    public void getRecipes(APIGetRequest.Callback callback, String id){

        this.callback = callback;
        String url = "https://www.food2fork.com/api/get?key=e2923f01061e85612e462bb2883ed039&rId="+id;

        RequestQueue requestRecipe = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        requestRecipe.add(jsonObjectRequest);
    }

    // This method is called when the request goes as expected
    @Override
    public void onResponse(JSONObject response) {

        System.out.println("get request gelukt");

        try {
            JSONObject jsonObject = response.getJSONObject("recipe");

            String title = jsonObject.getString("title");
            String id = jsonObject.getString("recipe_id");
            String image = jsonObject.getString("image_url");
            String source = jsonObject.getString("source_url");


            ArrayList ingredients_list = new ArrayList();
            JSONArray ingredients_json = jsonObject.getJSONArray("ingredients");

            for (int j = 0; j < ingredients_json.length(); j++) {
                ingredients_list.add(ingredients_json.getString(j));
            }

            GetRecipe get_recipe = new GetRecipe(title, id, image, source, ingredients_list);

            callback.gotRecipe(get_recipe);

            System.out.println("get request info is binnen");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // This method is called when something about the request goes wrong
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotRecipeError(error.getMessage());
    }

}