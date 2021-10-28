package com.example.catalog;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;


public  class items implements Serializable
{

    public String name;
    public int price;
    public String key="";
    public Float rating=0.0F;
    public Integer count=1;
    public String imageurl;
    public String recipieurl;
    public ArrayList<String>reviews;
    items()
    {
        
    }
    items(String dish,int val,String imageurlurl)

    {
        this.name=dish;
        this.price=val;
        this.imageurl=imageurlurl;

        //this.dishimg=image;
    }

}
