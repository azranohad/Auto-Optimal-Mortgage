package com.auto_optimal_mortgage.autoOptimalMortgageApplication.CPI.service;

import com.auto_optimal_mortgage.autoOptimalMortgageApplication.CPI.repository.entity.CPIEntity;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.enums.Scenario;

import java.util.List;
import java.util.Map;

public interface CPIService {


    public CPIEntity getCPI(int year);
    public void addCPI(CPIEntity cpi);
    List<CPIEntity> getAllCPI();
    void setConstValue(CPIEntity cpi);

    Map<Integer, CPIEntity> getAllCPIAsMap();

    double getMonthlyCpi(int numberOfPayment, Scenario routeType);
    double getMonthlyCpi(Map<Integer, CPIEntity> cpiMap, int numberOfPayment, Scenario routeType);
}
