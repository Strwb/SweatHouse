package com.example.sweathouse.util.searchUtil;

public enum SearchCategory {
    TAGS("tag"),
    NAMES("name");

    private final String displayName;

    SearchCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
