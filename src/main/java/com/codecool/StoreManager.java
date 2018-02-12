package com.codecool;

import java.util.List;

public class StoreManager {

    private StorageCapable storage;

    public StoreManager() {
    }

    public void addStorage(StorageCapable storage) {
        this.storage = storage;
    }

    public void addCDProduct(String name, int price, int tracks) {
        storage.storeCDProduct(name, price, tracks);
    }

    public void addBookProduct(String name, int price, int pages) {
        storage.storeBookProduct(name, price, pages);
    }

    public String listProducts() {
        String listProduct = "";
        for (Product p : storage.getAllProduct()) {
            listProduct = listProduct.concat(p + "\n");
        }
        return listProduct;
    }

    public int getTotalProductPrice() {
        List<Product> allProduct = storage.getAllProduct();
        int totalProductPrice = 0;
        for (Product product : allProduct) {
            totalProductPrice += (product.getPrice());
        }
        return totalProductPrice;
    }


}
