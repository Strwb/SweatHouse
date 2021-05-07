package com.example.sweathouse.controllers;

import com.example.sweathouse.security.registration.RegistrationRequest;
import com.example.sweathouse.security.registration.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/auth")
public class AuthController {

    private RegistrationService registrationService;

    public AuthController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registrationRequest", new RegistrationRequest());
        return "register";
    }

    @PostMapping("/processRegistration")
    public String processRegistration(@ModelAttribute("registrationRequest") RegistrationRequest request) {
        System.out.println(request);
        registrationService.register(request);
        return "user-page";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
