package com.example.recipe_app;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ResultsAdapter extends ArrayAdapter {

    private ArrayList<SearchRecipe> recipeList;
    private Context context;

    public ResultsAdapter(Context context, int layout, ArrayList<SearchRecipe> recipeList) {
        super(context, layout, recipeList);

        this.context = context;
        this.recipeList = recipeList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recipe_item, parent, false);
        }

        TextView title = convertView.findViewById(R.id.recipe_title);
        final ImageView image = convertView.findViewById(R.id.image_url);

        SearchRecipe currentrecipe = recipeList.get(position);

        String recipe_name = currentrecipe.getName();
        String recipe_image = String.valueOf(currentrecipe.getImage());

        title.setText(recipe_name);

        String currentString = recipe_image;
        String[] seperated = currentString.split(":");
        currentString = seperated[0] + "s:" + seperated[1];

        Picasso.Builder builder = new Picasso.Builder(parent.getContext());
        builder.listener(new Picasso.Listener() {

            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                Log.d("Picasso Error", "Errored out, hiding view" + exception);

            }
        });
        Picasso pic = builder.build();
        pic.load(currentString).into(image);


        return convertView;

    }



}
