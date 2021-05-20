package com.example.sweathouse.util.home;

import com.example.sweathouse.database.appuser.User;
import com.example.sweathouse.database.entities.Exercise;

public class ShowExerciseWrapper {

    private Exercise exercise;

    private boolean isFavourite;

    public ShowExerciseWrapper(Exercise exercise, boolean isFavourite) {
        this.exercise = exercise;
        this.isFavourite = isFavourite;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
