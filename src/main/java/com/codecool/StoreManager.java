package com.codecool;

import java.util.List;

public class StoreManager {

    StorageCapable sc = new StorageCapable() {  // Kell ilyen?!
        @Override
        public List<Product> getAllProduct() {
            return null;
        }

        @Override
        public void storeCDProduct(String name, int price, int tracks) {

        }

        @Override
        public void storeBookProduct(String name, int price, int pages) {

        }
    };

    public StoreManager() {
    }

    public String addStorage(storage: StorageCapable) {
        return String;
    }

    public void addCDProduct(String name, int price, int tracks) {

        // storeCDProduct from interface
    }

    public void addBookProduct(String name, int price, int pages) {

        // storeBookProduct from interface
    }

    public String listProducts() {

    }

    public int getTotalProductPrice() {

    }


}
