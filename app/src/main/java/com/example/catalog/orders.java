package com.example.catalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class orders extends AppCompatActivity {
    private RecyclerView recycle2;
    TextView textview2;
    ArrayList<items> p=new ArrayList<items>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        recycle2=findViewById(R.id.recycle2);
        textview2=findViewById(R.id.textView2);


        ArrayList<items> p = (ArrayList<items>) getIntent().getSerializableExtra("key");
        recycle2.setLayoutManager(new LinearLayoutManager(this));
        CustomAdapter1 c=new CustomAdapter1(this,p);
        recycle2.setAdapter(c);
        String cost=getIntent().getStringExtra("key2");
        textview2.setText("TOTAL COST:"+cost);
    }
}