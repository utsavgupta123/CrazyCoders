package com.example.catalog;

import android.content.Context;
import android.content.Intent;
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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    ArrayList<items> localDataSet;
    public TextView mtextview;
    public ArrayList<items> orders;
    public  int cost;
    private Context context;
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final Button button,ratingbtn,reviewbtn;
        private final ImageView imageview;
        private final Context context3;

        public ViewHolder(View view) {

            super(view);
            context3= view.getContext();
            textView = (TextView) view.findViewById(R.id.textView);
            button=(Button)view.findViewById(R.id.button);
            ratingbtn=(Button)view.findViewById(R.id.ratingbtn);
            reviewbtn=(Button)view.findViewById(R.id.button13);
            imageview=(ImageView)view.findViewById(R.id.imageView2);
            // Define click listener for the ViewHolder's View

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String p=button.getText().toString();
                           cost=cost+Integer.parseInt(p);
                            int pos = getAdapterPosition();
                            Toast.makeText(view.getContext(), Integer.toString(cost), Toast.LENGTH_SHORT).show();
                            mtextview.setText(Integer.toString(cost));
                           // items s1=new items(localDataSet.get(pos).name,localDataSet.get(pos).price);

                            orders.add(localDataSet.get(pos));
                        }
                    });

                    reviewbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(context3,Showreviews.class);

                            ArrayList<String> v3=localDataSet.get(getAdapterPosition()).reviews;
                            //  ArrayList<String>v3=new ArrayList<>();
                           // Toast.makeText(context3, "op", Toast.LENGTH_SHORT).show();
                            intent.putStringArrayListExtra("key6",v3);
                            context3.startActivity(intent);

                        }
                    });



        }

        public TextView getTextView() {
            return textView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *  @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     * @param textview5
     * @param ordered
     */
    public CustomAdapter(Context context, ArrayList<items>dataSet, TextView textview5, ArrayList<items> ordered) {
        localDataSet = dataSet;
        // textview5.setText(Integer.toString(cost));
        mtextview=textview5;
        orders=ordered;
        this.context=context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(context)
                .inflate(R.layout.text_row_item, viewGroup, false);
        ViewHolder vh=new ViewHolder(view);
//        context= viewGroup.getContext();

        return vh;
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element



        viewHolder.getTextView().setText(localDataSet.get(position).name);
        viewHolder.button.setText(Integer.toString(localDataSet.get(position).price));
        viewHolder.ratingbtn.setText("RATING-"+Float.toString(localDataSet.get(position).rating/localDataSet.get(position).count));
      //  Glide.with(context).load(localDataSet.get(position).imageurl).placeholder(android.R.drawable.progress_indeterminate_horizontal).error(android.R.drawable.stat_notify_error).into(viewHolder.imageview);
        Glide.with(context).load(localDataSet.get(position).imageurl).into(viewHolder.imageview);
       // viewHolder.imageview.getDrawable(R.drawable.ic_launcher_background);
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public  void filteredlist(ArrayList<items> filterlist)
    {
        localDataSet=filterlist;
        notifyDataSetChanged();
    }
    public  void settozero()
    {
        cost=0;
        mtextview.setText("0");
        orders.clear();
        notifyDataSetChanged();
    }
}