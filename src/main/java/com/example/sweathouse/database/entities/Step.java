package com.example.sweathouse.database.entities;

import javax.persistence.*;

@Entity
@Table(name = "steps")
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "step")
    private String step;

    @ManyToOne(fetch = FetchType.LAZY)
    private Exercise exercise;

    public Step() {

    }

    public Step(String step) {
        this.step = step;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", step='" + step + '\'' +
                ", exercise=" + exercise +
                '}';
    }
}
