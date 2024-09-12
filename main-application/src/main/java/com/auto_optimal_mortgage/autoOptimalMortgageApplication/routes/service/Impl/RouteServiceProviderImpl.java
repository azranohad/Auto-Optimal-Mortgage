package com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.service.Impl;

import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.enums.RouteType;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.service.RouteService;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.service.RouteServiceProvider;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RouteServiceProviderImpl implements RouteServiceProvider {

    @Autowired
    private List<RouteService> routeServices;

    private Map<RouteType, RouteService> routeServiceMap;

    @PostConstruct
    private void init(){
        routeServiceMap = routeServices.stream().collect(Collectors.toMap(RouteService::getRouteType, Function.identity()));
    }
    @Override
    public RouteService getRouteService(RouteType routeType) {
        return routeServiceMap.get(routeType);
    }
}
