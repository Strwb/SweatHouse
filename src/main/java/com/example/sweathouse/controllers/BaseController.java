package com.example.sweathouse.controllers;

import com.example.sweathouse.database.entities.Exercise;
import com.example.sweathouse.database.services.ExerciseServiceImplementation;
import com.example.sweathouse.database.services.inter.ExerciseService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class BaseController {

    private final ExerciseService exerciseService;

    public BaseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("")
    public String redirectHome() {
        return "redirect:/home";
    }

    @GetMapping("home")
    public String homePage(Model model) {
        model.addAttribute("exercises", this.exerciseService.getAllExercises());
        return "home";
    }

    @GetMapping("initialize")
    public String hiddenInitializeUrl() {
        this.exerciseService.initializeDb();
        return "redirect:/home";
    }

    @GetMapping("addExercise")
    public String addExercise(Model model) {
        Exercise exercise = new Exercise();
        model.addAttribute("newExercise", exercise);
        return "add-exercise";
    }

    @PostMapping("saveNewExercise")
    public String saveNewExercise(@RequestParam("newExercise") Exercise exercise) {
        //TODO:
        // - Separate form for tags, because we're doing them exactly like in Mendeley
        // - Decide on how to process steps (I think a method of creating them based on String will be fine)
        // - Split tags, and properly add them to the newly created exercise
        // - Save the new Exercise object to the database
        // - Redirect home
        return "redirect:/home";
    }
}
