package com.codecool;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Menu {

    private static final Set<String> LINES = Set.of("1", "2", "3", "4", "5");
    private static Scanner scanner = new Scanner(System.in);
    private static StoreManager sm = new StoreManager();

    public void handleMenu() {
        PersistentStore storage = null;
        System.out.println("\nWelcome To The Inventory!\n");
        try {
            storage = new PersistentStore();
        } catch (ParserConfigurationException e) {
            System.err.println("Oh-oh, something went wrong with the XML, please check it!\n");
            System.exit(1);
        } catch (SAXException e) {
            System.err.println("Some syntax errors are in the XML, please check it!\n");
            System.exit(2);
        } catch (IOException e) {
            System.out.println("I couldn't find the XML file: the XML store is empty, you can load it with adding new products!\n");
            storage = new PersistentStore(new ArrayList<>());
        }

        sm.addStorage(storage);
        System.out.println("Please type a number you need!\n");
        System.out.println("\t1   Add CD");
        System.out.println("\t2   Add Book");
        System.out.println("\t3   List");
        System.out.println("\t4   Get Total Price of Products");
        System.out.println("\t5   Exit\n");

        while (true) {
            String line = scanner.nextLine();
            if (!LINES.contains(line)) {
                System.out.println("Please type 1, 2, 3 or 4!\n");
            } else if ("5".equals(line)) break;
            else if ("4".equals(line)) handleTotalPrice();
            else if ("3".equals(line)) handleList();
            else if ("2".equals(line)) handleAddBoook();
            else if ("1".equals(line)) handleAddCD();
        }
    }

    private static void handleList() {
        System.out.println(sm.listProducts());
    }

    private static void handleAddBoook() {

        String type = "Book";
        System.out.println("Please enter the title of the book!");
        String name = scanner.nextLine();
        System.out.println("Please enter the price of the book!");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter the number of pages!");
        int numOfPages = Integer.parseInt(scanner.nextLine());

        sm.addBookProduct(name, price, numOfPages);
    }

    private static void handleAddCD() {

        String type = "CD";
        System.out.println("Please enter the name of the CD!");
        String name = scanner.nextLine();
        System.out.println("Please enter the price of the CD!");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter the number of tracks");
        int numOfTracks = Integer.parseInt(scanner.nextLine());

        sm.addCDProduct(name, price, numOfTracks);
    }

    private static void handleTotalPrice() {

        System.out.println("The price of all products:");
        System.out.println(sm.getTotalProductPrice());
    }
}
