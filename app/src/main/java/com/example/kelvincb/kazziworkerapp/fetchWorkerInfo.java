package com.example.kelvincb.kazziworkerapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class fetchWorkerInfo {

    Context context;
    String worker_id,worker_name,phone_no,occupation,skillSet,rating,no_of_ratings,imageUrl,jobsdone,
            service_one,service_two,service_three,service_four,service_five;

    public fetchWorkerInfo(Context context) {
        this.context = context;
    }

    public String getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(String worker_id) {
        this.worker_id = worker_id;
    }

    public Context getContext() {
        return context;
    }

    public String getWorker_name() {
        return worker_name;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getSkillSet() {
        return skillSet;
    }

    public String getRating() {
        return rating;
    }

    public String getNo_of_ratings() {
        return no_of_ratings;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getJobsdone() {
        return jobsdone;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getService_one() {
        return service_one;
    }

    public String getService_two() {
        return service_two;
    }

    public String getService_three() {
        return service_three;
    }

    public String getService_four() {
        return service_four;
    }

    public String getService_five() {
        return service_five;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setWorker_name(String worker_name) {
        this.worker_name = worker_name;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setSkillSet(String skillSet) {
        this.skillSet = skillSet;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setNo_of_ratings(String no_of_ratings) {
        this.no_of_ratings = no_of_ratings;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setJobsdone(String jobsdone) {
        this.jobsdone = jobsdone;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public void setService_one(String service_one) {
        this.service_one = service_one;
    }

    public void setService_two(String service_two) {
        this.service_two = service_two;
    }

    public void setService_three(String service_three) {
        this.service_three = service_three;
    }

    public void setService_four(String service_four) {
        this.service_four = service_four;
    }

    public void setService_five(String service_five) {
        this.service_five = service_five;
    }

    public void fetchData(){
        final fetchPhoneNumber fetchPhoneNumber=new fetchPhoneNumber(context);
        fetchPhoneNumber.mynumber();

        String MyURL = "http://104.248.124.210/android/iKazi/phpFiles/fetchworkerID.php";

        StringRequest stringRequest=new StringRequest(Request.Method.POST, MyURL, new com.android.volley.Response.Listener<String>(){

            @Override
            public void onResponse(String response) {


                try {
                    JSONObject parentObject = new JSONObject(response);

                    String id=parentObject.getJSONObject("results").getString("id");
                    String name=parentObject.getJSONObject("results").getString("name");
                    String phone_no=parentObject.getJSONObject("results").getString("phonenumber");
                    String occupation=parentObject.getJSONObject("results").getString("occupation");
                    String skillset=parentObject.getJSONObject("results").getString("skillSet");
                    String rating=parentObject.getJSONObject("results").getString("rating");
                    String no_of_rates=parentObject.getJSONObject("results").getString("numberOfRates");
                    String imageurl=parentObject.getJSONObject("results").getString("imageUrl");
                    String jobsdone=parentObject.getJSONObject("results").getString("jobs");
                    String service_one=parentObject.getJSONObject("results").getString("serviceOne");
                    String service_two=parentObject.getJSONObject("results").getString("serviceTwo");
                    String service_three=parentObject.getJSONObject("results").getString("serviceThree");
                    String service_four=parentObject.getJSONObject("results").getString("serviceFour");
                    String service_five=parentObject.getJSONObject("results").getString("serviceFive");




                    setWorker_name(name);
                    setWorker_id(id);
                    setPhone_no(phone_no);
                    setOccupation(occupation);
                    setSkillSet(skillset);
                    setRating(rating);
                    setNo_of_ratings(no_of_rates);
                    setImageUrl(imageurl);
                    setJobsdone(jobsdone);
                    setService_one(service_one);
                    setService_two(service_two);
                    setService_three(service_three);
                    setService_four(service_four);
                    setService_five(service_five);




                } catch (JSONException e) {
                    e.printStackTrace();
                }

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

    }
}
