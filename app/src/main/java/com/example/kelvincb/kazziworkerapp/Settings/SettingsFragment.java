package com.example.kelvincb.kazziworkerapp.Settings;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.kelvincb.kazziworkerapp.R;


public class SettingsFragment extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      View v=  inflater.inflate(R.layout.fragment_settings, container, false);



        // Inflate the layout for this fragment
        return  v;
    }


}
