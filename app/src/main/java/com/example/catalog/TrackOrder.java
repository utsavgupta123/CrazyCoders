package com.example.catalog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.os.Build;
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



    public static final int PRIMARY_FOREGROUND_NOTIF_SERVICE_ID = 1001;

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
                   // Toast.makeText(TrackOrder.this, Integer.toString(tapcount[0])+"op"+keyult, Toast.LENGTH_SHORT).show();
                    if(m1.idg.equals(keyult))
                    {
                       // Toast.makeText(TrackOrder.this, status, Toast.LENGTH_SHORT).show();
                        kp="Order Status-"+m1.orderstatus+"\n"+"Order No"+Integer.toString(tapcount[0]);
                       // Toast.makeText(TrackOrder.this, kp, Toast.LENGTH_SHORT).show();
                        textView9.setText(kp);
                        if(tapcount[0]==1)
                        {
                            submitfeedback.setVisibility(View.VISIBLE);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                                String id = "_channel_01";
                                int importance = NotificationManager.IMPORTANCE_LOW;
                                NotificationChannel mChannel = new NotificationChannel(id, "notification", importance);
                                mChannel.enableVibration(true);
                                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

                                mChannel.enableLights(true);

                                Notification notification = new Notification.Builder(TrackOrder.this, id)
                                        .setSmallIcon(R.drawable.iconnotify)
                                        .setContentTitle("FOODIE")
                                        .setContentText("YOUR FOOD IS READY!!! PICK IT UP")

                                        .setLights(0xff0000ff, 300, 1000) // blue color
                                        .setWhen(System.currentTimeMillis())

                                        .build();
                                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                if (mNotificationManager != null) {
                                    mNotificationManager.createNotificationChannel(mChannel);
                                    mNotificationManager.notify(PRIMARY_FOREGROUND_NOTIF_SERVICE_ID, notification);
                                }



                            }




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