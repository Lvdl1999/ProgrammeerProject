/**
 * UserSearchRequest
 * This class handles the SearchRequest which filters the database on the users given searchword
 * Created by Levy van der Linde (june 2019)
 * Minor Programmeren
 */


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

public class UserSearchRequest implements Response.Listener<JSONArray>, Response.ErrorListener {

    private Context context;
    private Callback callback;
    private String keyword;

    // Describes what the callback should get back and it's methods will show how
    public interface Callback{
        void gotUsersRecipe (ArrayList<SearchRecipe> GetRecipe);
        void gotUsersRecipeError (String message);
    }

    // Constructor for UserGetRequest that accepts a Context type parameter
    UserSearchRequest(Context context){
        this.context = context;
    }
    public void getUsersRecipe(Callback callback, String id){
        this.callback = callback;
        this.keyword = id;
        String url = "https://ide50-lvanderlinde.legacy.cs50.io:8080/searchRecipe";

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

            // In case the API request didn't find any recipe with the users searchword
            if (response.length() == 0){
                callback.gotUsersRecipeError("Couldn't find this ingredient in the user DataBase");
            }

            // Getting back a response from DB and saving the data into Strings, Boolean and Array
            for (int i = 0; i < response.length(); i++){
                jsonObject = response.getJSONObject(i);
                String title = jsonObject.getString("title");
                String id = jsonObject.getString("id");
                String ingredients = jsonObject.getString("ingredients");

                // An users recipe goes with the tag 'isAPI' being false to underestimate
                Boolean recipetag = false;

                String image = "http://static.food2fork.com/chickenandcashewnuts_89299_16x9986b.jpg";
                SearchRecipe recipe = new SearchRecipe(title, id, image, recipetag);

                // Performing the searchwords filter method locally for the userrecipes
                // The rester database unfortunately doesn't come with this option for online search
                // The keywords are comma seperated and compared to ingredients from the database
                String[] keyword_seperate = keyword.split(",");
                boolean contain = false;
                for(int j = 0; j <keyword_seperate.length; j++) {
                    if (ingredients.contains(keyword_seperate[j])) {
                        contain = true;
                    }
                    else {
                        contain = false;
                        break;
                    }
                }
                // If the searchword matches a recipes ingredient it will be shown in the results
                if(contain) {
                    arrayList.add(recipe);
                }
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