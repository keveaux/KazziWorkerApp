package com.example.kelvincb.kazziworkerapp;

import android.content.Context;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kelvincb.kazziworkerapp.fragments.acceptedRequests.AcceptedGetterSetterClass;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ReyclerViewHolder> {
    private LayoutInflater layoutInflater;
    private Animation animationUp, animationDown;
    private Context context;
    private final int COUNTDOWN_RUNNING_TIME = 500;
    ArrayList<AcceptedGetterSetterClass> requestList;

    public RecyclerAdapter(Context context, Animation animationUp, Animation animationDown,ArrayList<AcceptedGetterSetterClass> requestList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.animationDown = animationDown;
        this.animationUp = animationUp;
        this.context = context;
        this.requestList=requestList;
    }

    @Override
    public ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.recycleradapter, parent, false);

        return new ReyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final ReyclerViewHolder holder, int position) {

        Typeface bold_font=Typeface.createFromAsset(context.getAssets(),"Quicksand-Bold.ttf");
        Typeface light_font=Typeface.createFromAsset(context.getAssets(),"Quicksand-Light.ttf");
        Typeface regular_font=Typeface.createFromAsset(context.getAssets(),"Quicksand-Regular.ttf");



        holder.name.setText(requestList.get(position).getUser_name());
        holder.date.setText(requestList.get(position).getDate()+", ");
        holder.time.setText(requestList.get(position).getTime());
        holder.description.setText(requestList.get(position).getJobdescription());
        holder.location.setText(requestList.get(position).getLocation());
        holder.landmark.setText(requestList.get(position).getLandmark());

        holder.name.setTypeface(bold_font);
        holder.date.setTypeface(light_font);
        holder.time.setTypeface(light_font);
        holder.description.setTypeface(regular_font);
        holder.location.setTypeface(regular_font);
        holder.landmark.setTypeface(regular_font);
        holder.showMore.setTypeface(bold_font);

        PicassoClient.loadImage(requestList.get(position).getImage_url(),holder.user_image);





        holder.showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.contentLayout.isShown()) {
                    holder.contentLayout.startAnimation(animationUp);

                    CountDownTimer countDownTimerStatic = new CountDownTimer(COUNTDOWN_RUNNING_TIME, 16) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                        }

                        @Override
                        public void onFinish() {
                            holder.contentLayout.setVisibility(View.GONE);
                        }
                    };
                    countDownTimerStatic.start();

                    holder.showMore.setText(context.getString(R.string.showmore));
                    holder.showMore.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0);
                } else {
                    holder.contentLayout.setVisibility(View.VISIBLE);
                    holder.contentLayout.startAnimation(animationDown);

                    holder.showMore.setText(context.getString(R.string.showless));
                    holder.showMore.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_up, 0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ReyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView user_image;
        private TextView showMore,name,date,time,description,location,landmark;
        private RelativeLayout contentLayout;

        Button accept,decline;

        private ReyclerViewHolder(final View v) {
            super(v);

            user_image =  v.findViewById(R.id.image);
            contentLayout =  v.findViewById(R.id.lowerLayout);
            showMore =  v.findViewById(R.id.show_more);
            date =  v.findViewById(R.id.date);
            time =  v.findViewById(R.id.time);
            description =  v.findViewById(R.id.job_description);
            location =  v.findViewById(R.id.location);
            accept =  v.findViewById(R.id.acceptjobbtn);
            decline =  v.findViewById(R.id.declinejobbtn);
            name=v.findViewById(R.id.title);
            landmark=v.findViewById(R.id.landmark);




        }
    }
}
