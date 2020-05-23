package com.example.covidapp.Contractor;

import android.content.Context;

import com.example.covidapp.pojo.WorldWidePojo;

import java.util.List;

public interface WorldWideContractor {

    interface View {

        void showSnackBar();

        void hideSnackBar();

        void showWorldWideData(List<WorldWidePojo> worldWidePojoList);

        void showLoadingError(String errMessage);

    }

    interface Presenter {

        void loadWorldWideData(Context context);

        void dropView();


    }

    interface onResponseCallBack {

        void onResponse(List<WorldWidePojo> worldWidePojoList);

        void onError(String errMessage);
    }
}
