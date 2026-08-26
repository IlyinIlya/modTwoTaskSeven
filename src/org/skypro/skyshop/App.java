package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.description.Article;
import org.skypro.skyshop.exceptions.BestResultNotFound;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        System.out.println("Shop Application");
        //initial values
        Product product1 = new SimpleProduct("Футболка", 1500);
        Product product2 = new SimpleProduct("Шорты", 2100);
        Product product3 = new DiscountedProduct("Рубашка", 1900, 20);
        Product product4 = new FixPriceProduct("Кепка");
        Product product5 = new DiscountedProduct("Брюки и Футболка", 2500, 15);
        Product product6 = new SimpleProduct("Ремень", 900);
        ProductBasket basket = new ProductBasket();

        //Adding product to the basket
        basket.addProduct(product1);
        basket.addProduct(product2);
        basket.addProduct(product3);
        basket.addProduct(product4);
        basket.addProduct(product5);
        basket.addProduct(product6);

        //Printing a basket (few products)
        basket.printBasketContents();

        //Total cost of a basket (few products)
        System.out.println("Общая стоимость: " + basket.getTotalBasketCost());

        //Searching product is in the basket
        System.out.println("Шорты в корзине?: " + basket.checkProductName("Шорты"));

        //Searching product is not in the basket
        System.out.println("Ремень в корзине?: " + basket.checkProductName("Ремень"));

        System.out.println("===========================================================");

        //Remove product from basket
        List<Product> basketItemsRemove = basket.basketRemoveProductByName("Рубашка");

        //Show removed product from basket
        System.out.println("Удалили: " + basketItemsRemove);

        //Printing basket
        System.out.println("\nСейчас в корзине:");
        basket.printBasketContents();

        //Removing product which not in the list
        String productName = "Свитер";
        List<Product> basketNoItemsRemove = basket.basketRemoveProductByName(productName);

        //Checking for empty list

        if (basketNoItemsRemove.isEmpty()) {
            System.out.println("Список пуст: '" + productName + "' не в списке");
        }

        //Printing basket
        System.out.println("\nСейчас в корзине:");
        basket.printBasketContents();

        //Clean the basket
        basket.clearBasket();

        //Printing empty basket
        System.out.println("\nСейчас в корзине:");
        basket.printBasketContents();

        //Total cost (empty basket)
        System.out.println("Общая стоимость: " + basket.getTotalBasketCost());

        //Searching by name (empty basket)
        System.out.println("Шорты в корзине?: " + basket.checkProductName("Шорты"));

        SearchEngine searchEngine = new SearchEngine(10);

        searchEngine.add(product1);
        searchEngine.add(product2);
        searchEngine.add(product3);
        searchEngine.add(product4);
        searchEngine.add(product5);
        searchEngine.add(product6);

        Article article1 = new Article("Модели футболок. футболка", "Футболки классифицируют по ...");
        Article article2 = new Article("Обозначение размеров шорт на этикетках и ярлыках", "Существует несколько систем маркировки размеров шорт..");
        Article article3 = new Article("Акссессуары для рубашки", "Футболка - нет, рубашка выглядела безупречно..");
        Article article4 = new Article("Кепка - только ли для лета?. Футболка", "Сегодня поговорим о стиле, моде и удобстве нашей головы...");

        searchEngine.add(article1);
        searchEngine.add(article2);
        searchEngine.add(article3);
        searchEngine.add(article4);

        Map<String, Searchable> result1 = searchEngine.search("футболка");
        System.out.println("\nПо запросу 'футболка' найдено:");
        for (Searchable resault : result1.values()) {
            System.out.println(resault.getStringRepresentation());
        }

        Map<String, Searchable> result2 = searchEngine.search("Шорты");
        System.out.println("\nПо запроссу 'Шорты' найдено:");
        for (Searchable resault : result2.values()) {
            System.out.println(resault.getStringRepresentation());
        }

        Map<String, Searchable> result3 = searchEngine.search("рубашка");
        System.out.println("\nПо запроссу 'рубашка' найдено:");
        for (Searchable resault : result3.values()) {
            System.out.println(resault.getStringRepresentation());
        }
        System.out.println("=================================================");
        try {
            Product noNameProduct = new SimpleProduct(" ", 10);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            Product noPriceProduct = new SimpleProduct("Футболка", 0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            Product noPriceDiscountProduct = new DiscountedProduct("Шорты", -10, 10);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            Product noDiscountProduct = new DiscountedProduct("Рубашка", 10, 101);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            Searchable result = searchEngine.getMostEqualElement("Футболка");
            System.out.println("Самый подходящий элемент: " + result.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            Searchable result = searchEngine.getMostEqualElement("Пиджак");
            System.out.println("Самый подходящий элемент: " + result.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
