package model;

import javafx.scene.control.TextField;

public class inputvalidation {

    public static boolean validInt (TextField inputBox, String userInput) {
        try {
            int x = Integer.parseInt(userInput);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }


    public static boolean validDbl (TextField inputBox, String userInput) {
        try {
            double x = Double.parseDouble(userInput);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

}

