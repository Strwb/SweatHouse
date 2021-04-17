package com.example.sweathouse.util.searchUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchWrapper {

    private SearchCategory category;
    private List<String> searchNames;
    private List<String> searchTags;
    private String searchInput;

    public SearchWrapper() {
        this.searchNames = new ArrayList<>();
        this.searchTags = new ArrayList<>();
    }

    public void prepareForQuery() {
        switch (this.category) {
            case TAGS:
                prepareNamesOrTags(this.searchTags);
                break;
            case NAMES:
                prepareNamesOrTags(this.searchNames);
                break;
        }
    }

    /**
     * Processes raw search input into a list of strings that can be used for quering the database
     *
     * @param collection specifies if we add user's input to searchNames or searchTags list. If we use searchNames
     *                   then we deal with name search, likewise if we use searchTags we deal with tag search.
     */
    private void prepareNamesOrTags(List<String> collection) {
        for (String property : this.searchInput.split(";")) {
            collection.add(property.toLowerCase(Locale.ROOT).trim());
        }
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

//    @Override
//    public String toString() {
//        return "SearchWrapper{" +
//                "category=" + category +
//                ", searchNames=" + searchNames +
//                ", searchTags=" + searchTags +
//                ", searchInput='" + searchInput + '\'' +
//                '}';
//    }
}
