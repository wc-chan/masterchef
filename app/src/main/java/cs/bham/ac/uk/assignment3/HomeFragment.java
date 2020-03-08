package cs.bham.ac.uk.assignment3;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    public HomeFragment() {
        // Required empty public constructor
    }

    //onCreateView method
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        System.out.println("Entered home fragment");

        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        //Implement button listener for buttons in fragments to override onClick method
        Button b = (Button) view.findViewById(R.id.search_button);
        b.setOnClickListener(this);
        return view;
    }//end onCreateView

    //Click button to enter the screen with search results
    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.search_button:
                Intent intent = new Intent(getActivity(), SearchResultsActivity.class);
                startActivity(intent);
        }
    }//end onClick

}
