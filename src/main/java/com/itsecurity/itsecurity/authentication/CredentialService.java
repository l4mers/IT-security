package com.itsecurity.itsecurity.authentication;

import com.itsecurity.itsecurity.models.Credentials;
import com.itsecurity.itsecurity.repositories.CredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CredentialService {

    final private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final CredentialsRepository repo;

    public boolean register(String userName, String password){
        if (repo.existsByUserName(userName)){
            return false;
        }
        repo.save(Credentials.builder()
                        .userName(userName)
                        .password(passwordEncoder.encode(password))
                .build());
        return true;
    }

    public boolean authenticate(String userName, String password){
        Credentials cred = repo.findByUserName(userName);
        if (cred == null){
            return true;
        } else return !passwordEncoder.matches(password, cred.getPassword());
    }
}
