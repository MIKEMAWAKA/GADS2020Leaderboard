package com.mike.gads2020leaderboard.Fragements;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.mike.gads2020leaderboard.Adapter.LearnerAdapter;
import com.mike.gads2020leaderboard.Model.Leaner;
import com.mike.gads2020leaderboard.R;
import com.mike.gads2020leaderboard.Volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.mike.gads2020leaderboard.Common.Common.URL_Learners;


public class LearningFragment extends Fragment {

    RecyclerView recyclerview;
    ArrayList<Leaner> leaners;

    LearnerAdapter learnerAdapter;



    public LearningFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_learning, container, false);

        recyclerview = view.findViewById(R.id.recyler);

        fetchingJSON();


        return view;
    }

    private void fetchingJSON() {


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL_Learners,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {



                        leaners = new ArrayList<>();



                        try{

                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject leanersd = response.getJSONObject(i);
                                Leaner leanermodel = new Leaner();


                                leanermodel.setName(leanersd.getString("name"));
                                leanermodel.setHours(leanersd.getInt("hours"));
                                leanermodel.setCountry(leanersd.getString("country"));
                                leanermodel.setBadgeurl(leanersd.getString("badgeUrl"));

                                leaners.add(leanermodel);


                            }

                            setupRecycler();
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred

                    }
                }
        );

        VolleySingleton.getInstance(this.getContext()).addToRequestQueue(jsonArrayRequest);

    }






    private void setupRecycler(){
        learnerAdapter = new LearnerAdapter(getContext(),leaners);
        recyclerview.setAdapter(learnerAdapter);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

    }
}