package com.example.kelvincb.kazziworkerapp.fragments.acceptedRequests;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AcceptedJsonDownloader {

    private final Context c;
    private AcceptedRequestRecyclerAdapter adapter;
    RequestQueue requestQueue;

    public AcceptedJsonDownloader(Context c) {
        this.c = c;
    }

    public void retrieveRequestInfo(String URL, final RecyclerView listView, final TextView tv, final Animation animationUp, final Animation animationDown, final ProgressBar myProgressBar) {

        final ArrayList<AcceptedGetterSetterClass> requestList = new ArrayList<>();

        myProgressBar.setIndeterminate(true);
        myProgressBar.setVisibility(View.VISIBLE);

        requestQueue = Volley.newRequestQueue(c);

        JsonObjectRequest jor=new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                if(response.isNull("results")){
                    myProgressBar.setVisibility(View.GONE);
                    tv.setVisibility(View.VISIBLE);

                }
                try{
                    tv.setVisibility(View.GONE);


                    AcceptedGetterSetterClass acceptedGetterSetterClass;
                    JSONArray ja = response.getJSONArray("results");
                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject jsonObject = ja.getJSONObject(i);


                        String user_name=jsonObject.getString("user_name");
                        String phone_number=jsonObject.getString("user_phone_number");
                        String date=jsonObject.getString("mdate");
                        String time=jsonObject.getString("mtime");
                        String job_description=jsonObject.getString("jobDescription");
                        String landmark=jsonObject.getString("landmark");
                        String url=jsonObject.getString("image");
                        String location=jsonObject.getString("location");
                        String currentdate=jsonObject.getString("currentdate");
                        String status=jsonObject.getString("status");

                        acceptedGetterSetterClass=new AcceptedGetterSetterClass();
                        acceptedGetterSetterClass.setUser_name(user_name);
                        acceptedGetterSetterClass.setUser_phone_number(phone_number);
                        acceptedGetterSetterClass.setDate(date);
                        acceptedGetterSetterClass.setTime(time);
                        acceptedGetterSetterClass.setJobdescription(job_description);
                        acceptedGetterSetterClass.setLandmark(landmark);
                        acceptedGetterSetterClass.setImage_url(url);
                        acceptedGetterSetterClass.setLocation(location);
                        acceptedGetterSetterClass.setDatesent(currentdate);
                        acceptedGetterSetterClass.setStatus(status);

                        if(acceptedGetterSetterClass.getStatus().equals("1")){
                            requestList.add(acceptedGetterSetterClass);

                        }




                    }

                    adapter = new AcceptedRequestRecyclerAdapter(c,animationUp,animationDown,requestList);
                    listView.setAdapter(adapter);
                    myProgressBar.setVisibility(View.GONE);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error.toString().equals("com.android.volley.TimeoutError")){
                            Toast.makeText(c, "check your internet connection" , Toast.LENGTH_SHORT).show();

                        }
                    }
                }

        );
        requestQueue.add(jor);

    }
}
