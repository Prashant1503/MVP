package com.example.covidapp.Model.Utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.covidapp.Contractor.Constants;
import com.example.covidapp.Contractor.CountryWiseContractor;
import com.example.covidapp.Contractor.CountryWiseUtil;
import com.example.covidapp.pojo.CountryWiseModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CountryWiseUtilImpl implements CountryWiseUtil {
    @Override
    public void getCountryWiseList(Context context, final CountryWiseContractor.onResponseCallBacks mOnCallBacks) {

        final List<CountryWiseModel> countryWiseModelList = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, Constants.COUNTRY_WISE_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray Countries = response.getJSONArray("Countries");

                    for (int i =0;i < Countries.length();i++) {
                        JSONObject responseData = Countries.getJSONObject(i);

                        String countryName = responseData.getString("Country");
                        String confirmedCases = responseData.getString("TotalConfirmed");
                        String newConfirmedCase = responseData.getString("NewConfirmed");
                        String totalDeath = responseData.getString("TotalDeaths");
                        String newDeath = responseData.getString("NewDeaths");
                        String totalRecovered = responseData.getString("TotalRecovered");
                        String newRecovered = responseData.getString("NewRecovered");

                        countryWiseModelList.add(new CountryWiseModel(countryName,confirmedCases,newConfirmedCase,totalDeath,newDeath,totalRecovered,newRecovered));
                        mOnCallBacks.onCountryResponse(countryWiseModelList);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        mOnCallBacks.onError(error.getMessage());

                    }
                });queue.add(objectRequest);

    }
}
