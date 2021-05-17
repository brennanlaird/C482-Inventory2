package model;

public class Outsourced extends Part {
    private int machineID;

    //Constructor to match the superclass
    public Outsourced(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
    }


    //Getters
    public int getmachineID() {
        return machineID;
    }


    //Setters
    public void setMachineID() {
        this.machineID = machineID;
    }

}