package com.example.catalog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Submitfeedback extends AppCompatActivity {

    private RecyclerView recyclefeedback;
    private Button submit;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference root1=db.getReference().child("Menu");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitfeedback);
        recyclefeedback=findViewById(R.id.recyclefeedback);
        submit=findViewById(R.id.button12);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<orderslist> object = (ArrayList<orderslist>) args.getSerializable("ARRAYLIST");
        Map<String, Float> myMap = new HashMap<String, Float>();
//
//
        recyclefeedback.setLayoutManager(new LinearLayoutManager(this));
        CustomAdapter6 c=new CustomAdapter6(this,object,myMap);

        recyclefeedback.setAdapter(c);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Submitfeedback.this, Float.toString(myMap.get("Paneer Tikka")), Toast.LENGTH_SHORT).show();

                root1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot snapshot)
                    {
                        for(DataSnapshot dataSnapshot:snapshot.getChildren())
                        {
                            items t3=dataSnapshot.getValue(items.class);
                            if(myMap.containsKey(t3.name))
                            {
                                items kp=new items();
                                kp=t3;
                                kp.rating=myMap.get(t3.name);
                                root1.child(t3.key).setValue(kp);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {

                    }
                });









            }
        });







    }
}