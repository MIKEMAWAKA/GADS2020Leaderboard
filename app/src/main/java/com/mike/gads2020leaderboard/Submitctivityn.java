package com.mike.gads2020leaderboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mike.gads2020leaderboard.Volley.VolleySingleton;

import java.util.HashMap;
import java.util.Map;

public class Submitctivityn extends AppCompatActivity {

    EditText fname,lname,email,link;
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
        email = findViewById(R.id.email);
        link = findViewById(R.id.linkgithub);

        btn = findViewById(R.id.submitbtnh);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confimdilog();
            }
        });




    }

    private void confimdilog() {

        final AlertDialog alertDialog;

        AlertDialog.Builder settingdialog = new AlertDialog.Builder(Submitctivityn.this);
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

        String url = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(Submitctivityn.this,"Success" + response.toString(),Toast.LENGTH_SHORT).show();


                     if (response.toString().equals("Your response has been recorded")){

                        success();


                     }
                     else {
                          failss();
                     }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Submitctivityn.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("entry.1877115667",fname.getText().toString());
                params.put("entry.2006916086",lname.getText().toString());
                params.put("entry.1824927963",email.getText().toString());
                params.put("entry.284483984",link.getText().toString());
                return params;


            }
        };

        VolleySingleton.getInstance(Submitctivityn.this).addToRequestQueue(stringRequest);
    }

    private void failss() {
        final AlertDialog alertDialog;

        AlertDialog.Builder settingdialog = new AlertDialog.Builder(Submitctivityn.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View settinview= inflater.inflate(R.layout.subimitnotsuccess, null);

        settingdialog.setView(settinview);
        alertDialog = settingdialog.create();
        alertDialog.show();
    }

    private void success() {

        final AlertDialog alertDialog;

        AlertDialog.Builder settingdialog = new AlertDialog.Builder(Submitctivityn.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View settinview= inflater.inflate(R.layout.subimitsuccess, null);

        settingdialog.setView(settinview);
        alertDialog = settingdialog.create();
        alertDialog.show();
    }
}