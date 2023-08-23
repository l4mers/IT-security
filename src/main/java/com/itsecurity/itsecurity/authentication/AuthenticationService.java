package com.itsecurity.itsecurity.authentication;

import com.itsecurity.itsecurity.models.Credentials;
import com.itsecurity.itsecurity.repositories.CredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final CredentialsRepository repo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public Credentials register(String userName, String password) {
        if(repo.existsByUserName(userName)){
            throw new RuntimeException();
        }
        return repo.save(Credentials.builder()
                        .userName(userName)
                        .password(passwordEncoder.encode(password))
                .build());

    }


    public Credentials authenticate(String userName, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userName,
                        password)
        );
        return repo.findByUserName(userName);
    }
}
