package org.skypro.skyshop.search;

import org.skypro.skyshop.exceptions.BestResultNotFound;

import java.util.*;

public class SearchEngine {
    private final List<Searchable> searchables;

    public SearchEngine(int iSize) {
        searchables = new LinkedList<>();
    }

    public void add(Searchable searchable) {
        searchables.add(searchable);
    }

    public Map<String, Searchable> search(String searchTerm) {
        Map<String, Searchable> elements = new TreeMap<>();
        for (Searchable searchable : searchables) {
            if (searchable != null && searchable.getSearchTerm().toLowerCase().contains(searchTerm.toLowerCase())) {
                elements.put(searchable.getName(), searchable);
            }
        }
        return elements;
    }

    public Searchable getMostEqualElement(String search) throws BestResultNotFound {
        Searchable mostEqualElement = null;
        int iMaxCount = 0;

        for (Searchable searchable : searchables) {
            if (searchable == null) {
                throw new BestResultNotFound(search);
            }

            String iSearch = searchable.getSearchTerm();

            int iCount = 0;
            int index = 0;
            int indexSubstring = iSearch.indexOf(search, index);

            while (indexSubstring != -1) {
                iCount++;
                index = indexSubstring + search.length();
                indexSubstring = iSearch.indexOf(search, index);
            }

            if (iCount > iMaxCount) {
                iMaxCount = iCount;
                mostEqualElement = searchable;
            }
        }
        if (mostEqualElement == null) {
            throw new BestResultNotFound(search);
        }
        return mostEqualElement;
    }

}
