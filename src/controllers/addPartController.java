package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.PartWarehouse;
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

    public void saveButtonClick(ActionEvent actionEvent) throws IOException {
        //checks the input for errors


        //Gets the input and assigns it to variables then changes the variables to the right type
        String partName = nameText.getText();
        int partInv = Integer.parseInt(invText.getText());
        double partPrice = Double.parseDouble(priceText.getText());
        int partMax = Integer.parseInt(maxText.getText());
        int partMin = Integer.parseInt(minText.getText());
        int partSource = Integer.parseInt(partSourceText.getText());

        //Determines if the object should be a an in house or outsourced part

        if (inHouseRadio.isSelected()){
            //run the in house constructor to create a new part based on the input data
            InHouse addedPart = new InHouse(1, partName, partPrice, partInv, partMin, partMax);

            //Calls the stockWarehouse method which adds the part to the observable list for the parts
            PartWarehouse.stockPartWarehouse(addedPart);

            //This returns to the main form after saving the entered part
            returnToMain(actionEvent);


        } else {
            //runs the outsourced constructor

        }




    }

    //method to return to the main form.
    public static void returnToMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(addPartController.class.getResource("/view/MainForm_v1.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
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
