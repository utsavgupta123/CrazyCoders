package com.example.catalog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class TrackOrder extends AppCompatActivity {
    private RecyclerView recycle3;
    TextView textView9;
    Button checkstatus,submitfeedback;
    String keyult="";
    ArrayList<Model>pk=new ArrayList<>();

     final int[] tapcount={0};
     String status="";
    private FirebaseDatabase db=FirebaseDatabase.getInstance();

    private DatabaseReference root4=db.getReference().child("Orders");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_order);
        recycle3=findViewById(R.id.recyclerView3);
        textView9=findViewById(R.id.textView9);
        checkstatus=findViewById(R.id.button11);
        submitfeedback=findViewById(R.id.feedbackbtn);
//        pk = (ArrayList<Model>) getIntent().getSerializableExtra("key6");
//        Toast.makeText(this, pk.get(0).customer, Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<orderslist> object = (ArrayList<orderslist>) args.getSerializable("ARRAYLIST");
        Toast.makeText(this, object.get(0).item, Toast.LENGTH_SHORT).show();
        recycle3.setLayoutManager(new LinearLayoutManager(this));
        keyult=intent.getExtras().getString("keykey");
        CustomAdapter5 c=new CustomAdapter5(this,object);

        recycle3.setAdapter(c);
       submitfeedback.setVisibility(View.GONE);
        checkstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getpositionandstatus();

            }
        });

        submitfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TrackOrder.this,Submitfeedback.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)object);
                intent.putExtra("BUNDLE",args);
                startActivity(intent);
            }
        });








    }



    private void   getpositionandstatus()
    {
        tapcount[0]=0;

        root4.addValueEventListener(new ValueEventListener() {

            private String kp="";
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Model m1=dataSnapshot.getValue(Model.class);
                    tapcount[0]+=1;
                    Toast.makeText(TrackOrder.this, Integer.toString(tapcount[0])+"op"+keyult, Toast.LENGTH_SHORT).show();
                    if(m1.idg.equals(keyult))
                    {
                        Toast.makeText(TrackOrder.this, status, Toast.LENGTH_SHORT).show();
                        kp="Order Status-"+m1.orderstatus+"\n"+"Order No"+Integer.toString(tapcount[0]);
                        Toast.makeText(TrackOrder.this, kp, Toast.LENGTH_SHORT).show();
                        textView9.setText(kp);
                        if(tapcount[0]==1)
                        {
                            submitfeedback.setVisibility(View.VISIBLE);
                        }
                        tapcount[0]=0;
                        break;

                    }
                }
                return;

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

    }
}