package com.example.recipe_app;
import android.content.Context;
import android.telecom.Call;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.recipe_app.GetRecipe;
import com.example.recipe_app.ResultsActivity;
import com.example.recipe_app.UsersRecipeRequest;

import org.json.JSONArray;

import java.util.ArrayList;

public class UserGetRequest implements Response.Listener<JSONArray>, Response.ErrorListener {


    private Context context;
    private Callback callback;

    public interface Callback{
        void gotUsersRec (ArrayList<GetRecipe> GetRecipe);
        void gotUsersRecError (String message);
    }



    UserGetRequest(Context context){
        this.context = context;
    }
    public void getUsersRecipe(Callback callback){
        this.callback = callback;

        String url = "https://ide50-lvanderlinde.legacy.cs50.io:8080/searchRecipe";


        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, this, this);
        queue.add(jsonArrayRequest);

    }






    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotUsersRecError(error.getMessage());
    }

    @Override
    public void onResponse(JSONArray response) {

        System.out.println("test voor user recipe getrequest");

    }
}
