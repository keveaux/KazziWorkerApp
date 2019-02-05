package com.example.kelvincb.kazziworkerapp.fragments.accepted;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kelvincb.kazziworkerapp.R;
import com.example.kelvincb.kazziworkerapp.fetchWorkerId;
import com.example.kelvincb.kazziworkerapp.fragments.Pending.PendingJsonDownloader;


public class AcceptedRequest extends Fragment {

    View view;
    private static String SITE_URL = "";
    ProgressBar myProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_accepted_request, container, false);

        myProgressBar = view.findViewById(R.id.accepted_progressbar);

        myProgressBar.setVisibility(View.VISIBLE);


        final fetchWorkerId fetchWorkerID=new fetchWorkerId(getContext());
        fetchWorkerID.fetchID();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                String id=fetchWorkerID.getWorker_id();
                SITE_URL = "http://104.248.124.210/android/iKazi/phpFiles/workerfetchPendingRequests.php?id=" +id;

                fetchRequest();


            }
        }, 2000);

        return view;
    }

    public void fetchRequest(){

        final ListView lv = view.findViewById(R.id.accepted_ListView);

        final TextView tv=view.findViewById(R.id.acceptedtxt);

        new AcceptedJsonDownloader(getActivity()).retrieveRequestInfo(SITE_URL,lv,tv,myProgressBar);

    }



}
