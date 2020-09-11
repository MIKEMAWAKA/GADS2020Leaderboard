package com.mike.gads2020leaderboard.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mike.gads2020leaderboard.Model.Leaner;
import com.mike.gads2020leaderboard.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class LearnerAdapter extends RecyclerView.Adapter<LearnerAdapter.LearnerViewHolder> {
    Context context;
    ArrayList<Leaner> leaners;

    public LearnerAdapter(Context context, ArrayList<Leaner> leaners) {
        this.context = context;
        this.leaners = leaners;
    }

    @NonNull
    @Override
    public LearnerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.learninglayout,viewGroup,false);
        return new LearnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnerViewHolder holder, int position) {
        holder.txtname.setText(leaners.get(position).getName());
        holder.hours.setText(new StringBuffer(" "+leaners.get(position).getHours()).append(" learning hours, ").append(leaners.get(position).getCountry()));

        Picasso.get()
                .load(leaners.get(position).getBadgeurl())
                .placeholder(R.mipmap.toplearner)
                .error(R.mipmap.toplearner)
                .into(holder.imagebg);


    }

    @Override
    public int getItemCount() {
        return leaners.size();
    }

    public class LearnerViewHolder extends RecyclerView.ViewHolder {
        TextView txtname,hours;
        ImageView imagebg;
        public LearnerViewHolder(@NonNull View itemView) {
            super(itemView);

            imagebg = itemView.findViewById(R.id.learningbq);
            txtname = itemView.findViewById(R.id.txt_name);
            hours = itemView.findViewById(R.id.txt_hourl);


        }
    }
}

