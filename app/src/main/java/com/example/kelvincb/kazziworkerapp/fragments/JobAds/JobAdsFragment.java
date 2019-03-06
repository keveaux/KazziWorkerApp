package com.example.kelvincb.kazziworkerapp.fragments.JobAds;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kelvincb.kazziworkerapp.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;


public class JobAdsFragment extends Fragment {

    String URL="http://104.248.124.210/android/iKazi/phpFiles/fetchads.php";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_job_ads, container, false);

        RecyclerView recyclerView=view.findViewById(R.id.jobads_Recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        TextView tv=view.findViewById(R.id.noadstxt);
        ProgressBar progressbar=view.findViewById(R.id.jobads_progressbar);

        new jobsjsondownloader(getActivity()).retrieveRequestInfo(URL,recyclerView,tv,progressbar);

        new ads(getContext());


        return view;
    }


}
