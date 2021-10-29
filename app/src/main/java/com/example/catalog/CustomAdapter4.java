package com.example.catalog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter4 extends RecyclerView.Adapter<CustomAdapter4.ViewHolder> {

    ArrayList<items> localDataSet;



    private Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public  class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final Button button8,recipie;
        private  final ImageView imageView3;
        private final Context context1;

        public ViewHolder(View view) {
            super(view);
            context1= view.getContext();
            // Define click listener for the ViewHolder's View
             button8=(Button)view.findViewById(R.id.button8);
             textView = (TextView) view.findViewById(R.id.textView6);
             imageView3=(ImageView)view.findViewById(R.id.imageView3);
             recipie=(Button)view.findViewById(R.id.recipie) ;
            button8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context1,NowUpdate.class);
                    items t=localDataSet.get(getAdapterPosition());
                    ArrayList<items> v3=new ArrayList<>();
                    v3.add(t);
                    intent.putExtra("key3",v3);
                    context1.startActivity(intent);
                }
            });


             recipie.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     String s= localDataSet.get(getAdapterPosition()).recipieurl;
                     Intent videoclk= new Intent(Intent.ACTION_VIEW,
                             Uri.parse(s));
                     context1.startActivity(videoclk);

                 }
             });


        }

        public TextView getTextView() {
            return textView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CustomAdapter4(Context context, ArrayList<items> dataSet) {
        localDataSet = dataSet;
        this.context = context;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.updatethecatalog, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {


        viewHolder.getTextView().setText(localDataSet.get(position).name+ "\n"+"PRICE"+localDataSet.get(position).price);
        Glide.with(context).load(localDataSet.get(position).imageurl).into(viewHolder.imageView3);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
