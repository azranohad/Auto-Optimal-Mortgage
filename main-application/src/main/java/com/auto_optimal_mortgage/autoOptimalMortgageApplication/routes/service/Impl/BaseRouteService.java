package com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.service.Impl;

import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.entity.RouteEntity;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.repository.RoutesRepository;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.service.RouteService;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.service.model.Route;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseRouteService implements RouteService {

    @Autowired
    private RoutesRepository routesRepository;

    public Route getRouteIfExist(Route route) {
        return Route.fromEntity(routesRepository.findByRouteTypeAndAmountAndNumberOfPaymentsAndInterestRate(route.getRouteType(),
                        route.getAmount(),
                        route.getNumberOfPayments(),
                        route.getInterestRate()));
    }

    @Override
    public Route saveRoute(Route route) {
        return Route.fromEntity(routesRepository.save(RouteEntity.builder()
                .routeType(route.getRouteType())
                .numberOfPayments(route.getNumberOfPayments())
                .amount(route.getAmount())
                .interestRate(route.getInterestRate())
                .maxPaymentWorstCase(route.getMaxPaymentWorstCase())
                .maxPaymentBaseCase(route.getMaxPaymentBaseCase())
                .totalPaymentWorstCase(route.getTotalPaymentWorstCase())
                .totalPaymentBaseCase(route.getTotalPaymentBaseCase())
                .build()));
    }

    /*
        * Delete all routes of the current type
     */
    @Override
    public void deleteAllByRouteType() {
        routesRepository.deleteByRouteType(getRouteType());
    }

    /*
        * Clear all routes
     */
    @Override
    public void deleteAll() {
        routesRepository.deleteAll();
    }
}
