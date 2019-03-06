package com.example.kelvincb.kazziworkerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class trial extends AppCompatActivity implements RewardedVideoAdListener {

    private RewardedVideoAd rewardedVideoAd;
    TextView coinstxt;
    Button watchAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial);

        coinstxt=findViewById(R.id.coinstxt);
        watchAd=findViewById(R.id.watchad);

        MobileAds.initialize(getApplicationContext(),"ca-app-pub-3940256099942544~3347511713");
        rewardedVideoAd=MobileAds.getRewardedVideoAdInstance(this);
        rewardedVideoAd.setRewardedVideoAdListener(this);



        loadRewardedVideo();

        watchAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startvideo();
            }
        });
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
        loadRewardedVideo();

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        coinstxt.setText("Available Coins: 5");

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

    @Override
    protected void onPause() {
        rewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        rewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        rewardedVideoAd.destroy(this);
        super.onDestroy();
    }
}
