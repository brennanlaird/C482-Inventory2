package InventoryMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Part;
import model.PartWarehouse;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        InHouse partInHouse1 = new InHouse(1,"Test Part 1",5.00,55,10,99); //adds a test part
        PartWarehouse partWarehouse = new PartWarehouse(); //creates an instance of the parts warehouse

        //ArrayList<Part> partList = new ArrayList<Part>();

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

//adding a comment to test for where the changes are pushed to on github