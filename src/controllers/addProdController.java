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
/**This class implements the Add Product form and the associated error checking to ensure the data is entered correctly.
 * There are also tables included that allow users to associate parts with the entered product
 * FUTURE ENHANCEMENT: The product data entry could pull prices from the associated parts and flag products where the price is less than the sum of the parts. */
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

    /**This method is called when the form is initilized to set up the tables to display the desired data.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Sets the top table to display all the parts
        partsTable.setItems(PartWarehouse.getAllParts());

        //Sets the columns for the top table
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Sets the associated parts table (the bottom table) to display the private observable list of associated parts
        assocPartTable.setItems(assocTable);

        //Sets up the columns for the bottom table.
        assocPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**Cancel button returns to the main screen and does not save changes*/
    public void cancelButtonClick(ActionEvent actionEvent) throws IOException {
        //Calls the static method to return to the main form
        addPartController.returnToMain(actionEvent);
    }

    /**The save button checks the input data and saves it to the products warehouse.*/
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
            //if an error is found, display the error dialog
            inputvalidation.errorMsg(errorMessages);
        } else {
            //If no error was found then create the new product and send it to the product warehouse
            //Create a new product
            Product addingProduct = new Product(inputvalidation.newProdID(), prodName, prodPrice, prodInv, prodMin, prodMax, null);
            //Include the associated parts with the product
            addingProduct.addAssociatedPart(assocTable);

            //Send the product to the warehouse and return to the main form
            ProdWarehouse.stockProdWarehouse(addingProduct);
            addPartController.returnToMain(actionEvent);
        }
    }

    /**Clears the fields and associated parts table and stays with the current form.*/
    public void clearFormButtonClick(ActionEvent actionEvent) {
        //Sets the text boxes to blank and clears the associated parts table.
        idProdText.setText("");
        nameProdText.setText("");
        invProdText.setText("");
        priceProdText.setText("");
        maxProdText.setText("");
        minProdText.setText("");
        assocPartTable.setItems(null);
    }
    /**Adds a selected part to the displayed list of associated parts.*/
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
    /**Removes a selected part from the displayed list of associated parts.*/
    public void removeAssocPartButton(ActionEvent actionEvent) {
        //Removes a selected part from the associated parts table
        assocPartTable.getItems().removeAll(assocPartTable.getSelectionModel().getSelectedItems());
    }
    /**This method searches for a match in the product name based on the string of test entered in the search box.*/
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
        String searchText = partSearchAdd.getText();

        //Set a list to display the search results for search by string
        ObservableList<Part> parts = searchPartNameAdd(searchText);

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
            partsTable.setItems(parts);
        }
    }

    /**Resets the search boxes to blank and repopulates the tables with all the data.*/
    public void clearSearchHandler(ActionEvent actionEvent) {
        partSearchAdd.setText("");


        partSearchHandler(actionEvent);

    }


}
