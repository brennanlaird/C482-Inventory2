package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.ArrayList;

//Was initially named input validation but this became a place to store several static methods.
/**This class is used for static methods that can be called from other methods. It was originally planned to be
 * used for error checking but that was discarded after the class was named.
 * FUTURE ENHANCEMENT: Change the name of the class to something that better represents what the class does.
 * Also, further static methods could be housed here to ensure consistency in how the code is deployed.*/
public class inputvalidation {

    //Declare the array lists that will be used to store the part and product ID
    private static ArrayList<Integer> partIDs = new ArrayList<Integer>();
    private static ArrayList<Integer> prodIDs = new ArrayList<Integer>();

    /**Code to display an error message when an input error is found. Message is passed from calling code so
     * this method can be used to display multiple types of errors.
     * @param msg The error message string to display.*/
    public static void errorMsg(String msg) {
        var alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**Creates and increments to the next available part ID.
     * @return The next available ID for a new part.*/
    public static int newPartID() {

        //The ID starts at 0
        int nextpartID = 0;

        //if the array list is NOT empty then go to the last item and add 1 to get the next ID
        if (!partIDs.isEmpty()) {
            nextpartID = partIDs.get(partIDs.size() - 1) + 1;
        } else {
            //starting Id is 1 for when the array list is empty
            nextpartID = 1;
        }

        //add the part ID determined from the if-else statement
        partIDs.add(nextpartID);
        return nextpartID;
    }

    /**Creates and increments to the next available product ID.
     * @return The next available ID for a new product.*/
    public static int newProdID() {

        //The ID starts at 0
        int nextprodID = 0;

        //if the array list is not empty then go to the last item and add 1 to get the next ID
        if (!prodIDs.isEmpty()) {
            nextprodID = prodIDs.get(prodIDs.size() - 1) + 1;
        } else {
            //starting Id is 1 for when the array list is empty
            nextprodID = 1;
        }

        //add the part ID determined from the if-else statement
        prodIDs.add(nextprodID);
        return nextprodID;
    }
}

