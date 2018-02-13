package com.codecool;

public class BookProduct extends Product {

    private int numOfPages;

    public BookProduct(String name, int price, int numOfPages) {
        super(name, price);
        this.numOfPages = numOfPages;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    @Override
    public String toString() {
        return String.format("%s  (%d HUF, number of pages: %d)", getName(), getPrice(), getNumOfPages());
    }
}
