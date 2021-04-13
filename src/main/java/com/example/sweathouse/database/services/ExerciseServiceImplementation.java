package com.example.sweathouse.database.services;

import com.example.sweathouse.database.entities.Exercise;
import com.example.sweathouse.database.entities.Tag;
import com.example.sweathouse.database.repositories.ExerciseRepository;
import com.example.sweathouse.database.repositories.TagRepository;
import com.example.sweathouse.database.services.inter.ExerciseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseServiceImplementation implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final TagRepository tagRepository;

    // this dependency is injected automatically because this whole class is a service
    public ExerciseServiceImplementation(ExerciseRepository exerciseRepository,
                                         TagRepository tagRepository) {
        this.exerciseRepository = exerciseRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public List<Exercise> getAllExercises() {
        List<Exercise> selectedExercises = this.exerciseRepository.findAllExercisesWithAllData(this.exerciseRepository.findAllExercisesWithTags());
        for (Exercise exercise : selectedExercises) {
            exercise.sortSteps();
        }
        return selectedExercises;
    }

    @Override
    @Transactional
    public void initializeDb() {
//        generateInitialTags();
//        generateInitialExercises();
    }

    @Transactional
    public void generateInitialExercises() {
        String[] names = {"push ups", "sit ups", "jumping jacks", "pull ups"};
        String source = "ancient people";
        String remarks = "You know what to do";
        List<List<String>> stepInstructions = generateStepInstructions();

        for (int i = 0; i < names.length; i++) {
            Exercise createdExercise = new Exercise(names[i], source, remarks);
            createdExercise.addManyStepsByInstructions(stepInstructions.get(i));
            createdExercise.addTag(this.tagRepository.findByName("Calisthenics"));
            createdExercise.addTag(this.tagRepository.findByName("Simple"));
            this.exerciseRepository.save(createdExercise);
        }
    }

    @Transactional
    public void generateInitialTags() {
        Tag tag1 = new Tag("Calisthenics");
        Tag tag2 = new Tag("Simple");
        this.tagRepository.save(tag1);
        this.tagRepository.save(tag2);
    }

    private List<List<String>> generateStepInstructions() {
        List<List<String>> steps = new ArrayList<>();
        List<String> stepsPushUps = new ArrayList<>();
        List<String> stepsSitUps = new ArrayList<>();
        List<String> stepsJumpingJacks = new ArrayList<>();
        List<String> stepsPullUps = new ArrayList<>();

        stepsPushUps.add("1. Push down");
        stepsPushUps.add("2. Push up");
        stepsSitUps.add("1. Sit down");
        stepsSitUps.add("2. Sit up");
        stepsJumpingJacks.add("1. Hands apart and legs together");
        stepsJumpingJacks.add("2. Hands together and legs apart");
        stepsJumpingJacks.add("3. Hands apart and legs together");
        stepsPullUps.add("1. Pull up");
        stepsPullUps.add("2. Pull down");


        steps.add(stepsPushUps);
        steps.add(stepsSitUps);
        steps.add(stepsJumpingJacks);
        steps.add(stepsPullUps);
        return steps;
    }
}
