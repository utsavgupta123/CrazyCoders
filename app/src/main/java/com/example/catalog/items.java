package com.example.catalog;

import android.os.Parcelable;

import java.io.Serializable;


public  class items implements Serializable
{

    public String name;
    public int price;
    public String key="";
    public String imageurl;
    public String recipieurl;
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
