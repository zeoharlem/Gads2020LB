package com.zeoharlem.gads.gads2020lb.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zeoharlem.gads.gads2020lb.Adapters.LearnersRecyclerAdapter;
import com.zeoharlem.gads.gads2020lb.Adapters.SkillIQRecyclerAdapter;
import com.zeoharlem.gads.gads2020lb.Models.LearnersBoard;
import com.zeoharlem.gads.gads2020lb.Models.SkillIQ;
import com.zeoharlem.gads.gads2020lb.R;
import com.zeoharlem.gads.gads2020lb.Services.LearnersService;
import com.zeoharlem.gads.gads2020lb.Services.ServiceBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillFragment extends Fragment {

    private View mView;
    private RecyclerView mRecyclerView;

    public SkillFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView   = inflater.inflate(R.layout.fragment_skill_iq_board, container, false);
        setRecyclerViewActions();
        return mView;
    }

    private void setRecyclerViewActions(){
        mRecyclerView   = mView.findViewById(R.id.skill_iq_recycler_view);

        final LinearLayoutManager layoutManager     = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);

        //LearnersBoard Api Call
        createRetrofitLearnersRestfulApi();

    }

    private void createRetrofitLearnersRestfulApi(){
        LearnersService learnersService = ServiceBuilder.buildService(LearnersService.class);
        Call<ArrayList<SkillIQ>> serviceLearnersBoard  = learnersService.getSkillIQLeaders();
        serviceLearnersBoard.enqueue(new Callback<ArrayList<SkillIQ>>() {
            @Override
            public void onResponse(Call<ArrayList<SkillIQ>> call, Response<ArrayList<SkillIQ>> response) {
                mRecyclerView.setAdapter(new SkillIQRecyclerAdapter(getContext(), response.body()));
            }

            @Override
            public void onFailure(Call<ArrayList<SkillIQ>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
