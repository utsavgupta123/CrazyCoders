package com.example.catalog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NowUpdate extends AppCompatActivity {

    EditText editname,editprice;
    ImageView imageView4;
    Button button9;
    public ArrayList<items> pt=new ArrayList<items>();
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference root=db.getReference().child("Menu");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_update);

        editname=findViewById(R.id.editname);
        editprice=findViewById(R.id.editprice);
        imageView4=findViewById(R.id.imageView4);
        button9=findViewById(R.id.button9);
        //items ko= (ArrayList <items>)getIntent().getSerializableExtra("key3");
        pt = (ArrayList<items>) getIntent().getSerializableExtra("key3");

        editname.setText(pt.get(0).name);
        editprice.setText(Integer.toString(pt.get(0).price));
        Glide.with(this).load(pt.get(0).imageurl).into(imageView4);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newname=editname.getText().toString();
                int newprice=Integer.parseInt(editprice.getText().toString());
                String newurl=pt.get(0).imageurl;
                items newcatalog=new items(newname,newprice,newurl);
                String key=pt.get(0).key;
                newcatalog.key=key;
                root.child(key).setValue(newcatalog).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(@NonNull Void unused) {
                        Toast.makeText(NowUpdate.this, "CATALOG CHANGED", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(NowUpdate.this,MainActivity.class));
                    }
                });













            }
        });

    }
}