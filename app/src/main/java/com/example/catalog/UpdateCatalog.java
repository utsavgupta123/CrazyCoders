package com.example.catalog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UpdateCatalog extends AppCompatActivity {


    RecyclerView recycleview3;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference root2=db.getReference().child("Menu");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_catalog);
        recycleview3=findViewById(R.id.recycleview3);
        recycleview3.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<items> arr=new ArrayList<>();

        CustomAdapter4 c4=new CustomAdapter4(this,arr);
        recycleview3.setAdapter(c4);


        root2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    items t3=dataSnapshot.getValue(items.class);
                    arr.add(t3);
                }
                c4.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}