import java.io.*;
import java.util.*;

/**
 * Subclass to Inventory. Used to manage the category part
 * of small hardware items
 */
public class SmallHardwareItems extends Inventory
{
    private String category;
    /**
     * SmallHardwareItems's default constructor to initialize the values
     * of ID, name, quantity, price and category
     */
    SmallHardwareItems()
    {
        super("", "", 0, 0.0);
        category = "";
    }
    /**
     * SmallHardwareItems's paramatarized constructor to initialize the values
     * of ID, name, quantity, price and category
     * @param id temp variable to initialize ID
     * @param n temp variable to initialize name
     * @param q temp variable to initialize quantity
     * @param p temp variable to initialize price
     * @param c temp variable to initialize category
     */
    SmallHardwareItems(String id, String n, Integer q, double p, String c)
    {
        super(id, n, q, p);
        category = c;        
    }
    
     /**
     * returns category associated with the item
     * @return associated category of an item
     */
    public String getCategory()
    {
        return category;
    }
     /**
     * defines category as a temp variable c that is passed to it
     * @param c is the category associated with the item
     */
    public void setCategory(String c)
    {
        category = c;
    }
}
