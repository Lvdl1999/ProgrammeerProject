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

    // Describes what the callback should get back and it's methods will show how
    public interface Callback {

        void gotRecipe(ArrayList<SearchRecipe> recipe);
        void gotRecipeError (String message);
    }

    // Constructor
    APISearchRequest(Context context){
        this.context = context;
    }

    public void getAPIRecipes(Callback callback, String key, int page){
        // key is variable to the searchword of the user
        // page is used as a variable to get the next or previous 30 results
        String url =  "https://www.food2fork.com/api/search?key=8413a4deaec24af1f8da381c9f6719a3&q="+key+"&page="+page;
        this.callback = callback;
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
                SearchRecipe recipe = new SearchRecipe(title, id, image);
                arrayList.add(recipe);
            }

            callback.gotRecipe(arrayList);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
