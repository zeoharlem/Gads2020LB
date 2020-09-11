package com.zeoharlem.gads.gads2020lb.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zeoharlem.gads.gads2020lb.Models.SkillIQ;
import com.zeoharlem.gads.gads2020lb.R;
import com.zeoharlem.gads.gads2020lb.ViewAttributes;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SkillIQRecyclerAdapter extends RecyclerView.Adapter<SkillIQRecyclerAdapter.SkillIQViewHolder> {

    private Context context;
    private ViewAttributes viewAttributes;
    private ArrayList<SkillIQ> mSkillIQArrayList;

    public SkillIQRecyclerAdapter(Context context) {
        this.context = context;
    }

    public SkillIQRecyclerAdapter(Context context, ArrayList<SkillIQ> skillIQArrayList) {
        this(context);
        mSkillIQArrayList = skillIQArrayList;
    }

    @NonNull
    @Override
    public SkillIQViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(context).inflate(R.layout.items_custom_layout, parent, false);
        return new SkillIQViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillIQViewHolder holder, int position) {
        SkillIQ skillIQ = mSkillIQArrayList.get(position);
        holder.studentName.setText(skillIQ.name);
        String hoursWithCountryString   = skillIQ.score+" Skill IQ Score, "+skillIQ.country;
        holder.scoreHoursCountry.setText(hoursWithCountryString);
        Glide.with(context).load(skillIQ.badgeUrl).override(60,60).centerCrop().into(holder.mCircleImageView);
    }

    @Override
    public int getItemCount() {
        return mSkillIQArrayList.size();
    }

    class SkillIQViewHolder extends RecyclerView.ViewHolder{
        Typeface mTypefaceRegular, mTypefaceBlack;
        TextView studentName, scoreHoursCountry;
        ImageView mCircleImageView;

        public SkillIQViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName         = itemView.findViewById(R.id.student_name);
            scoreHoursCountry   = itemView.findViewById(R.id.scores_hours_country);
            mCircleImageView    = itemView.findViewById(R.id.circularImg);

            setTextTypeface();
        }

        private void setTextTypeface(){
            mTypefaceBlack      = ViewAttributes.getIntanceViewAttributes(context).getMySpartanBlack();
            mTypefaceRegular    = ViewAttributes.getIntanceViewAttributes(context).getMySpartanRegular();
            scoreHoursCountry.setTypeface(mTypefaceRegular);
            studentName.setTypeface(mTypefaceBlack);
        }
    }
}
