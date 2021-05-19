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
        alert.setTitle("Input Error");
        alert.setHeaderText("One or more issues detected");
        alert.setContentText(msg);

        alert.showAndWait();
    }

    public static int newPartID() {
        //creates an increments to the next available part ID


        //ArrayList<Integer> partIDs = new ArrayList<Integer>();


        int nextpartID = 0;

        if (!partIDs.isEmpty()) {

            nextpartID = partIDs.get(partIDs.size() - 1) + 1;

            partIDs.add(nextpartID);

        } else {
            nextpartID = 1;
            partIDs.add(nextpartID);

        }


        return nextpartID;
    }

}

