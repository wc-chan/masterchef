package cs.bham.ac.uk.assignment3;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreferencesFragment extends Fragment implements View.OnClickListener {

    private SharedPreferences sharedPref; //In the class definition

    public PreferencesFragment() {
        // Required empty public constructor
    }


    //Class variables
    private Spinner meal_preference_spinner;
    private Spinner order_preference_spinner;

    //onCreateView method
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_preferences, container, false);

        //Declare shared preferences and give it a key so the key can be used across other activities
        sharedPref = view.getContext().getSharedPreferences("userSetPreferences",0);

        //Implement button listener for buttons in fragments to override onClick method
        Button b = (Button) view.findViewById(R.id.preferences_button);
        b.setOnClickListener(this);

        //Link XML to fragment
        meal_preference_spinner = view.findViewById(R.id.meal_spinner);
        order_preference_spinner = view.findViewById(R.id.order_spinner);

        //To ensure the spinner choices retain itself even after switching between activities
        meal_preference_spinner.setSelection(sharedPref.getInt("meal_preference_spinner_id", 0));
        order_preference_spinner.setSelection(sharedPref.getInt("order_preference_spinner_id", 0));

        return view;
    }//end onCreateView

    //onClick method
    @Override
    public void onClick(View view)
    {
        //Start to store values into shared preferences
        SharedPreferences.Editor editor = sharedPref.edit();

        //Assign the position of the user's choices into the keys
        editor.putInt("order_preference_spinner_id", order_preference_spinner.getSelectedItemPosition());
        editor.putInt("meal_preference_spinner_id", meal_preference_spinner.getSelectedItemPosition());

        //Commit changes to the shared preferences
        editor.commit();

        //Toast created as a confirmation message
        Toast.makeText(getActivity(),"Preferences confirmed!", Toast.LENGTH_SHORT).show();
    }//end onClick
}
