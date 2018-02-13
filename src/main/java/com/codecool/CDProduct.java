package com.codecool;

public class CDProduct extends Product {

    private int numOfTracks;

    public CDProduct(String name, int price, int numOfTracks) {
        super(name, price);
        this.numOfTracks = numOfTracks;
    }

    public int getNumOfTracks() {
        return numOfTracks;
    }

    @Override
    public String toString() {
        return String.format("%s  (%d HUF, number of tracks: %d)", getName(), getPrice(), getNumOfTracks());
    }
}
