package com.example.sweathouse.controllers;

import com.example.sweathouse.database.appuser.User;
import com.example.sweathouse.database.services.inter.ExerciseService;
import com.example.sweathouse.database.services.inter.TagService;
import com.example.sweathouse.database.services.inter.UserService;
import com.example.sweathouse.util.formUtil.AddExerciseFormData;
import com.example.sweathouse.util.searchUtil.SearchWrapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class BaseController {

    private final ExerciseService exerciseService;
    private final TagService tagService;
    private final UserService userService;

    public BaseController(ExerciseService exerciseService,
                          TagService tagService,
                          UserService userService) {
        this.exerciseService = exerciseService;
        this.tagService = tagService;
        this.userService = userService;
    }

    @GetMapping("")
    public String redirectHome() {
        return "redirect:/home";
    }

    @GetMapping("home")
    public String homePage(Model model, @AuthenticationPrincipal User loggedUser) {
//        model.addAttribute("exercises", this.exerciseService.getAllExercises());
        model.addAttribute("searchWrapper", new SearchWrapper());
        if (loggedUser != null) {
            // if logged in pass user's id so that we can implement some user functionality
            model.addAttribute("loggedUserId", loggedUser.getId());
            model.addAttribute("exerciseWrappers", this.userService.getExerciseWrapperList(loggedUser.getId()));
        } else {
            model.addAttribute("exerciseWrappers", this.userService.getExerciseWrapperList(-1));
        }
        return "home";
    }

    @GetMapping("addExercise")
    public String addExercise(Model model) {
//        List<Tag> tagsAvailable = this.tagService.getAllTags();
        model.addAttribute("tempExerciseUtil", new AddExerciseFormData());
        model.addAttribute("tagsAvailable", this.tagService.getAllTags());
        model.addAttribute("savedSuccessfully", true);
        return "add-exercise";
    }

    @PostMapping("saveNewExercise")
    public String saveNewExercise(Model model,
            @ModelAttribute("tempExerciseUtil") AddExerciseFormData tempExerciseUtil) {
//        System.out.println(tempExerciseUtil);
        if (!this.exerciseService.saveExercise(tempExerciseUtil)) {
            model.addAttribute("savedSuccessfully", false);
        }
        return "redirect:/home";
    }

    @GetMapping("searchForExercise")
    public String searchForExercise(Model model,
                                    @ModelAttribute("searchWrapper") SearchWrapper wrapper) {
        model.addAttribute("exercises", this.exerciseService.searchExercise(wrapper));
        return "search-result";
    }

}
