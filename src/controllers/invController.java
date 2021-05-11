package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class invController implements Initializable {
    public Label testLabel; //test label to try out button functionality

    public Button exitButton;

    public Button addPartButton;
    public Button modPartButton;
    public Button deletePartButton;

    public Button addProdButton;
    public Button modProdButton;
    public Button deleteProdButton;

    public TreeTableView partTable;
    public TreeTableColumn partIDCol;
    public TreeTableColumn partNameCol;
    public TreeTableColumn partInvCol;
    public TreeTableColumn partPriceCol;

    public TreeTableView productTable;
    public TreeTableColumn productIDCol;
    public TreeTableColumn productNameID;
    public TreeTableColumn prodInvCol;
    public TreeTableColumn prodPriceCol;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Nothing here. This runs before the GUI so stuff can be done here.

        //Add test data here


        //end of test data

    }

    public void addPartButtonClick(ActionEvent actionEvent) throws IOException {
        //Performs when the add part button is clicked.

        Parent root = FXMLLoader.load(getClass().getResource("/view/AddPartForm_v1.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();

        //testLabel.setText("You pressed Add part");
    }

    public void modPartButtonClick(ActionEvent actionEvent) {
        //Performs actions when the mod part button is pressed
        testLabel.setText("You pressed modify part");
    }

    public void deletePartButtonClick(ActionEvent actionEvent) {
        //Performs actions when the delete part button is pressed
        testLabel.setText("You pressed delete part");
    }

    public void exitButtonPress(ActionEvent actionEvent) {
        //Closes the stage using the .close method to end the program
        ((Stage)(((Node)actionEvent.getSource()).getScene().getWindow())).close();
    }
}
