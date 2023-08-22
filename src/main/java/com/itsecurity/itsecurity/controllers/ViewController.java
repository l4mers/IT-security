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
    public String login(@RequestParam String userName,
                        @RequestParam String password,
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
    public String register(@RequestParam String userName,
                           @RequestParam String password,
                           @RequestParam String equalPassword,
                           Model model) {

        if(repo.existsByUserName(userName)){
            model.addAttribute("msg", "user already exists");
        } else if (!password.equals(equalPassword)){
            model.addAttribute("passwordMsg", "password does not match");
        } else {
            model.addAttribute("passwordMsg", "password does not match");
        }
        return "create.html";
    }
}