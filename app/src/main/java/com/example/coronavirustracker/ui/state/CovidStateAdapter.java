package com.example.coronavirustracker.ui.state;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coronavirustracker.R;

import java.util.ArrayList;

public class CovidStateAdapter extends RecyclerView.Adapter<CovidStateAdapter.ViewHolder> {
    ArrayList<CovidState> covidStates;
    public CovidStateAdapter(ArrayList<CovidState>covidStates){
        this.covidStates=covidStates;
    }


    @NonNull
    @Override
    public CovidStateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_covid_state,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CovidStateAdapter.ViewHolder holder, int position) {
        CovidState covidState=covidStates.get(position);
        holder.tvTotalCases.setText(covidState.getmCases());
        holder.tvStateName.setText(covidState.getmCovidState());

    }

    @Override
    public int getItemCount() {

        return covidStates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTotalCases,tvStateName;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            tvTotalCases= itemView.findViewById(R.id.tvTotalCases);
            tvStateName=itemView.findViewById(R.id.tvStateName);

        }
    }
}
