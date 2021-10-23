package com.example.catalog;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentResolver;
import android.content.Intent;

import android.net.Uri;

import android.os.Bundle;

import android.view.View;

import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Menuitems extends AppCompatActivity {

    EditText mprice,mdishname;
    Button button5,button7;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference root=db.getReference().child("Menu");
  //  private StorageReference reference= FirebaseStorage.getInstance().getReference();
    Uri imageuri;
    Uri newuri;
    ImageView imageview;
    public String kp="";
    ActivityResultLauncher<String>mGetContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuitems);

        mprice=findViewById(R.id.mprice);
        mdishname=findViewById(R.id.mdishname);
        button5=findViewById(R.id.button5);
        button7=findViewById(R.id.button7);
        imageview=findViewById(R.id.imageView);

         mGetContent=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
             @Override
             public void onActivityResult(Uri result) {
                 if(result!=null) {
                     imageuri=result;
                     imageview.setImageURI(result);
                 }
             }
         });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t1=mdishname.getText().toString();
                int price=Integer.valueOf(mprice.getText().toString());

                 if(imageuri!=null)
                 {
                    upload(t1,price);
//                     items d=new items(t1,price,kp);
//                     root.push().setValue(d).addOnCompleteListener(new OnCompleteListener<Void>() {
//                         @Override
//                         public void onComplete(@NonNull Task<Void> task) {
//                             Toast.makeText(Menuitems.this, "CATALOG UPDATED", Toast.LENGTH_SHORT).show();
//                             startActivity(new Intent(Menuitems.this, MainActivity.class));
//
//                         }
//                     });


                 }
                 else
                 {
                     Toast.makeText(Menuitems.this, "PLEASE UPLOAD IMAGE", Toast.LENGTH_SHORT).show();
                 }
               //  kp=newuri.toString();







            }


        });


        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContent.launch("image/*");
            }
        });





    }




    public void  upload (String item1,int cost){

        if (imageuri != null){


            final StorageReference fileRef  = FirebaseStorage.getInstance().getReference(System.currentTimeMillis()+getFileExtension(imageuri) );

            fileRef.putFile(imageuri)
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                            fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri)
                                {
                                   kp=uri.toString();
                                   newuri=uri;
                                    items d=new items(item1,cost,kp);
                                    root.push().setValue(d).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(Menuitems.this, "CATALOG UPDATED", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(Menuitems.this, MainActivity.class));

                                        }
                                    });
                                    Toast.makeText(Menuitems.this, newuri.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });


        }


    }














    public  String getFileExtension(Uri uri)
    {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        //Toast.makeText(this, "getextension", Toast.LENGTH_SHORT).show();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));


    }



}