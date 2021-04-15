package com.example.sweathouse.controllers;

import com.example.sweathouse.database.entities.Tag;
import com.example.sweathouse.database.repositories.TagRepository;
import com.example.sweathouse.database.services.inter.ExerciseService;
import com.example.sweathouse.database.services.inter.TagService;
import com.example.sweathouse.postObjects.AddExerciseFormData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class BaseController {

    private final ExerciseService exerciseService;
    private final TagService tagService;

    public BaseController(ExerciseService exerciseService, TagService tagService) {
        this.exerciseService = exerciseService;
        this.tagService = tagService;
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

    @GetMapping("addExercise")
    public String addExercise(Model model) {
//        List<Tag> tagsAvailable = this.tagService.getAllTags();
        model.addAttribute("tempExerciseUtil", new AddExerciseFormData());
        model.addAttribute("tagsAvailable", this.tagService.getAllTags());
        return "add-exercise";
    }

    @PostMapping("saveNewExercise")
    public String saveNewExercise(@ModelAttribute("tempExerciseUtil") AddExerciseFormData tempExerciseUtil) {
//        System.out.println(tempExerciseUtil);
        System.out.println("WHAT IS HAPPENING?!");
        this.exerciseService.saveExercise(tempExerciseUtil);
        return "redirect:/home";
    }
}
