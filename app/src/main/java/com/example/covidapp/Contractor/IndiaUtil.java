package com.example.covidapp.Contractor;

import android.content.Context;

public interface IndiaUtil {

    void getIndiaCases(Context context, IndiaCasesContractor.onResponseCallBack mOnResponseCallBack);
}
