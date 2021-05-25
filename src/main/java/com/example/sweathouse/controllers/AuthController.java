package com.example.sweathouse.controllers;

import com.example.sweathouse.database.appuser.User;
import com.example.sweathouse.database.services.inter.ExerciseService;
import com.example.sweathouse.database.services.inter.UserService;
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
    private final UserService userService;

    public AuthController(RegistrationService registrationService, ExerciseService exerciseService, UserService userService) {
        this.registrationService = registrationService;
        this.exerciseService = exerciseService;
        this.userService = userService;
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
        return "redirect:/home";
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
    }

    @GetMapping("/user/addToFavourites")
    public String addToFavourites(@RequestParam("exerciseId") int exerciseId,
                                  @AuthenticationPrincipal User loggedUser) {
        this.userService.addFavouriteExercise(loggedUser, exerciseId);
        return "redirect:/auth/user/" + loggedUser.getId();
    }

    @GetMapping("/user/removeFromFavourites")
    public String removeFromFavourites(@RequestParam("exerciseId") int exerciseId,
                                       @AuthenticationPrincipal User loggedUser) {
        this.userService.removeExerciseFromFavourites(loggedUser, exerciseId);
        return "redirect:/auth/user/" + loggedUser.getId();
    }
}
