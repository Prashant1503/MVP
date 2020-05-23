package com.example.covidapp.Contractor;

import android.content.Context;

import com.example.covidapp.pojo.CountryWiseModel;

import java.util.List;

public interface CountryWiseContractor {

    interface View {

        void showSwipeRefreshLayout();

        void hideSwipeRefreshLayout();

        void showCountryWiseList(List<CountryWiseModel> countryWiseModelList);

        void showLoadingError(String errMessage);

    }

    interface Presenter {

        void loadCountryWiseData(Context context);

        void dropCountryView();


    }

    interface onResponseCallBacks {

        void onCountryResponse(List<CountryWiseModel> countryWiseModelList);

        void onError(String errMessage);

    }


}
