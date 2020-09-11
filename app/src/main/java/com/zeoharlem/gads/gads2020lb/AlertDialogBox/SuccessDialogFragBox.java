package com.zeoharlem.gads.gads2020lb.AlertDialogBox;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.zeoharlem.gads.gads2020lb.R;

public class SuccessDialogFragBox extends DialogFragment {

    private AlertDialog.Builder mBuilder;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        mBuilder    = new AlertDialog.Builder(getActivity());
        mBuilder.setView(getActivity().getLayoutInflater().inflate(R.layout.custom_pop_success, null));
        return mBuilder.create();
        //return super.onCreateDialog(savedInstanceState);
    }

    public void callAlertDialog(AlertDialogListener alertDialogListener){
        alertDialogListener.callbackAction(this);
    }

    public interface AlertDialogListener{
        void callbackAction(SuccessDialogFragBox successDialogFragBox);
    }
}
