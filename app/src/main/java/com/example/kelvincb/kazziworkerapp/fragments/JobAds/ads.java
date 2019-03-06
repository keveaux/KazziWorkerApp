package com.example.kelvincb.kazziworkerapp.fragments.JobAds;

import android.content.Context;
import android.os.Handler;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class ads {
    private Context context;
    private RewardedVideoAd rewardedVideoAd;


    public ads(Context context) {
        this.context = context;

        MobileAds.initialize(context,"ca-app-pub-3940256099942544~3347511713");
        rewardedVideoAd=MobileAds.getRewardedVideoAdInstance(context);
        rewardedVideoAd.setRewardedVideoAdListener( rewardedVideoAdListener);

        loadRewardedVideo();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startvideo();

            }},2000);

    }

    private void loadRewardedVideo(){
        if(!rewardedVideoAd.isLoaded()){
            rewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",new AdRequest.Builder().build());
        }
    }

    public void startvideo(){
        if(rewardedVideoAd.isLoaded()){
            rewardedVideoAd.show();
        }
    }

    RewardedVideoAdListener rewardedVideoAdListener = new RewardedVideoAdListener() {
        @Override
        public void onRewardedVideoAdLoaded() {

        }

        @Override
        public void onRewardedVideoAdOpened() {

        }

        @Override
        public void onRewardedVideoStarted() {

        }

        @Override
        public void onRewardedVideoAdClosed() {

        }

        @Override
        public void onRewarded(RewardItem rewardItem) {

        }

        @Override
        public void onRewardedVideoAdLeftApplication() {

        }

        @Override
        public void onRewardedVideoAdFailedToLoad(int i) {

        }

        @Override
        public void onRewardedVideoCompleted() {

        }

    };


}
