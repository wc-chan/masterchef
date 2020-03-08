package cs.bham.ac.uk.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchResultsActivity extends AppCompatActivity {

    //Class variables
    public static SharedPreferences sharedPref;
    private ArrayList<FoodItem> array_list_food_items = new ArrayList<FoodItem>();
    private FoodAdapter foodAdpt;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        //Create new FoodAdapter object
        foodAdpt = new FoodAdapter(array_list_food_items);

        //Create new RecyclerView object - listView
        RecyclerView listView = (RecyclerView) findViewById(R.id.foodList);

        //Create and set a layout manager for the search results
        layoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);

        //Set an adapter for the search results
        listView.setAdapter(foodAdpt);

        //Retrieve shared preferences configured from the preferences fragment
        sharedPref = this.getSharedPreferences("userSetPreferences",0);

        //To determine which url to query
        String url = "https://www.sjjg.uk./eat/food-items/?";
        int order_preference_spinner_id = sharedPref.getInt("order_preference_spinner_id", 9);
        int meal_preference_spinner_id = sharedPref.getInt("meal_preference_spinner_id", 9);

        if(order_preference_spinner_id==1)
        {
            url = url + "ordering=" + "asc&";
        }
        else if(order_preference_spinner_id==2)
        {
            url = url + "ordering=" + "desc&";
        }

        if(meal_preference_spinner_id==1)
        {
            url = url + "prefer=" + "Breakfast";
        }
        else if(meal_preference_spinner_id==2)
        {
            url = url + "prefer=" + "Lunch";
        }
        else if(meal_preference_spinner_id==3)
        {
            url = url + "prefer=" + "Dinner";
        }

        //Request query
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        populateList(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                    }
                }
        );
        requestQueue.add(getRequest);
    }//end onCreate

    //Method for populateList
    private void populateList(JSONArray items)
    {
        array_list_food_items.clear(); //clear ArrayList

        try
        {

            for(int i = 0; i < items.length(); i++)
            {
                JSONObject jo = items.getJSONObject(i);
                array_list_food_items.add(new FoodItem(jo.getString("name"), jo.getInt("id"), jo.getInt("time")));
            }
        }
        catch(JSONException err)
        {

        }
        foodAdpt.notifyDataSetChanged();
    }//end populateList method

    //Back button method
    public void onBackButton(View view)
    {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }//end onBackButton method
}
