package com.example.recipe_app;

import java.io.Serializable;

public class SearchRecipe implements Serializable {
    private String name, id, image;

    SearchRecipe(String name, String id, String image){
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

}
