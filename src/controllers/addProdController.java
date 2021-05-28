package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Part;
import model.PartWarehouse;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(PartWarehouse.getAllParts());

        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        //Create an Observalbel list to populate the bottom table
        ObservableList<Part> assocTable = FXCollections.observableArrayList();




        assocPartTable.setItems(assocTable);

        assocPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    public void cancelButtonClick(ActionEvent actionEvent) throws IOException {
        addPartController.returnToMain(actionEvent);
    }

    public void saveButtonClick(ActionEvent actionEvent) {
    }

    public void clearFormButtonClick(ActionEvent actionEvent) {
    }

    public void addToAssocPartButton(ActionEvent actionEvent) {

    }

    public void removeAssocPartButton(ActionEvent actionEvent) {
        assocPartTable.getItems().removeAll(assocPartTable.getSelectionModel().getSelectedItems());
    }
}
