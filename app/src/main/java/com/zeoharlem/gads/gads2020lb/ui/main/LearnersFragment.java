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
import com.zeoharlem.gads.gads2020lb.Models.LearnersBoard;
import com.zeoharlem.gads.gads2020lb.R;
import com.zeoharlem.gads.gads2020lb.Services.LearnersService;
import com.zeoharlem.gads.gads2020lb.Services.ServiceBuilder;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearnersFragment extends Fragment {
    private View mView;
    private RecyclerView mRecyclerView;
    private LearnersRecyclerAdapter mLearnersRecyclerAdapter;

    public LearnersFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView   = inflater.inflate(R.layout.fragment_leaders_board, container, false);

        setRecyclerViewActions();

        return mView;
    }

    private void setRecyclerViewActions(){
        mRecyclerView   = mView.findViewById(R.id.learners_recycler_view);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
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
        Call<ArrayList<LearnersBoard>> serviceLearnersBoard  = learnersService.getLearnersBoard();
        serviceLearnersBoard.enqueue(new Callback<ArrayList<LearnersBoard>>() {
            @Override
            public void onResponse(Call<ArrayList<LearnersBoard>> call, Response<ArrayList<LearnersBoard>> response) {
                mRecyclerView.setAdapter(new LearnersRecyclerAdapter(getContext(), response.body()));
            }

            @Override
            public void onFailure(Call<ArrayList<LearnersBoard>> call, Throwable t) {

            }
        });

    }
}
