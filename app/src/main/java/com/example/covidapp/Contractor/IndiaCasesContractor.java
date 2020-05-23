package com.example.covidapp.Contractor;

import android.content.Context;

import com.example.covidapp.pojo.IndiaCasesModel;
import com.example.covidapp.pojo.WorldWidePojo;

import java.util.List;

public interface IndiaCasesContractor {

    interface View {

        void showProgressBar();

        void hideProgressBar();

        void showIndiaCasesData(List<IndiaCasesModel> indiaCasesModelList);

        void showLoadingError(String errMessage);

    }

    interface Presenter {

        void loadIndiaData(Context context);

        void dropIndiaView();


    }

    interface onResponseCallBack {

        void onResponse(List<IndiaCasesModel> indiaCasesModelList);

        void onError(String errMessage);
    }
}
