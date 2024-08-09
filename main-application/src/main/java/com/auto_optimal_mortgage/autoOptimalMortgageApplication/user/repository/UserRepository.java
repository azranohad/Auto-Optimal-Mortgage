package com.auto_optimal_mortgage.autoOptimalMortgageApplication.user.repository;

import com.auto_optimal_mortgage.autoOptimalMortgageApplication.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);

    List<User> findByPhoneNumber(String phoneNumber);

    List<User> findByEmailIgnoreCase(String email);
}
