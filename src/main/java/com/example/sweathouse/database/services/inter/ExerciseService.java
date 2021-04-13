package com.example.sweathouse.database.services.inter;

import com.example.sweathouse.database.entities.Exercise;

import java.util.List;

public interface ExerciseService {

    public List<Exercise> getAllExercises();

    public void initializeDb();
}
