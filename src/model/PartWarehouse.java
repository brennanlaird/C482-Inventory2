package model;

//Class to store parts objects and methods to create unique part IDs

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class PartWarehouse {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();





    //Constructor
    public PartWarehouse () {

        System.out.println("The part warehouse constructor ran");
    }

    //static method to add parts to the warehouse by adding them to the observable list object
    public static void stockPartWarehouse(Part newPart) {

        allParts.add(newPart);
    }

    //takes a part from the modify part controller and inserts the modified data into the parts list
    public static void modifyPart (Part alteredPart) {
        int i = 0;

        for (Part parts : allParts) {
            //is the current item the matching ID to the modified part
            //if that is true then overwrite this item with the modified values


            i++;
        }




    }


    public static void newPartID() {
        //method to get a unique part id
    }

    //This returns all the parts in the observable parts list for display in the main form
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }


}
