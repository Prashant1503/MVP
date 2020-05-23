package com.example.covidapp.View.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidapp.R;
import com.example.covidapp.pojo.CountryWiseModel;

import java.util.ArrayList;
import java.util.List;

public class CountryWiseAdapter extends RecyclerView.Adapter<CountryWiseAdapter.CountryWiseRcHolder> {

    private Context context;
    private List<CountryWiseModel> countryWiseModelList = new ArrayList<>();

    public CountryWiseAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CountryWiseRcHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_wise_rc_item_layout,parent,false);

        return new CountryWiseRcHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryWiseRcHolder holder, int position) {

        CountryWiseModel model =countryWiseModelList.get(position);

        holder.confirmedCaseTv.setText(model.getConfirmedCases());


        holder.deathCasesTv.setText(model.getDeathCases());
        holder.recoveredCasesTv.setText(model.getRecoveredCases());

    }

    @Override
    public int getItemCount() {
        return countryWiseModelList.size();
    }

    public static class CountryWiseRcHolder extends RecyclerView.ViewHolder {

        AppCompatTextView countryNameTv;
        AppCompatTextView confirmedCaseTv,newConfirmedCases;
        AppCompatTextView deathCasesTv,todayDeathCasesTv;
        AppCompatTextView recoveredCasesTv,newRecoveredCases;


        public CountryWiseRcHolder(@NonNull View itemView) {
            super(itemView);

            countryNameTv = itemView.findViewById(R.id.countryNameTv);
            confirmedCaseTv = itemView.findViewById(R.id.country_confirmedCases);
            newConfirmedCases = itemView.findViewById(R.id.country_newConfirmedCases);

            deathCasesTv = itemView.findViewById(R.id.country_DeathCases);
            todayDeathCasesTv = itemView.findViewById(R.id.country_DeathtodayCases);

            recoveredCasesTv = itemView.findViewById(R.id.country_RecoveredCases);
            newRecoveredCases = itemView.findViewById(R.id.country_CriticalTodayCases);


        }
    }

    public void setList(List<CountryWiseModel> list){

        countryWiseModelList.clear();

        countryWiseModelList.addAll(list);

        notifyDataSetChanged();

        Log.d("TAG", "setList: " + countryWiseModelList.size());

    }

    public void updateList(List<CountryWiseModel> updatedList) {
        countryWiseModelList = updatedList;
        notifyDataSetChanged();
    }
}
