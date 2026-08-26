package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {
    // Basket contains a list of products
    private final Map<String, List<Product>> products = new HashMap<>();

    //Method for adding a product to the basket
    public void addProduct(Product product) {
        String nameProduct = product.getName();
        products.computeIfAbsent(nameProduct, k -> new ArrayList<>()).add(product);
    }

    //Method for obtaining the total basket cost
    public int getTotalBasketCost() {
        int totalCost = 0;
        for (List<Product> listProduct : products.values()) {
            for (Product product : listProduct) {
                if (product != null) {
                    totalCost += product.getPrice();
                }
            }
        }
        return totalCost;
    }

    //Method counts special products
    public int countSpecialProducts() {
        int iCount = 0;
        for (List<Product> listProduct : products.values()) {
            for (Product product : listProduct) {
                if (product != null && product.isSpecial()) {
                    iCount++;
                }
            }
        }
        return iCount;
    }

    //Method prints the basket contents
    public void printBasketContents() {
        boolean emptybasket = true;
        for (List<Product> listProduct : products.values()) {
            for (Product product : listProduct) {
                if (product != null) {
                    System.out.println(product.toString());
                    emptybasket = false;
                }
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
        return products.containsKey(productName);
    }

    //Method for clearing the basket
    public void clearBasket() {
        products.clear();
    }

    public List<Product> basketRemoveProductByName(String name) {
        List<Product> basketItemsRemove = products.remove(name);

        if (basketItemsRemove == null) {
            return new ArrayList<>();
        }
        return basketItemsRemove;
    }
}
