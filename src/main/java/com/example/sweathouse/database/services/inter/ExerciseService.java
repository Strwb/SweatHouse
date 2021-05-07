package com.example.sweathouse.database.services.inter;

import com.example.sweathouse.database.entities.Exercise;
import com.example.sweathouse.util.formUtil.AddExerciseFormData;
import com.example.sweathouse.util.searchUtil.SearchWrapper;

import java.util.List;

public interface ExerciseService {

    public List<Exercise> getAllExercises();

    public boolean saveExercise(AddExerciseFormData entityUtil);

    public List<Exercise> searchExercise(SearchWrapper wrapper);

}
