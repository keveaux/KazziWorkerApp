package com.example.kelvincb.kazziworkerapp.fragments.acceptedRequests;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.kelvincb.kazziworkerapp.PicassoClient;
import com.example.kelvincb.kazziworkerapp.R;

import java.util.ArrayList;

public class AcceptedAdapterClass extends BaseAdapter{

    Context c;
    ArrayList<AcceptedGetterSetterClass> requestList;

    public AcceptedAdapterClass(Context c, ArrayList<AcceptedGetterSetterClass> requestList) {
        this.c = c;
        this.requestList = requestList;
    }

    @Override
    public int getCount() {
        return requestList.size();
    }

    @Override
    public Object getItem(int position) {
        return requestList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.accepted_row_model,parent,false);
        }

        ImageView userImage=convertView.findViewById(R.id.accepted_user_image);
        TextView userName=convertView.findViewById(R.id.accepted_user_name);
        TextView work=convertView.findViewById(R.id.accepted_worktxt);
        TextView date=convertView.findViewById(R.id.accepted_date);
        TextView review=convertView.findViewById(R.id.accepted_details);
        TextView location=convertView.findViewById(R.id.accepted_location);



        Typeface boldfont=Typeface.createFromAsset(c.getAssets(),"RobotoSlab-Bold.ttf");
        location.setTypeface(boldfont);


        Typeface font=Typeface.createFromAsset(c.getAssets(),"RobotoSlab-Regular.ttf");
        userName.setTypeface(font);
        work.setTypeface(font);
        date.setTypeface(font);

        final AcceptedGetterSetterClass acceptedGetterSetterClass=(AcceptedGetterSetterClass) this.getItem(position);

        userName.setText(acceptedGetterSetterClass.getUser_name());
        work.setText(acceptedGetterSetterClass.getJobdescription());
        date.setText(acceptedGetterSetterClass.getDatesent());
        location.setText(acceptedGetterSetterClass.getLocation());

        PicassoClient.loadImage(acceptedGetterSetterClass.getImage_url(),userImage);

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=acceptedGetterSetterClass.getUser_name();
                String date=acceptedGetterSetterClass.getDate();
                String time=acceptedGetterSetterClass.getTime();
                String jobdesc=acceptedGetterSetterClass.getJobdescription();
                String landmark=acceptedGetterSetterClass.getLandmark();
                String user_pno=acceptedGetterSetterClass.getUser_phone_number();
                String url=acceptedGetterSetterClass.getImage_url();
                String location=acceptedGetterSetterClass.getLocation();


                Bundle sendData=new Bundle();
                sendData.putString("name",name);
                sendData.putString("jobdesc",jobdesc);
                sendData.putString("date",date);
                sendData.putString("time",time);
                sendData.putString("landmark",landmark);
                sendData.putString("pno",user_pno);
                sendData.putString("url",url);
                sendData.putString("location",location);


                Intent intent=new Intent(c,AcceptedTaskInfo.class);
                intent.putExtras(sendData);
                c.startActivity(intent);
            }
        });

        return convertView;
    }
}
