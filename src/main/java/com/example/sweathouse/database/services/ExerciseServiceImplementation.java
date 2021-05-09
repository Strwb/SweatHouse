package com.example.sweathouse.database.services;

import com.example.sweathouse.database.appuser.User;
import com.example.sweathouse.database.entities.Exercise;
import com.example.sweathouse.database.repositories.ExerciseRepository;
import com.example.sweathouse.database.repositories.TagRepository;
import com.example.sweathouse.database.repositories.UserRepository;
import com.example.sweathouse.database.services.inter.ExerciseService;
import com.example.sweathouse.util.formUtil.AddExerciseFormData;
import com.example.sweathouse.util.searchUtil.SearchWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImplementation implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    // this dependency is injected automatically because this whole class is a service
    public ExerciseServiceImplementation(ExerciseRepository exerciseRepository,
                                         TagRepository tagRepository, UserRepository userRepository) {
        this.exerciseRepository = exerciseRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<Exercise> getAllExercises() {
        List<Exercise> exercisesWithTags = this.exerciseRepository.findAllExercisesWithTags();
        if (exercisesWithTags.size() == 0) {
            return new ArrayList<>();
        } else {
            return this.exerciseRepository.findAllExercisesWithAllData(exercisesWithTags);
        }
    }

    @Override
    @Transactional
    public boolean saveExercise(AddExerciseFormData entityUtil) {
        boolean savedSuccessfully = false;
        // prepare the raw view data from the wrapper class
        entityUtil.prepareForEntity();
        // we search if the exercise exists
        List<Exercise> exerciseList = this.exerciseRepository.findExercisesByName(entityUtil.getName());
        if (exerciseList.size() == 0) {
            Exercise exercise = new Exercise(entityUtil);
            this.exerciseRepository.save(exercise);
            savedSuccessfully = true;
        }
        return savedSuccessfully;
    }

    @Override
    @Transactional
    public List<Exercise> searchExercise(SearchWrapper wrapper) {
        wrapper.prepareForQuery();
        List<Exercise> searchResult;
        if (wrapper.isEmpty()) {
            searchResult = new ArrayList<>();
        } else {
            switch (wrapper.getCategory()) {
                case NAMES:
                    searchResult = this.exerciseRepository.searchExercisesByNames(this.exerciseRepository.searchExercisesByNamesStepOne(wrapper.getSearchNames()));
                    break;
                case TAGS:
                    searchResult = this.exerciseRepository.searchExercisesByTags(this.exerciseRepository.searchExercisesByTagsStepOne(wrapper.getSearchTags()));
                    break;
                default:
                    searchResult = new ArrayList<>();
            }
        }
        return searchResult;
    }

    @Override
    @Transactional
    public List<Exercise> searchUserExercises(User user) {
        List<Exercise> result = new ArrayList<>();
        Optional<User> userSearchResult = this.userRepository.findById(user.getId());
        if (userSearchResult.isPresent()) {
            User currentUser = userSearchResult.get();
            if (currentUser.getExercises().size() > 0) {
                List<Exercise> stepOne = this.exerciseRepository.searchUserExercisesStepOne(currentUser.getExercises());
                result = this.exerciseRepository.searchUserExercisesStepTwo(stepOne);
            }
        }
        return result;
    }
}
