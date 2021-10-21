package com.example.catalog;

import android.os.Parcelable;

import java.io.Serializable;


public  class items implements Serializable
{

    public String name;
    public int price;
   // public String dishimg;
    items()
    {
        
    }
    items(String dish,int val)

    {
        this.name=dish;
        this.price=val;
        //this.dishimg=image;
    }

}
