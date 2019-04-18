import java.io.*;
import java.util.*;

/**
 * SuperClass to Employee and Customer. Manages all the
 * general information like ID, first name and last name
 */
public class User implements Serializable
{
    private int ID;
    private String SSN;
    private String firstName, lastName, address, number;
    private float salary;
    
    /**
     * User default constructor to initialize the values
     * of ID, firstName and lastName 
     */
    User()
    {
        ID = -1;
        firstName = "";
        lastName = "";
    }
    /**
     * User paramatarized constructor to initialize the values
     * of ID, firstName and lastName 
     * @param i temp variable to initialize ID
     * @param f temp variable to initialize firstName
     * @param l temp variable to initialize lastName
     */
    User(Integer i, String f, String l)
    {
        ID = i;
        firstName = f;
        lastName = l;
    }
    /**
     * ID setter to pass in values to the variable
     * @param i temp variable to initialize ID
     */
    public void setID(Integer i)
    {
        ID = i;
    }
    /**
     * ID getter to return ID value for an instance
     */
    public Integer getID()
    {
        return ID;
    }
    /**
     * firstName setter to pass in values to the variable
     * @param f temp variable to initialize firstName
     */
    public void setFirstName(String f)
    {
        firstName = f;
    }
    /**
     * firstName getter to return firstName value for an instance
     */
    public String getFirstName()
    {
        return firstName;
    }
    /**
     * lastName setter to pass in values to the variable
     * @param l temp variable to initialize lastName
     */
    public void setLastName(String l)
    {
        lastName = l;
    }
    /**
     * lastName getter to return lastName value for an instance
     */
    public String getLastName()
    {
        return lastName;
    }
    public String getSSN(){return SSN;}
    public void setSSN(String s){ SSN = s;}
    public float getSalary(){return salary;}
    public void setSalary(float s){ salary = s;}
    public String getNumber(){return number;}
    public void setNumber(String n){ number = n;}
    public String getAddress(){return address;}
    public void setAddress(String a){address = a;}
    
}
