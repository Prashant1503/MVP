package com.example.covidapp.View.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidapp.R;
import com.example.covidapp.pojo.SymptomsModel;

import java.util.List;

public class SymptomsRcAdapter extends RecyclerView.Adapter<SymptomsRcAdapter.SymptomsRcViewholder> {

    private List<SymptomsModel> symptomsModelList;
    private Context mContext;

    public SymptomsRcAdapter(List<SymptomsModel> symptomsModelList, Context mContext) {
        this.symptomsModelList = symptomsModelList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public SymptomsRcViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.symptoms_rc_item_layout,parent,false);

        return new SymptomsRcViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SymptomsRcViewholder holder, int position) {

        SymptomsModel model = symptomsModelList.get(position);

        holder.image.setImageResource(model.getImage());
        holder.title.setText(model.getTitle());

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class SymptomsRcViewholder extends RecyclerView.ViewHolder {

        AppCompatImageView image;
        AppCompatTextView title;


        public SymptomsRcViewholder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.symptomsImageView);
            title = itemView.findViewById(R.id.symptomsTitleTv);
        }
    }
}
