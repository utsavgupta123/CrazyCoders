package com.example.catalog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    EditText cEmailBox,cPasswordBox,sEmailBox,sPasswordBox;
    Button cLoginBtn,cSignupBtn,sLoginBtn,sSignupBtn;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cEmailBox=findViewById(R.id.cEmailBox);
        cPasswordBox=findViewById(R.id.cPasswordBox);
        sEmailBox=findViewById(R.id.sEmailBox);
        sPasswordBox=findViewById(R.id.sPasswordBox);
        cLoginBtn=findViewById(R.id.cLoginBtn);
        cSignupBtn=findViewById(R.id.cCreateBtn);
        sLoginBtn=findViewById(R.id.sLoginBtn);
        sSignupBtn=findViewById(R.id.sCreateBtn);

        mAuth=FirebaseAuth.getInstance();

        cSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });

        sSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });
        cLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCustomer();
            }
        });

        sLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginShopkeeper();
            }
        });

    }
    private void loginCustomer(){
        String email=cEmailBox.getText().toString();
        String pass=cPasswordBox.getText().toString();

        if(TextUtils.isEmpty(email)){
            cEmailBox.setError("Email cannot be empty");
            cEmailBox.requestFocus();
        }else if(TextUtils.isEmpty(pass)){
            cPasswordBox.setError("Password cannot be empty");
            cPasswordBox.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this,"Customer Login Successful",Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(LoginActivity.this,CustomerDashboard.class));
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this,"Login Error"+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void loginShopkeeper(){
        String email=sEmailBox.getText().toString();
        String pass=sPasswordBox.getText().toString();

        if(TextUtils.isEmpty(email)){
            sEmailBox.setError("Email cannot be empty");
            sEmailBox.requestFocus();
        }else if(TextUtils.isEmpty(pass)){
            sPasswordBox.setError("Password cannot be empty");
            sPasswordBox.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this,"Shopkeeper Login Successful",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this,Shoporders.class));
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this,"Login Error"+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}