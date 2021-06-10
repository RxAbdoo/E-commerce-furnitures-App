package com.example.furnitures2;

import com.google.firebase.database.Exclude;

public class Categories {
    String id;
    String name;
    String image;
    String price;
    @Exclude
    public boolean isfav= false;

    public Categories() {
    }


    public Categories(String id, String name, String image, String price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
