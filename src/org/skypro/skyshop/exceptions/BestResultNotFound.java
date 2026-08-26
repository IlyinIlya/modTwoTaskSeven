package org.skypro.skyshop.exceptions;

public class BestResultNotFound extends Exception {

    public BestResultNotFound(String search) {
        super("Для поискового запроса не нашлось подходящей статьи: " + search);
    }
}
