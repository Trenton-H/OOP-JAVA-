import java.io.*;
import java.util.*;
/**
 * Subclass to Inventory. Used to manage appliances
 */
public class Appliances extends Inventory
{
    private String brand;
    private String type;
    
    /**
     * Appliances's default constructor to initialize the values
     * of ID, name, quantity, price, brand, and type
     */
    Appliances()
    {
        super("", "", 0, 0.0);
        brand = "";
        type = "";
    }
    /**
     * Appliances's paramatarized constructor to initialize the values
     * of ID, name, quantity, price, brand, and type
     * @param id temp variable to initialize ID
     * @param n temp variable to initialize name
     * @param q temp variable to initialize quantity
     * @param p temp variable to initialize price
     * @param b temp variable to initialize brand
     * @param t temp variable to initialize type
     */
    Appliances(String id, String n, Integer q, Double p, String b, String t)
    {
        super(id, n, q, p); 
        brand = b;
        type = t;
    }
    /**
     * brand setter to pass values to the brand variable 
     * @param b temp variable to pass to brand
     */
    public void setBrand(String b)
    {
        brand = b;
    }
    /**
     * brand getter to return the brand variable 
     */
    public String getBrand()
    {
        return brand;
    }
    /**
     * type setter to pass values to the brand variable 
     * @param t temp variable to pass to brand
     */
    public void setType(String t)
    {
        type = t;
    }
    /**
     * brand getter to return the brand variable 
     */
    public String getType()
    {
        return type;
    }
}
