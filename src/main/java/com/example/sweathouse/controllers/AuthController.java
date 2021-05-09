package com.example.sweathouse.controllers;

import com.example.sweathouse.database.appuser.User;
import com.example.sweathouse.database.services.inter.ExerciseService;
import com.example.sweathouse.security.registration.RegistrationRequest;
import com.example.sweathouse.security.registration.inter.RegistrationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final ExerciseService exerciseService;

    public AuthController(RegistrationService registrationService, ExerciseService exerciseService) {
        this.registrationService = registrationService;
        this.exerciseService = exerciseService;
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

    @GetMapping("/user/{id}")
    public String userPage(@PathVariable("id") Integer userId,
                           @AuthenticationPrincipal User loggedUser,
                           Model model) {
        // Retrieve current (logged in) user's data
        if (userId == loggedUser.getId()) {
            model.addAttribute("loggedUser", loggedUser);
            model.addAttribute("loggedUserExercises", this.exerciseService.searchUserExercises(loggedUser));
            return "user-page";
        } else {
            return "wrong-user";
        }
        //TODO:
        // - add link at home page that figures out the current user and redirects here
        // - verify if id in the URL is equal to the logged user's id
        // - get all exercises with all data and display them at this page:
        //      - get all exercises in that many to many relationship
        //      - get all steps and tags connected to those exercises in that relationship
        // - option to add exercises to favourites at the home page
        // - option to remove favourites at the user's profile
    }
}
