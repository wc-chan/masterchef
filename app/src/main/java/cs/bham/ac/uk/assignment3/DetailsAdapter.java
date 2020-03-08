package cs.bham.ac.uk.assignment3;

import android.content.Intent;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder>
{
    //Store ingredients and steps
    private ArrayList<String> list;

    //Creates the view (in this case, RecyclerView) to put content into
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textView;
        public ViewHolder(TextView v)
        {
            super(v);
            textView = v;
        }
    }//end ViewHolder

    //Constructor
    public DetailsAdapter(ArrayList<String> list)
    {
        this.list = list;
    }

    //Override onCreateViewHolder listener to paint the instance on the screen
    @Override
    public DetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        ViewHolder vh = new ViewHolder(new TextView(parent.getContext()));
        vh.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,15f);
        return vh;
    }//end OnCreateViewHolder


    //Override onBindViewHolder, bind data in array list to the views
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        holder.textView.setText(list.get(position).toString());
        
    }//end onBindViewHolder

    //Return size of array list
    @Override
    public int getItemCount()
    {
        return list.size();
    }

}
