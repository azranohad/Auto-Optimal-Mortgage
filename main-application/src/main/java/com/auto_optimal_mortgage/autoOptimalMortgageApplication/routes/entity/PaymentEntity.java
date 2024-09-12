package com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.entity;

import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.enums.Scenario;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "payments")

public class PaymentEntity {

    @Id
    private Long routeId;

    private int paymentNumber;
    private double totalPayments;
    private double principal;
    private double interest;
    private double remainingBalance;
    Scenario scenario;

}
