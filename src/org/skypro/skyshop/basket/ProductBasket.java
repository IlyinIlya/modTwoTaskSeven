package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ProductBasket {
    // Basket contains a list of products
    private final List<Product> products = new LinkedList<>();

    //Method for adding a product to the basket
    public void addProduct(Product product) {
        products.add(product);
    }

    //Method for obtaining the total basket cost
    public int getTotalBasketCost() {
        int totalCost = 0;
        for (Product product : products) {
            if (product != null) {
                totalCost += product.getPrice();
            }
        }
        return totalCost;
    }

    //Method counts special products
    public int countSpecialProducts() {
        int iCount = 0;
        for (Product product : products) {
            if (product != null && product.isSpecial()) {
                iCount++;
            }
        }
        return iCount;
    }

    //Method prints the basket contents
    public void printBasketContents() {
        boolean emptybasket = true;
        for (Product product : products) {
            if (product != null) {
                System.out.println(product.toString());
                emptybasket = false;

            }
        }
        if (emptybasket) {
            System.out.println("В корзине пусто");
        } else {
            System.out.println("Итого: " + getTotalBasketCost());
            System.out.println("Специальных товаров: " + countSpecialProducts());
        }

    }

    //Method for checking a product in the basket by name
    public boolean checkProductName(String productName) {
        for (Product product : products) {
            if (product != null && product.getName().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    //Method for clearing the basket
    public void clearBasket() {
        products.clear();
    }

    public List <Product> basketRemoveProductByName (String name) {
        List <Product> basketItemsRemove = new LinkedList<>();
        Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product != null && product.getName().equals(name)) {
                basketItemsRemove.add(product);
                iterator.remove();
            }
        }
        return basketItemsRemove;
    }
}
