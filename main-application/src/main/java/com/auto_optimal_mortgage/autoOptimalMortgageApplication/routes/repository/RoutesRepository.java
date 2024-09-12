package com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.repository;

import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.entity.RouteEntity;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.enums.RouteType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoutesRepository  extends JpaRepository<RouteEntity, Long> {

    RouteEntity findByRouteTypeAndAmountAndNumberOfPaymentsAndInterestRate(RouteType routeType, Double amount, Integer numberOfPayments, Double interestRate);

    @Override
    void deleteAll();

}
