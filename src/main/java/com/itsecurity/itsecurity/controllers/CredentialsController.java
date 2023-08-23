package com.itsecurity.itsecurity.controllers;

import com.itsecurity.itsecurity.authentication.AuthenticationService;
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
    private final AuthenticationService service;

    @GetMapping("/all")
    public List<Credentials> getAllUsers(){
        return repo.findAll();
    }

    @PostMapping("/reg")
    public ResponseEntity<Credentials> reg(@RequestParam String userName,
                                          @RequestParam String password){
        return ResponseEntity.ok(service.register(userName, password));
    }
    @PostMapping("/auth")
    public ResponseEntity<Credentials> auth(@RequestParam String userName,
                           @RequestParam String password){
        return ResponseEntity.ok(service.authenticate(userName, password));
    }
}
