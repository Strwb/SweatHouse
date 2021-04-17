package com.example.sweathouse.database.services;

import com.example.sweathouse.database.entities.Exercise;
import com.example.sweathouse.database.repositories.ExerciseRepository;
import com.example.sweathouse.database.repositories.TagRepository;
import com.example.sweathouse.database.services.inter.ExerciseService;
import com.example.sweathouse.util.postObjects.AddExerciseFormData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
//        List<Exercise> selectedExercises = this.exerciseRepository.findAllExercisesWithAllData(this.exerciseRepository.findAllExercisesWithTags());
//        for (Exercise exercise : selectedExercises) {
//            exercise.sortSteps();
//        }
//        return selectedExercises;
        return this.exerciseRepository.findAllExercisesWithAllData(this.exerciseRepository.findAllExercisesWithTags());
    }

    @Override
    @Transactional
    public void saveExercise(AddExerciseFormData entityUtil) {
        // prepare the raw view data from the wrapper class
        entityUtil.prepareForEntity();
        Exercise exercise = new Exercise(entityUtil);
        try {
            this.exerciseRepository.save(exercise);
        } catch (Exception e) {
            System.out.println("OOOPSIE DOOPSIE!!!");
        }
    }

}
