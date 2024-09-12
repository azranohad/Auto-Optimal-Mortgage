package com.auto_optimal_mortgage.autoOptimalMortgageApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AutoOptimalMortgageApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoOptimalMortgageApplication.class, args);

		System.out.printf("HW1111");
	}

}
