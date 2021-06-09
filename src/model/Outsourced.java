package model;
/**This class extends the Part class to create a subclass of Parts called Outsourced. This extends the part class by defining the company tht the part was purchased from.*/
public class Outsourced extends Part {
    private String companyName;

    /**Constructor for the outsourced sub class to match the part superclass.*/
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**Method to retrieve the company name of the outsourced class.*/
    public String getcompanyName() {
        return companyName;
    }

    /**Method to set the company name of the outsourced class.*/
    public void setcompanyName(String companyName) {
        this.companyName = companyName;
    }
}