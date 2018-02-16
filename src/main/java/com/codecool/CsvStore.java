package com.codecool;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CsvStore implements StorageCapable {

    public CsvStore(List<Product> productList) {
        this.productList = productList;
    }

    public CsvStore() {
    }

    private List<Product> productList = new ArrayList<>();

    private void saveToCsv(Product product) {

        StringBuilder sb = new StringBuilder();

        try {
            if (product instanceof CDProduct) {
                CDProduct c = (CDProduct) product;
                sb.append("Cd,");
                sb.append(String.format("%s,", c.getName()));
                sb.append(String.format("%s,", String.valueOf(c.getPrice())));
                sb.append(String.format("%s\n", String.valueOf(c.getNumOfTracks())));

            } else {
                BookProduct b = (BookProduct) product;
                sb.append("Book,");
                sb.append(String.format("%s,", b.getName()));
                sb.append(String.format("%s,", String.valueOf(b.getPrice())));
                sb.append(String.format("%s\n", String.valueOf(b.getNumOfPages())));
            }

            FileWriter writer = new FileWriter("products.csv", true);
            writer.write(sb.toString());
            writer.close();
            sb.setLength(0);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    protected void storeProduct(Product product) {
        productList.add(product);
    }

    protected Product createProduct(String type, String name, int price, int size) {
        if (type.equals("Cd")) return new CDProduct(name, price, size);
        else return new BookProduct(name, price, size);
    }

    public List<Product> loadProductsFromCsv() throws IOException {
        List<Product> productsLoaded = new ArrayList<>();
        String csvFile = "products.csv";

        String productFromCsv = "";
        String cvsSplitBy = ",";

        BufferedReader br = new BufferedReader(new FileReader(csvFile));

        while ((productFromCsv = br.readLine()) != null) {

            String[] fieldsFromCsv = productFromCsv.split(cvsSplitBy);

            if (fieldsFromCsv[0].equals("Cd")) {
                productsLoaded.add(new CDProduct(fieldsFromCsv[1], Integer.parseInt(fieldsFromCsv[2]), Integer.parseInt(fieldsFromCsv[3])));
            } else if (fieldsFromCsv[0].equals("Book")) {
                productsLoaded.add(new BookProduct(fieldsFromCsv[1], Integer.parseInt(fieldsFromCsv[2]), Integer.parseInt(fieldsFromCsv[3])));
            }
        }
        return productsLoaded;

    }

    public void store(Product p) {
        saveToCsv(p);
        storeProduct(p);
    }

    @Override
    public List<Product> getAllProduct() {
        return productList;
    }

    @Override
    public void storeCDProduct(String name, int price, int tracks) {
        Product p = createProduct("Cd", name, price, tracks);
        //productList.add(p);
        store(p);
    }

    @Override
    public void storeBookProduct(String name, int price, int pages) {
        Product p = createProduct("Book", name, price, pages);
        //productList.add(p);
        store(p);
    }
}
