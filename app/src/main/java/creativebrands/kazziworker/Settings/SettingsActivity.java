package creativebrands.kazziworker.Settings;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static creativebrands.kazziworker.Settings.SettingsFragment.availabilitySwitch;
import static creativebrands.kazziworker.Settings.SettingsFragment.sharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import creativebrands.kazziworker.R;
import creativebrands.kazziworker.fetchWorkerInfo;

import java.util.HashMap;
import java.util.Map;

public class SettingsActivity extends AppCompatActivity {

    private String UPDATE_URL="http://104.248.124.210/android/iKazi/phpFiles/updateavailability.php";
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar=findViewById(R.id.toolbar);
        TextView title=findViewById(R.id.settingstitle);
        final FrameLayout layout=findViewById(R.id.settings_frame_container);
        final ProgressBar progressBar=findViewById(R.id.settings_progressbar);
        progressBar.setVisibility(View.VISIBLE);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);


        Typeface font=Typeface.createFromAsset(getAssets(),"Quicksand-Bold.ttf");
        title.setTypeface(font);


        final fetchWorkerInfo fetchWorkerID=new fetchWorkerInfo(getApplicationContext());
        fetchWorkerID.fetchData();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                id=fetchWorkerID.getWorker_id();
                progressBar.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);


            }
        }, 2000);

        Fragment fragment=new SettingsFragment();
        loadFragment(fragment);



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("toggleButton", availabilitySwitch.isChecked());
                editor.apply();

                onBackPressed();
            }
        });




    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.settings_frame_container, fragment,"ONE");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void uploadData(final String id, final String availability) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPDATE_URL,
                new com.android.volley.Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(SettingsActivity.this, ""+response, Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();


                params.put("id", id);
                params.put("availability", availability);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }


    @Override
    public void onBackPressed() {

        String availability;
        Boolean checkavailability=sharedPreferences.getBoolean("toggleButton",false);

        if(checkavailability.equals(true)){
            availability="1";
            uploadData(id,availability);


        }else {
            availability="0";
            uploadData(id,availability);
        }
        this.finish();
    }
}
