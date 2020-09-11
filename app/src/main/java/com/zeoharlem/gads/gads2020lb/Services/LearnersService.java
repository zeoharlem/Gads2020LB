package com.zeoharlem.gads.gads2020lb.Services;

import com.zeoharlem.gads.gads2020lb.Models.LearnersBoard;
import com.zeoharlem.gads.gads2020lb.Models.SkillIQ;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LearnersService {
    @GET("api/hours")
    Call<ArrayList<LearnersBoard>> getLearnersBoard();

    @GET("api/skilliq")
    Call<ArrayList<SkillIQ>> getSkillIQLeaders();
}
