package com.zeoharlem.gads.gads2020lb.ViewModel;

import androidx.lifecycle.ViewModel;

import com.zeoharlem.gads.gads2020lb.Models.LearnersBoard;
import com.zeoharlem.gads.gads2020lb.Services.ServiceBuilder;

public class LeanersViewModel extends ViewModel {

    private LearnersBoard mLearnersBoard;
    private ServiceBuilder mServiceBuilder  = new ServiceBuilder();

    private LearnersBoard getLearnersBoard(){
        if(mLearnersBoard == null){

        }
        return mLearnersBoard;
    }
}
