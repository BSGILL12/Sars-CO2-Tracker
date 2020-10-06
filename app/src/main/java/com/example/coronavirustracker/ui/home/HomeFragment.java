package com.example.coronavirustracker.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coronavirustracker.R;

import org.json.JSONObject;

public class HomeFragment extends Fragment {
    private TextView tvTotalConfirmed,tvTotalDeaths,tvTotalRecovered;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        tvTotalConfirmed=root.findViewById(R.id.tvTotalConfirmed);
        tvTotalDeaths=root.findViewById(R.id.tvTotalDeaths);
        tvTotalRecovered=root.findViewById(R.id.tvTotalRecovered);
        progressBar=root.findViewById(R.id.progress_circular_home);
        
        getData();



        return root;
    }

    private void getData()
    {
        RequestQueue queue= Volley.newRequestQueue(getActivity());

        String url="https://corona.lmao.ninja/all";


        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);

                try {
                    JSONObject jsonobject = new JSONObject(response.toString());

                    tvTotalConfirmed.setText(jsonobject.getString("cases"));
                    tvTotalDeaths.setText(jsonobject.getString("deaths"));
                    tvTotalRecovered.setText(jsonobject.getString("recovered"));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Log.d("Error Response",error.toString());

            }
        });

    }
}