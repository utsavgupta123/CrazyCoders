package com.example.catalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;

public class orders extends AppCompatActivity implements PaymentResultListener {
    private RecyclerView recycle2;
    TextView textview2;
    Button button2;
    ArrayList<items> p=new ArrayList<items>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        recycle2=findViewById(R.id.recycle2);
        textview2=findViewById(R.id.textView2);
        button2=findViewById(R.id.button2);

        ArrayList<items> p = (ArrayList<items>) getIntent().getSerializableExtra("key");
        recycle2.setLayoutManager(new LinearLayoutManager(this));
        CustomAdapter1 c=new CustomAdapter1(this,p);
        recycle2.setAdapter(c);
        String cost=getIntent().getStringExtra("key2");
        textview2.setText("TOTAL COST:"+cost);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startPayment(cost);
            }
        });
    }

    public void startPayment(String amt) {

        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_odYsNwFtPWXq1p");
        /**
         * Set your logo here
         */
       // checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */

          int k=Integer.parseInt(amt)*100;
          String l=Integer.toString(k);
        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "SMART RESTAURANT");
            options.put("description", "PAY TO RESTAURANT");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
           // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", l );//pass amount in currency subunits
//            options.put("prefill.email", "gaurav.kumar@example.com");
//            options.put("prefill.contact","9988776655");
//            JSONObject retryObj = new JSONObject();
//            retryObj.put("enabled", true);
//            retryObj.put("max_count", 4);
//            options.put("retry", retryObj);

            checkout.open(this, options);

        } catch(Exception e) {
            //Log.e(TAG, "Error in starting Razorpay Checkout", e);
            Toast.makeText(this, "Error in starting Razorpay Checkout", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPaymentSuccess(String s)
    {
        Toast.makeText(this, "PAYMENT SUCESSS", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "PAYMENT FAILED", Toast.LENGTH_SHORT).show();

    }
}