package com.example.sweathouse.database.repositories;

import com.example.sweathouse.database.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

    // so we take the whole exercises table along with rows in tags that match selected exercise rows (in this case all tags)
    // we use that distinct hint because we're sure that returned data will be distinct, so we can use it to speed things up
    @Query("select distinct e from Exercise e join fetch e.tags")
    @QueryHints(value = {@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false")})
    public List<Exercise> findAllExercisesWithTags();

    // we take whole exercises table along with rows in steps that match selected exercise rows, but this time
    // we take previously selected data and we say we want our selected exercises exactly like the parameter
    // (so with tags), in the end we slap matching steps to the result and we return it
    @Query("SELECT distinct e from Exercise e JOIN FETCH e.steps where e in :exercises order by e.name asc")
    @QueryHints(value = {@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false")})
    public List<Exercise> findAllExercisesWithAllData(@Param("exercises") List<Exercise> exercises);
}
