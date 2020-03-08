package cs.bham.ac.uk.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipeDetailsActivity extends AppCompatActivity {

    //Declaration of class variables
    public static SharedPreferences sharedPref; //In the class definition

    private ArrayList<String> array_ingredients_list = new ArrayList<String>();
    private ArrayList<String> array_steps_list = new ArrayList<String>();

    private DetailsAdapter ingredientsAdapter;
    private DetailsAdapter stepsAdapter;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.LayoutManager layoutManager2;

    private int food_id;
    private String food_name;
    private int food_preparation_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        //Create DetailsAdapter objects
        ingredientsAdapter = new DetailsAdapter(array_ingredients_list);
        stepsAdapter = new DetailsAdapter(array_steps_list);

        //Link XML elements to activity
        RecyclerView ingredientsListView = (RecyclerView) findViewById(R.id.ingredients_list);
        RecyclerView stepsListView = (RecyclerView) findViewById(R.id.steps_list);

        //Disable nested scrolling to make scrolling smoother
        ingredientsListView.setNestedScrollingEnabled(false);
        stepsListView.setNestedScrollingEnabled(false);

        //Set layout managers for ingredients and steps list
        layoutManager = new LinearLayoutManager(this);
        layoutManager2 = new LinearLayoutManager(this);
        ingredientsListView.setLayoutManager(layoutManager);
        stepsListView.setLayoutManager(layoutManager2);

        //Set adapters (from DetailsAdapter) for ingredients and steps list
        ingredientsListView.setAdapter(ingredientsAdapter);
        stepsListView.setAdapter(stepsAdapter);

        //Fetch the food ID queried by the user
        food_id = getIntent().getIntExtra("id", 0);

        //Load the shared preferences named userSetPreferences
        sharedPref = this.getSharedPreferences("userSetPreferences",0);

        //Fetch the data
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET,
                "https://www.sjjg.uk/eat/recipe-details/" + food_id, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                                                populateList(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(getRequest);
    }//end onCreate

    //Method for populating the ingredients and steps list
    private void populateList(JSONObject entries)
    {
        array_ingredients_list.clear(); //clear ArrayList
        array_steps_list.clear();

        food_name = getIntent().getStringExtra("name");
        food_preparation_time = getIntent().getIntExtra("time", 0);
        String food_preparation_time_string = String.valueOf(food_preparation_time);

        TextView name = findViewById(R.id.name);
        TextView desc = findViewById((R.id.description));
        TextView preparationTime = findViewById(R.id.preparation_time);

        name.setText(food_name);
        preparationTime.setText("Preparation time: " + food_preparation_time_string + " minutes.");

        try
        {
            desc.setText(entries.getString("description"));
            JSONArray ingredients_JSON = entries.getJSONArray("ingredients");
            for(int i = 0; i < ingredients_JSON.length(); i++)
            {
                array_ingredients_list.add((i+1) + ". " + (String)ingredients_JSON.get(i));
            }

            JSONArray steps_JSON = entries.getJSONArray("steps");
            for(int i = 0; i < steps_JSON.length(); i++)
            {
                array_steps_list.add((i+1) + ". " + (String)steps_JSON.get(i));
            }
        }
        catch(JSONException err)
        {

        }
        ingredientsAdapter.notifyDataSetChanged();
        stepsAdapter.notifyDataSetChanged();
    }//end populateList method

    //Back button
    public void onBackButton(View view)
    {
        Intent intent = new Intent(this, SearchResultsActivity.class);
        startActivity(intent);
    }//end onBackButton method
}
