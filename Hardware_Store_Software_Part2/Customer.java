
/**
 * Subclass to User. Used to manage the number and address
 * to customers.
 */
public class Customer extends User
{
    private String number;
    private String address;
    
    /**
     * Customer's default constructor to initialize the values
     * of ID, firstName, lastName, number and address
     */
    Customer()
    {
        super(-1, "" ,"");
        number = "";
        address = "";
    }
    /**
     * Customer's paramatarized constructor to initialize the values
     * of ID, firstName, lastName, number and address
     * @param i temp variable to initialize ID
     * @param f temp variable to initialize firstName
     * @param l temp variable to initialize lastName
     * @param n temp variable to initialize number
     * @param a temp variable to initialize address
     */
    Customer(Integer i, String f, String l, String n, String a)
    {
        super(i, f, l);
        number = n;
        address = a;
    }
    /**
     * number setter to pass values to the number variable 
     * @param n temp variable to pass to number
     */
    public void setNumber(String n)
    {
        number = n;
    }
    /**
     * number getter to return the number variable 
     */
    public String getNumber()
    {
        return number;
    }
    /**
     * address setter to pass values to the number variable 
     * @param a temp variable to pass to number
     */
    public void setAddress(String a)
    {
        address = a;
    }
    /**
     * number getter to return the number variable 
     */
    public String getAddress()
    {
        return address;
    }
}
