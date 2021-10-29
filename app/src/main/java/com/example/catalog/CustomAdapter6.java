package com.example.catalog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomAdapter6 extends RecyclerView.Adapter<CustomAdapter6.ViewHolder> {

    ArrayList<orderslist> localDataSet;
    Map<String, Float> feedbackmap;
    Map<String, String> feedbackreview;
    private Context context;
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView,ratingtext;
        private final RatingBar ratingbar;
        private final EditText feedbacktext;


        public ViewHolder(View view) {

            super(view);
            textView = (TextView) view.findViewById(R.id.itemname);
            ratingbar=(RatingBar)view.findViewById(R.id.ratingbar);
            ratingtext= (TextView) view.findViewById(R.id.ratingtext);
            feedbacktext=(EditText)view.findViewById(R.id.feedbacktext);
             ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                 @Override

                 public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                     if(rating ==0)
                     {
                         ratingtext.setText("Very Dissatisfied");

                     }
                     else if(rating==1)
                     {
                       ratingtext.setText("Dissatisfied");

                     }
                     else if(rating==2 || rating==3)
                     {
                         ratingtext.setText("Quite Good");

                     }

                     else if(rating==4)
                     {
                         ratingtext.setText("Good");

                     }
                     else if(rating==5)
                     {
                         ratingtext.setText("VERY GODD");

                     }
                     String kt=localDataSet.get(getAdapterPosition()).item;

                     feedbackmap.put(kt,rating);
                     String lt=feedbacktext.getText().toString();
                     feedbackreview.put(kt,lt);
                     Toast.makeText(context, lt, Toast.LENGTH_SHORT).show();

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

     */
    public CustomAdapter6(Context context, ArrayList<orderslist> dataSet,Map<String,Float> myMap,Map<String,String> myMap2 ) {
        localDataSet = dataSet;
        // textview5.setText(Integer.toString(cost));
        this.feedbackreview=myMap2;
        this.feedbackmap=myMap;
        this.context=context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(context)
                .inflate(R.layout.feedback_form, viewGroup, false);
        ViewHolder vh=new ViewHolder(view);
//        context= viewGroup.getContext();

        return vh;
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element



        viewHolder.getTextView().setText(localDataSet.get(position).item);

      //  feedbackreview.put(kt,lt);

        //  Glide.with(context).load(localDataSet.get(position).imageurl).placeholder(android.R.drawable.progress_indeterminate_horizontal).error(android.R.drawable.stat_notify_error).into(viewHolder.imageview);

        // viewHolder.imageview.getDrawable(R.drawable.ic_launcher_background);
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}