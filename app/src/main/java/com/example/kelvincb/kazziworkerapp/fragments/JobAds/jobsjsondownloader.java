package com.example.kelvincb.kazziworkerapp.fragments.JobAds;

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
import com.example.kelvincb.kazziworkerapp.fragments.acceptedRequests.AcceptedGetterSetterClass;
import com.example.kelvincb.kazziworkerapp.fragments.acceptedRequests.AcceptedRequestRecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class jobsjsondownloader {
    private final Context c;
    private jobAdsAdapter adapter;
    RequestQueue requestQueue;

    public jobsjsondownloader(Context c) {
        this.c = c;
    }

    public void retrieveRequestInfo(String URL, final RecyclerView listView, final TextView tv, final ProgressBar myProgressBar) {

        final ArrayList<jobadsgettersetters> requestList = new ArrayList<>();

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


                    jobadsgettersetters GetterSetterClass;
                    JSONArray ja = response.getJSONArray("results");

                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject jsonObject = ja.getJSONObject(i);


                        String business_name=jsonObject.getString("company_name");
                        String image_url=jsonObject.getString("image_url");
                        String job=jsonObject.getString("job");
                        String number_of_people=jsonObject.getString("number_of_people");
                        String job_desciption=jsonObject.getString("job_description");
                        String location=jsonObject.getString("Location");
                        String link=jsonObject.getString("link_url");




                        GetterSetterClass=new jobadsgettersetters();
                        GetterSetterClass.setBussiness_name(business_name);
                        GetterSetterClass.setImage_url(image_url);
                        GetterSetterClass.setJob(job);
                        GetterSetterClass.setJob_description(job_desciption);
                        GetterSetterClass.setPeople(number_of_people);
                        GetterSetterClass.setLocation(location);
                        GetterSetterClass.setLinkurl(link);



                        requestList.add(GetterSetterClass);

                    }

                    adapter = new jobAdsAdapter(c,requestList);
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
