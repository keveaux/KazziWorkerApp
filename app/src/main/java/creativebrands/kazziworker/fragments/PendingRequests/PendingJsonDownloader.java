package creativebrands.kazziworker.fragments.PendingRequests;

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

public class PendingJsonDownloader {

    private final Context c;
    private PendingRequestRecyclerAdapter adapter;
    RequestQueue requestQueue;

    public PendingJsonDownloader(Context c) {
        this.c = c;
    }

    public void retrieveRequestInfo(String URL, final RecyclerView listView, final TextView tv, final Animation animationUp, final Animation animationDown, final ProgressBar myProgressBar)  {

        final ArrayList<PendingGetterSetterClass> requestList = new ArrayList<>();

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

                    PendingGetterSetterClass pendingGetterSetterClass;
                    JSONArray ja = response.getJSONArray("results");
                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject jsonObject = ja.getJSONObject(i);


                        String id=jsonObject.getString("id");
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


                    pendingGetterSetterClass=new PendingGetterSetterClass();
                    pendingGetterSetterClass.setId(id);
                    pendingGetterSetterClass.setUser_name(user_name);
                    pendingGetterSetterClass.setUser_phone_number(phone_number);
                    pendingGetterSetterClass.setDate(date);
                    pendingGetterSetterClass.setTime(time);
                    pendingGetterSetterClass.setJob_desctiption(job_description);
                    pendingGetterSetterClass.setLandmark(landmark);
                    pendingGetterSetterClass.setImage_url(url);
                    pendingGetterSetterClass.setLocation(location);
                    pendingGetterSetterClass.setCurrentdate(currentdate);
                    pendingGetterSetterClass.setStatus(status);


                            requestList.add(pendingGetterSetterClass);



                    }

                    adapter = new PendingRequestRecyclerAdapter(animationUp,animationDown,c,requestList);
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
                        Toast.makeText(c, "" + error, Toast.LENGTH_SHORT).show();
                    }
                }

        );
        requestQueue.add(jor);

    }
    }
