package model;

//Class to store and modify products objects

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**This class implements an object to store the products which are created. The class provides methods to add, modify and retrieve part objects*/
public class ProdWarehouse {
    //The observable list of all parts used to display in the tableview and store all the parts added
    private static ObservableList<Product> allProds = FXCollections.observableArrayList();

    //No constructor as the default constructor works fine.

    /**Static method to add parts to the warehouse by adding them to the observable list object.*/
    public static void stockProdWarehouse(Product newProd) {
        allProds.add(newProd);
    }

    /**Takes a product from the modify product controller and inserts the modified data into the products list.*/
    public static void modifyProd(Product alteredProd) {

        //The for loop iterates through each product in the all products list
        for (int i = 0; i < allProds.size(); i++) {
            //If the ID of the altered product that was passed in from the modify product controller matches the current product selected by the for loop

            if (alteredProd.getId() == allProds.get(i).getId()) {

                //The data is altered in the list if the part ID numbers matched
                allProds.get(i).setId(alteredProd.getId());
                allProds.get(i).setName(alteredProd.getName());
                allProds.get(i).setStock(alteredProd.getStock());
                allProds.get(i).setPrice(alteredProd.getPrice());
                allProds.get(i).setMax(alteredProd.getMax());
                allProds.get(i).setMin(alteredProd.getMin());

                allProds.get(i).addAssociatedPart(alteredProd.getAssocParts());
            }
        }
    }

    /**This returns all the products in the observable products list for display in the main form.*/
    public static ObservableList<Product> getAllProds() {
        return allProds;
    }


}
