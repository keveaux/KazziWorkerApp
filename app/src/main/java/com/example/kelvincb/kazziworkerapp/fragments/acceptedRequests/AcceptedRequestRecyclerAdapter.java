package com.example.kelvincb.kazziworkerapp.fragments.acceptedRequests;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
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

import com.example.kelvincb.kazziworkerapp.PicassoClient;
import com.example.kelvincb.kazziworkerapp.R;

import java.util.ArrayList;

public class AcceptedRequestRecyclerAdapter extends RecyclerView.Adapter<AcceptedRequestRecyclerAdapter.ReyclerViewHolder> {
    private LayoutInflater layoutInflater;
    private Animation animationUp, animationDown;
    private Context context;
    private final int COUNTDOWN_RUNNING_TIME = 500;
    ArrayList<AcceptedGetterSetterClass> requestList;

    public AcceptedRequestRecyclerAdapter(Context context, Animation animationUp, Animation animationDown, ArrayList<AcceptedGetterSetterClass> requestList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.animationDown = animationDown;
        this.animationUp = animationUp;
        this.context = context;
        this.requestList=requestList;
    }

    @Override
    public ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.acceptedrequestsrecycleradapter, parent, false);

        return new ReyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final ReyclerViewHolder holder, final int position) {

        Typeface bold_font=Typeface.createFromAsset(context.getAssets(),"Quicksand-Bold.ttf");
        Typeface regular_font=Typeface.createFromAsset(context.getAssets(),"Quicksand-Regular.ttf");



        holder.name.setText(requestList.get(position).getUser_name());
        holder.date.setText(requestList.get(position).getDate()+", ");
        holder.time.setText(requestList.get(position).getTime());
        holder.description.setText(requestList.get(position).getJobdescription());
        holder.location.setText(requestList.get(position).getLocation());
        holder.landmark.setText(requestList.get(position).getLandmark());

        holder.name.setTypeface(bold_font);
        holder.date.setTypeface(regular_font);
        holder.time.setTypeface(regular_font);
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

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", requestList.get(position).getUser_phone_number(), null));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }

    class ReyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView user_image;
        private TextView showMore,name,date,time,description,location,landmark;
        private RelativeLayout contentLayout;

        Button call;

        private ReyclerViewHolder(final View v) {
            super(v);

            user_image =  v.findViewById(R.id.image);
            contentLayout =  v.findViewById(R.id.lowerLayout);
            showMore =  v.findViewById(R.id.show_more);
            date =  v.findViewById(R.id.date);
            time =  v.findViewById(R.id.time);
            description =  v.findViewById(R.id.job_description);
            location =  v.findViewById(R.id.location);
            call =  v.findViewById(R.id.callbtn);
            name=v.findViewById(R.id.title);
            landmark=v.findViewById(R.id.landmark);




        }
    }
}
