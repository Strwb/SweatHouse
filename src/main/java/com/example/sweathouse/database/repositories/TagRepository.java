package com.example.sweathouse.database.repositories;

import com.example.sweathouse.database.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    public Tag findByName(String name);
}
