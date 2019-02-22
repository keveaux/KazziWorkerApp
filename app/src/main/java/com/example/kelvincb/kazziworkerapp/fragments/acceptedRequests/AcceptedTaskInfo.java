package com.example.kelvincb.kazziworkerapp.fragments.acceptedRequests;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kelvincb.kazziworkerapp.PicassoClient;
import com.example.kelvincb.kazziworkerapp.R;

public class AcceptedTaskInfo extends AppCompatActivity {

    String user_pno;
    TextView name,timetv,time,datetv,date,descriptiontv,description,locationtv,location,landmarktv,landmark;
    ImageView user_image;
    FloatingActionButton fab;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accepted_task_info);

        toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        name=findViewById(R.id.user_name);
        timetv=findViewById(R.id.textView_time);
        time=findViewById(R.id.time);
        datetv=findViewById(R.id.textView_date);
        date=findViewById(R.id.date);

        descriptiontv=findViewById(R.id.textView_jobdesc);
        description=findViewById(R.id.jobDescription);
        locationtv=findViewById(R.id.textView_location);
        location=findViewById(R.id.location);
        landmarktv=findViewById(R.id.textView_landmark);
        landmark=findViewById(R.id.landmark);

        user_image=findViewById(R.id.user_profile_image);

        fab=findViewById(R.id.fab);

        Typeface font=Typeface.createFromAsset(getAssets(),"RobotoSlab-Bold.ttf");
        name.setTypeface(font);
        date.setTypeface(font);
        datetv.setTypeface(font);
        time.setTypeface(font);
        timetv.setTypeface(font);
        descriptiontv.setTypeface(font);
        description.setTypeface(font);
        landmark.setTypeface(font);
        landmarktv.setTypeface(font);
        locationtv.setTypeface(font);
        location.setTypeface(font);






        Bundle bundle = this.getIntent().getExtras();

        if (bundle != null) {
            //ObtainBundleData in the object
            final String sname = bundle.getString("name");
            String stime = bundle.getString("time");
            String sdate = bundle.getString("date");
            String sjobDescription = bundle.getString("jobdesc");
            String slandmark = bundle.getString("landmark");
            user_pno = bundle.getString("pno");
            String url = bundle.getString("url");
            String slocation=bundle.getString("location");



            name.setText(sname);
            time.setText(stime);
            description.setText(sjobDescription);
            date.setText(sdate);
            landmark.setText(slandmark);
            location.setText(slocation);

            PicassoClient.loadImage(url,user_image);



        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", user_pno, null));
                startActivity(intent);
            }
        });
        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
