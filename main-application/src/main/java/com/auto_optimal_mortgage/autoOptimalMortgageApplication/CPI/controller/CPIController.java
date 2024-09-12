package com.auto_optimal_mortgage.autoOptimalMortgageApplication.CPI.controller;


import com.auto_optimal_mortgage.autoOptimalMortgageApplication.CPI.controller.dto.CPIDto;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.CPI.repository.entity.CPIEntity;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.CPI.service.CPIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cpi")
@RequiredArgsConstructor
@Validated
public class CPIController {


    private final CPIService cpiService;

    /**
     * This method is called when a GET request is made
     * URL: localhost:8080/cpi/
     * Purpose: Fetches all the cpi in the cpi table
     * @return List of CPI
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<CPIDto>> getAllCPI() {
        return ResponseEntity.ok()
                .body(cpiService.getAllCPI().stream()
                        .map(CPIDto::fromEntity)
                        .toList());
    }

    @PostMapping("/add")
    public ResponseEntity<CPIDto> addCPI(@RequestBody CPIDto cpi) {
        cpiService.addCPI(CPIEntity.fromDto(cpi));
        return ResponseEntity.ok().body(CPIDto.fromEntity(cpiService.getCPI(cpi.getYear())));
    }

    @PostMapping("/set-const-value")
    public ResponseEntity<Void> setConstValue(@RequestBody CPIDto cpi) {
        cpiService.setConstValue(CPIEntity.fromDto(cpi));
        return ResponseEntity.ok().build();
    }
}
