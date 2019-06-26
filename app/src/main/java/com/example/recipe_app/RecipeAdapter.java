package com.example.recipe_app;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeAdapter extends ArrayAdapter {
    ArrayList<String> ingredients_list;

    public RecipeAdapter(Context context, int resource, ArrayList<String> ingredients_list){
        super(context, resource, ingredients_list);
        this.ingredients_list = ingredients_list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // A new view must be inflated if it doesn't exist already
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ingredients_adapter, parent, false);
        }
        // Setting ingredients to ingredients adapter
        String ingredient = ingredients_list.get(position);
        TextView ingredient_text = convertView.findViewById(R.id.ingredients_textview);
        ingredient_text.setText(ingredient);

        return convertView;
    }
}
