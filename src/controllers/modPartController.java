package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import model.*;

import java.io.IOException;

public class modPartController {
    public RadioButton inHouseRadio;
    public RadioButton outSourcedRadio;
    public ToggleGroup addPartToggle;

    public Label invTextLabel;
    public Label partSourceLabel;

    public TextField idTextMod;
    public TextField nameTextMod;
    public TextField invTextMod;
    public TextField priceTextMod;
    public TextField maxTextMod;
    public TextField minTextMod;
    public TextField partSourceTextMod;

    public Button cancelButton;
    public Button saveButton;
    public Button clearFormButton;

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
        addPartController.returnToMain(actionEvent);
    }

    public void saveButtonClick(ActionEvent actionEvent) throws IOException {
        //Set of try-catch blocks to determine if the input types are valid. This will detect if an incorrect type or blank is entered.
        try {
            int partInv = Integer.parseInt(invTextMod.getText());
        } catch (Exception e) {
            inputvalidation.errorMsg("Error with inventory input. Please enter only numbers.");
        }

        try {
            double partPrice = Double.parseDouble(priceTextMod.getText());
        } catch (Exception e) {
            inputvalidation.errorMsg("Error with price input. Please enter a number.");
        }

        try {
            int partMax = Integer.parseInt(maxTextMod.getText());
        } catch (Exception e) {
            inputvalidation.errorMsg("Error with max field. Please enter only numbers.");
        }

        try {
            int partMin = Integer.parseInt(minTextMod.getText());
        } catch (Exception e) {
            inputvalidation.errorMsg("Error with min field. Please enter only numbers.");
        }

        try {
            if (inHouseRadio.isSelected()) {
                int partSource = Integer.parseInt(partSourceTextMod.getText());
            }
        } catch (Exception e) {
            inputvalidation.errorMsg("Machine ID must be an integer.");
        }


        //Gets the input and assigns it to variables then changes the variables to the right type
        int partID = Integer.parseInt(idTextMod.getText());
        String partName = nameTextMod.getText();
        int partInv = Integer.parseInt(invTextMod.getText());
        double partPrice = Double.parseDouble(priceTextMod.getText());
        int partMax = Integer.parseInt(maxTextMod.getText());
        int partMin = Integer.parseInt(minTextMod.getText());
        String partSource = partSourceTextMod.getText(); //changed this to a string input as the initial integer input caused a runtime error when using outsourced part


        //Error checking to determine if values fall in the correct ranges
        String errorMessages = "";
        boolean errorFound = false;

        //Minimum inventory is greater than maximum
        if (partMin >= partMax) {
            errorMessages = "Maximum inventory cannot be less than minimum inventory";
            errorFound = true;
            System.out.println("Max < Min");
        }

        //Inventory must fall between the minimum and maximum values
        if (partInv > partMax || partInv < partMin) {
            errorMessages = errorMessages + " Inventory value must fall between min and max values.";
            errorFound = true;
            System.out.println("Inventory out of bounds");
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

                //run the in house constructor to create a new part based on the input data but uses the same part ID
                InHouse addedPart = new InHouse(partID, partName, partPrice, partInv, partMin, partMax, machineID);

                //Calls the modify part method which adds the part to the observable list by overwriting with the changes
                PartWarehouse.modifyPart(addedPart);
            } else {
                //runs the outsourced constructor and sends the data to the warehouse
                Outsourced addedPart = new Outsourced(partID, partName, partPrice, partInv, partMin, partMax, partSource);

                ///Calls the modify part method which adds the part to the observable list by overwriting with the changes
                PartWarehouse.modifyPart(addedPart);
            }

            //This returns to the main form after saving the entered part
            addPartController.returnToMain(actionEvent);
        }

    }

    //Method to receive the part to be modified and populate the for with the data. This method is static as it is called from the main form
    public void receiveModPart(Part partForMod) {
        idTextMod.setText(String.valueOf(partForMod.getId()));
        nameTextMod.setText(String.valueOf(partForMod.getName()));
        invTextMod.setText(String.valueOf(partForMod.getStock()));
        priceTextMod.setText(String.valueOf(partForMod.getPrice()));
        maxTextMod.setText(String.valueOf(partForMod.getMax()));
        minTextMod.setText(String.valueOf(partForMod.getMin()));


        if (partForMod instanceof InHouse) {
            //set the button
            inHouseRadio.setSelected(true);
            //Change the label
            partSourceLabel.setText("Machine ID");
            //populate the text
            partSourceTextMod.setText(String.valueOf(((InHouse) partForMod).getmachineID()));

        } else {
            //For outsourced

            //set the button
            outSourcedRadio.setSelected(true);
            //change the label
            partSourceLabel.setText("Company Name");
            //populate the text
            partSourceTextMod.setText(String.valueOf(((Outsourced) partForMod).getcompanyName()));
        }
    }


}



