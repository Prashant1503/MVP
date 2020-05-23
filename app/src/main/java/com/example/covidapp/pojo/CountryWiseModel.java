package com.example.covidapp.pojo;

public class CountryWiseModel {

    private String countryName;
    private String confirmedCases;
    private String newConfirmedCases;
    private String deathCases;
    private String newDeathCases;
    private String recoveredCases;
    private String newRecoveredCases;

    public CountryWiseModel(String countryName, String confirmedCases, String newConfirmedCases, String deathCases, String newDeathCases, String recoveredCases, String newRecoveredCases) {
        this.countryName = countryName;
        this.confirmedCases = confirmedCases;
        this.newConfirmedCases = newConfirmedCases;
        this.deathCases = deathCases;
        this.newDeathCases = newDeathCases;
        this.recoveredCases = recoveredCases;
        this.newRecoveredCases = newRecoveredCases;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getConfirmedCases() {
        return confirmedCases;
    }

    public void setConfirmedCases(String confirmedCases) {
        this.confirmedCases = confirmedCases;
    }

    public String getNewConfirmedCases() {
        return newConfirmedCases;
    }

    public void setNewConfirmedCases(String newConfirmedCases) {
        this.newConfirmedCases = newConfirmedCases;
    }

    public String getDeathCases() {
        return deathCases;
    }

    public void setDeathCases(String deathCases) {
        this.deathCases = deathCases;
    }

    public String getNewDeathCases() {
        return newDeathCases;
    }

    public void setNewDeathCases(String newDeathCases) {
        this.newDeathCases = newDeathCases;
    }

    public String getRecoveredCases() {
        return recoveredCases;
    }

    public void setRecoveredCases(String recoveredCases) {
        this.recoveredCases = recoveredCases;
    }

    public String getNewRecoveredCases() {
        return newRecoveredCases;
    }

    public void setNewRecoveredCases(String newRecoveredCases) {
        this.newRecoveredCases = newRecoveredCases;
    }
}

