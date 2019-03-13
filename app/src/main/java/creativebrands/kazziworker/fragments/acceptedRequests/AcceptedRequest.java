package creativebrands.kazziworker.fragments.acceptedRequests;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import creativebrands.kazziworker.R;
import creativebrands.kazziworker.fetchWorkerInfo;


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


        final fetchWorkerInfo fetchWorkerInfo=new fetchWorkerInfo(getContext());
        fetchWorkerInfo.fetchData();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                String id=fetchWorkerInfo.getWorker_id();


                SITE_URL = "http://104.248.124.210/android/iKazi/phpFiles/workerfetchAcceptedRequest.php?id=" +id;

                fetchRequest();


            }
        }, 2000);

        return view;
    }


    public void fetchRequest(){

        final RecyclerView recyclerView = view.findViewById(R.id.accepted_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        Animation animationUp = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up);
        Animation animationDown = AnimationUtils.loadAnimation(getContext(), R.anim.slide_down);

        final TextView tv=view.findViewById(R.id.acceptedtxt);

        new AcceptedJsonDownloader(getActivity()).retrieveRequestInfo(SITE_URL,recyclerView,tv, animationUp, animationDown,myProgressBar);

    }



}
