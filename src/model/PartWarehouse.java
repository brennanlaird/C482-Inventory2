package model;

//Class to store and modify parts objects

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**This class implements an object to store the parts which are created. The class provides methods to add, modify and retrieve part objects
 * FUTURE ENHANCEMENT: The inventory data should persist when the program is closed. It would be ideal to use a database implementation to ensure
 * that saved data can be brought back after the program is closed.*/
public class PartWarehouse {
    //The observable list of all parts used to display in the tableview and store all the parts added
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    //No constructor as the default constructor works fine.


    /**Static method to add parts to the warehouse by adding them to the observable list object.
     * @param newPart The new part to be saved.*/
    public static void stockPartWarehouse(Part newPart) {
        allParts.add(newPart);
    }

    /**Takes a part from the modify part controller and inserts the modified data into the parts list.
     * @param alteredPart The part that was modified and will be saved.*/
    public static void modifyPart(Part alteredPart) {
        int i = 0; //counter for the For-loop below

        //The for loop iterates through each part in the all parts list
        for (Part parts : allParts) {
            //If the ID of the altered part that was passed in from the modify part controller matches the current part selected by the for loop
            if (alteredPart.getId() == allParts.get(i).getId()) {

                //The data is altered in the list if the part ID numbers matched
                allParts.get(i).setId(alteredPart.getId());
                allParts.get(i).setName(alteredPart.getName());
                allParts.get(i).setStock(alteredPart.getStock());
                allParts.get(i).setPrice(alteredPart.getPrice());
                allParts.get(i).setMax(alteredPart.getMax());
                allParts.get(i).setMin(alteredPart.getMin());

                //This if statement checks the type of part and then makes the necessary substitution with the modifications
                if (alteredPart instanceof InHouse) {
                    ((InHouse) allParts.get(i)).setMachineID(((InHouse) alteredPart).getmachineID());
                } else {
                    ((Outsourced) allParts.get(i)).setcompanyName(((Outsourced) alteredPart).getcompanyName());
                }
            }
            i++; //Iterates the loop counter to move to the next part in the list
        }
    }

    /**This returns all the parts in the observable parts list for display in the main form.
     * @return Returns the observable list of all saved parts.*/
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
}
