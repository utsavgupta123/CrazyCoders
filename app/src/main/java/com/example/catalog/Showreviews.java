package com.example.catalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Showreviews extends AppCompatActivity {


    RecyclerView reviewsrecycleview;
    public ArrayList<String> pt=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showreviews);

        reviewsrecycleview=findViewById(R.id.reviewsrecycleview);
        pt = getIntent().getExtras().getStringArrayList("key6");
        //pt.add("UTSABasnjsdjknsjkkfnjksdfnjkdfnvjk");
        reviewsrecycleview.setLayoutManager(new LinearLayoutManager(this));

        CustomAdapter7 c=new CustomAdapter7(this,pt);

       reviewsrecycleview.setAdapter(c);




    }
}