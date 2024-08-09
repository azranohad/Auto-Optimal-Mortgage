package com.auto_optimal_mortgage.autoOptimalMortgageApplication.user.service;

import com.auto_optimal_mortgage.autoOptimalMortgageApplication.user.entity.User;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {


    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        log.info("User with id: {} doesn't exist", id);
        return null;
    }

    public User saveUser (User user){

        if(user.getEmail() == null) {
            log.info("User details are missing, email is mandatory");
            return null;
        }
        if (isUserExists(user)) {
            log.info("User already exists");
            return null;
        } else if (isUserNameExists(user)) {
            log.info("User already exists with the same name");
            return null;
        }
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        log.info("User with id: {} saved successfully", user.getId());
        return savedUser;
    }

    public User updateUser (User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        user.setCreatedAt(existingUser.get().getCreatedAt());
        user.setUpdatedAt(LocalDateTime.now());

        User updatedUser = userRepository.save(user);

        log.info("User with id: {} updated successfully", user.getId());
        return updatedUser;
    }

    public void deleteUserById (Integer id) {
        userRepository.deleteById(id);
    }

    private boolean isUserExists(User user) {
        if (user.getPhoneNumber() != null &&
                !userRepository.findByPhoneNumber(user.getPhoneNumber()).isEmpty()) {

            log.info("User exists with phone number: {}", user.getPhoneNumber());
            return true;
        }

        if (user.getEmail() != null &&
                !userRepository.findByEmailIgnoreCase(user.getEmail()).isEmpty()) {

            log.info("User exists with email: {}", user.getEmail());
            return true;
        }

        return false;
    }

    private boolean isUserNameExists(User user) {
        if (user.getFirstName() != null && user.getLastName() != null
                && !userRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(user.getFirstName(), user.getLastName()).isEmpty()) {

            log.info("User exists with first name: {} and last name: {}", user.getFirstName(), user.getLastName());
            return true;
        }

        return false;
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

}
