package com.example.kelvincb.kazziworkerapp.fragments.Pending;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kelvincb.kazziworkerapp.R;
import com.example.kelvincb.kazziworkerapp.fetchWorkerId;


public class PendingRequest extends Fragment {

    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment_pending_request, container, false);

        final fetchWorkerId fetchWorkerID=new fetchWorkerId(getContext());
        fetchWorkerID.fetchID();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(getContext(), ""+fetchWorkerID.getWorker_id(), Toast.LENGTH_SHORT).show();


            }
        }, 2000);


        return view;
    }


}
