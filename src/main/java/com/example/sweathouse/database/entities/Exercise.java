package com.example.sweathouse.database.entities;

import com.example.sweathouse.database.appuser.User;
import com.example.sweathouse.util.formUtil.AddExerciseFormData;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "source")
    private String source;

    @Column(name = "remarks")
    private String remarks;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id")
    private List<Step> steps;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "exercises_tags",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @ManyToMany(mappedBy = "exercises")
    private List<User> users;

    public Exercise() {

    }

    public Exercise(String name, String source, String remarks) {
        this.name = name;
        this.source = source;
        this.remarks = remarks;
    }

    public Exercise(AddExerciseFormData addExerciseFormData) {
        this.name = addExerciseFormData.getName();
        this.source = addExerciseFormData.getSource();
        this.remarks = addExerciseFormData.getRemarks();
        this.addTags(addExerciseFormData.getTagsSelected());
        this.addSteps(addExerciseFormData.getStepList());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addSteps(Step step) {
        if (this.steps == null) {
            this.steps = new ArrayList<>();
        }
        this.steps.add(step);
        step.setExercise(this);
    }

    public void addSteps(List<Step> steps) {
        for (Step step : steps) {
            this.addSteps(step);
        }
    }

    public void addManyStepsByInstructions(List<String> stepsInstructions) {
        for (String instruction : stepsInstructions) {
            this.addSteps(new Step(instruction));
        }
    }

    public void sortSteps() {
        this.steps.sort(Comparator.comparing(Step::getStep)); // we're doing functional programming!
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void addTags(Tag tag) {
        if (this.tags == null) {
            this.tags = new ArrayList<>();
        }
        this.tags.add(tag);
        tag.addExercise(this);
    }

    public void addTags(List<Tag> tags) {
        for (Tag tag : tags) {
            this.addTags(tag);
        }
    }

    public void createTagsFromFormInput(String formInput) {
        String[] splitTagStrings = formInput.split(";");
        for (String tagString : splitTagStrings) {
            this.addTags(new Tag(tagString));
        }
    }

    public void addUser(User user) {
        if (this.users == null) {
            this.users = new ArrayList<>();
        }
        this.users.add(user);
    }


//    @Override
//    public String toString() {
//        return "Exercise{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", source='" + source + '\'' +
//                ", remarks='" + remarks + '\'' +
//                ", steps=" + steps +
//                ", tags=" + tags +
//                '}';
//    }
}
