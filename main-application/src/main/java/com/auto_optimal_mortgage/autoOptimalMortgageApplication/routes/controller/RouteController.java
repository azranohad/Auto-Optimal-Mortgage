package com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.controller;

import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.controller.dto.RouteDto;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.enums.RouteType;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.service.RouteServiceProvider;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.service.model.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/routes")
@RequiredArgsConstructor
@Validated
public class RouteController {

    @Autowired
    private RouteServiceProvider routeServiceProvider;


    @PostMapping("/calc-route")
    public ResponseEntity<RouteDto> calcRoute(@RequestBody RouteDto route) {
        return ResponseEntity.ok()
                .body(RouteDto.fromModel
                        (routeServiceProvider.getRouteService(route.getRouteType()).calculatePayments(Route.fromDto(route))));

    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteAll() {
        routeServiceProvider.getRouteService(RouteType.FIXED_RATE).deleteAllByRouteType();
        return ResponseEntity.noContent().build();
    }

}
