package com.example.covidapp.Presenter;

import android.content.Context;

import com.example.covidapp.Contractor.WorldWideContractor;
import com.example.covidapp.Model.Utils.WorldWideUtilImpl;
import com.example.covidapp.pojo.WorldWidePojo;

import java.util.List;

public class WorldWidePresenterImpl implements WorldWideContractor.Presenter {

    private WorldWideContractor.View mView;
    private WorldWideUtilImpl worldWideUtilImpl;

    public WorldWidePresenterImpl(WorldWideContractor.View mView, WorldWideUtilImpl worldWideUtilImpl) {
        this.mView = mView;
        this.worldWideUtilImpl = worldWideUtilImpl;
    }

    @Override
    public void loadWorldWideData(Context context) {
//        mView.showSnackBar();
        worldWideUtilImpl.getWorldWideCasesList(context,mCallBack);

    }

    private final WorldWideContractor.onResponseCallBack mCallBack = new WorldWideContractor.onResponseCallBack() {
        @Override
        public void onResponse(List<WorldWidePojo> worldWidePojoList) {
            mView.showWorldWideData(worldWidePojoList);
            mView.hideSnackBar();

        }


        @Override
        public void onError(String errMessage) {
            mView.hideSnackBar();
            mView.showLoadingError(errMessage);
        }
    };


    @Override
    public void dropView() {
        mView =null;
    }
}
