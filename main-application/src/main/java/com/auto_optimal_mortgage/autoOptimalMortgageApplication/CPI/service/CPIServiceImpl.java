package com.auto_optimal_mortgage.autoOptimalMortgageApplication.CPI.service;

import com.auto_optimal_mortgage.autoOptimalMortgageApplication.CPI.repository.entity.CPIEntity;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.CPI.repository.CPIRepository;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.enums.Scenario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class CPIServiceImpl implements CPIService {


    private final CPIRepository cpiRepository;



    @Override
    public CPIEntity getCPI(int year) {
        return cpiRepository.findById(year).orElse(null);
    }

    @Override
    public void addCPI(CPIEntity cpi) {
        cpiRepository.save(cpi);
    }



    @Override
    public List<CPIEntity> getAllCPI() {

        return cpiRepository.findAll();
    }

    @Override
    public void setConstValue(CPIEntity cpi) {
        for (int i = 0; i < 30; i++) {
            addCPI(CPIEntity.builder()
                    .year(i)
                    .baseValue(cpi.getBaseValue())
                    .worstValue(cpi.getWorstValue())
                    .build());
        }
    }

    @Override
    public Map<Integer, CPIEntity> getAllCPIAsMap() {
        Map<Integer, CPIEntity> cpiMap = new HashMap<>();
        for (CPIEntity cpi : getAllCPI()) {
            cpiMap.put(cpi.getYear(), cpi);
        }
        return cpiMap;
    }

    @Override
    public double getMonthlyCpi(int numberOfPayment, Scenario routeType) {
        CPIEntity cpi = getCPI(numberOfPayment%12);
        if (routeType == Scenario.WORST_CASE) {
            return cpi.getWorstValue();
        } else {
            return cpi.getBaseValue();
        }
    }

    @Override
    public double getMonthlyCpi(Map<Integer, CPIEntity> cpiMap, int numberOfPayment, Scenario routeType) {
        CPIEntity cpi = cpiMap.get(numberOfPayment%12);
        double cpiValue = 0;
        if (routeType == Scenario.WORST_CASE) {
            cpiValue = cpi.getWorstValue();
        } else {
            cpiValue = cpi.getBaseValue();
        }
        return cpiValue/1200;
    }

}
