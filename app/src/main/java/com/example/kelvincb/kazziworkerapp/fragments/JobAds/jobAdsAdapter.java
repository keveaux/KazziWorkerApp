package com.example.kelvincb.kazziworkerapp.fragments.JobAds;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kelvincb.kazziworkerapp.PicassoClient;
import com.example.kelvincb.kazziworkerapp.R;
import com.example.kelvincb.kazziworkerapp.fragments.acceptedRequests.AcceptedRequestRecyclerAdapter;

import java.util.ArrayList;

public class jobAdsAdapter extends RecyclerView.Adapter<jobAdsAdapter.ReyclerViewHolder> {

    private LayoutInflater layoutInflater;
    Context c;
    ArrayList<jobadsgettersetters> arrayList;

    public jobAdsAdapter(Context c,ArrayList<jobadsgettersetters> arrayList) {
        this.layoutInflater = LayoutInflater.from(c);
        this.c = c;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public jobAdsAdapter.ReyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item = layoutInflater.inflate(R.layout.jobadsadapter, viewGroup, false);

        return new ReyclerViewHolder(item);    }

    @Override
    public void onBindViewHolder(@NonNull final ReyclerViewHolder holder, final int position) {

        Typeface regular_font=Typeface.createFromAsset(c.getAssets(),"Quicksand-Regular.ttf");
        Typeface bold_font=Typeface.createFromAsset(c.getAssets(),"Quicksand-Bold.ttf");


        holder.businessname.setText(arrayList.get(position).getBussiness_name());
        holder.job.setText(arrayList.get(position).getJob());
        holder.jobdescription.setText(arrayList.get(position).getJob_description());
        holder.people.setText(arrayList.get(position).getPeople()+" people needed");
        holder.location.setText(arrayList.get(position).getLocation());

        PicassoClient.loadImage(arrayList.get(position).getImage_url(),holder.imageView);

        holder.businessname.setTypeface(bold_font);
        holder.jobdescription.setTypeface(regular_font);
        holder.job.setTypeface(regular_font);
        holder.people.setTypeface(regular_font);
        holder.location.setTypeface(regular_font);

        holder.jobscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(arrayList.get(position).getLinkurl()));
                c.startActivity(browserIntent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ReyclerViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView businessname,job,jobdescription,people,location;
        CardView jobscard;
        ReyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.jobimage);
            businessname=itemView.findViewById(R.id.business_name);
            job=itemView.findViewById(R.id.job);
            jobdescription=itemView.findViewById(R.id.jobdesc);
            people=itemView.findViewById(R.id.peoplerequired);
            location=itemView.findViewById(R.id.location);
            jobscard=itemView.findViewById(R.id.jobscardview);

        }
    }
}
