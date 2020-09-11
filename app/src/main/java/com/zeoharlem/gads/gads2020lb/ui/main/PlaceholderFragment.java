package com.zeoharlem.gads.gads2020lb.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zeoharlem.gads.gads2020lb.Adapters.LearnersRecyclerAdapter;
import com.zeoharlem.gads.gads2020lb.LeadersBoardActivity;
import com.zeoharlem.gads.gads2020lb.Models.LearnersBoard;
import com.zeoharlem.gads.gads2020lb.R;
import com.zeoharlem.gads.gads2020lb.Services.LearnersService;
import com.zeoharlem.gads.gads2020lb.Services.ServiceBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private RecyclerView mRecyclerView;
    private LearnersRecyclerAdapter mLearnersRecyclerAdapter;

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root       = inflater.inflate(R.layout.fragment_leaders_board, container, false);
        mRecyclerView   = root.findViewById(R.id.learners_recycler_view);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setHasFixedSize(true);

        //LearnersBoard Api Call
        createRetrofitLearnersRestfulApi();

//        final TextView textView = root.findViewById(R.id.section_label);
//        pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    private void createRetrofitLearnersRestfulApi(){
        LearnersService learnersService = ServiceBuilder.buildService(LearnersService.class);
        Call<ArrayList<LearnersBoard>> serviceLearnersBoard  = learnersService.getLearnersBoard();
        serviceLearnersBoard.enqueue(new Callback<ArrayList<LearnersBoard>>() {
            @Override
            public void onResponse(Call<ArrayList<LearnersBoard>> call, Response<ArrayList<LearnersBoard>> response) {
                Toast.makeText(getContext(), Objects.requireNonNull(response.body()).toString(), Toast.LENGTH_LONG).show();
                mRecyclerView.setAdapter(new LearnersRecyclerAdapter(getContext(), response.body()));
            }

            @Override
            public void onFailure(Call<ArrayList<LearnersBoard>> call, Throwable t) {

            }
        });

    }
}