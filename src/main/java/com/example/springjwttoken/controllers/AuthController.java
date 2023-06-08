package com.example.springjwttoken.controllers;


import com.example.springjwttoken.dto.AuthenticationDTO;
import com.example.springjwttoken.dto.PersonDTO;
import com.example.springjwttoken.models.Person;
import com.example.springjwttoken.security.JWTUtil;
import com.example.springjwttoken.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final RegistrationService registrationService;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public Map<String,String> performRegistration(@RequestBody PersonDTO personDTO){
        Person person = convertToPerson(personDTO);

        registrationService.register(person);
        String token = jwtUtil.generateToken(person.getUsername());
        return Map.of("jtw-token",token);
    }

//    public Map<String,String> performLogin(@RequestBody AuthenticationDTO authenticationDTO){
//        UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(),authenticationDTO.getPassword());
//
//
//    }

    public Person convertToPerson(PersonDTO personDTO){
        return modelMapper.map(personDTO, Person.class);
    }
}
