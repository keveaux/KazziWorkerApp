package creativebrands.kazziworker.Settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import creativebrands.kazziworker.LoginPackage.LoginActivity;

import creativebrands.kazziworker.R;
import creativebrands.kazziworker.WebViewPackage.WebViewActivity;
import com.google.firebase.auth.FirebaseAuth;


public class SettingsFragment extends Fragment {

    public static SwitchCompat availabilitySwitch;
    RelativeLayout update,signout;
    TextView updatetxt,signouttxt;
    private FirebaseAuth mAuth;
    public static SharedPreferences sharedPreferences;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=  inflater.inflate(R.layout.fragment_settings, container, false);

        Typeface font=Typeface.createFromAsset(getActivity().getAssets(),"Quicksand-Regular.ttf");

        update=v.findViewById(R.id.update);
        signout=v.findViewById(R.id.signout);

        updatetxt=v.findViewById(R.id.updatetxt);
        signouttxt=v.findViewById(R.id.signouttxt);

        updatetxt.setTypeface(font);
        signouttxt.setTypeface(font);

        mAuth = FirebaseAuth.getInstance();


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), WebViewActivity.class);
                startActivity(intent);
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        availabilitySwitch=v.findViewById(R.id.simpleSwitch);
        availabilitySwitch.setTypeface(font);
       sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getContext());


      availabilitySwitch.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {


          }
      });

      availabilitySwitch.setChecked(sharedPreferences.getBoolean("toggleButton", true));  //default is false




        // Inflate the layout for this fragment
        return  v;
    }

    private void signOut() {
        mAuth.signOut();
        Intent i = new Intent(getContext(), LoginActivity.class);
        startActivity(i);
    }


}
