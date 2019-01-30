package com.example.kelvincb.kazziworkerapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class fetchWorkerId {

    Context context;
    String worker_id;

    public fetchWorkerId(Context context) {
        this.context = context;
    }

    public String getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(String worker_id) {
        this.worker_id = worker_id;
    }

    public String fetchID(){
        final fetchPhoneNumber fetchPhoneNumber=new fetchPhoneNumber(context);
        fetchPhoneNumber.mynumber();

        String MyURL = "http://104.248.124.210/android/iKazi/phpFiles/fetchworkerID.php";

        StringRequest stringRequest=new StringRequest(Request.Method.POST, MyURL, new com.android.volley.Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                setWorker_id(response);
            }
        }
                , new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,error+"",Toast.LENGTH_LONG).show();

            }
        }){

            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();


                params.put("phone",fetchPhoneNumber.getPhone_no());

                return params;
            }

        };

        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

        return worker_id;

    }
}
