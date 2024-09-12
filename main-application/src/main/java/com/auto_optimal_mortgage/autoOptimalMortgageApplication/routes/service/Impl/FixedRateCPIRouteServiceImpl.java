package com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.service.Impl;

import com.auto_optimal_mortgage.autoOptimalMortgageApplication.CPI.repository.entity.CPIEntity;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.CPI.service.CPIService;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.commonServices.PMTService;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.entity.PaymentEntity;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.enums.RouteType;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.enums.Scenario;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.repository.PaymentRepository;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.repository.RoutesRepository;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.service.RouteService;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.routes.service.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Map;

@Service
public class FixedRateCPIRouteServiceImpl extends BaseRouteService implements RouteService {

    @Autowired
    private PMTService pmtService;
    @Autowired
    private RoutesRepository routesRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CPIService cpiService;

    @Override
    public Route calculatePayments(Route route) {

        Route calculatedRoute = getRouteIfExist(route);
        if (calculatedRoute != null) {
            return calculatedRoute;
        }

        route.setId(saveRoute(route).getId());
        Map<Integer, CPIEntity> cpiMap = cpiService.getAllCPIAsMap();

        EnumSet.allOf(Scenario.class).forEach(scenario -> {
            int remainPayments = route.getNumberOfPayments();
            double amountTemp = route.getAmount();
            double ratePaymentTemp = route.getInterestRate();

            double totalPayments = 0;
            double maxMonthlyPayment = 0;

            for (; remainPayments > 0; remainPayments--) {
                double monthlyRepayment = pmtService.calculatePMT(amountTemp, ratePaymentTemp, remainPayments);
                maxMonthlyPayment = Math.max(maxMonthlyPayment, monthlyRepayment);

                totalPayments += monthlyRepayment;
                double ratePayment = amountTemp * ratePaymentTemp / 12;
                double principalPayment = monthlyRepayment - ratePayment;

                amountTemp -= principalPayment;


                int paymentNumber = route.getNumberOfPayments() - remainPayments + 1;
                paymentRepository.save(PaymentEntity.builder()
                        .routeId(route.getId())
                        .paymentNumber(paymentNumber)
                        .principal(principalPayment)
                        .interest(ratePayment)
                        .totalPayments(totalPayments)
                        .remainingBalance(amountTemp)
                        .scenario(scenario)
                        .build());


                double monthlyCPI = cpiService.getMonthlyCpi(cpiMap, paymentNumber, scenario);
                amountTemp = amountTemp * (1 + monthlyCPI);
            }
            updateRoute(route, scenario, totalPayments, maxMonthlyPayment);
        });

        return route;
    }

    @Override
    public RouteType getRouteType() {
        return RouteType.FIXED_RATE_CPI;
    }

    private void updateRoute(Route route, Scenario scenario, double totalPayments, double maxMonthlyPayment) {

        switch (scenario) {
            case BASE_CASE:
                route.setTotalPaymentBaseCase(totalPayments);
                route.setMaxPaymentBaseCase(maxMonthlyPayment);
                break;
            case WORST_CASE:
                route.setTotalPaymentWorstCase(totalPayments);
                route.setMaxPaymentWorstCase(maxMonthlyPayment);
        }
    }

}
