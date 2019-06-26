/**
 * GetRecipe
 * The GetRecipe model class for all fields of a (get)userrecipe
 * Created by Levy van der Linde (june 2019)
 * Minor Programmeren
 */


package com.example.recipe_app;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GetRecipe implements Serializable{

    private String name, id, image, source;
    private ArrayList ingredients;

    GetRecipe(String name, String id, String image, String source, ArrayList ingredients){
        this.name = name;
        this.id = id;
        this.image = image;
        this.source = source;
        this.ingredients = ingredients;
    }

    // Getters for all fields
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
}