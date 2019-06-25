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

public class APISearchRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    private Callback callback;
    private Context context;
    private boolean isAPI = true;

    // Describes what the callback should get back and it's methods will show how
    public interface Callback {

        void gotRecipe(ArrayList<SearchRecipe> recipe);
        void gotRecipeError (String message);
    }

    // Constructor for APISearchRequest that accepts a Context type parameter
    APISearchRequest(Context context){
        this.context = context;
    }

    // This method attempts to retrieve categories from the API
    public void getAPIRecipes(Callback callback, String searchword, int page){
        // Searchword is a variable to use the given searchword by the user
        // Page is used as a variable to get the next or previous 30 results
        String url =  "https://www.food2fork.com/api/search?key=8413a4deaec24af1f8da381c9f6719a3&q="+searchword+"&page="+page;
        this.callback = callback;
        RequestQueue requestRecipe = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        requestRecipe.add(jsonObjectRequest);
    }

    // This method is called when the request goes as expected
    @Override
    public void onResponse(JSONObject response) {

        ArrayList arrayList = new ArrayList();
        JSONArray jsonArray;

        try {
            jsonArray = response.getJSONArray("recipes");
            JSONObject jsonObject;

            for (int i = 0; i < jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String id = jsonObject.getString("recipe_id");
                String image = jsonObject.getString("image_url");

                // An API recipe gets the tag 'isAPI' being true to underestimate from API recipes
                Boolean recipe_tag = isAPI;

                SearchRecipe recipe = new SearchRecipe(title, id, image, recipe_tag);
                arrayList.add(recipe);
            }
            callback.gotRecipe(arrayList);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // This method is called when something about the request goes wrong
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotRecipeError(error.getMessage());
    }
}
