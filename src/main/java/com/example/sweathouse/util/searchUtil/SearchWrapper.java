package com.example.sweathouse.util.searchUtil;

import java.util.ArrayList;
import java.util.List;

public class SearchWrapper {

    private SearchCategory category;
    private List<String> searchNames;
    private List<String> searchTags;
    private String searchInput;

    public SearchWrapper() {
        this.searchNames = new ArrayList<>();
        this.searchTags = new ArrayList<>();
    }

    public List<String> getSearchNames() {
        return searchNames;
    }

    public List<String> getSearchTags() {
        return searchTags;
    }

    public String getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(String searchInput) {
        this.searchInput = searchInput;
    }

    public SearchCategory getCategory() {
        return category;
    }

    public void setCategory(SearchCategory category) {
        this.category = category;
    }
}
