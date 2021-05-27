package model;

public class InHouse extends Part {
    private int machineID;

    //Constructor to match the superclass
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }


    //Getters for the Inhouse subclass
    public int getmachineID() {
        return machineID;
    }


    //Setters for the InHouse subclass
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }

}