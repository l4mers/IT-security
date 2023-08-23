package com.itsecurity.itsecurity.controllers;

import com.itsecurity.itsecurity.models.Credentials;
import com.itsecurity.itsecurity.repositories.CredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ViewController {

    public final CredentialsRepository repo;

    @RequestMapping("/index")
    public String showMyPage() {
        return "login.html";
    }

    @PostMapping("/login")
    public String login(@RequestParam(defaultValue = "") String userName,
                        @RequestParam(defaultValue = "") String password,
                        Model model) {
//        if (repo.findByUserNameAndPassword(userName, password) != null){
//
//        }
        Credentials credentials = repo.findByUserName(userName);
        if(credentials == null){
            model.addAttribute("msg", "user does not exist");
        } else if (!credentials.getPassword().equals(password)){
            model.addAttribute("msg", "wrong password");
        } else {
            return "welcome.html";
        }
        return "login.html";
    }

    @PostMapping("/create")
    public String register(@RequestParam(defaultValue = "") String userName,
                           @RequestParam(defaultValue = "") String password,
                           @RequestParam(defaultValue = "") String equalPassword,
                           Model model) {

        if(repo.existsByUserName(userName)){
            model.addAttribute("msg", "user already exists");
        } else if (!password.equals(equalPassword)){
            model.addAttribute("msg", "password does not match");
        } else {
            repo.save(Credentials.builder()
                            .userName(userName)
                            .password(password)
                    .build());
            model.addAttribute("msg", "user created");
        }
        return "create.html";
    }
}
