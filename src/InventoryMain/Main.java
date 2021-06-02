package InventoryMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {


        //Create the part Warehouse to store and display part objects
        PartWarehouse partWarehouse = new PartWarehouse();
        ProdWarehouse productWarehouse = new ProdWarehouse();

        //test data is added on program launch and then added to the parts and product warehouses

        InHouse partInHouse1 = new InHouse(inputvalidation.newPartID(), "Test Part 1", 5.00, 55, 10, 99, 42069); //adds a test part
        PartWarehouse.stockPartWarehouse(partInHouse1);
        Outsourced partOutsourced1 = new Outsourced(inputvalidation.newPartID(), "OS Test1", 3.14, 97, 2, 99, "DF Consult");
        PartWarehouse.stockPartWarehouse(partOutsourced1);

        InHouse partInHouse2 = new InHouse(inputvalidation.newPartID(), "Test Part 2", 6.00, 11, 10, 99, 42); //adds a test part
        PartWarehouse.stockPartWarehouse(partInHouse2);
        InHouse partInHouse3 = new InHouse(inputvalidation.newPartID(), "Test Part 3", 7.00, 66, 10, 99, 77); //adds a test part
        PartWarehouse.stockPartWarehouse(partInHouse3);




        Product Tester = new Product(inputvalidation.newProdID(), "Test Part", 3.50, 55, 1, 99, null);
        Tester.addAssociatedPart(PartWarehouse.getAllParts());

        ProdWarehouse.stockProdWarehouse(Tester);




        //Loads the main stage and sets of the main for for the first scene
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainForm_v1.fxml"));
        primaryStage.setTitle("DFC Inventory Control");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);


    }
}

