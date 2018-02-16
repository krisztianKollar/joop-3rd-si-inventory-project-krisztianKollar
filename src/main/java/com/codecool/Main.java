package com.codecool;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static Menu menu = new Menu();

    public static void main(String[] args) throws IOException {

        StoreManager sm = new StoreManager();
        sm.addStorage(new CsvStore());
        CsvStore csv = new CsvStore();
        System.out.println("_______________________________");
        System.out.println(csv.loadProductsFromCsv());
        System.out.println("_______________________________");


        sm.addCDProduct("Volkerball", 3500, 12);
        sm.addBookProduct("Calvin and Hobbes", 4000, 215);
        sm.addBookProduct("Kifestő hülyegyerekeknek", 10, 1);
        System.out.println();
        System.out.println(sm.listProducts());


        //menu.handleMenu();
    }
}