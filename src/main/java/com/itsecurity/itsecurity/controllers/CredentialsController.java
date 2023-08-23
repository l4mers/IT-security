package com.itsecurity.itsecurity.controllers;

import com.itsecurity.itsecurity.authentication.CredentialService;
import com.itsecurity.itsecurity.models.Credentials;
import com.itsecurity.itsecurity.repositories.CredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credentials")
public class CredentialsController {

    private final CredentialsRepository repo;

    @GetMapping("/all")
    public List<Credentials> getAllUsers(){
        return repo.findAll();
    }
}
