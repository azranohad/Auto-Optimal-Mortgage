package com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.commonServices;

public interface PMTService {

    public double calculatePMT(double pv, double rate, double nper);
}
