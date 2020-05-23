package com.example.covidapp.pojo;

public class WorldWidePojo {

    private String confirmedCases;
    private String activeCases;
    private String deathCases;
    private String recoveredCases;

    public WorldWidePojo(String confirmedCases, String activeCases, String deathCases, String recoveredCases) {
        this.confirmedCases = confirmedCases;
        this.activeCases = activeCases;
        this.deathCases = deathCases;
        this.recoveredCases = recoveredCases;
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
}
