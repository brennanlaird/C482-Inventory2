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

public class modProdController implements Initializable {

    public Label invTextLabel;

    public TextField idProdTextMod;
    public TextField nameProdTextMod;
    public TextField invProdTextMod;
    public TextField priceProdTextMod;
    public TextField maxProdTextMod;
    public TextField minProdTextMod;

    public Button cancelButton;
    public Button saveProdButton;

    public TableView<Part> partsTableMod;
    public TableColumn partIDCol;
    public TableColumn partNameCol;
    public TableColumn partInvCol;
    public TableColumn partPriceCol;

    public TableView<Part> assocPartTableMod;
    public TableColumn assocPartIDCol;
    public TableColumn assocPartNameCol;
    public TableColumn assocPartInvCol;
    public TableColumn assocPartPriceCol;

    public Button addButton;
    public Button removeButton;
    public TextField partSearchMod;
    public Button partSearchButton;
    public Button clearSearchButton;

    //Create an Observable list to populate the bottom table
    private ObservableList<Part> assocTableMod = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTableMod.setItems(PartWarehouse.getAllParts());

        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        assocPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        System.out.println("Initialize");


    }


    public void cancelButtonClick(ActionEvent actionEvent) throws IOException {
        //Cancel button returns to the main screen and does not save changes
        addPartController.returnToMain(actionEvent);
    }

    public void saveButtonClick(ActionEvent actionEvent) throws IOException {
        //Set of try-catch blocks to determine if the input types are valid. This will detect if an incorrect type or blank is entered.
        //If the type is incorrect the error message is called and passed the appropriate message
        try {
            int prodInv = Integer.parseInt(invProdTextMod.getText());
        } catch (Exception e) {
            inputvalidation.errorMsg("Error with inventory input. Please enter only numbers.");
        }

        try {
            double prodPrice = Double.parseDouble(priceProdTextMod.getText());
        } catch (Exception e) {
            inputvalidation.errorMsg("Error with price input. Please enter a number.");
        }

        try {
            int prodMax = Integer.parseInt(maxProdTextMod.getText());
        } catch (Exception e) {
            inputvalidation.errorMsg("Error with max field. Please enter only numbers.");
        }

        try {
            int prodMin = Integer.parseInt(minProdTextMod.getText());
        } catch (Exception e) {
            inputvalidation.errorMsg("Error with min field. Please enter only numbers.");
        }


        //Gets the input and assigns it to variables then changes the variables to the right type
        int prodIDMod = Integer.parseInt(idProdTextMod.getText());
        String prodNameMod = nameProdTextMod.getText();
        int prodInvMod = Integer.parseInt(invProdTextMod.getText());
        double prodPriceMod = Double.parseDouble(priceProdTextMod.getText());
        int prodMaxMod = Integer.parseInt(maxProdTextMod.getText());
        int prodMinMod = Integer.parseInt(minProdTextMod.getText());


        //Error checking to determine if values fall in the correct ranges
        String errorMessages = "";
        boolean errorFound = false;


        //Minimum inventory is greater than maximum
        if (prodMinMod >= prodMaxMod) {
            errorMessages = "Maximum inventory cannot be less than minimum inventory";
            errorFound = true;
        }

        //Inventory must fall between the minimum and maximum values
        if (prodInvMod > prodMaxMod || prodInvMod < prodMinMod) {
            errorMessages = errorMessages + " Inventory value must fall between min and max values.";
            errorFound = true;
        }


        if (errorFound) {
            inputvalidation.errorMsg(errorMessages);
        } else {


            Product modifiedProduct = new Product(prodIDMod, prodNameMod, prodPriceMod, prodInvMod, prodMinMod, prodMaxMod, null);

            modifiedProduct.addAssociatedPart(assocTableMod);

            ProdWarehouse.modifyProd(modifiedProduct);
            addPartController.returnToMain(actionEvent);
        }
    }


    public void receiveModProd(Product prodForMod) {
        System.out.println(prodForMod.getName() + " received");

        idProdTextMod.setText(String.valueOf(prodForMod.getId()));
        nameProdTextMod.setText(String.valueOf(prodForMod.getName()));
        invProdTextMod.setText(String.valueOf(prodForMod.getStock()));
        priceProdTextMod.setText(String.valueOf(prodForMod.getPrice()));
        maxProdTextMod.setText(String.valueOf(prodForMod.getMax()));
        minProdTextMod.setText(String.valueOf(prodForMod.getMin()));


        assocTableMod = prodForMod.getAssocParts();

        assocPartTableMod.setItems(assocTableMod);

        //assocPartTableMod.setItems(prodForMod.getAssocParts());

        System.out.println(String.valueOf(prodForMod.getAssocParts()));

        //assocPartTableMod.setItems(assocTableMod);

        //assocPartTableMod.setItems(assocTableMod);
/*
        assocPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
*/

    }

    public void addToAssocPartButtonMod(ActionEvent actionEvent) {
        //Gets the part selected from the top table and assigns it to a temp value
        Part temp = partsTableMod.getSelectionModel().getSelectedItem();

        //If no thing is selected then return and do nothing
        if (temp == null) {
            return;
        }
        //Add the selected part to the lower table.
        System.out.println("Got part Id: " + temp.getId());

        assocTableMod.add(temp);
    }

    public void removeAssocPartButtonMod(ActionEvent actionEvent) {
        //assocPartTableMod.getItems().removeAll(assocPartTableMod.getSelectionModel().getSelectedItem());


        //Part to_remove = assocPartTableMod.getSelectionModel().getSelectedItem();

        assocTableMod.remove(assocPartTableMod.getSelectionModel().getSelectedItem());

    }


    private ObservableList<Part> searchPartNameMod(String partialName) {
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
        String searchText = partSearchMod.getText();

        ObservableList<Part> parts = searchPartNameMod(searchText);


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
            partsTableMod.setItems(parts);
        }

    }

    //resets the search boxes to blank and repopulates the tables with all the data
    public void clearSearchHandler(ActionEvent actionEvent) {
        partSearchMod.setText("");


        partSearchHandler(actionEvent);

    }
}
