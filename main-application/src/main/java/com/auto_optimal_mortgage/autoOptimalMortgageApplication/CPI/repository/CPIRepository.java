package com.auto_optimal_mortgage.autoOptimalMortgageApplication.CPI.repository;

import com.auto_optimal_mortgage.autoOptimalMortgageApplication.CPI.repository.entity.CPIEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CPIRepository extends JpaRepository<CPIEntity, Integer> {
}
