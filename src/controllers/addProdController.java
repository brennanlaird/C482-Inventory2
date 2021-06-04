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
    public Button clearSearchButtonAdd;
    public Button partSearchButtonAdd;
    public TextField partSearchAdd;


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

        //Set of try-catch blocks to determine if the input types are valid. This will detect if an incorrect type or blank is entered.
        //If the type is incorrect the error message is called and passed the appropriate message
        try {
            int prodInv = Integer.parseInt(invProdText.getText());
        } catch (Exception e) {
            inputvalidation.errorMsg("Error with inventory input. Please enter only numbers.");
        }

        try {
            double prodPrice = Double.parseDouble(priceProdText.getText());
        } catch (Exception e) {
            inputvalidation.errorMsg("Error with price input. Please enter a number.");
        }

        try {
            int prodMax = Integer.parseInt(maxProdText.getText());
        } catch (Exception e) {
            inputvalidation.errorMsg("Error with max field. Please enter only numbers.");
        }

        try {
            int prodMin = Integer.parseInt(minProdText.getText());
        } catch (Exception e) {
            inputvalidation.errorMsg("Error with min field. Please enter only numbers.");
        }




        //Gets the input and assigns it to variables then changes the variables to the right type
        String prodName = nameProdText.getText();
        int prodInv = Integer.parseInt(invProdText.getText());
        double prodPrice = Double.parseDouble(priceProdText.getText());
        int prodMax = Integer.parseInt(maxProdText.getText());
        int prodMin = Integer.parseInt(minProdText.getText());



        //Error checking to determine if values fall in the correct ranges
        String errorMessages = "";
        boolean errorFound = false;


        //Minimum inventory is greater than maximum
        if (prodMin >= prodMax) {
            errorMessages = "Maximum inventory cannot be less than minimum inventory";
            errorFound = true;
        }

        //Inventory must fall between the minimum and maximum values
        if (prodInv > prodMax || prodInv < prodMin) {
            errorMessages = errorMessages + " Inventory value must fall between min and max values.";
            errorFound = true;
        }


        if (errorFound) {
            inputvalidation.errorMsg(errorMessages);
        } else {


            Product addingProduct = new Product(inputvalidation.newProdID(), prodName, prodPrice, prodInv, prodMin, prodMax, null);

            addingProduct.addAssociatedPart(assocTable);

            ProdWarehouse.stockProdWarehouse(addingProduct);
            addPartController.returnToMain(actionEvent);
        }


    }

    public void clearFormButtonClick(ActionEvent actionEvent) {
        idProdText.setText("");
        nameProdText.setText("");
        invProdText.setText("");
        priceProdText.setText("");
        maxProdText.setText("");
        minProdText.setText("");
        assocPartTable.setItems(null);
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

    private ObservableList<Part> searchPartNameAdd(String partialName) {
        //Sets up a list to store the parts found with a partial string match search
        ObservableList<Part> foundParts = FXCollections.observableArrayList();

        //Sets up a list to store all the parts to search through
        ObservableList<Part> allParts = PartWarehouse.getAllParts();

        //
        for (Part p : allParts) {
            if (p.getName().contains(partialName)) {
                foundParts.add(p);
            }
        }
        return foundParts;
    }

    private Part getPartIDMatch(int searchID) {
        ObservableList<Part> allParts = PartWarehouse.getAllParts();

        for (Part q : allParts) {
            if (q.getId() == searchID) {
                return q;
            }
        }


        return null;
    }


    public void partSearchHandler(ActionEvent actionEvent) {
        String searchText = partSearchAdd.getText();

        ObservableList<Part> parts = searchPartNameAdd(searchText);


        if (parts.size() == 0) {

            try {

                int partID = Integer.parseInt(searchText);
                Part q = getPartIDMatch(partID);
                if (q != null) {
                    parts.add(q);
                }
            } catch (NumberFormatException e) {
            }

        }


        if (parts.isEmpty()) {

            inputvalidation.errorMsg("No matching part found.");

        } else {
            partsTable.setItems(parts);
        }


    }

    //resets the search boxes to blank and repopulates the tables with all the data
    public void clearSearchHandler(ActionEvent actionEvent) {
        partSearchAdd.setText("");


        partSearchHandler(actionEvent);

    }


}
