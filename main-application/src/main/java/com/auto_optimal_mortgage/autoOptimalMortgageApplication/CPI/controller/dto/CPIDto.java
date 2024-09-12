package com.auto_optimal_mortgage.autoOptimalMortgageApplication.CPI.controller.dto;

import com.auto_optimal_mortgage.autoOptimalMortgageApplication.CPI.repository.entity.CPIEntity;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class CPIDto implements Serializable {

    private int year;
    private double worstValue;
    private double baseValue;

    public static CPIDto fromEntity(CPIEntity entity) {
        return CPIDto.builder()
                .year(entity.getYear())
                .baseValue(entity.getBaseValue())
                .worstValue(entity.getWorstValue())
                .build();
    }
}
