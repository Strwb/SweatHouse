package com.example.sweathouse.database.services;

import com.example.sweathouse.database.appuser.User;
import com.example.sweathouse.database.entities.Exercise;
import com.example.sweathouse.database.repositories.ExerciseRepository;
import com.example.sweathouse.database.repositories.UserRepository;
import com.example.sweathouse.database.services.inter.UserService;
import com.example.sweathouse.util.home.ShowExerciseWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceExercises implements UserService {

    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;


    public UserServiceExercises(UserRepository userRepository, ExerciseRepository exerciseRepository) {
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
    }


    @Override
    @Transactional
    public void addFavouriteExercise(User user, int exerciseId) {
        Optional<Exercise> favouriteExercise = this.exerciseRepository.findById(exerciseId);
        if (favouriteExercise.isPresent()) {
            Exercise exercise = favouriteExercise.get();
            user.addExercise(exercise);
            this.userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public void removeExerciseFromFavourites(User user, int exerciseId) {
        Optional<Exercise> favouriteExercise = this.exerciseRepository.findById(exerciseId);
        if (favouriteExercise.isPresent()) {
            Exercise exercise = favouriteExercise.get();
            user.removeExercise(exercise);
            this.userRepository.save(user);
        }

    }

    /**
     *
     * @param userId ID of the current logged user
     * @return List of whether every exercise is in the current user's list of favourites.
     */

    @Override
    @Transactional
    public List<ShowExerciseWrapper> getExerciseWrapperList(int userId) {
        // get all exercises, then iterate over every exercise's users (who added this exercise to favourites)
        // if a user with id matching to the current logged one is present, then add true to the list,
        // if there isn't such a user, then add false. Each index in the result list corresponds to exercise displayed
        // to the user
        List<ShowExerciseWrapper> result = new ArrayList<>();
        List<Exercise> allExercises = this.exerciseRepository.findAll();
        // userId = -1 if we aren't logged in, so there is no sense in checking whether the exercise is favourite
        if (userId == -1) {
            for (Exercise exercise : allExercises) {
                result.add(new ShowExerciseWrapper(exercise, false));
            }
        } else {
            // if we're logged in check whether the exercise is a favourite of the logged user
            for (Exercise exercise : allExercises) {
                boolean added = false;
                for (User user : exercise.getUsers()) {
                    if (user.getId() == userId) {
                        result.add(new ShowExerciseWrapper(exercise, false));
                        added = true;
                        break;
                    }
                }
                if (!added) {
                    result.add(new ShowExerciseWrapper(exercise, true));
                }
            }
        }
        return result;
    }
}
