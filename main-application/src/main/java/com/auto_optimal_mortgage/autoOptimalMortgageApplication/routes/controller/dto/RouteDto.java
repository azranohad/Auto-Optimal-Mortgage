package com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.controller.dto;

import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.enums.RouteType;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.service.model.Route;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;

@Builder
@Getter
public class RouteDto implements Serializable {

    private Long id;

    @NonNull
    private RouteType routeType;
    @NonNull
    private Integer numberOfPayments;
    @NonNull
    private Double amount;
    @NonNull
    private Double interestRate;
    private double maxPaymentWorstCase;
    private double maxPaymentBaseCase;
    private double totalPaymentWorstCase;
    private double totalPaymentBaseCase;


    public static RouteDto fromModel(Route model) {
        return RouteDto.builder()
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
