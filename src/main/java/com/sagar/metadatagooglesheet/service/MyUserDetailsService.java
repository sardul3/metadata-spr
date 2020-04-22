package com.sagar.metadatagooglesheet.service;

import com.sagar.metadatagooglesheet.model.User;
import com.sagar.metadatagooglesheet.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        return new User("sagar", "sagar", new ArrayList<>());
        Optional<User> user = userRepository.findByUsername(s);
        user.orElseThrow(() -> new UsernameNotFoundException("User is not found"));

        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(),
                user.get().isEnabled(), true, true, true, getAuthorities("USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

}
