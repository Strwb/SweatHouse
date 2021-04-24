package com.example.sweathouse.database.repositories;

import com.example.sweathouse.database.entities.Exercise;
import com.example.sweathouse.database.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

    public List<Exercise> findExercisesByName(String name);

    // BELOW IS SEARCHING ALL EXERCISES
    // so we take the whole exercises table along with rows in tags that match selected exercise rows (in this case all tags)
    // we use that distinct hint because we're sure that returned data will be distinct, so we can use it to speed things up,
    // we're speeding things up because we deduplicate references to newly created objects (bc we know that they will
    // represent the same unique collection, only extended after second query)
    @Query("select distinct e from Exercise e join fetch e.tags")
    @QueryHints(value = {@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false")})
    public List<Exercise> findAllExercisesWithTags();

    // we take whole exercises table along with rows in steps that match selected exercise rows, but this time
    // we take previously selected data and we say we want our selected exercises exactly like the parameter
    // (so with tags), in the end we slap matching steps to the result and we return it
    @Query("SELECT distinct e from Exercise e JOIN FETCH e.steps where e in :exercises order by e.name asc")
    @QueryHints(value = {@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false")})
    public List<Exercise> findAllExercisesWithAllData(@Param("exercises") List<Exercise> exercises);

    // BELOW IS SEARCHING BY NAMES
    @Query("select distinct e from Exercise e join fetch e.tags where e.name in :exerciseNames")
    @QueryHints(value = {@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false")})
    public List<Exercise> searchExercisesByNamesStepOne(@Param("exerciseNames") List<String> exerciseNames);

    @Query("select distinct e from Exercise e join fetch e.steps where e in :exercises order by e.name asc")
    @QueryHints(value = {@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false")})
    public List<Exercise> searchExercisesByNames(@Param("exercises") List<Exercise> exercises);

    // BELOW IS SEARCHING BY TAGS

    // so here i join tags table and exercises table by their mapped manytomany relationship,
    // when they are joined I search for relationships where there are tags with names in tagNames,
    // I take those relationships (whole relations, not just tags in tagNames)
    // what if I had used join fetch? Well join fetch tries to do everything in a single query, it would do it like this:
    // select exercises that have tag names in tagName and join them with only those tags that have names in tagNames
    // it can do it in a single query, so it's faster but it doesn't take whole relations between exercises and tags,
    // only takes exercises that have matching tags and these matching tags.
    @Query("select distinct e, t from Exercise e left join e.tags t where t.name in :tagNames")
    @QueryHints(value = {@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false")})
    public List<Exercise> searchExercisesByTagsStepOne(@Param("tagNames") List<String> tagNames);

    // here for example it takes all exercises and their steps where those exercises can be found in exercises list,
    // single statement no worries.
    @Query("select distinct e from Exercise e join fetch e.steps where e in :exercises order by e.name asc")
    @QueryHints(value = {@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH, value = "false")})
    public List<Exercise> searchExercisesByTags(@Param("exercises") List<Exercise> exercises);



}
