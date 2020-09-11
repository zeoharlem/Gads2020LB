package com.zeoharlem.gads.gads2020lb.Services;

import com.zeoharlem.gads.gads2020lb.Models.GoogleForm;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface FormService {

    @POST
    Call<GoogleForm> createGoogleForm(@Url String altUrl, @Body GoogleForm newGoogleForm);

    @FormUrlEncoded
    @POST
    Call<Void> submitGoogleForm(@Url String altUrl,
                                @Field("entry.1877115667")String firstname,
                                @Field("entry.2006916086")String lastname,
                                @Field("entry.1824927963")String email,
                                @Field("entry.284483984")String githubUrl
                                );
}
