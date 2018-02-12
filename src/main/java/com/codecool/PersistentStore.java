package com.codecool;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class PersistentStore extends Store {

    private List<Product> productList;

    public PersistentStore() throws ParserConfigurationException, SAXException, IOException {
        productList = loadProducts();
    }

    public PersistentStore(List<Product> productList)  {
        this.productList = productList;
    }

    @Override
    public List<Product> getAllProduct() {
        return productList;
    }

    @Override
    public void storeCDProduct(String name, int price, int tracks) {
        Product p = createProduct("CD", name, price, tracks);
        productList.add(p);
        store(p);
    }

    @Override
    public void storeBookProduct(String name, int price, int pages) {
        Product p = createProduct("Book", name, price, pages);
        productList.add(p);
        store(p);
    }
}
