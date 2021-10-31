package com.example.catalog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    RecyclerView recycleview1;
    public TextView textview5,viewcart;
  //  items s1=new items("Paneers Masala",50,"");
  //  items s2=new items("Butter Chicken",150,"");
  //  items s3=new items("Chicken Biryani",550,"");
   // items s4=new items("Hyderabadi Chicken",530);
  //  items s7=new items("Hyderabadi Chicken",530);
   // String []arr={s1.name ,s2.name,s3.name,s4.name};

//    public  int cost;
//
//    ArrayList<items> ordered=new ArrayList<items>();
//    private FirebaseDatabase db=FirebaseDatabase.getInstance();
//    private DatabaseReference root1=db.getReference().child("Menu");
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(MainActivity.this,MainActivity2.class));
        finish();
     // startActivity(new Intent(MainActivity.this,TrackOrder.class));
      //  startActivity(new Intent(MainActivity.this,Menuitems.class));
//        recycleview1=findViewById(R.id.recyclerView);
//        textview5=findViewById(R.id.textView5);
//        recycleview1.setLayoutManager(new LinearLayoutManager(this));
//        viewcart=findViewById(R.id.viewcart);







        ArrayList<items>arr=new ArrayList<>();

      //    arr.add(s1);
       //   arr.add(s2);
       //   arr.add(s3);
//           arr.add(s1);
//          arr.add(s2);
//         arr.add(s3);
//        arr.add(s1);
//        arr.add(s2);
//        arr.add(s3);

//        CustomAdapter c=new CustomAdapter(this,arr,textview5,ordered);
//        recycleview1.setAdapter(c);
//
//         textview5.setText(Integer.toString(cost));
//
//
//        viewcart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,orders.class);
//                String str=textview5.getText().toString();
//                intent.putExtra("key", ordered);
//                intent.putExtra("key2",str);
//                startActivity(intent);
//
//            }
//        });
//
//
//        root1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull  DataSnapshot snapshot)
//            {
//                for(DataSnapshot dataSnapshot:snapshot.getChildren())
//                {
//                    items t3=dataSnapshot.getValue(items.class);
//                    arr.add(t3);
//                }
//                c.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull  DatabaseError error) {
//
//            }
//        });

    }
}

