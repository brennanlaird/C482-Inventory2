package model;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class inputvalidation {

    private static ArrayList<Integer> partIDs = new ArrayList<Integer>();


    public static boolean validInt(TextField inputBox, String userInput) {
        try {
            int x = Integer.parseInt(userInput);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static boolean validDbl(TextField inputBox, String userInput) {
        try {
            double x = Double.parseDouble(userInput);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Code to display an error message when an input error is found
    //Code partially from https://thecodinginterface.com/blog/javafx-alerts-and-dialogs/#error-alert
    public static void errorMsg(String msg) {
        var alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);

        alert.showAndWait();
    }

    public static int newPartID() {
        //creates an increments to the next available part ID

        //The ID starts at 0
        int nextpartID = 0;

        //if the array list is not empty then go to the last item and add 1 to get the next ID
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

}

