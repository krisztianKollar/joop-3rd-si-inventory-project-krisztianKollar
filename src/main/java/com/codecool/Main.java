package com.codecool;


public class Main {

    private static Menu menu = new Menu();

    public static void main(String[] args) {

        StoreManager sm = new StoreManager();
        CsvStore csv = new CsvStore();
        sm.addStorage(csv);
        Product cd = new CDProduct("Volkerball", 3500, 12);
        csv.createProduct("Cd", "Volkerball", 3500, 12);
        csv.store(cd);
        Product book = new BookProduct("Calvin and Hobbes", 4000, 215);
        csv.createProduct("Book", "Calvin and Hobbes", 4000, 215);
        csv.store(book);
        System.out.println(sm.listProducts());

        //menu.handleMenu();
    }
}