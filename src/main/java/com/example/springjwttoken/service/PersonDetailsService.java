package com.example.springjwttoken.service;


import com.example.springjwttoken.repository.PersonRepository;
import com.example.springjwttoken.security.PersonDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PersonDetailsService implements UserDetailsService {
    public final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository.findByUsername(username).map(PersonDetails::new).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }
}
