package com.auto_optimal_mortgage.autoOptimalMortgageApplication.user.dto;

import com.auto_optimal_mortgage.autoOptimalMortgageApplication.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private List<String> addresses;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
        this.addresses = user.getAddresses();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
