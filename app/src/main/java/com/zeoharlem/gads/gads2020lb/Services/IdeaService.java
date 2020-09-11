package com.zeoharlem.gads.gads2020lb.Services;

import com.zeoharlem.gads.gads2020lb.Models.LearnersBoard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IdeaService {

    @GET("learnersboard")
    Call<List<LearnersBoard>> getLearnersBoard();

    
}
