package br.com.ecommerceapi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String login;
    private String phone;
    private String email;
    private String password;


}
