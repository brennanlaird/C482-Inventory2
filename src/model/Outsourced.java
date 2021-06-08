package model;

public class Outsourced extends Part {
    private String companyName;

    //Constructor to match the superclass
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    //Getters for the outsourced subclass
    public String getcompanyName() {
        return companyName;
    }

    //Setters for the outsourced subclass
    public void setcompanyName(String companyName) {
        this.companyName = companyName;
    }
}