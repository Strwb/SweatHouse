package com.example.sweathouse.util.postObjects;

import com.example.sweathouse.database.entities.Step;
import com.example.sweathouse.database.entities.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        this.prepareName();
        this.prepareSteps();
        this.prepareRemarks();
    }

    private void prepareSteps() {
        String[] stepsSplit = this.stepsRaw.split(";");
        // steps are entered in order from the first one to the last, when quering database the order should be
        // automatically upheld
        for (int i = 0; i < stepsSplit.length; i++) {
            this.stepList.add(new Step(String.format("%d. %s", i + 1, stepsSplit[i].trim().toLowerCase(Locale.ROOT))));
        }
    }

    private void prepareName() {
        this.name = this.name.toLowerCase(Locale.ROOT);
    }

    private void prepareRemarks() {
        this.remarks = this.remarks.toLowerCase(Locale.ROOT);
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
