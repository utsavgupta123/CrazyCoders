package com.example.catalog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;

public class Shoporders extends AppCompatActivity
{
     RecyclerView recycleview2;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference root=db.getReference().child("Orders");
    ArrayList<Model> ord;
    HashSet<String>keymap;
        Button button4;


    //ArrayList<String>ord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        Toast.makeText(this, "WELCOME", Toast.LENGTH_SHORT).show();
         setContentView(R.layout.activity_shoporders);
        recycleview2=findViewById(R.id.recycleview2);
       // recycleview2.hasFixedSize();
       // recycleview2.setItemViewCacheSize(0);
        recycleview2.setHasFixedSize(true);
        button4=findViewById(R.id.button4);
        ord=new ArrayList<>();
        keymap=new HashSet<>();
        recycleview2.setLayoutManager(new LinearLayoutManager(this));

        CustomAdapter2 c=new CustomAdapter2(this,ord,keymap);

        recycleview2.setAdapter(c);


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
//
                  Model model=dataSnapshot.getValue(Model.class);
                  ord.add(model);
                }
               c.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        button4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Shoporders.this,Menuitems.class));
            }
        });







    }
}