package com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.service;

import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.enums.RouteType;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.service.model.Route;

public interface RouteService {

    Route calculatePayments(Route route);

    Route saveRoute(Route route);

    RouteType getRouteType();

    void deleteAllByRouteType();

    void deleteAll();


}
