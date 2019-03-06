package com.example.kelvincb.kazziworkerapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.kelvincb.kazziworkerapp.Settings.SettingsActivity;
import com.example.kelvincb.kazziworkerapp.fragments.JobAds.JobAdsFragment;
import com.example.kelvincb.kazziworkerapp.fragments.JobAds.ads;
import com.example.kelvincb.kazziworkerapp.fragments.WorkerProfile;
import com.example.kelvincb.kazziworkerapp.fragments.HomeFragment;
import com.google.android.gms.ads.AdView;

import java.util.ArrayDeque;
import java.util.Deque;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;


public class MainActivity extends AppCompatActivity {

    AdView mAdView;
    Fragment fragment;
    BottomNavigationView navigation;

    Deque<Integer> mStack = new ArrayDeque<>();
    boolean isBackPressed  = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.main_toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);



        TextView appname=findViewById(R.id.appname);
        Typeface font=Typeface.createFromAsset(getAssets(),"Quicksand-Bold.ttf");
        appname.setTypeface(font);


        navigation =  findViewById(R.id.navigation);
        navigation.getMenu().getItem(0).setChecked(true);

        setBottomNavigationView();

    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment,"ONE");
        transaction.addToBackStack(null);
        transaction.commit();
    }


    private void setBottomNavigationView() {
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        if(!isBackPressed) {
                            pushFragmentIntoStack(R.id.navigation_home);
                        }
                        isBackPressed = false;
                        fragment=new HomeFragment();
                        loadFragment(fragment);
                        return true;

                    case R.id.navigation_ads:
                        if(!isBackPressed) {
                            pushFragmentIntoStack(R.id.navigation_ads);
                        }
                        isBackPressed = false;
                        fragment=new JobAdsFragment();
                        loadFragment(fragment);
                        return true;

                    case R.id.navigation_profile:
                        if(!isBackPressed) {
                            pushFragmentIntoStack(R.id.navigation_profile);
                        }
                        isBackPressed = false;
                        fragment=new WorkerProfile();
                        loadFragment(fragment);
                        return true;

                    default:
                        return false;
                }
            }
        });

        navigation.setSelectedItemId(R.id.navigation_home);
        pushFragmentIntoStack(R.id.navigation_home);
    }

    private void pushFragmentIntoStack(int id)
    {
        if(mStack.size() < 3)
        {
            mStack.push(id);
        }
        else
        {
            mStack.removeLast();
            mStack.push(id);
        }
    }



    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);

        if (currentFragment instanceof HomeFragment) {

            fragment=new HomeFragment();
            loadFragment(fragment);

        }


        if(mStack.size() > 1)
        {
            isBackPressed = true;
            mStack.pop();
            navigation.setSelectedItemId(mStack.peek());
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);


        return true;
    }

    public void confetti(){
        KonfettiView viewKonfetti=findViewById(R.id.viewKonfetti);
        viewKonfetti.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA,Color.BLUE,Color.RED,Color.CYAN)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .addSizes(new Size(7,8))
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .setPosition(-50f, viewKonfetti.getWidth() + 50f, -50f, -50f)
                .streamFor(300, 5000L);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_settings:
                Intent intent=new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.navigation_ads:
                confetti();
                new ads(getApplicationContext());
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
