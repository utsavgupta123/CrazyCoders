package com.example.catalog;

import java.io.Serializable;
import java.util.ArrayList;

public class Model implements Serializable
{
    public String  customer;
    public String idg="45";
    public ArrayList<orderslist> ids;
    public String token;
    public String orderstatus ="PENDING";
    Model()
    {

    }


    Model(String customer,ArrayList<orderslist>k,String py)
    {

        this.customer=customer;
        this.ids=k;
        this.idg=py;
    }

}
