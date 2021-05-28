package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Part;
import model.PartWarehouse;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class invController implements Initializable {
    public Button exitButton;
    public Button addPartButton;
    public Button modPartButton;
    public Button deletePartButton;

    public Button addProdButton;
    public Button modProdButton;
    public Button deleteProdButton;

    public TableView<Part> partTable;
    public TableColumn partIDCol;
    public TableColumn partNameCol;
    public TableColumn partInvCol;
    public TableColumn partPriceCol;

    public TableView productTable;
    public TableColumn productIDCol;
    public TableColumn productNameCol;
    public TableColumn productInvCol;
    public TableColumn productPriceCol;
    public Pane partsPane1;
    public Pane partsPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Sets the parts table from JavaFX to display the items that are contained in the all parts observable list found in the PartWarehouse class
        partTable.setItems(PartWarehouse.getAllParts());

        //Sets the columns to get the data to display - Note, the last part is named based on the getter methods
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


    }

    //Performs when the add part button is clicked.
    public void addPartButtonClick(ActionEvent actionEvent) throws IOException {
        //Sets up and shows the add part form from the fxml file
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddPartForm_v1.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    //Performs actions when the mod part button is pressed
    public void modPartButtonClick(ActionEvent actionEvent) throws IOException {
        //Initializes the Modify Part controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModifyPartForm_v1.fxml"));
        Parent root = loader.load();
        modPartController modController = loader.getController();

        //Sends the selected part to the modify part controller
        modController.receiveModPart(partTable.getSelectionModel().getSelectedItem());

        //Shows the modify part controller after passing the data from the selected part
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Part");
        stage.setScene(scene);
        stage.show();
    }

    //Performs actions when the delete part button is pressed
    public void deletePartButtonClick(ActionEvent actionEvent) {
        //Sets a dialog to ensure the user wants to delete
        var deleteConfirm = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        deleteConfirm.setTitle("Confirm Delete");
        deleteConfirm.setContentText("Are you sure you want to delete the selected items?");
        deleteConfirm.showAndWait();

        //If the user presses yes, the part is deleted from the part table
        if (deleteConfirm.getResult() == ButtonType.YES) {
            partTable.getItems().removeAll(partTable.getSelectionModel().getSelectedItems());
        }
    }

    public void addProdButtonClick (ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddProductForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }

    public void exitButtonPress(ActionEvent actionEvent) {
        //Closes the stage using the .close method to end the program
        ((Stage) (((Node) actionEvent.getSource()).getScene().getWindow())).close();
    }
}
