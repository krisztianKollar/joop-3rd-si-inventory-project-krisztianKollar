package com.codecool;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public abstract class CsvStore implements StorageCapable {

    private List<Product> productList = new ArrayList<>();

    private void saveToCsv(Product product) {

        StringBuilder sb = new StringBuilder();

        try {
            for (Product p : getAllProduct()) {
                if (product instanceof CDProduct) {
                    CDProduct c = (CDProduct) p;
                    sb.append("Cd");
                    sb.append(c.getName());
                    sb.append(String.valueOf(c.getPrice()));
                    sb.append(String.valueOf(c.getNumOfTracks()));

                } else {
                    BookProduct b = (BookProduct) p;
                    sb.append("Book");
                    sb.append(b.getName());
                    sb.append(String.valueOf(b.getPrice()));
                    sb.append(String.valueOf(b.getNumOfPages()));
                }

                System.out.println("File saved!");
            }
            FileWriter writer = new FileWriter("products.csv");
            writer.write(sb.toString());
            writer.close();

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

        BufferedReader bufferedReader = new BufferedReader(new FileReader("products.csv"));
        String productFromCsv = bufferedReader.readLine();
        while (productFromCsv != null) {
            String[] fieldFromCsv = productFromCsv.split(",");
            if (fieldFromCsv[0].equals("Cd")) {
                productsLoaded.add(new CDProduct(fieldFromCsv[1], Integer.parseInt(fieldFromCsv[2]), Integer.parseInt(fieldFromCsv[3])));
            } else if (fieldFromCsv[0].equals("Book")) {
                productsLoaded.add(new BookProduct(fieldFromCsv[1], Integer.parseInt(fieldFromCsv[2]), Integer.parseInt(fieldFromCsv[3])));
            }
            return productsLoaded;
        }
    }

        public void store(Product p) {
            saveToCsv(p);
            storeProduct(p);
        }
    }
}
