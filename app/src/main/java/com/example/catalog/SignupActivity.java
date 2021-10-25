package com.example.catalog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    EditText cEmailBox,cPasswordBox,sEmailBox,sPasswordBox,cName;
    Button cLoginBtn,cSignupBtn,sLoginBtn,sSignupBtn;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String customerId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        cName=findViewById(R.id.cName);
        cEmailBox=findViewById(R.id.cEmailBox);
        cPasswordBox=findViewById(R.id.cPasswordBox);
        sEmailBox=findViewById(R.id.sEmailBox);
        sPasswordBox=findViewById(R.id.sPasswordBox);
        cLoginBtn=findViewById(R.id.cLoginBtn);
        cSignupBtn=findViewById(R.id.cCreateBtn);
        sLoginBtn=findViewById(R.id.sLoginBtn);
        sSignupBtn=findViewById(R.id.sCreateBtn);

        mAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        cSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCustomer();
            }
        });

        sSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createShopkeeper();
            }
        });

        cLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
            }
        });

        sLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
            }
        });
    }
    private  void createCustomer(){
        String name=cName.getText().toString();
        String email=cEmailBox.getText().toString();
        String pass=cPasswordBox.getText().toString();

        if(TextUtils.isEmpty(name)){
            cName.setError("Full Name cannot be empty");
            cName.requestFocus();
        }
        else if(TextUtils.isEmpty(email)){
            cEmailBox.setError("Email cannot be empty");
            cEmailBox.requestFocus();
        }else if(TextUtils.isEmpty(pass)){
            cPasswordBox.setError("Password cannot be empty");
            cPasswordBox.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignupActivity.this,"User Signup Successful",Toast.LENGTH_SHORT).show();
                        customerId=mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference=fStore.collection("customers").document(customerId);
                        Map<String,Object> customer=new HashMap<>();
                        customer.put("fName",name);
                        customer.put("email",email);
                        documentReference.set(customer);
                        startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                    }else{
                        Toast.makeText(SignupActivity.this,"Signup Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private  void createShopkeeper(){
        String email=sEmailBox.getText().toString();
        String pass=sPasswordBox.getText().toString();

        if(TextUtils.isEmpty(email)){
            sEmailBox.setError("Email cannot be empty");
            sEmailBox.requestFocus();
        }else if(TextUtils.isEmpty(pass)){
            sPasswordBox.setError("Password cannot be empty");
            sPasswordBox.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignupActivity.this,"Shopkeeper Signup Successful",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                    }else{
                        Toast.makeText(SignupActivity.this,"Signup Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

