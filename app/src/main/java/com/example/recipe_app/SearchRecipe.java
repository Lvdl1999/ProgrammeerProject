package com.example.recipe_app;

import java.io.Serializable;

public class SearchRecipe implements Serializable {
    private String name, id, image;
    private Boolean recipetag;

    SearchRecipe(String name, String id, String image, Boolean recipetag){
        this.name = name;
        this.id = id;
        this.image = image;
        this.recipetag = recipetag;
    }

    // Getters for all fields
    public String getName(){
        return name;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public Boolean getRecipetag() {
        return recipetag;
    }
}
