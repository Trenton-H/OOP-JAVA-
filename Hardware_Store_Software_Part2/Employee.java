/**
 * Holds and allows modification of Employee data
 * subclass of User 
 */
public class Employee extends User
{
    private String SSN;
    private float salary;
    
    /**
     * default constructor for Employee Class
     */
    Employee()
    {
        super(-1, "", "");
        SSN = "";
        salary = 0;
    }
    /**
     * Paramatarized constructor for Employee Class
     * @param id temp variable to set Employee ID in User
     * @param f temp variable to set first name in User
     * @param l temp variable to set last name in User
     * @param life temp variable to set SSN
     * @param s temp variable to set Salary
     */
    Employee(Integer id, String f, String l, String life, float s)
    {
        super(id, f, l);
        SSN = life;
        salary = s;
    }
    /**
     * SSN setter
     * @param life temp variable to set SSN
     */
    public void setSSN(String life)
    {
        SSN = life;
    }
    /**
     * SSN getter to return SSN of instance
     */
    public String getSSN()
    {
        return SSN;
    }
    /**
     * Salary setter
     * @param s temp variable to set Salary
     */
    public void setSalary(float s)
    {
        salary = s;
    }
    /**
     * Salary getter to return salary of instance
     */
    public float getSalary()
    {
        return salary;
    }
}
