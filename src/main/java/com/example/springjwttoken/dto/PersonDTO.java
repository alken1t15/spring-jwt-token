package com.example.springjwttoken.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {
    private String username;
    private int yearOfBirth;
    private String password;
}
