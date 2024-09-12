package com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.repository;

import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
