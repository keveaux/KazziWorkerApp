package com.example.kelvincb.kazziworkerapp.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kelvincb.kazziworkerapp.R;
import com.example.kelvincb.kazziworkerapp.fragments.Pending.PendingRequest;
import com.example.kelvincb.kazziworkerapp.fragments.accepted.AcceptedRequest;

import java.util.ArrayList;
import java.util.List;

public class mainFragment extends Fragment {


    View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_main, container, false);

        viewPager =  view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout =  view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        return view;
    }

    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabOne.setText("PENDING");
        Typeface font=Typeface.createFromAsset(getActivity().getAssets(),"RobotoSlab-Bold.ttf");
        tabOne.setTypeface(font);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabTwo.setText("ACCEPTED");
        Typeface font2=Typeface.createFromAsset(getActivity().getAssets(),"RobotoSlab-Bold.ttf");
        tabTwo.setTypeface(font2);
        tabLayout.getTabAt(1).setCustomView(tabTwo);


    }

    private void setupViewPager(ViewPager viewPager) {
        mainFragment.ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new PendingRequest(), "ONE");
        adapter.addFrag(new AcceptedRequest(), "TWO");
        viewPager.setAdapter(adapter);
    }



    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);

        }



    }


}
