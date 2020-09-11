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



public class SkilliqAdapter extends RecyclerView.Adapter<SkilliqAdapter.SkilliqViewHolder> {
    Context context;
    ArrayList<Leaner> leaners;

    public SkilliqAdapter(Context context, ArrayList<Leaner> leaners) {
        this.context = context;
        this.leaners = leaners;
    }

    @NonNull
    @Override
    public SkilliqViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.iqlayout,viewGroup,false);
        return new SkilliqViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkilliqViewHolder holder, int position) {
        holder.txtname.setText(leaners.get(position).getName());
        holder.score.setText(new StringBuffer(" "+leaners.get(position).getScore()).append(" Skill IQ Score, ").append(leaners.get(position).getCountry()));

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

    public class SkilliqViewHolder extends RecyclerView.ViewHolder {
        TextView txtname,score;
        ImageView imagebg;
        public SkilliqViewHolder(@NonNull View itemView) {
            super(itemView);

            imagebg = itemView.findViewById(R.id.imageiq);
            txtname = itemView.findViewById(R.id.txt_name);
            score = itemView.findViewById(R.id.txt_hourl);


        }
    }
}


