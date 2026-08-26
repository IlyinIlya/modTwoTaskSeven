package org.skypro.skyshop.description;

import org.skypro.skyshop.search.Searchable;

public class Article implements Searchable {

    private final String title;
    private final String description;

    public Article(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public String getSearchTerm() {
        return toString();
    }

    @Override
    public String getTypeContent() {
        return "ARTICLE";
    }

    @Override
    public String toString() {
        return title + "\n" + description;
    }
}
