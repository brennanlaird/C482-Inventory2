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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.*;

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

    public TextField partSearchMain;
    public Button searchPartsButton;
    public Button searchProdButton;
    public Button clearSearchButton;
    public TextField prodSearchMain;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Sets the parts table from JavaFX to display the items that are contained in the all parts observable list found in the PartWarehouse class
        partTable.setItems(PartWarehouse.getAllParts());

        //Sets the columns to get the data to display for the parts table
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Sets the columns to display the data for the products table.
        productTable.setItems(ProdWarehouse.getAllProds());
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    //Runs when the add part button is clicked.
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
        //The try-catch block is used to avoid a null pointed error if the button is pushed with nothing selected.
        try {
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
        } catch (Exception e) {
            //If nothing was selected when this button is pushed, an error is displayed.
            inputvalidation.errorMsg("Please select a part to modify");
        }
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

    public void deleteProdButtonClick(ActionEvent actionEvent) {
        //The try-catch block will display an error if no product is selected.
        try {
            //Create a temp product from the selected item in the list. Used to determine if the product selected has associated parts.
            Product deleteCheck = (Product) productTable.getSelectionModel().getSelectedItem();
            //If the product selected has associated parts it cannot be deleted
            if (!deleteCheck.getAssocParts().isEmpty()) {
                //There are associated parts, can not delete
                inputvalidation.errorMsg("This product has associated parts and cannot be deleted.");
            } else {
                //Sets a dialog to ensure the user wants to delete
                var deleteConfirm = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES,  ButtonType.CANCEL);
                deleteConfirm.setTitle("Confirm Delete");
                deleteConfirm.setContentText("Are you sure you want to delete the selected items?");
                deleteConfirm.showAndWait();

                //If the user presses yes, the part is deleted from the part table
                if (deleteConfirm.getResult() == ButtonType.YES) {
                    productTable.getItems().removeAll(productTable.getSelectionModel().getSelectedItems());
                }
            }
        } catch (Exception e) {
            //If nothing was selected from the list to delete, an error message is displayed.
            inputvalidation.errorMsg("Nothing selected!");
        }
    }

    public void addProdButtonClick(ActionEvent actionEvent) throws IOException {
        //Sets up and displays the Add Product form
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddProductForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }

    public void modProdButtonClick(ActionEvent actionEvent) throws IOException {
        //The try-catch block will be used to show an error message if something is not selected when the button is pressed
        try {
            //Initializes the Modify Product controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModProductForm.fxml"));
            Parent root = loader.load();
            modProdController modController = loader.getController();

            //Sends the selected product to the modify product controller
            modController.receiveModProd((Product) productTable.getSelectionModel().getSelectedItem());

            //Shows the modify product controller after passing the data from the selected product
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Modify Product");
            stage.setScene(scene);
            stage.show();
        } catch (Exception exception) {
            //Displays an error on a null pointer exception when no product was selected to modify
            inputvalidation.errorMsg("Please select a product to modify.");
        }
    }

    public void exitButtonPress(ActionEvent actionEvent) {
        //Closes the stage using the .close method to end the program
        ((Stage) (((Node) actionEvent.getSource()).getScene().getWindow())).close();
    }

    //Method used to search part names for a partial match. Retuns the list of found parts as an observable list.
    private ObservableList<Part> searchPartName(String partialName) {
        //Sets up a list to store the parts found with a partial string match search
        ObservableList<Part> foundParts = FXCollections.observableArrayList();

        //Sets up a list to store all the parts to search through
        ObservableList<Part> allParts = PartWarehouse.getAllParts();

        //For loop iterates through all the parts in the all parts list
        for (Part p : allParts) {
            //If search string is found to match any part of the name, it is added to the list of found parts
            if (p.getName().contains(partialName)) {
                foundParts.add(p);
            }
        }
        return foundParts;
    }

    //Method used to search for parts by ID. Returns the ID of the found part if it is found
    private Part getPartIDMatch(int searchID) {
        //Set up an observable list of all parts
        ObservableList<Part> allParts = PartWarehouse.getAllParts();

        //Iterates through all the parts in the full list
        for (Part q : allParts) {
            //If the ID of the current part matches the part ID searched for, send that part back to the calling method.
            if (q.getId() == searchID) {
                return q;
            }
        }
        //If nothing was found, return null
        return null;
    }

    //Method to execute when the part search button is pressed
    public void partSearchHandler(ActionEvent actionEvent) {
        //Gets the text string from the parts search box
        String searchText = partSearchMain.getText();

        //Creates an observable list from the returned parts found via partial string match
        ObservableList<Part> parts = searchPartName(searchText);

        //If the list is empty after searching by string
        if (parts.size() == 0) {

            try {
                //Search by part ID if nothing was matched by string
                int partID = Integer.parseInt(searchText);
                Part q = getPartIDMatch(partID);
                //If the part ID search method returns anything, add it to the list to display
                if (q != null) {
                    parts.add(q);
                }
            } catch (NumberFormatException e) {
                //Ignore
            }
        }

        if (parts.isEmpty()) {
            //if the list is empty after using both search methods, display an error message
            inputvalidation.errorMsg("No matching part found.");
        } else {
            //Display the list of parts that were found.
            partTable.setItems(parts);
        }
    }

    //resets the search boxes to blank and repopulates the tables with all the data
    public void clearSearchHandler(ActionEvent actionEvent) {
        //Sets the test of both search boxes to the empty string
        partSearchMain.setText("");
        prodSearchMain.setText("");

        //Runs the search handlers to reset the lists to all the items.
        partSearchHandler(actionEvent);
        prodSearchHandler(actionEvent);
    }

    //Method used to search product names for a partial match. Returns the list of found products as an observable list.
    private ObservableList<Product> searchProdName(String partialName) {
        //Sets up a list to store the parts found with a partial string match search
        ObservableList<Product> foundProds = FXCollections.observableArrayList();

        //Sets up a list to store all the parts to search through
        ObservableList<Product> allProds = ProdWarehouse.getAllProds();

        //Searches through all products
        for (Product p : allProds) {
            if (p.getName().contains(partialName)) {
                foundProds.add(p);
            }
        }
        return foundProds;
    }

    //Method used to search for products by ID. Returns the ID of the found product if it is found
    private Product getProdIDMatch(int searchID) {
        //Set up an observable list of all products
        ObservableList<Product> allProds = ProdWarehouse.getAllProds();

        //Iterates through all products
        for (Product q : allProds) {
            //If the current products has the same ID as the search, return it
            if (q.getId() == searchID) {
                return q;
            }
        }
        //If nothing is found, return null
        return null;
    }

    //Method to handle searching for products when the button is pressed
    public void prodSearchHandler(ActionEvent actionEvent) {
        //Gets the text string from the product search box
        String searchText = prodSearchMain.getText();

        //Set up a list to display all the found products by the string search method
        ObservableList<Product> prods = searchProdName(searchText);

        //If the string search method did not work, then the search is done by ID
        if (prods.size() == 0) {

            try {
                //Search by the integer entered in to the search box.
                int prodID = Integer.parseInt(searchText);
                Product q = getProdIDMatch(prodID);
                if (q != null) {
                    prods.add(q);
                }
            } catch (NumberFormatException e) {
                //Ignore
            }

        }

        if (prods.isEmpty()) {
            //If nothing was found via search, display an error
            inputvalidation.errorMsg("No matching product found.");

        } else {
            //Display the results of the search if something was found.
            productTable.setItems(prods);
        }
    }

}
