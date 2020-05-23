package com.example.covidapp.Presenter;

import android.content.Context;

import com.example.covidapp.Contractor.CountryWiseContractor;
import com.example.covidapp.Model.Utils.CountryWiseUtilImpl;
import com.example.covidapp.pojo.CountryWiseModel;

import java.util.List;

public class CountryWisePresenterImpl implements CountryWiseContractor.Presenter {

    private CountryWiseContractor.View mView;
    private CountryWiseUtilImpl mCountryWiseUtilImpl;

    public CountryWisePresenterImpl(CountryWiseContractor.View mView, CountryWiseUtilImpl mCountryWiseUtilImpl) {
        this.mView = mView;
        this.mCountryWiseUtilImpl = mCountryWiseUtilImpl;
    }

    @Override
    public void loadCountryWiseData(Context context) {
        mView.showSwipeRefreshLayout();
        mCountryWiseUtilImpl.getCountryWiseList(context,mOnCallBacks);

    }


    @Override
    public void dropCountryView() {
        mView = null;
    }

    private final CountryWiseContractor.onResponseCallBacks mOnCallBacks = new CountryWiseContractor.onResponseCallBacks() {
        @Override
        public void onCountryResponse(List<CountryWiseModel> countryWiseModelList) {
            mView.showCountryWiseList(countryWiseModelList);
            mView.hideSwipeRefreshLayout();
        }

        @Override
        public void onError(String errMessage) {
            mView.hideSwipeRefreshLayout();
            mView.showLoadingError(errMessage);

        }
    };
}
