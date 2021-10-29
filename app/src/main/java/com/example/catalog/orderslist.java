package com.example.catalog;

import java.io.Serializable;

public class orderslist implements Serializable
{
    public String item;
     public int Quantity;
     orderslist()
     {

     }
    orderslist(String k,int t)
    {
        this.item=k;
        this.Quantity=t;
    }
}
