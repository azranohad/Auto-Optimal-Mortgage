package com.auto_optimal_mortgage.autoOptimalMortgageApplication.CPI.repository.entity;

import com.auto_optimal_mortgage.autoOptimalMortgageApplication.CPI.controller.dto.CPIDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class CPIEntity {
    @Id
    @Column(name = "Year", length = 30, nullable = false, unique = true)
    private int year;
    private double worstValue;
    private double baseValue;


    public static CPIEntity fromDto(CPIDto dto) {
        return CPIEntity.builder()
                .year(dto.getYear())
                .baseValue(dto.getBaseValue())
                .worstValue(dto.getWorstValue())
                .build();
    }
}
