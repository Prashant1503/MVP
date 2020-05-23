package com.example.covidapp.pojo;

public class IndiaCasesModel {

    private String confirmedCases;
    private String activeCases;
    private String deathCases;
    private String recoveredCases;

//    live cases per day..

    private String perDayCases;

    public IndiaCasesModel(String confirmedCases, String activeCases, String deathCases, String recoveredCases, String perDayCases) {
        this.confirmedCases = confirmedCases;
        this.activeCases = activeCases;
        this.deathCases = deathCases;
        this.recoveredCases = recoveredCases;
        this.perDayCases = perDayCases;
    }

    public String getConfirmedCases() {
        return confirmedCases;
    }

    public void setConfirmedCases(String confirmedCases) {
        this.confirmedCases = confirmedCases;
    }

    public String getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(String activeCases) {
        this.activeCases = activeCases;
    }

    public String getDeathCases() {
        return deathCases;
    }

    public void setDeathCases(String deathCases) {
        this.deathCases = deathCases;
    }

    public String getRecoveredCases() {
        return recoveredCases;
    }

    public void setRecoveredCases(String recoveredCases) {
        this.recoveredCases = recoveredCases;
    }

    public String getPerDayCases() {
        return perDayCases;
    }

    public void setPerDayCases(String perDayCases) {
        this.perDayCases = perDayCases;
    }
}
