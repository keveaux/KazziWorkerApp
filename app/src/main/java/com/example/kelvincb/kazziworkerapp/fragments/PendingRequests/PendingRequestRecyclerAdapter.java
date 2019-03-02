package com.example.kelvincb.kazziworkerapp.fragments.PendingRequests;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kelvincb.kazziworkerapp.MainActivity;
import com.example.kelvincb.kazziworkerapp.PicassoClient;
import com.example.kelvincb.kazziworkerapp.R;
import com.example.kelvincb.kazziworkerapp.myRequestHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class PendingRequestRecyclerAdapter extends RecyclerView.Adapter<PendingRequestRecyclerAdapter.ReyclerViewHolder> {


    private LayoutInflater layoutInflater;
    private Animation animationUp, animationDown;
    private Context context;
    private final int COUNTDOWN_RUNNING_TIME = 500;
    ArrayList<PendingGetterSetterClass> requestList;
    String formattedDate,id,user_pno;
    public static final String UPDATE_URL = "http://104.248.124.210/android/iKazi/phpFiles/rejectrequest.php";

    public static final String ACCEPT_URL = "http://104.248.124.210/android/iKazi/phpFiles/acceptrequest.php";


    public PendingRequestRecyclerAdapter( Animation animationUp, Animation animationDown, Context context, ArrayList<PendingGetterSetterClass> requestList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.animationUp = animationUp;
        this.animationDown = animationDown;
        this.context = context;
        this.requestList = requestList;
    }

    @NonNull
    @Override
    public ReyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item = layoutInflater.inflate(R.layout.pendingrequestrecycleradapter, viewGroup, false);

        return new ReyclerViewHolder(item);    }

    @Override
    public void onBindViewHolder(@NonNull final ReyclerViewHolder holder, final int position) {
        Typeface bold_font=Typeface.createFromAsset(context.getAssets(),"Quicksand-Bold.ttf");
        Typeface regular_font=Typeface.createFromAsset(context.getAssets(),"Quicksand-Regular.ttf");


        id=requestList.get(position).getId();
        user_pno=requestList.get(position).getUser_phone_number();
        final String name=requestList.get(position).getUser_name();

        holder.name.setText(requestList.get(position).getUser_name());
        holder.date.setText(requestList.get(position).getDate()+", ");
        holder.time.setText(requestList.get(position).getTime());
        holder.description.setText(requestList.get(position).getJob_desctiption());
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
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c);

        holder.acceptbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int greenColorValue = Color.parseColor("#00ff00");

                showDialog(context,"accept "+name+"'s request",R.drawable.accepted,greenColorValue,1);

            }
        });
        holder.rejectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int greenColorValue = Color.parseColor("#FFF60606");
                showDialog(context,"reject "+name+"'s request",R.drawable.cancel,greenColorValue,2);

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

        Button acceptbtn,rejectbtn;

        private ReyclerViewHolder(final View v) {
            super(v);

            user_image =  v.findViewById(R.id.image);
            contentLayout =  v.findViewById(R.id.lowerLayout);
            showMore =  v.findViewById(R.id.show_more);
            date =  v.findViewById(R.id.date);
            time =  v.findViewById(R.id.time);
            description =  v.findViewById(R.id.job_description);
            location =  v.findViewById(R.id.location);
            acceptbtn =  v.findViewById(R.id.acceptjobbtn);
            rejectbtn=v.findViewById(R.id.rejectjobbtn);
            name=v.findViewById(R.id.title);
            landmark=v.findViewById(R.id.landmark);




        }
    }

    public void showDialog(Context activity, String msg, int images, int color, final int whichbutton){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog);



        TextView text = dialog.findViewById(R.id.text_dialog);
        text.setText(msg);

        ImageView imageView=dialog.findViewById(R.id.popupimage);
        imageView.setImageResource(images);
        imageView.setBackgroundColor(color);

        Typeface font1=Typeface.createFromAsset(context.getAssets(),"RobotoSlab-Bold.ttf");
        text.setTypeface(font1);

        Button dialogButton =  dialog.findViewById(R.id.btn_dialog_yes);

        dialogButton.setTypeface(font1);
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
                loading = ProgressDialog.show(context, "Loading...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equals("Successfully Uploaded")){
                    Intent intent=new Intent(context, MainActivity.class);
                    context.startActivity(intent);
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
                loading = ProgressDialog.show(context, "Loading...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();

                Toast.makeText(context, ""+s, Toast.LENGTH_SHORT).show();
                if(s.equals("Successfully Uploaded")){
                    Toast.makeText(context, "complete!!! check your accepted requests", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(context, MainActivity.class);
                    context.startActivity(intent);
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
