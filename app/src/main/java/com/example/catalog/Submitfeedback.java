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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Submitfeedback extends AppCompatActivity {

    private RecyclerView recyclefeedback;
    private Button submit;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference root1=db.getReference().child("Menu");
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String customerId;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitfeedback);
        recyclefeedback=findViewById(R.id.recyclefeedback);
        submit=findViewById(R.id.button12);
        mAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<orderslist> object = (ArrayList<orderslist>) args.getSerializable("ARRAYLIST");
        Map<String, Float> myMap = new HashMap<String, Float>();
        Map<String,String>myMap2=new HashMap<String,String>();


        recyclefeedback.setLayoutManager(new LinearLayoutManager(this));
        CustomAdapter6 c=new CustomAdapter6(this,object,myMap,myMap2);

        recyclefeedback.setAdapter(c);

        customerId=mAuth.getCurrentUser().getUid();
        DocumentReference documentReference=fStore.collection("customers").document(customerId);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(@NonNull  DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists())
                {
                   name=documentSnapshot.getString("fName");
                }

            }
        });





        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                root1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot snapshot) {



                        for(DataSnapshot dataSnapshot:snapshot.getChildren())
                        {
                            items t3=dataSnapshot.getValue(items.class);

                            if(myMap.containsKey(t3.name))
                            {

                                items kp=new items();

                                kp=t3;
                                if(t3.reviews.size()==0)
                                {
                                    kp.reviews=new ArrayList<>();
                                }
                                Float f=t3.rating;
                                kp.rating=myMap.get(t3.name)+f;
                                kp.count=t3.count+1;

                               // Toast.makeText(Submitfeedback.this, myMap2.get(t3.name)+customerId, Toast.LENGTH_SHORT).show();
                                kp.reviews.add(Float.toString(kp.count)+"."+name+'\n'+myMap2.get(t3.name));
                                root1.child(t3.key).setValue(kp);
                            }
                        }
                    }



                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {

                    }
                });


                Intent newIntent=new Intent(Submitfeedback.this,CustomerDashboard.class);

                newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(newIntent);




            }
        });







    }
}