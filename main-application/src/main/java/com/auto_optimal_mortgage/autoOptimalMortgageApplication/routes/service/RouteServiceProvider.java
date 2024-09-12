package com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.service;

import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.enums.RouteType;

public interface RouteServiceProvider {

    RouteService getRouteService(RouteType routeType);
}
