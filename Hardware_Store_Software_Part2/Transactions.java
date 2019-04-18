import java.sql.*;

/**
 * Class to manage the 5 needed variables for storing the
 * transaction info for a sale
 */
public class Transactions
{
    private String idItem;
    private long date;
    private Integer quantity;
    private Integer idCustomer;
    private Integer idEmployee;
    
    /**
     * Transactions default constructor to initialize the values
     * of idItem, quantity, idCustomer, and idEmployee 
     */
    Transactions()
    {
        idItem = "";
        quantity = -1;
        idCustomer = -1;
        idEmployee = -1;
    }
    /**
     * Transactions paramatarized constructor to initialize the values
     * of idItem, quantity, idCustomer, and idEmployee 
     * @param iT temp variable to initialize idItem
     * @param d temp variable to initialize date
     * @param q temp variable to initialize quantity
     * @param iC temp variable to initialize idCustomer
     * @param iE temp variable to initialize idEmployee
     */
    Transactions(String iT, long d, Integer q, Integer iC, Integer iE)
    {
        idItem = iT;
        date = d;
        quantity = q;
        idCustomer = iC;
        idEmployee = iE;
    }
    /**
     * idItem setter to pass values to the idItem variable 
     * @param iT temp variable to pass to idItem
     */
    public void setIDItem(String iT)
    {
        idItem = iT;
    }
    /**
     * idItem getter to return the idItem variable 
     */
    public String getIDItem()
    {
        return idItem;
    }
    /**
     * date setter to pass values to the date variable 
     * @param d temp variable to pass to date
     */
    public void setDate(long d)
    {
        date = d;
    }
    /**
     * date getter to return the date variable 
     */
    public long getDate()
    {
        return date;
    }
    /**
     * quantity setter to pass values to the quantity variable 
     * @param q temp variable to pass to quantity
     */
    public void setQuantity(Integer q)
    {
        quantity = q;
    }
    /**
     * quantity getter to return the quantity variable 
     */
    public Integer getQuantity()
    {
        return quantity;
    }
    /**
     * idCustomer setter to pass values to the idCustomer variable 
     * @param iC temp variable to pass to idCustomer
     */
    public void setIDCustomer(Integer iC)
    {
        idCustomer = iC;
    }
    /**
     * idCustomer getter to return the idCustomer variable 
     */
    public Integer getIDCustomer()
    {
        return idCustomer;
    }
    /**
     * idEmployee setter to pass values to the idEmployee variable 
     * @param iE temp variable to pass to idEmployee
     */
    public void setIDEmployee(Integer iE)
    {
        idEmployee = iE;
    }
    /**
     * idEmployee getter to return the idEmployee variable 
     */
    public Integer getIDEmployee()
    {
        return idEmployee;
    }
}
