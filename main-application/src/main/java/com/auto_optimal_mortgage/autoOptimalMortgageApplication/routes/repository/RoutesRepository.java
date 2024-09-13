package com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.repository;

import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.entity.RouteEntity;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.enums.RouteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface RoutesRepository  extends JpaRepository<RouteEntity, Long> {

    RouteEntity findByRouteTypeAndAmountAndNumberOfPaymentsAndInterestRate(RouteType routeType, Double amount, Integer numberOfPayments, Double interestRate);


    List<RouteEntity> findByRouteType(RouteType routeType);


    @Transactional
    @Modifying
    @Query("DELETE FROM RouteEntity r WHERE r.routeType = :routeType")
    void deleteByRouteType(RouteType routeType);

    @Override
    void deleteAll();

}
