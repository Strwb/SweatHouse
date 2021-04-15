package com.example.sweathouse.database.services.inter;

import com.example.sweathouse.database.entities.Exercise;
import com.example.sweathouse.database.entities.Tag;
import com.example.sweathouse.postObjects.AddExerciseFormData;

import java.util.List;

public interface ExerciseService {

    public List<Exercise> getAllExercises();

    public void saveExercise(AddExerciseFormData entityUtil);

}
