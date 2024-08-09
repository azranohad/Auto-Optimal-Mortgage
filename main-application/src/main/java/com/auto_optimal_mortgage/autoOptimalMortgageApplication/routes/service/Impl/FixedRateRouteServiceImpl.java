package com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.service.Impl;

import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.commonServices.PMTService;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.entity.PaymentEntity;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.entity.RouteEntity;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.enums.RouteType;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.enums.Scenario;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.repository.PaymentRepository;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.repository.RoutesRepository;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.service.RouteService;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.service.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FixedRateRouteServiceImpl extends BaseRouteService implements RouteService {

    @Autowired
    private PMTService pmtService;
    @Autowired
    private RoutesRepository routesRepository;
    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    public Route calculatePayments(Route route) {

        Route calculatedRoute = getRouteIfExist(route);
        if (calculatedRoute != null) {
            return calculatedRoute;
        }

        int remainPayments = route.getNumberOfPayments();
        double amountTemp = route.getAmount();
        double ratePaymentTemp = route.getInterestRate();
        double monthlyRepayment = pmtService.calculatePMT(amountTemp, ratePaymentTemp, remainPayments);

        // Save route to get the id
        route.setId(saveRoute(route).getId());

        double totalPayments = 0;
        for (; remainPayments > 0; remainPayments--) {
            totalPayments += monthlyRepayment;
            double ratePayment = amountTemp * ratePaymentTemp / 12;
            double principalPayment = monthlyRepayment - ratePayment;
            amountTemp -= principalPayment;


            paymentRepository.saveAndFlush(PaymentEntity.builder()
                    .routeId(route.getId())
                    .paymentNumber(route.getNumberOfPayments() - remainPayments + 1)
                    .principal(principalPayment)
                    .interest(ratePayment)
                    .totalPayments(totalPayments)
                    .remainingBalance(amountTemp)
                    .scenario(Scenario.NONE)
                    .build());
        }

        route.setTotalPaymentBaseCase(totalPayments);
        route.setMaxPaymentBaseCase(monthlyRepayment);
        route.setTotalPaymentWorstCase(totalPayments);
        route.setMaxPaymentWorstCase(monthlyRepayment);

        routesRepository.save(RouteEntity.fromModel(route));

        return Route.fromEntity(routesRepository.findById(route.getId()).get());
    }

    @Override
    public RouteType getRouteType() {
        return RouteType.FIXED_RATE;
    }
}
