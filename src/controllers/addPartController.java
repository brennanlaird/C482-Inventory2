package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Part;

import java.util.ArrayList;



import java.io.IOException;

public class addPartController {
    public Label testLabel;
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




    public void setInHouseRadioSelected(ActionEvent actionEvent) {
        partSourceLabel.setText("Machine ID");
    }

    public void outSourcedRadioSelected(ActionEvent actionEvent) {
        partSourceLabel.setText("Company Name");
    }

    public void cancelButtonClick(ActionEvent actionEvent) throws IOException {
        //Cancel button returns to the main screen and does not save changes
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainForm_v1.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("DFC Inventory Control");
        stage.setScene(scene);
        stage.show();
    }

    public void saveButtonClick(ActionEvent actionEvent) {
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
            //run the in house constructor
            InHouse addedPart = new InHouse(1, partName, partPrice, partInv, partMin, partMax);

        } else {
            //run the outsourced constructor

        }

        //calls the appropriate constructor for the parts and passes the inputs to the constructor
        //adds the new item to the list for table display


    }

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
