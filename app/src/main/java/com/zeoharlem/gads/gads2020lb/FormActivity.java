package com.zeoharlem.gads.gads2020lb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.zeoharlem.gads.gads2020lb.AlertDialogBox.FailureDialogFragBox;
import com.zeoharlem.gads.gads2020lb.AlertDialogBox.MyDialogFragBox;
import com.zeoharlem.gads.gads2020lb.AlertDialogBox.SuccessDialogFragBox;
import com.zeoharlem.gads.gads2020lb.Models.GoogleForm;
import com.zeoharlem.gads.gads2020lb.Services.FormService;
import com.zeoharlem.gads.gads2020lb.Services.ServiceBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormActivity extends AppCompatActivity implements MyDialogFragBox.CommunicateWithDialogBox{

    Dialog mDialog;
    Button submit;
    TextInputLayout firstname, lastname, email, github;
    TextView projectSubmissionTitle;
    TextInputEditText mEditTextFirstname, mEditTextLastname, mEditTextEmailAddress, mTextInputGitHub;
    GoogleForm mGoogleForm;
    public static final String regEx = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";

    static final String ALT_URL_STRING = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";
    private MyDialogFragBox myDialogFragBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.gads_sm_logo);

        mDialog     = new Dialog(this);

        mGoogleForm = new GoogleForm();

        setViewByIdTextInputs();
        setTypefaceAction();

        myDialogFragBox = new MyDialogFragBox();
        submit.setOnClickListener(mOnClickListener);
    }

    private void setTypefaceAction(){
        submit.setTypeface(ViewAttributes.getIntanceViewAttributes(this).getMySpartanBlack());
        mEditTextFirstname.setTypeface(ViewAttributes.getIntanceViewAttributes(this).getMySpartanBlack());
        mEditTextEmailAddress.setTypeface(ViewAttributes.getIntanceViewAttributes(this).getMySpartanBlack());
        mEditTextLastname.setTypeface(ViewAttributes.getIntanceViewAttributes(this).getMySpartanBlack());
        projectSubmissionTitle.setTypeface(ViewAttributes.getIntanceViewAttributes(this).getMySpartanBlack());
        mTextInputGitHub.setTypeface(ViewAttributes.getIntanceViewAttributes(this).getMySpartanBlack());
        //Regular Typeface
        firstname.setTypeface(ViewAttributes.getIntanceViewAttributes(this).getMySpartanRegular());
        lastname.setTypeface(ViewAttributes.getIntanceViewAttributes(this).getMySpartanRegular());
        email.setTypeface(ViewAttributes.getIntanceViewAttributes(this).getMySpartanRegular());
        github.setTypeface(ViewAttributes.getIntanceViewAttributes(this).getMySpartanRegular());
    }

    private void setViewByIdTextInputs(){
        submit                  = findViewById(R.id.submitNow);
        email                   = findViewById(R.id.emailAddress);
        projectSubmissionTitle  = findViewById(R.id.project_submission);
        mEditTextEmailAddress   = findViewById(R.id.emailAddressEdit);
        mEditTextFirstname      = findViewById(R.id.firstnameEdit);
        mEditTextLastname       = findViewById(R.id.lastnameEdit);
        mTextInputGitHub        = findViewById(R.id.githubUrlEdit);
        firstname               = findViewById(R.id.firstname);
        lastname                = findViewById(R.id.lastname);
        github                  = findViewById(R.id.githubUrl);
    }

    //Set class member fields for Form
    private void setGoogleFormProperty(){
        mGoogleForm.setFirstname(mEditTextFirstname.getText().toString());
        mGoogleForm.setLastname(mEditTextLastname.getText().toString());
        mGoogleForm.setEmail(mEditTextEmailAddress.getText().toString());
        mGoogleForm.setGithubUrl(mTextInputGitHub.getText().toString());
    }

    public void showDialogMyFragDialogBox(View view){
        FragmentManager manager = getSupportFragmentManager();
        myDialogFragBox.show(manager, "MyFragDialogBox");
    }

    View.OnClickListener mOnClickListener   = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //setGoogleFormProperty();
            if(validateRow()) {
                showDialogMyFragDialogBox(view);
            }
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent   = new Intent(this, LeadersBoardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP  | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            Intent intent   = new Intent(this, LeadersBoardActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP  | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDialogBoxConfirm(boolean s) {
        if(s){
            FormService formService     = ServiceBuilder.buildService(FormService.class);
            Call<Void> createRequest    = formService.submitGoogleForm(
                    ALT_URL_STRING,
                    mEditTextFirstname.getText().toString(),
                    mEditTextLastname.getText().toString(),
                    mEditTextEmailAddress.getText().toString(),
                    mTextInputGitHub.getText().toString()
            );

            createRequest.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    myDialogFragBox.dismiss();
                    if(!response.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Error: "+response.code(), Toast.LENGTH_LONG).show();
                        return;
                    }
                    SuccessDialogFragBox successDialogFragBox   = new SuccessDialogFragBox();
                    successDialogFragBox.show(getSupportFragmentManager(), "success");
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    FailureDialogFragBox failureDialogFragBox   = new FailureDialogFragBox();
                    failureDialogFragBox.show(getSupportFragmentManager(), "failed");
                    Toast.makeText(getApplicationContext(), t.getMessage()+"", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @SuppressLint("ShowToast")
    private boolean validateRow(){
        Pattern pattern     = Pattern.compile(regEx);
        Matcher matcher     = pattern.matcher(mEditTextEmailAddress.getText().toString());
        if(mEditTextFirstname.getText().toString().equals("") || mEditTextFirstname.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Your Firstname Field is Empty", Toast.LENGTH_LONG);
            return false;
        }
        else if(mEditTextLastname.getText().toString().equals("") || mEditTextLastname.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Your Lastname Field is Empty", Toast.LENGTH_LONG);
            return false;
        }
        else if(mEditTextEmailAddress.getText().toString().equals("") || mEditTextEmailAddress.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Your Email Field is Empty", Toast.LENGTH_LONG);
            return false;
        }
        else if(mTextInputGitHub.getText().toString().equals("") || mTextInputGitHub.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Your Github Field is Empty", Toast.LENGTH_LONG);
            return false;
        }
        else if(!matcher.find()){
            Toast.makeText(getApplicationContext(), "Your Email is incorrect", Toast.LENGTH_LONG);
            return false;
        }
        return true;
    }
}