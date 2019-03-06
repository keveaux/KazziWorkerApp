package com.example.kelvincb.kazziworkerapp.WebViewPackage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.kelvincb.kazziworkerapp.R;


public class WebViewFragment extends Fragment {


    WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_web_view, container, false);

        webView=new WebView(getContext());
        getActivity().setContentView(webView);
        webView.loadUrl("");

        return v;
    }



}
