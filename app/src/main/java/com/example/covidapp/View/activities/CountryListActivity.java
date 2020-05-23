package com.example.covidapp.View.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.covidapp.Contractor.Constants;
import com.example.covidapp.Contractor.CountryWiseContractor;
import com.example.covidapp.Model.Utils.CountryWiseUtilImpl;
import com.example.covidapp.Presenter.CountryWisePresenterImpl;
import com.example.covidapp.R;
import com.example.covidapp.View.adapter.CountryWiseAdapter;
import com.example.covidapp.pojo.CountryWiseModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class CountryListActivity extends AppCompatActivity implements CountryWiseContractor.View {

    public Toolbar countryToolbar;
    public AppCompatEditText searchEdt;
    public RecyclerView countryRecyclerView;
    public CountryWiseAdapter countryWiseAdapter;
    public CountryWisePresenterImpl mCountryWiseImpl;
    public SwipeRefreshLayout countryRefreshLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        initViews();
    }
    private void initViews() {

        mCountryWiseImpl = new CountryWisePresenterImpl(this,new CountryWiseUtilImpl());

//        country view field..
        countryToolbar = findViewById(R.id.countryToolbar);
        searchEdt = findViewById(R.id.search_barEdt);

        countryRefreshLayout = findViewById(R.id.countryWiseSwipeRefreshLayout);
        countryRecyclerView = findViewById(R.id.country_wise_recyclerView);
        countryRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        countryWiseAdapter = new CountryWiseAdapter(getApplicationContext());
        countryRecyclerView.setAdapter(countryWiseAdapter);

//        swipe listner ..
        countryRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mCountryWiseImpl.loadCountryWiseData(getApplicationContext());
            }
        });

        searchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                Toast.makeText(getApplicationContext(),"Could not found",Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void showSwipeRefreshLayout() {
        countryRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideSwipeRefreshLayout() {

        countryRefreshLayout.setRefreshing(false);

    }

    @Override
    public void showCountryWiseList(List<CountryWiseModel> countryWiseModelList) {

        if (!countryWiseModelList.isEmpty()) {

            for (int i =0;i < countryWiseModelList.size();i++) {

                countryWiseAdapter.setList(countryWiseModelList);
            }

        }
        else {

            Log.d(Constants.CountryTag, "showCountryWiseList: " + "Something went wrong");
        }

    }

    @Override
    public void showLoadingError(String errMessage) {
        hideSwipeLayoutNdShowErr(errMessage);

    }

    public void hideSwipeLayoutNdShowErr(String errMessage) {
        Toast.makeText(getApplicationContext(),errMessage,Toast.LENGTH_SHORT).show();
        showOrHideCountryList(false);
    }

    public void showOrHideCountryList(boolean flag) {
        if (flag) {
            countryRecyclerView.setVisibility(View.VISIBLE);
        }
        else {
            countryRecyclerView.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mCountryWiseImpl.dropCountryView();
    }
}
