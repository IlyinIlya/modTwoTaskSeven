package org.skypro.skyshop.search;

public interface Searchable {

    String getSearchTerm();

    String getTypeContent();

    String getName();

    default String getStringRepresentation() {
        return getName() + " - " + getTypeContent();
    }
}
