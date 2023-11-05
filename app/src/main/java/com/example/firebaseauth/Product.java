package com.example.firebaseauth;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Product {
    public String id;
    public String name;
    public String price;
    public Product(){

    }
    public Product(String id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getProductName(){
        return name;
    }
    public String getPrice(){
        return price;
    }

    public String getId(){
        return id;
    }
}
