import java.io.*;
import java.util.*;

/**
 * Class to manage and alter all the informations for each individual item
 * in the store inventory.
 */
class Inventory implements Serializable
{
    private String idNumber;
    private String name;
    private Integer quantity;
    private Double price;
    private String category;
    private String type;
    private String brand;
    
    /**
     * Constructs an Inventory Object that initializes all variables
     * with default values
     */
    Inventory()
    {
        idNumber = "";
        name = "";
        category = "";
        quantity = 0;
        price = 0.0;
    }
    /**
     * Constructs an Inventory object that initializes all the values
     * associated with it to the arguments passed to it.
     * @param id is the ID Number associated with the item
     * @param n is the name associated with the item
     * @param c is the category associated with the item
     * @param q is the quantity associated with the item
     * @param p is the price associated with the item
     */
    Inventory(String id, String n, Integer q, Double p)
    {
        idNumber = id;
        name = n;
        quantity = q;
        price = p;
    }
    /**
     * defines idNumber of an item as temp variable id that is passed to it
     * @param id is the ID Number associated with the item
     */
    public void setIDNumber(String id)
    {
        idNumber = id;
    }
    /**
     * returns the ID Number associated to a given item
     * @returns ID number
     */
    public String getIDNumber()
    {
        return idNumber;
    }
    /**
     * defines item's name as a temp variable n that is passed to it
     * @param n is the name associated with the item
     */
    public void setName(String n)
    {
        name = n;
    }
    /**
     * returns that name of a give item
     * @returns item's name
     */
    public String getName()
    {
        return name;
    }
    /**
     * defines quantity as a temp variable q that is passed to it
     * @param q is the quantity associated with the item
     */
    public void setQuantity(Integer q)
    {
        quantity = q;
    }
    /**
     * returns quantity of an item on hand
     * @returns item quantity
     */
    public Integer getQuantity()
    {
        return quantity;
    }
    /**
     * defines price as a temp variable p that is passed to it
     * @param p is the price associated with the item
     */
    public void setPrice(Double p)
    {
        price = p;
    }
    /**
     * Returns the price of an item
     * @return price of current item on inventory manifest
     */
    public Double getPrice()
    {
        return price;
    }
    public String getCategory(){return category;}
    public void setCategory(){}
    public String getBrand(){return brand;}
    public void setBrand(){}
    public String getType(){return type;}
    public void setType(){}
}