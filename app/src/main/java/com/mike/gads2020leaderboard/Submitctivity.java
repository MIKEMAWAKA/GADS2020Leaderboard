package com.mike.gads2020leaderboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mike.gads2020leaderboard.Retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Submitctivity extends AppCompatActivity {

    EditText fname,lname,emaili,link;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitctivity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setTitle(null);

        fname = findViewById(R.id.firsttname);
        lname = findViewById(R.id.lname);
        emaili = findViewById(R.id.email);
        link = findViewById(R.id.linkgithub);

        btn = findViewById(R.id.submitbtnh);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confimdilog();
            }
        });

        inputIsValid();




    }

    private void confimdilog() {

        final AlertDialog alertDialog;

        AlertDialog.Builder settingdialog = new AlertDialog.Builder(Submitctivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View settinview= inflater.inflate(R.layout.confim, null);

        ImageView cancel = settinview.findViewById(R.id.cancel);
        TextView yes = settinview.findViewById(R.id.comfimy);
        settingdialog.setView(settinview);
        alertDialog = settingdialog.create();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
                alertDialog.dismiss();
            }
        });





        alertDialog.show();
//        alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,900); //Controlling width and height.


    }

    private void submit() {

        String firstName = fname.getText().toString().trim();
        String lastName = lname.getText().toString().trim();
        String email = emaili.getText().toString().trim();
        String LinkToProject = link.getText().toString().trim();

        ApiClient.getGoogleDocsClient().submitProject(firstName,lastName,email,LinkToProject)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){

                            success();

                        } else {
                          failss();
                        }


                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                        failss();
                    }
                });

    }



    private boolean inputIsValid() {
        if (fname.getText().toString().trim().equals("")){
            fname.setError("First name is required");
            return false;
        }
        if (lname.getText().toString().trim().equals("")) {
            lname.setError("Last name is required");
            return false;
        }
        if (emaili.getText().toString().trim().equals("")) {
            emaili.setError("Email address is required");
            return false;
        }
        if (link.getText().toString().trim().equals("")) {
            link.setError("GitHub link is required");
            return false;
        }
        return true;
    }



    private void failss() {
        final AlertDialog alertDialog;

        AlertDialog.Builder settingdialog = new AlertDialog.Builder(Submitctivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View settinview= inflater.inflate(R.layout.subimitnotsuccess, null);

        settingdialog.setView(settinview);
        alertDialog = settingdialog.create();
        alertDialog.show();
    }

    private void success() {

        final AlertDialog alertDialog;

        AlertDialog.Builder settingdialog = new AlertDialog.Builder(Submitctivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View settinview= inflater.inflate(R.layout.subimitsuccess, null);

        settingdialog.setView(settinview);
        alertDialog = settingdialog.create();
        alertDialog.show();
    }




}

