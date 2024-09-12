package com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.service.model;

import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.controller.dto.RouteDto;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.entity.RouteEntity;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.enums.RouteType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Route {

    private Long id;
    private RouteType routeType;
    private int numberOfPayments;
    private double amount;
    private double interestRate;
    private double maxPaymentWorstCase;
    private double maxPaymentBaseCase;
    private double totalPaymentWorstCase;
    private double totalPaymentBaseCase;


    public static Route fromEntity(RouteEntity routeEntity) {
        if (routeEntity == null) {
            return null;
        }
        return Route.builder()
                .id(routeEntity.getId())
                .routeType(routeEntity.getRouteType())
                .numberOfPayments(routeEntity.getNumberOfPayments())
                .amount(routeEntity.getAmount())
                .interestRate(routeEntity.getInterestRate())
                .maxPaymentWorstCase(routeEntity.getMaxPaymentWorstCase())
                .maxPaymentBaseCase(routeEntity.getMaxPaymentBaseCase())
                .totalPaymentWorstCase(routeEntity.getTotalPaymentWorstCase())
                .totalPaymentBaseCase(routeEntity.getTotalPaymentBaseCase())
                .build();
    }

    public static Route fromDto(RouteDto dto) {
        if (dto == null) {
            return null;
        }
        return Route.builder()
                .id(dto.getId())
                .routeType(dto.getRouteType())
                .numberOfPayments(dto.getNumberOfPayments())
                .amount(dto.getAmount())
                .interestRate(dto.getInterestRate())
                .maxPaymentWorstCase(dto.getMaxPaymentWorstCase())
                .maxPaymentBaseCase(dto.getMaxPaymentBaseCase())
                .totalPaymentWorstCase(dto.getTotalPaymentWorstCase())
                .totalPaymentBaseCase(dto.getTotalPaymentBaseCase())
                .build();
    }
}
