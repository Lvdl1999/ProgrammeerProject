package com.example.recipe_app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class UsersRecipeAdapter extends ArrayAdapter {

    ArrayList<String> usersrecipes_list;

    public UsersRecipeAdapter(Context context, int resource, ArrayList<String> usersrecipes_list) {
        super(context, resource, usersrecipes_list);
        this.usersrecipes_list = usersrecipes_list;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {

        // Setting highscore adapter layout to high score adapter

        // Putting the scores in the textviews

        return convertview;
    }



}
