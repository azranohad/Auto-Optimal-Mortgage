package com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.commonServices;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class PMTServiceImpl implements PMTService{

    public double calculatePMT(double amount, double interestRate, double numberOfPayments) {

        double rateTemp = interestRate / 1200;
        return amount * (rateTemp / (1 - Math.pow( (1 + rateTemp), (-1) * numberOfPayments)));
    }
}
