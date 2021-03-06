package creativebrands.kazziworker.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import creativebrands.kazziworker.PicassoClient;

import creativebrands.kazziworker.R;
import creativebrands.kazziworker.Settings.SettingsActivity;
import creativebrands.kazziworker.fetchWorkerInfo;

import java.text.DecimalFormat;


public class WorkerProfile extends Fragment {

    View view;
    ImageView worker_image;
    TextView worker_name, worker_skillset,num_of_jobs,rating_tv,info,profession,phone_number,location,email;
    TextView tag_one,tag_two,tag_three,tag_four,tag_five;
    RelativeLayout mylayout;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_worker_profile, container, false);

        worker_image=view.findViewById(R.id.worker_image);
        worker_name=view.findViewById(R.id.worker_name_tv);
        worker_skillset =view.findViewById(R.id.worker_skillset_tv);
        num_of_jobs=view.findViewById(R.id.worker_num_jobs_tv);
        rating_tv=view.findViewById(R.id.worker_rating_tv);
        info=view.findViewById(R.id.info);
        profession=view.findViewById(R.id.profession_tv);
        phone_number=view.findViewById(R.id.worker_pno_tv);
        tag_one=view.findViewById(R.id.serviceone);
        tag_two=view.findViewById(R.id.servicetwo);
        tag_three=view.findViewById(R.id.servicethree);
        tag_four=view.findViewById(R.id.servicefour);
        tag_five=view.findViewById(R.id.servicefive);
        mylayout=view.findViewById(R.id.mylayout);
        email=view.findViewById(R.id.email);
        location=view.findViewById(R.id.locationtxt);



        final ProgressBar progressBar=view.findViewById(R.id.profile_progressBar);

        Typeface boldfont=Typeface.createFromAsset(getActivity().getAssets(),"Quicksand-Bold.ttf");

        worker_name.setTypeface(boldfont);
        info.setTypeface(boldfont);
        num_of_jobs.setTypeface(boldfont);
        rating_tv.setTypeface(boldfont);


        Typeface font=Typeface.createFromAsset(getActivity().getAssets(),"Quicksand-Regular.ttf");

        worker_skillset.setTypeface(font);
        phone_number.setTypeface(font);
        worker_skillset.setTypeface(font);
        tag_one.setTypeface(font);
        tag_two.setTypeface(font);
        tag_three.setTypeface(font);
        tag_four.setTypeface(font);
        tag_five.setTypeface(font);
        email.setTypeface(font);


        profession.setTypeface(font);
        phone_number.setTypeface(font);

        final fetchWorkerInfo fetchWorkerInfo = new fetchWorkerInfo(getContext());
        fetchWorkerInfo.fetchData();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                progressBar.setVisibility(View.GONE);
                mylayout.setVisibility(View.VISIBLE);


                String name=fetchWorkerInfo.getWorker_name();
                String occupation=fetchWorkerInfo.getOccupation();
                String skillset=fetchWorkerInfo.getSkillSet();
                String imageurl=fetchWorkerInfo.getImageUrl();
                String rating=fetchWorkerInfo.getRating();
                String numberofrates=fetchWorkerInfo.getNo_of_ratings();
                String phone_no=fetchWorkerInfo.getPhone_no();
                String service_one=fetchWorkerInfo.getService_one();
                String service_two=fetchWorkerInfo.getService_two();
                String service_three=fetchWorkerInfo.getService_three();
                String service_four=fetchWorkerInfo.getService_four();
                String service_five=fetchWorkerInfo.getService_five();
                String jobsdone=fetchWorkerInfo.getJobsdone();
                String emailstr=fetchWorkerInfo.getEmail();
                String locationstr=fetchWorkerInfo.getLocation();



                PicassoClient.loadImage(imageurl,worker_image);
                worker_name.setText(name);
                profession.setText(occupation);
                worker_skillset.setText(skillset);
                num_of_jobs.setText(jobsdone);
                tag_one.setText(service_one);
                tag_two.setText(service_two);
                tag_three.setText(service_three);
                tag_four.setText(service_four);
                tag_five.setText(service_five);
                email.setText(emailstr);
                location.setText(locationstr);


                if(rating==null ){
                     Toast.makeText(getContext(), "check your internet connection", Toast.LENGTH_SHORT).show();
                 }else if(rating.equals("0")){
                     rating_tv.setText("0.0");

                 }
                 else{
                     double workerrating = Double.parseDouble(rating) / Double.parseDouble(numberofrates);
                     DecimalFormat df = new DecimalFormat("#.##");
                     rating_tv.setText(""+df.format(workerrating));
                 }

                phone_number.setText(phone_no);

            }},2000);







        return view;
    }




}
