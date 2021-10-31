package com.example.catalog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerDashboard extends AppCompatActivity {

    Button logout,clearall;
    FirebaseAuth mAuth;
    RecyclerView recycleview1;
    public TextView textview5,viewcart;
    public  int cost;
   EditText searchbar;

    ArrayList<items> ordered=new ArrayList<items>();
    ArrayList<items>arr=new ArrayList<>();
    CustomAdapter c;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference root1=db.getReference().child("Menu");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        logout=findViewById(R.id.logout);
        FirebaseMessaging.getInstance().subscribeToTopic("all");

        mAuth=FirebaseAuth.getInstance();
        storeToken();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(CustomerDashboard.this, LoginActivity.class));
            }
        });

        recycleview1=findViewById(R.id.recyclerView);
        textview5=findViewById(R.id.textView5);
        recycleview1.setLayoutManager(new LinearLayoutManager(this));
        viewcart=findViewById(R.id.viewcart);
        searchbar=findViewById(R.id.searchbar);
        clearall=findViewById(R.id.clearbtn);

         c=new CustomAdapter(this,arr,textview5,ordered);
        recycleview1.setAdapter(c);

        textview5.setText(Integer.toString(cost));


        viewcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CustomerDashboard.this,orders.class);
                String str=textview5.getText().toString();
                intent.putExtra("key", ordered);
                intent.putExtra("key2",str);
                startActivity(intent);

            }
        });

         clearall.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v)
             {
                 c.settozero();

             }
         });





        root1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    items t3=dataSnapshot.getValue(items.class);
                    arr.add(t3);
                }
                c.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

       searchbar.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

           }

           @Override
           public void afterTextChanged(Editable s) {

               filter(s.toString());

           }
       });

    }


    private void filter(String text)
    {
        ArrayList<items> filterlist=new ArrayList<>();
        for(items t1:arr)
        {
            if(t1.name.toLowerCase().contains(text.toLowerCase()))
            {
                filterlist.add(t1);
            }
        }

        c.filteredlist(filterlist);
    }

    public void storeToken()
    {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            //Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        String usermail=mAuth.getCurrentUser().getEmail();
                        Map<String,Object> customertoken=new HashMap<>();
                        customertoken.put("email",usermail);
                        customertoken.put("token",token);
                        String currkey=FirebaseAuth.getInstance().getCurrentUser().getUid();
                        FirebaseDatabase.getInstance().getReference().child("Tokens").child(currkey).setValue(customertoken);
                        Toast.makeText(CustomerDashboard.this, token, Toast.LENGTH_SHORT).show();
                        // Log and toast
                       // String msg = getString(R.string.msg_token_fmt, token);
                       // Log.d(TAG, msg);

                    }
                });
    }

}