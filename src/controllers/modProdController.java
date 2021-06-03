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
        System.out.println("This should do stuff...");
        //Gets the input and assigns it to variables then changes the variables to the right type
        int prodIDMod = Integer.parseInt(idProdTextMod.getText());
        String prodNameMod = nameProdTextMod.getText();
        int prodInvMod = Integer.parseInt(invProdTextMod.getText());
        double prodPriceMod = Double.parseDouble(priceProdTextMod.getText());
        int prodMaxMod = Integer.parseInt(maxProdTextMod.getText());
        int prodMinMod = Integer.parseInt(minProdTextMod.getText());

        Product modifiedProduct = new Product(prodIDMod, prodNameMod, prodPriceMod, prodInvMod, prodMinMod, prodMaxMod, null);

        modifiedProduct.addAssociatedPart(assocTableMod);

        ProdWarehouse.modifyProd(modifiedProduct);
        addPartController.returnToMain(actionEvent);
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
}
