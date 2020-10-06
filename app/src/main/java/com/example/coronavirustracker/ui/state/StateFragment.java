package com.example.coronavirustracker.ui.state;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coronavirustracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StateFragment extends Fragment {
    RecyclerView rvCovidState;
    ProgressBar progressBar;

    private static final String TAG=StateFragment.class.getSimpleName();
    ArrayList<CovidState> covidStates;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_state, container, false);


        rvCovidState=root.findViewById(R.id.rvCovidState);
        progressBar=root.findViewById(R.id.progress_circular_state);
        rvCovidState.setLayoutManager(new LinearLayoutManager(getActivity()));


        getDataFromServer();

        return root;
    }

    public void showRecyclerView(){
        CovidStateAdapter covidStateAdapter =new CovidStateAdapter(covidStates);
        rvCovidState.setAdapter(covidStateAdapter);
    }

    private void getDataFromServer()
    {
        String url= "apify.com/zuzka/covid-in";
        covidStates=new ArrayList<>();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onResponse : " + response);
                }
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject data = jsonArray.getJSONObject(i);
                        covidStates.add(new CovidState(data.getString("state"), data.getString("cases")));
                    }
                    showRecyclerView();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                progressBar.setVisibility(View.GONE);
                Log.e(TAG,"onResponse" + error);
            }
        });
        Volley.newRequestQueue(getActivity()).add(stringRequest);


    }
}