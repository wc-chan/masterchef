package cs.bham.ac.uk.assignment3;

import android.content.Intent;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>
{
    //Store food items
    private ArrayList<FoodItem> array_list_food_items;

    //Creates the view (in this case, buttons) to put content into
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public Button button;
        public ViewHolder(Button v)
        {
            super(v);
            button = v;
        }
    }//end ViewHolder class

    //Class constructor
    public FoodAdapter(ArrayList<FoodItem> array_list_food_items)
    {
        this.array_list_food_items = array_list_food_items;
    }

    //Override onCreateViewHolder listener to paint the instance on the screen
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        ViewHolder vh = new ViewHolder(new Button(parent.getContext()));
        vh.button.setTextSize(TypedValue.COMPLEX_UNIT_SP,20f);
        return vh;
    }//end onCreateViewHolder


    //Override onBindViewHolder, bind data in array list to the views
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        holder.button.setText(array_list_food_items.get(position).toString());
        holder.button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), RecipeDetailsActivity.class);
                intent.putExtra("id", array_list_food_items.get(position).getFood_id());
                intent.putExtra("name", array_list_food_items.get(position).getFood_name());
                intent.putExtra("time", array_list_food_items.get(position).getTime());
                v.getContext().startActivity(intent);
            }
        });
    }//end onBindViewHolder

    //Return size of array list
    @Override
    public int getItemCount()
    {
        return array_list_food_items.size();
    }

}
