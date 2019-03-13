package creativebrands.kazziworker.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import creativebrands.kazziworker.R;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;


public class AboutFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Element versionElement = new Element();
        versionElement.setTitle("Version 1.0.0");

        Element adelement=new Element();
        adelement.setTitle("Advertise Job Openings");
        adelement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Element hireus=new Element();
        hireus.setTitle("Hire us to make an app");
        hireus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "+25422146246", null));
                startActivity(intent);
            }
        });


        String description=getActivity().getResources().getString(R.string.slide_1_desc);
        View aboutPage = new AboutPage(getContext())
                .isRTL(false)
                .addItem(versionElement)
                .addItem(adelement)
                .addItem(hireus)
                .setDescription(description)
                .addGroup("Connect with us")
                .addEmail("elmehdi.sakout@gmail.com")
                .addWebsite("http://medyo.github.io/")
                .addFacebook("the.medy")
                .addTwitter("medyo80")
//                .addYoutube("UCdPQtdWIsg7_pi4mrRu46vA")
                .addPlayStore("com.ideashower.readitlater.pro")
//                .addGitHub("medyo")
                .addInstagram("medyo80")

                .create();


        return aboutPage;
    }

}
