package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Outsourced;
import model.PartWarehouse;
import model.inputvalidation;

import java.io.IOException;

public class addPartController {
    public RadioButton inHouseRadio;
    public RadioButton outSourcedRadio;
    public Button cancelButton;
    public Button saveButton;
    public Button clearFormButton;
    public Label partSourceLabel;
    public ToggleGroup addPartToggle;
    public TextField idText;
    public TextField nameText;
    public TextField invText;
    public TextField priceText;
    public TextField maxText;
    public TextField partSourceText;
    public TextField minText;
    public Label invTextLabel;


    //Changes the label if the in-house radio button is selected
    public void setInHouseRadioSelected(ActionEvent actionEvent) {
        partSourceLabel.setText("Machine ID");
    }

    //Changes the label if the outsourced radio button is selected
    public void outSourcedRadioSelected(ActionEvent actionEvent) {
        partSourceLabel.setText("Company Name");
    }

    public void cancelButtonClick(ActionEvent actionEvent) throws IOException {
        //Cancel button returns to the main screen and does not save changes
        returnToMain(actionEvent);
    }

    //runtime error - need to change the company name so it accepts strings in the part source input.
    //runtime error input validation error box displayed but the code crashed after hitting ok. Need to add a way to reload the form with the data and not continue the code
    public void saveButtonClick(ActionEvent actionEvent) throws IOException {

        //Set of try-catch blocks to determine if the input types are valid. This will detect if an incorrect type or blank is entered.
        //If the type is incorrect the error message is called and passed the appropriate message
        try {
            int partInv = Integer.parseInt(invText.getText());
        } catch (Exception e) {
            inputvalidation.errorMsg("Error with inventory input. Please enter only numbers.");
        }

        try {
            double partPrice = Double.parseDouble(priceText.getText());
        } catch (Exception e) {
            inputvalidation.errorMsg("Error with price input. Please enter a number.");
        }

        try {
            int partMax = Integer.parseInt(maxText.getText());
        } catch (Exception e) {
            inputvalidation.errorMsg("Error with max field. Please enter only numbers.");
        }

        try {
            int partMin = Integer.parseInt(minText.getText());
        } catch (Exception e) {
            inputvalidation.errorMsg("Error with min field. Please enter only numbers.");
        }

        try {
            if (inHouseRadio.isSelected()) {
                int partSource = Integer.parseInt(partSourceText.getText());
            }
        } catch (Exception e) {
            inputvalidation.errorMsg("Machine ID must be an integer.");
        }


        //Gets the input and assigns it to variables then changes the variables to the right type
        String partName = nameText.getText();
        int partInv = Integer.parseInt(invText.getText());
        double partPrice = Double.parseDouble(priceText.getText());
        int partMax = Integer.parseInt(maxText.getText());
        int partMin = Integer.parseInt(minText.getText());
        String partSource = partSourceText.getText(); //changed this to a string input as the initial integer input caused a runtime error when using outsourced part


        //Error checking to determine if values fall in the correct ranges
        String errorMessages = "";
        boolean errorFound = false;


        //Minimum inventory is greater than maximum
        if (partMin >= partMax) {
            errorMessages = "Maximum inventory cannot be less than minimum inventory";
            errorFound = true;
        }

        //Inventory must fall between the minimum and maximum values
        if (partInv > partMax || partInv < partMin) {
            errorMessages = errorMessages + " Inventory value must fall between min and max values.";
            errorFound = true;
        }

        //This if statement displays an error message if the inventory and min max statements found issues.
        //If there were no issues, the objects are created and stored and then the program returns to the main menu.
        if (errorFound) {
            inputvalidation.errorMsg(errorMessages);
        } else {
            //Determines if the object should be a an in house or outsourced part
            if (inHouseRadio.isSelected()) {

                //Puts the partSource to an integer to be used with the in-house constructor
                int machineID = Integer.parseInt(partSource);

                //run the in house constructor to create a new part based on the input data
                InHouse addedPart = new InHouse(inputvalidation.newPartID(), partName, partPrice, partInv, partMin, partMax, machineID);

                //Calls the stockWarehouse method which adds the part to the observable list for the parts
                PartWarehouse.stockPartWarehouse(addedPart);
            } else {
                //runs the outsourced constructor and sends the data to the warehouse
                Outsourced addedPart = new Outsourced(inputvalidation.newPartID(), partName, partPrice, partInv, partMin, partMax, partSource);

                //Calls the stockWarehouse method which adds the part to the observable list for the parts
                PartWarehouse.stockPartWarehouse(addedPart);
            }

            //This returns to the main form after saving the entered part
            returnToMain(actionEvent);
        }

    }

    //method to return to the main form. Made static so it can be called from other controllers.
    public static void returnToMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(addPartController.class.getResource("/view/MainForm_v1.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("DFC Inventory Control");
        stage.setScene(scene);
        stage.show();
    }

    //Clears the fields and stays with the current form
    public void clearFormButtonClick(ActionEvent actionEvent) {
        //Resets the text boxes to blank without saving.
        idText.setText("");
        nameText.setText("");
        invText.setText("");
        priceText.setText("");
        maxText.setText("");
        minText.setText("");
        partSourceText.setText("");
    }


}
