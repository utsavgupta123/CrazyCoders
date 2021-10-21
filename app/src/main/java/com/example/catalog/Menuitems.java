package com.example.catalog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Menuitems extends AppCompatActivity {

    EditText mprice,mdishname;
    Button button5;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference root=db.getReference().child("Menu");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuitems);
        mprice=findViewById(R.id.mprice);
        mdishname=findViewById(R.id.mdishname);
        button5=findViewById(R.id.button5);

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t1=mdishname.getText().toString();
                int price=Integer.valueOf(mprice.getText().toString());
                items d=new items(t1,price);
                root.push().setValue(d).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull  Task<Void> task) {
                        Toast.makeText(Menuitems.this, "CATALOG UPDATED", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Menuitems.this,MainActivity.class));
                    }
                });
            }
        });
    }
}