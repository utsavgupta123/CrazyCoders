package com.example.catalog;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;


import com.google.firebase.messaging.FirebaseMessaging;

import android.os.Message;
import android.telephony.SmsManager;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.MissingResourceException;
import java.util.Objects;



public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.ViewHolder> {

    private ArrayList<Model> localDataSet;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();

    private DatabaseReference root=db.getReference().child("Orders");
    HashSet<String>placed;
    private Context context;



    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
       private EditText mname;
       private Button button;
        private final Context context1;


        public ViewHolder(View view) {

            super(view);
            context1= view.getContext();
            textView = (TextView) view.findViewById(R.id.textView4);
            mname=(EditText)view.findViewById(R.id.mname);
            button=(Button)view.findViewById(R.id.button3);
            // Define click listener for the ViewHolder's View

//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String l=localDataSet.get(getAdapterPosition()).idg;
//                    placed.add(l);
//                    deletedata(placed);
//                    localDataSet.remove(getAdapterPosition());
//                     notifyDataSetChanged();
//                    Toast.makeText(context, "ITEM ADDED", Toast.LENGTH_SHORT).show();
//                }
//            });









        }

        public TextView getTextView() {
            return textView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet   String[] containing the data to populate views to be used
     *                  by RecyclerView.
     *
     */
    public CustomAdapter2(Context context, ArrayList<Model> dataSet, HashSet<String>place) {
        localDataSet = dataSet;
        // textview5.setText(Integer.toString(cost));
         placed=place;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(context)
                .inflate(R.layout.pending_orders, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);


//        context= viewGroup.getContext();

        return vh;
    }

//    @NonNull
//    @org.jetbrains.annotations.NotNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
//        return null;
//    }

    // Replace the contents of a view (invoked by the layout manager)

    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

          //  viewHolder.setIsRecyclable(false);
      //  viewHolder.guideLinearLayout.removeAllViews();
           viewHolder.mname.setText(localDataSet.get(position).customer);
           String temp="";
           for(int i=0;i<localDataSet.get(position).ids.size();i++)
           {
               String str=" ITEM- "+localDataSet.get(position).ids.get(i).item+" Quantity "+" "+localDataSet.get(position).ids.get(i).Quantity+" ";
               temp+=str+"\n";
           }
         int idx=viewHolder.getAdapterPosition();
        viewHolder.getTextView().setText(temp);
        viewHolder.button.setText(localDataSet.get(position).orderstatus);

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key=localDataSet.get(position).idg;
               DatabaseReference dataref=FirebaseDatabase.getInstance().getReference().child("Orders").child(key);
 //              dataref.removeValue();
                 Model kk=localDataSet.get(position);
                 kk.orderstatus="ORDER READY";

                 dataref.setValue(kk);
                 String currtoken=localDataSet.get(position).token;
                 sendSMS();

//                root.child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                   @Override
//                    public void onComplete(@NonNull  Task<Void> task) {
//
//////                            Intent intent=new Intent(context1,Shoporders.class);
//////
//////
//////
//////                            context1.startActivity(intent);
//                        if(task.isSuccessful()) {
//                           Toast.makeText(context, "data deleted", Toast.LENGTH_SHORT).show();
//                           localDataSet.remove(position);
//                           notifyItemRemoved(position);
//                           // notifyDataSetChanged();
//                           sendSMS();
//                           notifyItemRangeChanged(position,localDataSet.size());
//
//                        }
//
//                       // notifyItemRemoved(getAdapterPosition());
//                    }
//                });


            }
        });










    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    private void sendSMS()
    {
      //  String phoneNo=phone.getText().toString().trim();
     //  String SMS=message.getText().toString().trim();
        String SMS="YOUR ORDER IS READY PLEASE PICK IT UP!!! THANKS SMART RESTAURANT";
        String phoneNo="917985671748";
        try {
            SmsManager smsManager=SmsManager.getDefault();

            smsManager.sendTextMessage(phoneNo,null,SMS,null,null);
            Toast.makeText(context, "MESSAGE IS SENT", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(context, "FAILED TO SENT", Toast.LENGTH_SHORT).show();
        }
    }








}






