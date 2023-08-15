package InventoryManagement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Product {
    String name;
    int price;
    int stock;

    public Product(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}

public class InventoryManagement {

    public static List<Product> sortProducts(List<Product> products, String sortKey, boolean ascending) {
        Comparator<Product> comparator;
        switch (sortKey) {
            case "name":
                comparator = Comparator.comparing(product -> product.name);
                break;
            case "price":
                comparator = Comparator.comparingInt(product -> product.price);
                break;
            case "stock":
                comparator = Comparator.comparingInt(product -> product.stock);
                break;
            default:
                throw new IllegalArgumentException("Invalid sort key");
        }
        
        if (!ascending) {
            comparator = comparator.reversed();
        }
        
        products.sort(comparator);
        return products;
    }

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product A", 100, 5));
        products.add(new Product("Product B", 200, 3));
        products.add(new Product("Product C", 50, 10));

        String sortKey = "price";
        boolean ascending = false;

        List<Product> sortedProducts = sortProducts(products, sortKey, ascending);

        for (Product product : sortedProducts) {
            System.out.println("Name: " + product.name + ", Price: " + product.price + ", Stock: " + product.stock);
        }
    }
}