package com.zeoharlem.gads.gads2020lb.AlertDialogBox;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.zeoharlem.gads.gads2020lb.Interface.Communicator;
import com.zeoharlem.gads.gads2020lb.R;
import com.zeoharlem.gads.gads2020lb.ViewAttributes;

public class MyDialogFragBox extends AppCompatDialogFragment implements View.OnClickListener {
    Button acceptBtn;
    TextView successPopTitle;
    ImageButton closeWindowBox;
    CommunicateWithDialogBox mCommunicator;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mCommunicator   = (CommunicateWithDialogBox) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view   = inflater.inflate(R.layout.custom_quest_frag, null);
        closeWindowBox  = view.findViewById(R.id.closeWindowBox);
        successPopTitle = view.findViewById(R.id.alertDialogTitle);
        acceptBtn       = view.findViewById(R.id.acceptBtn);
        setProperties();
        acceptBtn.setOnClickListener(this);
        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.acceptBtn){
            mCommunicator.onDialogBoxConfirm(true);
            successPopTitle.setText("Wait... Processing");
            closeWindowBox.setVisibility(View.GONE);
            acceptBtn.setVisibility(View.GONE);
            setCancelable(false);
        }
        else if(view.getId() == R.id.closeWindowBox){
            mCommunicator.onDialogBoxConfirm(false);
            dismiss();
        }
    }

    private void setProperties(){
        acceptBtn.setTypeface(ViewAttributes.getIntanceViewAttributes(getActivity()).getMySpartanBlack());
        successPopTitle.setTypeface(ViewAttributes.getIntanceViewAttributes(getActivity()).getMySpartanBlack());
    }

    public interface CommunicateWithDialogBox{
        void onDialogBoxConfirm(boolean s);
    }
}
