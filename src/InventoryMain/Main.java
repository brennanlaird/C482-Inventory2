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


        //test data is added on program launch and then added to the parts warehouse

        InHouse partInHouse1 = new InHouse(inputvalidation.newPartID(), "Test Part 1", 5.00, 55, 10, 99, 42069); //adds a test part
        PartWarehouse.stockPartWarehouse(partInHouse1);
        Outsourced partOutsourced1 = new Outsourced(inputvalidation.newPartID(), "OS Test1", 3.14, 97, 2, 99, "DF Consult");
        PartWarehouse.stockPartWarehouse(partOutsourced1);


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

