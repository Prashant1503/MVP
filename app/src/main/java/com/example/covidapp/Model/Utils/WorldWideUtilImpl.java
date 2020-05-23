package com.example.covidapp.Model.Utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.covidapp.Contractor.Constants;
import com.example.covidapp.Contractor.WorldWideContractor;
import com.example.covidapp.Contractor.WorldWideUtil;
import com.example.covidapp.pojo.WorldWidePojo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WorldWideUtilImpl implements WorldWideUtil {
    @Override
    public void getWorldWideCasesList(Context context, final WorldWideContractor.onResponseCallBack mOnResponseCallBack) {

        RequestQueue queue = Volley.newRequestQueue(context);
        final List<WorldWidePojo> worldWidePojosList = new ArrayList<>();

        JsonObjectRequest worldObj = new JsonObjectRequest(Request.Method.GET, Constants.WORLD_WIDE_CASES_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    for (int i =0;i < response.length();i++) {

                        String confirmedCases = response.getString("cases");
                        String activeCases = response.getString("active");
                        String deathCases = response.getString("deaths");
                        String recoveredCases = response.getString("recovered");

                        worldWidePojosList.add(new WorldWidePojo(confirmedCases,activeCases,deathCases,recoveredCases));
                        mOnResponseCallBack.onResponse(worldWidePojosList);

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
        });queue.add(worldObj);
    }
}
