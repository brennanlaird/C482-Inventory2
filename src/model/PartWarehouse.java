package model;

//Class to store parts objects and methods to create unique part IDs

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class PartWarehouse {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();


    //Part[] partArray = new Part[10];
    //int partStored=0;

    //ArrayList<Part> partList = new ArrayList<Part>();


    //Constructor
    public PartWarehouse () {
        //ArrayList<Part> partList = new ArrayList<Part>();

        //System.out.println();
        System.out.println("The part warehouse constructor ran");
    }

    //static method to add parts to the warehouse
    public static void stockPartWarehouse(Part newPart) {
        //partArray[partStored++] = new InHouse();
        //partList.add(newPart);
        allParts.add(newPart);
    }

    //write code to use a data structure to store each object
    //this should only run once when the program launches



    public static void newPartID() {
        //method to get a unique part id
    }

    public static ObservableList<Part> getAllParts(){
        return allParts;
    }


}
