package com.example.kelvincb.kazziworkerapp.fragments.PendingRequests;

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

public class PendingAdapterClass extends BaseAdapter{

    Context c;
    ArrayList<PendingGetterSetterClass> requestList;

    public PendingAdapterClass(Context c, ArrayList<PendingGetterSetterClass> requestList) {
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
            convertView= LayoutInflater.from(c).inflate(R.layout.pending_row_model,parent,false);
        }

        ImageView userImage=convertView.findViewById(R.id.pending_user_image);
        TextView userName=convertView.findViewById(R.id.pending_user_name);
        TextView work=convertView.findViewById(R.id.pending_worktxt);
        TextView date=convertView.findViewById(R.id.pending_date);
        TextView review=convertView.findViewById(R.id.pending_details);
        TextView location=convertView.findViewById(R.id.pending_location);



        Typeface font=Typeface.createFromAsset(c.getAssets(),"RobotoSlab-Light.ttf");
        userName.setTypeface(font);
        work.setTypeface(font);
        date.setTypeface(font);
        location.setTypeface(font);


        final PendingGetterSetterClass pendingGetterSetterClass=(PendingGetterSetterClass) this.getItem(position);


        userName.setText(pendingGetterSetterClass.getUser_name());
        work.setText(pendingGetterSetterClass.getJob_desctiption());
        date.setText(pendingGetterSetterClass.getCurrentdate());
        location.setText(pendingGetterSetterClass.getLocation());

        PicassoClient.loadImage(pendingGetterSetterClass.getImage_url(),userImage);




       review.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String name=pendingGetterSetterClass.getUser_name();
               String date=pendingGetterSetterClass.getDate();
               String time=pendingGetterSetterClass.getTime();
               String jobdesc=pendingGetterSetterClass.getJob_desctiption();
               String landmark=pendingGetterSetterClass.getLandmark();
               String jobid=pendingGetterSetterClass.getId();
               String user_pno=pendingGetterSetterClass.getUser_phone_number();
               String url=pendingGetterSetterClass.getImage_url();


               Bundle sendData=new Bundle();
               sendData.putString("name",name);
               sendData.putString("jobdesc",jobdesc);
               sendData.putString("date",date);
               sendData.putString("time",time);
               sendData.putString("landmark",landmark);
               sendData.putString("id",jobid);
               sendData.putString("pno",user_pno);
               sendData.putString("url",url);

               Intent intent=new Intent(c,PendingInfo.class);
               intent.putExtras(sendData);
               c.startActivity(intent);
           }
       });
        return convertView;
    }
}
