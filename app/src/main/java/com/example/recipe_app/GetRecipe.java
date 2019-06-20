package com.example.recipe_app;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GetRecipe implements Serializable{

    private String name, id, image, source, recipetag;
    private ArrayList ingredients;

    GetRecipe(String name, String id, String image, String source, String recipetag, ArrayList ingredients){
        this.name = name;
        this.id = id;
        this.image = image;
        this.source = source;
        this.ingredients = ingredients;
        this.recipetag = recipetag;
    }

    // Getters
    public String getName(){ return name; }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getSource() { return source; }

    public ArrayList getIngredients(){
        return ingredients;
    }

    public String getRecipetag() {
        return recipetag;
    }

}