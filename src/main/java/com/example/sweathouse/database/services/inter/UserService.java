package com.example.sweathouse.database.services.inter;

import com.example.sweathouse.database.appuser.User;
import com.example.sweathouse.util.home.ShowExerciseWrapper;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void addFavouriteExercise(User user, int exerciseId);

    List<ShowExerciseWrapper> getExerciseWrapperList(int userId);

    void removeExerciseFromFavourites(User user, int exerciseId);
}
