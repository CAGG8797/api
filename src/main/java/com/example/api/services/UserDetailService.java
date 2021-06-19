package com.example.api.services;

import com.example.api.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.example.api.models.User> optional = usersRepository.findByUsername(username);

        if ( !optional.isPresent() ){
            throw new UsernameNotFoundException("");
        }

        com.example.api.models.User user = optional.get();

        return new User(user.getUsername(), passwordEncoder.encode(user.getPassword()), true, true, true, true,
                new ArrayList<GrantedAuthority>());
    }
}
