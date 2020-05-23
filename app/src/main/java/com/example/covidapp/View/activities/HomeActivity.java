package com.example.covidapp.View.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.covidapp.Contractor.Constants;
import com.example.covidapp.Contractor.IndiaCasesContractor;
import com.example.covidapp.Contractor.WorldWideContractor;
import com.example.covidapp.Model.Utils.IndiaCasesUtilImpl;
import com.example.covidapp.Model.Utils.WorldWideUtilImpl;
import com.example.covidapp.Presenter.IndiaCasePresenterImpl;
import com.example.covidapp.Presenter.WorldWidePresenterImpl;
import com.example.covidapp.R;
import com.example.covidapp.pojo.IndiaCasesModel;
import com.example.covidapp.pojo.SymptomsModel;
import com.example.covidapp.View.adapter.SymptomsRcAdapter;
import com.example.covidapp.pojo.WorldWidePojo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity implements WorldWideContractor.View, IndiaCasesContractor.View {

//    dashboard field..
    public AppCompatTextView confirmedCaseCountTv,activeCaseCountTv,deathCaseCountTv,recoveredCaseCountTv;
    public AppCompatTextView indiaConfirmedCasesCountTv,indiaActiveCasesCountTv,indiaDeathCasesCountTv,indiaRecoveredCasesCountTv;
    public AppCompatTextView indiaConfirmedPerDayCountTv,indiaActivePerDayCountTv,indiaDeathPerDayCountTv,indiaRecoveredPerDayCountTv;
    public FloatingActionButton refreshButton;
    public AppCompatButton moreInfoBtn;
    public LinearLayoutCompat otherCountryLayoutContainer;


    //    symtoms field.
    public RecyclerView symtomsRecyclerView;
    public SymptomsRcAdapter symptomsRcAdapter;
    public List<SymptomsModel> symptomsModelList;
    public SymptomsModel symptomsModel;

//    worldwide cases;
    public WorldWidePresenterImpl mPresenterImpl;
    public IndiaCasePresenterImpl mIndiaPresenterImpl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        initViews();
    }

    public void initViews() {

        mIndiaPresenterImpl = new IndiaCasePresenterImpl(this,new IndiaCasesUtilImpl());
        mPresenterImpl = new WorldWidePresenterImpl(this,new WorldWideUtilImpl());

        refreshButton = findViewById(R.id.refreshButton);


//        word wide fields..
        confirmedCaseCountTv = findViewById(R.id.confirmed_cases_count);
        activeCaseCountTv = findViewById(R.id.active_cases_count);
        deathCaseCountTv = findViewById(R.id.death_cases_count);
        recoveredCaseCountTv = findViewById(R.id.recovered_cases_count);

//        end..

//      india cases field..
        indiaConfirmedCasesCountTv = findViewById(R.id.IndiaConfirmed_case_tv);
        indiaActiveCasesCountTv = findViewById(R.id.active_IndiaTotal_casesTv);
        indiaDeathCasesCountTv = findViewById(R.id.IndiaDeath_total_cases);
        indiaRecoveredCasesCountTv= findViewById(R.id.IndiaRecovered_cases_tv);



//        increased cases field..
        indiaConfirmedPerDayCountTv = findViewById(R.id.increasing_IndiaConfirmed_cases_tv);
        indiaActivePerDayCountTv = findViewById(R.id.increasing_IndiaActive_cases_tv);
        indiaDeathPerDayCountTv = findViewById(R.id.increasing_IndiaDeath_cases_tv);
        indiaRecoveredPerDayCountTv = findViewById(R.id.increasing_IndiaRecovered_cases_tv);

//        more info btn..
        moreInfoBtn = findViewById(R.id.more_info_btn);
        otherCountryLayoutContainer =findViewById(R.id.seeCountryLayoutContainer);



//        init symtoms rc and set image and title
        symtomsRecyclerView = findViewById(R.id.symptoms_recyclerView);
        symptomsModelList = new ArrayList<>();
//        adding data
        symptomsModelList.add(new SymptomsModel(R.string.fever_txt,R.drawable.fever));
        symptomsModelList.add(new SymptomsModel(R.string.cough_txt,R.drawable.cough));
        symptomsModelList.add(new SymptomsModel(R.string.breath_txt,R.drawable.breath));
        symptomsModelList.add(new SymptomsModel(R.string.shore_txt,R.drawable.shore_throat));
        symptomsModelList.add(new SymptomsModel(R.string.headache_txt,R.drawable.headache));

        symtomsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        symptomsModelList.add(symptomsModel);
        symptomsRcAdapter = new SymptomsRcAdapter(symptomsModelList,getApplicationContext());
        symtomsRecyclerView.setAdapter(symptomsRcAdapter);

//        setting listner on refreshbtn..

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mIndiaPresenterImpl.loadIndiaData(getApplicationContext());


                Log.d("Resonse", "onClick: " + "Updated data will be comed upo....");
            }
        });

        moreInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,CovidInfoActivity.class));

            }
        });
        otherCountryLayoutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,CountryListActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        mPresenterImpl.loadWorldWideData(getApplicationContext());
        mIndiaPresenterImpl.loadIndiaData(getApplicationContext());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenterImpl.dropView();
        mIndiaPresenterImpl.dropIndiaView();

        Log.d(Constants.TAG, "onDestroy: " + "View is dropped");
    }

    @Override
    public void showSnackBar() {

       Toast.makeText(getApplicationContext(),"Getting updated data",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideSnackBar() {
        Log.d("TAG", "hideSnackBar: " + "Hide");

    }


    @Override
    public void showProgressBar() {
        Toast.makeText(getApplicationContext(),"Refreshing data..",Toast.LENGTH_SHORT).show();


    }

    @Override
    public void hideProgressBar() {

        Log.d(Constants.TAG, "hideProgressBar: " + "Something went wrong..");
    }

    @Override
    public void showIndiaCasesData(List<IndiaCasesModel> indiaCasesModelList) {
        if (!indiaCasesModelList.isEmpty()) {
            for (int i =0;i < indiaCasesModelList.size();i++) {

                String confirmedCases = indiaCasesModelList.get(i).getConfirmedCases();
                String activeCases = indiaCasesModelList.get(i).getActiveCases();
                String recoveredCases = indiaCasesModelList.get(i).getRecoveredCases();
                String deathCases = indiaCasesModelList.get(i).getDeathCases();
                String todayConfirmedCases = indiaCasesModelList.get(i).getPerDayCases();



                indiaConfirmedCasesCountTv.setText(confirmedCases);
                indiaActiveCasesCountTv.setText(activeCases);
                indiaDeathCasesCountTv.setText(deathCases);
                indiaRecoveredCasesCountTv.setText(recoveredCases);
                indiaActivePerDayCountTv.setText(todayConfirmedCases);

            }
        }
        else
        {
            Log.d(Constants.TAG, "showIndiaCasesData: " + "Something went wrong");
        }


    }

    @Override
    public void showWorldWideData(List<WorldWidePojo> worldWidePojoList) {
        if (!worldWidePojoList.isEmpty()) {

            for (int i =0;i < worldWidePojoList.size();i++) {

                String confirmedCases = worldWidePojoList.get(i).getConfirmedCases();
                String activeCases = worldWidePojoList.get(i).getActiveCases();
                String recoveredCases = worldWidePojoList.get(i).getRecoveredCases();
                String deathCases = worldWidePojoList.get(i).getDeathCases();

                confirmedCaseCountTv.setText(confirmedCases);
                activeCaseCountTv.setText(activeCases);
                recoveredCaseCountTv.setText(recoveredCases);
                deathCaseCountTv.setText(deathCases);


            }
        }
        else

        {
            Log.d(Constants.TAG, "showWorldWideData: " + "Something went wrong");
            confirmedCaseCountTv.setText(0);
            activeCaseCountTv.setText(0);
            recoveredCaseCountTv.setText(0);
            deathCaseCountTv.setText(0);
        }

    }

    @Override
    public void showLoadingError(String errMessage) {


        Log.d(Constants.TAG, "showLoadingError: " + errMessage.toString());
    }
}
