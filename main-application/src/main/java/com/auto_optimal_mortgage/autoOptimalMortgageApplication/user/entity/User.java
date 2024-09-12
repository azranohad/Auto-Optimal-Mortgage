package com.auto_optimal_mortgage.autoOptimalMortgageApplication.user.entity;


import com.auto_optimal_mortgage.autoOptimalMortgageApplication.user.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private List<String> addresses;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(UserDto dto) {
        this.firstName = dto.getFirstName() != null ? dto.getFirstName().replaceFirst(".", (dto.getFirstName().charAt(0) + "").toUpperCase()) : null;
        this.lastName = dto.getLastName() != null ? dto.getLastName().replaceFirst(".", (dto.getLastName().charAt(0) + "").toUpperCase()) : null;
        this.phoneNumber = dto.getPhoneNumber();
        this.email = dto.getEmail();
        this.addresses = dto.getAddresses();
        this.createdAt = dto.getCreatedAt();
        this.updatedAt = dto.getUpdatedAt();
    }

}
