package cs.bham.ac.uk.assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
{
    //Declaration of fragments
    Fragment someFragment;
    PreferencesFragment pF = new PreferencesFragment();
    HomeFragment hF = new HomeFragment();

    //Fragment manager to help us add/remove fragments
    FragmentManager fm = getSupportFragmentManager();

    //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ((BottomNavigationView)findViewById(R.id.bottom_navi)).setOnNavigationItemSelectedListener(this);

        //For rotation in other fragments
        if (savedInstanceState == null)
        {
            fm.beginTransaction().add(R.id.frag_frame, hF).commit();
        }

    }//end onCreate

    //Listener for BottomNavigationView
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //Start the transaction
        FragmentTransaction fT = fm.beginTransaction();

        //Depending on menu item/fragment selected
        switch (item.getItemId())
        {
            case R.id.menu_home:
                fT.replace(R.id.frag_frame, hF);
                break;
            case R.id.menu_preferences:
                fT.replace(R.id.frag_frame, pF);
                break;
        }

        //Apply changes
        fT.commit();

        return true;
    }//end onNavigationItemSelected

}
