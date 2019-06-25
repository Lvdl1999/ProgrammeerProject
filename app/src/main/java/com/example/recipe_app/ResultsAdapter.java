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
        this.recipeList = recipeList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recipe_item, parent, false);
        }

        // Saving textview and imageview that will show a recipe
        TextView title = convertView.findViewById(R.id.recipe_title);
        final ImageView image = convertView.findViewById(R.id.image_url);

        // For every recipe it's name and image_url are saved
        SearchRecipe currentrecipe = recipeList.get(position);
        String recipe_name = currentrecipe.getName();
        String recipe_image = String.valueOf(currentrecipe.getImage());

        // Set recipes name
        title.setText(recipe_name);

        // Before the url can be set to the image, it is updated from a 'http' to 'https' link
        String currentString = recipe_image;
        String[] seperated = currentString.split(":");
        currentString = seperated[0] + "s:" + seperated[1];

        // In case setting the image went wrong this will return an error
        Picasso.Builder builder = new Picasso.Builder(parent.getContext());
        builder.listener(new Picasso.Listener() {

            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                Log.d("Picasso Error", "Errored out, hiding view" + exception);

            }
        });

        // Set recipes image to corresponding imageview
        // Only set the recipe url if it's an API recipe because they receive a corresponding image
        // User recipes get a standard image to be able to distinguish them
        if (currentrecipe.getRecipetag()) {
            Picasso pic = builder.build();
            pic.load(currentString).into(image);
        }

        return convertView;
    }
}
