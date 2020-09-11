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
import com.zeoharlem.gads.gads2020lb.Models.LearnersBoard;
import com.zeoharlem.gads.gads2020lb.R;
import com.zeoharlem.gads.gads2020lb.ViewAttributes;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class LearnersRecyclerAdapter extends RecyclerView.Adapter<LearnersRecyclerAdapter.LearnersViewHolder> {

    private Context context;
    private ViewAttributes viewAttributes;
    private ArrayList<LearnersBoard> mLearnersBoardArrayList;
    private iLearnersRecyclerAdapterListener mLearnersRecyclerAdapterListener;

    public LearnersRecyclerAdapter(Context context) {
        this.context = context;
    }

    public LearnersRecyclerAdapter(Context context, ArrayList<LearnersBoard> learnersBoardArrayList) {
        this(context);
        mLearnersBoardArrayList = learnersBoardArrayList;
    }

    public ArrayList<LearnersBoard> getLearnersBoardArrayList() {
        return mLearnersBoardArrayList;
    }

    public void setLearnersBoardArrayList(ArrayList<LearnersBoard> learnersBoardArrayList) {
        mLearnersBoardArrayList = learnersBoardArrayList;
    }

    public iLearnersRecyclerAdapterListener getLearnersRecyclerAdapterListener() {
        return mLearnersRecyclerAdapterListener;
    }

    public void setLearnersRecyclerAdapterListener(iLearnersRecyclerAdapterListener learnersRecyclerAdapterListener) {
        mLearnersRecyclerAdapterListener = learnersRecyclerAdapterListener;
    }

    @NonNull
    @Override
    public LearnersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(context).inflate(R.layout.items_custom_layout, parent, false);
        return new LearnersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnersViewHolder holder, int position) {
        LearnersBoard learnersBoard = mLearnersBoardArrayList.get(position);
        holder.studentName.setText(learnersBoard.name);
        String hoursWithCountryString   = learnersBoard.hours+" Learning Hours, "+learnersBoard.country;
        holder.scoreHoursCountry.setText(hoursWithCountryString);
        Glide.with(context).load(learnersBoard.badgeUrl).override(60,60).centerCrop().into(holder.mCircleImageView);
    }

    @Override
    public int getItemCount() {
        return mLearnersBoardArrayList.size();
    }

    public interface iLearnersRecyclerAdapterListener{
        void onItemClickListener(int position, View view);
    }

    class LearnersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Typeface mTypefaceRegular, mTypefaceBlack;
        TextView studentName, scoreHoursCountry;
        ImageView mCircleImageView;

        public LearnersViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName         = itemView.findViewById(R.id.student_name);
            scoreHoursCountry   = itemView.findViewById(R.id.scores_hours_country);
            mCircleImageView    = itemView.findViewById(R.id.circularImg);

            setTextTypeface();

            //itemView.setOnClickListener(this);
        }

        private void setTextTypeface(){
            mTypefaceBlack      = ViewAttributes.getIntanceViewAttributes(context).getMySpartanBlack();
            mTypefaceRegular    = ViewAttributes.getIntanceViewAttributes(context).getMySpartanRegular();
            scoreHoursCountry.setTypeface(mTypefaceRegular);
            studentName.setTypeface(mTypefaceBlack);
        }

        @Override
        public void onClick(View view) {
            //mLearnersRecyclerAdapterListener.onItemClickListener(getAdapterPosition(), view);
        }
    }
}
