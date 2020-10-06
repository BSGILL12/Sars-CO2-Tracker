package com.example.coronavirustracker.ui.state;

public class CovidState {
 String mCovidState,mCases,mTodayCases,mDeaths,mTodayDeaths,mRecovered,mCritical;

    public CovidState(String mCovidState, String mCases) {
        this.mCovidState = mCovidState;
        this.mCases = mCases;
        this.mTodayCases = mTodayCases;
        this.mDeaths = mDeaths;
        this.mTodayDeaths = mTodayDeaths;
        this.mRecovered = mRecovered;
        this.mCritical = mCritical;
    }

    public String getmCovidState() {
        return mCovidState;
    }

    public String getmCases() {
        return mCases;
    }
}
