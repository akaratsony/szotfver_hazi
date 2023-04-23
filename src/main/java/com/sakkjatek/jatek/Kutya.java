package com.sakkjatek.jatek;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class Kutya {
    private int height = 80;
    private int width = 80;
    private int columnLocation;
    private int rowLocation;
    private String name;
    private int number;
    private String picture = "C:/Users/szajb/Pictures/Screenshots/brigisuni.png";
    // Konstruktor
    public Kutya(int columnLocation, int rowLocation, String name,int number) {
        this.columnLocation = columnLocation;
        this.rowLocation = rowLocation;
        this.name = name;
        this.number = number;


    }

    // Getterek és setterek
    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }
    public int number() {
        return number;
    }
    public String getpicture() {
        return picture;
    }
    public int getColumnLocation() {
        return columnLocation;
    }
    public void setnumber(int number) {
         this.number = number;
    }


    public void setColumnLocation(int columnLocation) {
        this.columnLocation = columnLocation;
    }

    public int getRowLocation() {
        return rowLocation;
    }

    public void setRowLocation(int rowLocation) {
        this.rowLocation = rowLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
