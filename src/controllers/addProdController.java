package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addProdController implements Initializable {
    public TableView<Part> partsTable;
    public TableView<Part> assocPartTable;

    public Label invTextLabel;

    public TextField idProdText;
    public TextField nameProdText;
    public TextField invProdText;
    public TextField priceProdText;
    public TextField maxProdText;
    public TextField minProdText;

    public Button cancelButton;
    public Button saveProdButton;
    public Button clearFormButton;

    public Button addButton;
    public Button removeButton;

    public TableColumn partIDCol;
    public TableColumn partNameCol;
    public TableColumn partInvCol;
    public TableColumn partPriceCol;
    public TableColumn assocPartIDCol;
    public TableColumn assocPartNameCol;
    public TableColumn assocPartInvCol;
    public TableColumn assocPartPriceCol;


    //Create an Observable list to populate the bottom table
    private ObservableList<Part> assocTable = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(PartWarehouse.getAllParts());

        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        assocPartTable.setItems(assocTable);

        assocPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    public void cancelButtonClick(ActionEvent actionEvent) throws IOException {
        addPartController.returnToMain(actionEvent);
    }

    public void saveButtonClick(ActionEvent actionEvent) throws IOException {

        //Error checking goes here



        //Gets the input and assigns it to variables then changes the variables to the right type
        String prodName = nameProdText.getText();
        int prodInv = Integer.parseInt(invProdText.getText());
        double prodPrice = Double.parseDouble(priceProdText.getText());
        int prodMax = Integer.parseInt(maxProdText.getText());
        int prodMin = Integer.parseInt(minProdText.getText());

        Product addingProduct = new Product(inputvalidation.newProdID(), prodName, prodPrice, prodInv, prodMin, prodMax, null);

        addingProduct.addAssociatedPart(assocTable);

        ProdWarehouse.stockProdWarehouse(addingProduct);
        addPartController.returnToMain(actionEvent);
    }

    public void clearFormButtonClick(ActionEvent actionEvent) {
    }

    public void addToAssocPartButton(ActionEvent actionEvent) {

        //Gets the part selected from the top table and assigns it to a temp value
        Part temp = partsTable.getSelectionModel().getSelectedItem();

        //If no thing is selected then return and do nothing
        if (temp == null) {
            return;
        }
        //Add the selected part to the lower table.
        assocTable.add(temp);
    }

    public void removeAssocPartButton(ActionEvent actionEvent) {
        assocPartTable.getItems().removeAll(assocPartTable.getSelectionModel().getSelectedItems());
    }
}
