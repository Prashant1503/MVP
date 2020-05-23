package com.example.covidapp.Presenter;

import android.content.Context;

import com.example.covidapp.Contractor.IndiaCasesContractor;
import com.example.covidapp.Model.Utils.IndiaCasesUtilImpl;
import com.example.covidapp.pojo.IndiaCasesModel;

import java.util.List;

public class IndiaCasePresenterImpl implements IndiaCasesContractor.Presenter {

    private IndiaCasesContractor.View mView;
    private IndiaCasesUtilImpl indiaCasesModelImpl;

    public IndiaCasePresenterImpl(IndiaCasesContractor.View mView, IndiaCasesUtilImpl indiaCasesUtilImpl) {
        this.mView = mView;
        this.indiaCasesModelImpl = indiaCasesUtilImpl;
    }



    @Override
    public void loadIndiaData(Context context) {
        mView.showProgressBar();
        indiaCasesModelImpl.getIndiaCases(context,mCallBack);


    }


    @Override
    public void dropIndiaView() {
        mView =null;

    }

    private final IndiaCasesContractor.onResponseCallBack mCallBack = new IndiaCasesContractor.onResponseCallBack() {
        @Override
        public void onResponse(List<IndiaCasesModel> indiaCasesModelList) {
            mView.showIndiaCasesData(indiaCasesModelList);
            mView.hideProgressBar();
        }

        @Override
        public void onError(String errMessage) {
            mView.hideProgressBar();
            mView.showLoadingError(errMessage);

        }
    };
}
