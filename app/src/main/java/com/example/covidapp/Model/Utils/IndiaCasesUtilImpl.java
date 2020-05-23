package com.example.covidapp.Model.Utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.covidapp.Contractor.Constants;
import com.example.covidapp.Contractor.IndiaCasesContractor;
import com.example.covidapp.Contractor.IndiaUtil;
import com.example.covidapp.Contractor.WorldWideContractor;
import com.example.covidapp.pojo.IndiaCasesModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class IndiaCasesUtilImpl implements IndiaUtil {


    @Override
    public void getIndiaCases(Context context, final IndiaCasesContractor.onResponseCallBack mOnResponseCallBack) {

        final List<IndiaCasesModel> indiaCasesModelList = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, Constants.INDIA_CASES_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    for (int i =0;i < response.length();i++) {

                        String confirmedCases = response.getString("cases");
                        String activeCases = response.getString("active");
                        String deathCases = response.getString("deaths");
                        String recoveredCases = response.getString("recovered");
                        String perDayCases = response.getString("todayCases");

                        indiaCasesModelList.add(new IndiaCasesModel(confirmedCases,activeCases,deathCases,recoveredCases,perDayCases));
                        mOnResponseCallBack.onResponse(indiaCasesModelList);

                    }

                }
                catch (Exception e) {
                    mOnResponseCallBack.onError(e.getMessage());

                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mOnResponseCallBack.onError(error.getMessage());

                    }
                });queue.add(objectRequest);

    }
}
