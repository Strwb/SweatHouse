package com.example.sweathouse.postObjects;

import com.example.sweathouse.database.entities.Step;
import com.example.sweathouse.database.entities.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddExerciseFormData {

    private String name;

    private String source;

    private String remarks;

    private List<Tag> tagsSelected;

    private String stepsRaw;

    final private List<Step> stepList;

    public AddExerciseFormData() {
        this.tagsSelected = new ArrayList<>();
        this.stepList = new ArrayList<>();
    }

    public void prepareForEntity() {
        this.prepareSteps();
    }

    private void prepareSteps() {
        String[] stepsSplit = this.stepsRaw.split(";");
        for (int i = 0; i < stepsSplit.length; i++) {
            this.stepList.add(new Step(String.format("%d. %s", i + 1, stepsSplit[i].trim())));
        }
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

    public List<Tag> getTagsSelected() {
        return tagsSelected;
    }

    public void setTagsSelected(List<Tag> tagsSelected) {
        this.tagsSelected = tagsSelected;
    }

    public String getStepsRaw() {
        return stepsRaw;
    }

    public void setStepsRaw(String stepsRaw) {
        this.stepsRaw = stepsRaw;
    }

    public List<Step> getStepList() {
        return stepList;
    }

    @Override
    public String toString() {
        return "AddExerciseUtil{" +
                "name='" + name + '\'' +
                ", source='" + source + '\'' +
                ", remarks='" + remarks + '\'' +
                ", tagsSplit=" + tagsSelected +
                ", stepsRaw='" + stepsRaw + '\'' +
                ", stepList=" + stepList +
                '}';
    }

}
