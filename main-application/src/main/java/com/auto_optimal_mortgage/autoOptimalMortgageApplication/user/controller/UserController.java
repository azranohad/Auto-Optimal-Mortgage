package com.auto_optimal_mortgage.autoOptimalMortgageApplication.user.controller;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.user.dto.UserDto;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.user.entity.User;
import com.auto_optimal_mortgage.autoOptimalMortgageApplication.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class is where all the user requests are handled and required/appropriate
 * responses are sent
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserController {


    private final UserService userService;

    /**
     * This method is called when a GET request is made
     * URL: localhost:8080/user/v1/
     * Purpose: Fetches all the users in the user table
     * @return List of Users
     */
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok()
                .body(userService.getAllUsers()
                        .stream().map(UserDto::new).toList());
    }

    /**
     * This method is called when a GET request is made
     * URL: localhost:8080/user/v1/1 (or any other id)
     * Purpose: Fetches user with the given id
     * @param id - user id
     * @return User with the given id
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id)
    {
        return ResponseEntity.ok().body(new UserDto(userService.getUserById(id)));
    }

    /**
     * Purpose: Save an User
     * @param user - Request body is an User
     * @return Saved User
     */
    @PostMapping("/")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto user)
    {
        return ResponseEntity.ok().body(new UserDto(userService.saveUser(new User(user))));
    }

    /**
     * This method is called when a PUT request is made
     * URL: localhost:8080/user/v1/
     * Purpose: Update an User entity
     * @param user - User entity to be updated
     * @return Updated User
     */
    @PutMapping("/")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user)
    {
        return ResponseEntity.ok().body(new UserDto(userService.updateUser(new User(user))));
    }

    /**
     * This method is called when a PUT request is made
     * URL: localhost:8080/user/v1/1 (or any other id)
     * Purpose: Delete an User entity
     * @param id - user's id to be deleted
     * @return a String message indicating user record has been deleted successfully
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id)
    {
        userService.deleteUserById(id);
        return ResponseEntity.ok().body("Deleted user successfully");
    }

}
