package model;

//Class to store parts objects and methods to create unique part IDs

import java.util.ArrayList;

public class PartWarehouse {
    Part[] partArray = new Part[10];
    int partStored=0;

    //Constructor
    public PartWarehouse () {
        ArrayList<Part> partList = new ArrayList<Part>();

        //System.out.println();
        System.out.println("The part warehouse constructor ran");
    }

    //static method to add parts to the warehouse
    public void stockPartWarehouse(Part newPart) {
        //partArray[partStored++] = new InHouse();

    }

    //write code to use a data structure to store each object
    //this should only run once when the program launches

    public static void newPartID() {
        //method to get a unique part id
    }




}
