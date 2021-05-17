package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.InHouse;
import model.Part;
import model.PartWarehouse;

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

    public TableView partTable;
    public TableColumn partIDCol;
    public TableColumn partNameCol;
    public TableColumn partInvCol;
    public TableColumn partPriceCol;

    public TableView productTable;
    public TableColumn productIDCol;
    public TableColumn productNameCol;
    public TableColumn productInvCol;
    public TableColumn productPriceCol;


    //Sets up an observable list to populate the parts table
    //private ObservableList<Part> allParts = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //This runs before the GUI so stuff can be done here.

        //Add test data here
        //InHouse partInHouse1 = new InHouse(1,"Test Part 1",5.00,55,10,99);
        //InHouse partInHouse2 = new InHouse(2,"Test Part 2",3.50,9,1,99);
        //end of test data


        //Sets the parts table from JavaFX to display the items that are contained in the all parts observable list
        partTable.setItems(PartWarehouse.getAllParts());

        //Sets the columns to get the data to display - Note, the last part is named based on the getter methods
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        //Adds the test data to the observable list
        //allParts.add(partInHouse1);
        //allParts.add(partInHouse2);


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
