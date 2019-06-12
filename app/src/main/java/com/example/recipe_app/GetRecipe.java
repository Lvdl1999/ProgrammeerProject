package com.example.recipe_app;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GetRecipe implements Serializable{

    private String name, id, image;
    private ArrayList ingredients;

    GetRecipe(String name, String id, String image, ArrayList ingredients){
        this.name = name;
        this.id = id;
        this.image = image;
    }


    // Getters
    public String getName(){
        return name;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public ArrayList getIngredients(){
        return ingredients;
    }

}