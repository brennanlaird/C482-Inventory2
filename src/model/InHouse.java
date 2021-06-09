package model;
/**This class extends the Part class to create a subclass of Parts called Inhouse. This extends the part class by defining the machine ID that made the part.*/
public class InHouse extends Part {
    private int machineID;

    /**Constructor for the in-house sub class to match the part superclass.*/
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    //Getters for the Inhouse subclass
    /**Method to retrieve the machine ID of the in-house class.*/
    public int getmachineID() {
        return machineID;
    }

    //Setters for the InHouse subclass
    /**Method to set the machine ID of the in-house class.*/
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}