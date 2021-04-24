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
public interface TagRepository extends JpaRepository<Tag, Integer> {

    public Tag findByName(String name);
}
