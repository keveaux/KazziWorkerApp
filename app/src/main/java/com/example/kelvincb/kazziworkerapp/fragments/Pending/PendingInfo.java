package com.example.kelvincb.kazziworkerapp.fragments.Pending;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kelvincb.kazziworkerapp.MainActivity;
import com.example.kelvincb.kazziworkerapp.R;
import com.example.kelvincb.kazziworkerapp.myRequestHandler;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.RequestHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class PendingInfo extends AppCompatActivity {

    TextView name,nametxt,date,datetxt,time,timetxt,jobdesc,jobdesctxt,landmark,landmarktxt;
    String user_pno;
    Button accepted,rejected;
    private InterstitialAd mInterstitialAd;
    String id,formattedDate;
    public static final String UPDATE_URL = "http://104.248.124.210/android/iKazi/phpFiles/rejectrequest.php";

    public static final String ACCEPT_URL = "http://104.248.124.210/android/iKazi/phpFiles/acceptrequest.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_info);

        //get current date
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c);

        Toast.makeText(this, ""+formattedDate, Toast.LENGTH_SHORT).show();

        name=findViewById(R.id.textView_name);
        nametxt=findViewById(R.id.name);
        date=findViewById(R.id.textView_date);
        datetxt=findViewById(R.id.date);
        time=findViewById(R.id.textView_time);
        timetxt=findViewById(R.id.time);
        jobdesc=findViewById(R.id.textView_jobdesc);
        jobdesctxt=findViewById(R.id.jobDescription);
        landmark=findViewById(R.id.textView_landmark);
        landmarktxt=findViewById(R.id.landmark);
        rejected=findViewById(R.id.rejectbtn);
        accepted=findViewById(R.id.acceptbtn);

        Typeface font=Typeface.createFromAsset(getAssets(),"RobotoSlab-Bold.ttf");
        name.setTypeface(font);
        nametxt.setTypeface(font);
        date.setTypeface(font);
        datetxt.setTypeface(font);
        time.setTypeface(font);
        timetxt.setTypeface(font);
        jobdesc.setTypeface(font);
        jobdesctxt.setTypeface(font);
        landmark.setTypeface(font);
        landmarktxt.setTypeface(font);
        rejected.setTypeface(font);
        accepted.setTypeface(font);

        mInterstitialAd = new InterstitialAd(PendingInfo.this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");



        Bundle bundle = this.getIntent().getExtras();

        if (bundle != null) {
            //ObtainBundleData in the object
            final String name = bundle.getString("name");
            String time = bundle.getString("time");
            String date = bundle.getString("date");
            String jobDescription = bundle.getString("jobdesc");
            String landmark=bundle.getString("landmark");
            user_pno=bundle.getString("pno");
            id=bundle.getString("id");


            nametxt.setText(name);
            timetxt.setText(time);
            datetxt.setText(date);
            timetxt.setText(time);
            jobdesctxt.setText(jobDescription);
            landmarktxt.setText(landmark);


            accepted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int greenColorValue = Color.parseColor("#00ff00");

                    showDialog(PendingInfo.this,"accept "+name+"'s request",R.drawable.accepted,greenColorValue,1);
                }
            });

            rejected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int greenColorValue = Color.parseColor("#FFF60606");
                    showDialog(PendingInfo.this,"reject "+name+"'s request",R.drawable.cancel,greenColorValue,2);
                }
            });

            //Do something here if data  received
        }
        else
        {
            //Do something here if data not received
        }

    }

    public void showDialog(Activity activity, String msg, int images, int color, final int whichbutton){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog);



        TextView text = dialog.findViewById(R.id.text_dialog);
        text.setText(msg);

        ImageView imageView=dialog.findViewById(R.id.popupimage);
        imageView.setImageResource(images);
        imageView.setBackgroundColor(color);

        Typeface font1=Typeface.createFromAsset(getAssets(),"RobotoSlab-Bold.ttf");
        text.setTypeface(font1);

        Button dialogButton =  dialog.findViewById(R.id.btn_dialog_yes);
        Button dialogbutton_no=dialog.findViewById(R.id.btn_dialog_no);

        dialogButton.setTypeface(font1);
        dialogbutton_no.setTypeface(font1);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(whichbutton==1){
                    acceptRequest();
                }else {
                    rejectRequest();
                }
                dialog.dismiss();

            }
        });

        dialogbutton_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });

        dialog.show();

    }

    public void rejectRequest(){

        class rejectRequest extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            myRequestHandler rh=new myRequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(PendingInfo.this, "Loading...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equals("Successfully Uploaded")){
                    Intent intent=new Intent(PendingInfo.this, MainActivity.class);
                    startActivity(intent);
                }


            }

            @Override
            protected String doInBackground(Void... params) {


                HashMap<String,String> data = new HashMap<>();

                data.put("id", id);
                data.put("pno",user_pno);
                data.put("date",formattedDate);



                String result = rh.sendPostRequest(UPDATE_URL,data);


                return result;
            }
        }
        rejectRequest rejectRequest=new rejectRequest();
        rejectRequest.execute();

    }


    public void acceptRequest(){


        class acceptRequest extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            myRequestHandler rh=new myRequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(PendingInfo.this, "Loading...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();

                if(s.equals("Successfully Uploaded")){
                    Toast.makeText(PendingInfo.this, "complete!!! check your accepted requests", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(PendingInfo.this, MainActivity.class);
                    startActivity(intent);
                }


            }

            @Override
            protected String doInBackground(Void... params) {


                HashMap<String,String> data = new HashMap<>();

                data.put("id", id);
                data.put("pno",user_pno);
                data.put("date",formattedDate);



                String result = rh.sendPostRequest(ACCEPT_URL,data);


                return result;
            }
        }
        acceptRequest acceptRequest=new acceptRequest();
        acceptRequest.execute();

    }



}
