package com.example.sweathouse.database.services;

import com.example.sweathouse.database.entities.Tag;
import com.example.sweathouse.database.repositories.ExerciseRepository;
import com.example.sweathouse.database.repositories.TagRepository;
import com.example.sweathouse.database.services.inter.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImplementation implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImplementation(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public List<Tag> getAllTags() {
        return this.tagRepository.findAll();
    }
}
