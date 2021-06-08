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
/**This implements the modify product form to change a part selected from the main form.*/
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

    /**This method is called when the form is initialized to set up the tables to display the desired data.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Dsiplays all available parts in the top table on the form
        partsTableMod.setItems(PartWarehouse.getAllParts());

        //Sets the columns for the top table
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Sets up the columns for the bottom table.
        assocPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**Cancel button returns to the main screen and does not save changes.*/
    public void cancelButtonClick(ActionEvent actionEvent) throws IOException {
        //Cancel button returns to the main screen and does not save changes
        addPartController.returnToMain(actionEvent);
    }

    /**The save button checks the input data and saves it to the products warehouse.*/
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

        //if an error is found, display the error dialog
        if (errorFound) {
            inputvalidation.errorMsg(errorMessages);
        } else {
            //If no error was found then create the new product and send it to the product warehouse
            //Create a new product
            Product modifiedProduct = new Product(prodIDMod, prodNameMod, prodPriceMod, prodInvMod, prodMinMod, prodMaxMod, null);
            //Include the associated parts with the product
            modifiedProduct.addAssociatedPart(assocTableMod);

            //Send the product to the warehouse and return to the main form
            ProdWarehouse.modifyProd(modifiedProduct);
            addPartController.returnToMain(actionEvent);
        }
    }


    /**Receives the data on the part selected in the main form and populates the form.*/
    public void receiveModProd(Product prodForMod) {
        //Sets the text boxes to display the data on the product sent from the main controller
        idProdTextMod.setText(String.valueOf(prodForMod.getId()));
        nameProdTextMod.setText(String.valueOf(prodForMod.getName()));
        invProdTextMod.setText(String.valueOf(prodForMod.getStock()));
        priceProdTextMod.setText(String.valueOf(prodForMod.getPrice()));
        maxProdTextMod.setText(String.valueOf(prodForMod.getMax()));
        minProdTextMod.setText(String.valueOf(prodForMod.getMin()));


        //Adds the associated parts of the received product to the observable list for associated parts
        assocTableMod = prodForMod.getAssocParts();

        //sets the bottom table to display the observalbe list of associated parts
        assocPartTableMod.setItems(assocTableMod);
    }
    /**Adds a selected part to the displayed list of associated parts.*/
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
    /**Removes a selected part from the displayed list of associated parts.*/
    public void removeAssocPartButtonMod(ActionEvent actionEvent) {
        //removes a part from the observable list that is selected
        assocTableMod.remove(assocPartTableMod.getSelectionModel().getSelectedItem());

    }

    /**This method searches for a match in the product name based on the string of test entered in the search box.*/
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
    /**This method searches the part ID based on the text entered into the search box.*/
    private Part getPartIDMatch(int searchID) {
        //Sets a list of all parts to search
        ObservableList<Part> allParts = PartWarehouse.getAllParts();

        //Searches all parts for an ID match
        for (Part q : allParts) {
            if (q.getId() == searchID) {
                return q;
            }
        }
        //return nothing if there isn't a match
        return null;
    }

    /**The search handler calls methods to search for the input string for either a partial string match or ID match.
     * Any matched data is returned as a list which is then displayed in the table.*/
    public void partSearchHandler(ActionEvent actionEvent) {
        //Get the test from the search box
        String searchText = partSearchMod.getText();

        //Set a list to display the search results for search by string
        ObservableList<Part> parts = searchPartNameMod(searchText);

        //If there are no results, try to search by ID
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
        //If no match was found, display an error, otherwise, display the matches
        if (parts.isEmpty()) {
            inputvalidation.errorMsg("No matching part found.");
        } else {
            partsTableMod.setItems(parts);
        }
    }

    /**Resets the search boxes to blank and repopulates the tables with all the data.*/
    public void clearSearchHandler(ActionEvent actionEvent) {
        partSearchMod.setText("");


        partSearchHandler(actionEvent);

    }
}
