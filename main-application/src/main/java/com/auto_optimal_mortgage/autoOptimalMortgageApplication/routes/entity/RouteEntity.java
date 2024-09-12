package com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.entity;


import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.enums.RouteType;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.enums.Scenario;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.service.model.Route;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "routes")
public class RouteEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private RouteType routeType;
    private int numberOfPayments;
    private double amount;
    private double interestRate;
    private double maxPaymentWorstCase;
    private double maxPaymentBaseCase;
    private double totalPaymentWorstCase;
    private double totalPaymentBaseCase;

    public static RouteEntity fromModel(Route model) {
        return RouteEntity.builder()
                .id(model.getId())
                .routeType(model.getRouteType())
                .numberOfPayments(model.getNumberOfPayments())
                .amount(model.getAmount())
                .interestRate(model.getInterestRate())
                .maxPaymentWorstCase(model.getMaxPaymentWorstCase())
                .maxPaymentBaseCase(model.getMaxPaymentBaseCase())
                .totalPaymentWorstCase(model.getTotalPaymentWorstCase())
                .totalPaymentBaseCase(model.getTotalPaymentBaseCase())
                .build();
    }
}
