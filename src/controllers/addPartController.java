package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

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

        //


        //calls the appropriate constructor for the parts
        //passes the inputs to the constructor
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
