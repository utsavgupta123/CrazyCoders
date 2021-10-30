package com.example.catalog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NotifyALL extends AppCompatActivity {

    EditText mtitle,mbody;
    Button sendbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_all);
        mtitle=findViewById(R.id.mtitle);
        mbody=findViewById(R.id.mbody);
        sendbtn=findViewById(R.id.sendbtn);




        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=mtitle.getText().toString();
                String body=mbody.getText().toString();
                FcmNotificationsSender notificationsSender=new FcmNotificationsSender("/topics/all",title,body,getApplicationContext(),NotifyALL.this);
                notificationsSender.SendNotifications();

            }
        });

    }
}