package com.example.catalog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter5 extends RecyclerView.Adapter<CustomAdapter5.ViewHolder> {

    ArrayList<orderslist> localDataSet;


     private String temp2="";
    private Context context;
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {

            super(view);
            textView = (TextView) view.findViewById(R.id.textView11);

            // Define click listener for the ViewHolder's View





        }

        public TextView getTextView() {
            return textView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *  @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.

     */
    public CustomAdapter5(Context context, ArrayList<orderslist>dataSet) {
        localDataSet = dataSet;
        // textview5.setText(Integer.toString(cost));

        this.context=context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(context)
                .inflate(R.layout.your_orders, viewGroup, false);
        ViewHolder vh=new ViewHolder(view);
//        context= viewGroup.getContext();

        return vh;
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element


        temp2=Integer.toString(position)+"."+localDataSet.get(position).item+"-"+Integer.toString(localDataSet.get(position).Quantity)+"\n";
        viewHolder.getTextView().setText(temp2);

        // viewHolder.imageview.getDrawable(R.drawable.ic_launcher_background);
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
