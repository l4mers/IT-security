package com.itsecurity.itsecurity.controllers;

import com.itsecurity.itsecurity.authentication.CredentialService;
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

    private final CredentialsRepository repo;
    private final CredentialService service;

    @RequestMapping("/index")
    public String showMyPage() {
        return "login";
    }

    @RequestMapping("/create")
    public String createPage() {
        return "create";
    }

    @PostMapping("/tryLogin")
    public String login(@RequestParam String userName,
                        @RequestParam String password,
                        Model model) {

        if(userName.length() < 1 || password.length() < 1) {
            model.addAttribute("msg", "all fields required");

        } else {
            //Lösning
            if (service.authenticate(userName, password)){
                model.addAttribute("msg", "wrong credential combination");
            } else {
                return "welcome";
            }
//        Credentials credentials = repo.findByUserName(userName);
//        if(credentials == null){
//            model.addAttribute("msg", "user does not exist");
//        } else if (!credentials.getPassword().equals(password)){
//            model.addAttribute("msg", "wrong password");
//        } else {
//            return "welcome";
//        }
        }
        return "login";
    }

    @PostMapping("/tryCreate")
    public String register(@RequestParam String userName,
                           @RequestParam String password,
                           @RequestParam String equalPassword,
                           Model model) {

        if(userName.length() < 1 || password.length() < 1 || equalPassword.length() < 1){
            model.addAttribute("msg", "all fields required");
        } else {

//            if (!password.equals(equalPassword)) {
//                model.addAttribute("msg", "password does not match");
//            }
//            else if (repo.existsByUserName(userName)) {
//                model.addAttribute("msg", "user already exists");
//            }
//            else {
//                model.addAttribute("msg", "user created");
//
//                repo.save(Credentials.builder()
//                                .userName(userName)
//                                .password(password)
//                        .build());
//            }

            //Lösning
            if (!password.equals(equalPassword)) {
                model.addAttribute("msg", "password does not match");
            }
            else if (service.register(userName, password)) {
                model.addAttribute("msg", "user created");
            }
            else {
                model.addAttribute("msg", "invalid user name");
            }
        }
        return "create";
    }
}
