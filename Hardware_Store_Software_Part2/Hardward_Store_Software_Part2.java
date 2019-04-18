/**
 *  Hardware Store Software for managing their inventory.
 *  @author Trenton Hohle
 *  @version 2.1
 *  @since 1.1
 */



import java.io.*;
import java.util.*;
import java.lang.*;

/**
 * Controls the files modification, menu, all the functions the modify the 
 * items on the inventory and inventory itself.
 */
public class Hardward_Store_Software_Part2
{
    /**
     * Reads in the inventory file, directs the menu, and writes back to
     * the inventory file
     * @param args 
     * @throws Exception in case the file doesn't open
     */
    public static void main(String [] args) throws Exception 
    {
        Integer answer = 0;
        ArrayList <Inventory> list = new ArrayList<Inventory>();
        ArrayList <User> users = new ArrayList<User>();
        ArrayList <Transactions> transactions = new ArrayList<Transactions>();
        Scanner in = new Scanner(System.in);
        File dataFile = new File("database.ser");
        File userFile = new File("userDatabase.ser");
        File transactionFile = new File("transactionDatabase.ser");
        
        try
        {            
            
            if(!dataFile.isFile())
            {
                System.out.println("database.ser doesn't exist, creating one now...");
                
                new File ("database.ser").createNewFile();
                
            }
            else
            {
                FileInputStream inFile = new FileInputStream(dataFile);
                ObjectInputStream fin = new ObjectInputStream(inFile);
                
                list = (ArrayList<Inventory>) fin.readObject();
                
                fin.close();
                inFile.close();
            }
            if(!userFile.isFile())
            {
                System.out.println("userDatabase.ser doesn't exist, creating one now...");
                
                new File ("userDatabase.ser").createNewFile();
                
            }
            else
            {
                FileInputStream inFile = new FileInputStream(userFile);
                ObjectInputStream fin = new ObjectInputStream(inFile);
                
                users = (ArrayList<User>) fin.readObject();
                
                fin.close();
                inFile.close();
            }
            if(!transactionFile.isFile())
            {
                System.out.println("transactionDatabase.ser doesn't exist, creating one now...");
                
                new File ("transactionDatabase.ser").createNewFile();
                
            }
            else
            {
                FileInputStream inFile = new FileInputStream(userFile);
                ObjectInputStream fin = new ObjectInputStream(inFile);
                
                transactions = (ArrayList<Transactions>) fin.readObject();
                
                fin.close();
                inFile.close();
            }
        }catch(FileNotFoundException ex)
        {
            System.out.println("Unable to open file, or file wasn't found.");
        }
        
        do
        {
            System.out.println("1. Show all existing items records in the database (sorted by ID number)");
            System.out.println("2. Add a new item (or quantity) to the database.");
            System.out.println("3. Delete an item from a database.");
            System.out.println("4. Search for an item given its name.");
            System.out.println("5. Show a list of users in the database.");
            System.out.println("6. add new user to the database.");
            System.out.println("7. Update user info (given their id)");
            System.out.println("8. Complete a sale transaction");
            System.out.println("9. Show complete sale transactions.");
            System.out.println("10. Exit program.");
            System.out.print("Your answer: ");
            answer = in.nextInt();
            switch (answer)
            {
                case(1): displayInventory(list); break;
                case(2): addToInventory(list); break;
                case(3): deleteItem(list); break;
                case(4): itemSearch(list); break;
                case(5): displayUsers(users); break;
                case(6): addUser(users); break;
                case (7): updateUser(users); break;
                case(8): completeTransaction(transactions, users, list); break;
                case(9): displayTransactions(transactions); break;
                case(10): break;
                default: System.out.println("\nThat is not a valid answer.\n");
                         break;
            }
        }while(answer != 10);
        
        FileOutputStream fos = new FileOutputStream(dataFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        
        FileOutputStream fout = new FileOutputStream(userFile);
        ObjectOutputStream foos = new ObjectOutputStream(fout);
        foos.writeObject(users);
        
        FileOutputStream transOut = new FileOutputStream(transactionFile);
        ObjectOutputStream transOOS = new ObjectOutputStream(transOut);
        transOOS.writeObject(transactions);
        
        oos.close();
        fos.close();
        foos.close();
        fout.close();
        transOut.close();
        transOOS.close();
    }
    /**
     * Outputs the item inventory while formating it for the user to read easier
     */
    static void  displayInventory(ArrayList<Inventory> list)
    {
        Integer formatSize = 86;
        for(int i = 0; i < formatSize; i++)
            System.out.print("-");
        System.out.println();
        System.out.println("|  ID # | NAME               | Quantity  | PRICE  | Other Info                       |");
        
        for(int i = 0; i <formatSize; i++)
            System.out.print("-");
        System.out.println();
        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i).getClass().equals(SmallHardwareItems.class))
            {
                System.out.println("| " + String.format("%-5s", list.get(i).getIDNumber())
                                + " | " + String.format("%-18s", list.get(i).getName())
                                + " | " + String.format("%-9s", list.get(i).getQuantity()) 
                                + " | " + String.format("%6.2f", list.get(i).getPrice())
                                + " | " + String.format("%-32s",list.get(i).getCategory()) 
                                + " | ");
            }
            else
            {
                System.out.println("| " + String.format("%-5s", list.get(i).getIDNumber())
                                + " | " + String.format("%-18s", list.get(i).getName())
                                + " | " + String.format("%-9s", list.get(i).getQuantity()) 
                                + " | " + String.format("%6.2f", list.get(i).getPrice())
                                + " | " + String.format("%-32s","brand: " + list.get(i).getBrand() +
                                " Type: " + list.get(i).getType()) + " | ");
            }
        }
    
        for(int i = 0; i < formatSize; i++)
            System.out.print("-");
        System.out.println();
    }
    /**
     * Checks the input ID # against the inventory list, if its there, lets 
     * the user choose the complete new quantity, if its not there, gathers
     * all the necessary information to add it to the list
     * @param list the arraylist to the item inventory list.
     */
    static void addToInventory(ArrayList<Inventory> list)
    {
        Scanner in = new Scanner(System.in);
        String IDNumber = "", category = "";
        Integer categoryTemp = -1;
        Integer quantity, foundIndex = -1, changedQuantity = -1;
        double price;
        Boolean categoryValidation = false, idFound = false;
 
        do
        {
            System.out.print("What is the ID # of the product: ");
            IDNumber = in.nextLine();
            
            if(IDNumber.length() != 5)
                System.out.println("Invalid ID size... Must be 5 characters!");
        }while(IDNumber.length() != 5);
        for(int i = 0; i < list.size(); i++)
        {
            String tempIDHolder = list.get(i).getIDNumber();
            if(IDNumber.toUpperCase().equals(tempIDHolder))
            {
                do
                {
                    System.out.print("What would you like to change the quantity to: ");
                    changedQuantity = in.nextInt();
                    in.nextLine();
                    if(changedQuantity >= 0)
                        list.get(i).setQuantity(changedQuantity);
                    else
                        System.out.println("\nInvalid Number");
                }while(changedQuantity < 0);
                idFound = true;
            }
        }
        if(!idFound)
        {
            System.out.println("\nThe ID you looked up wasn't found so we will add it to the inventory.\n");
            
            Integer answer = 0;
            do
            {
                System.out.println("What type of Item is it?");
                System.out.println("1. Appliance");
                System.out.println("2. Small Hardware Item");
                answer = in.nextInt();
                in.nextLine();
                if(answer != 1 && answer != 2)
                    System.out.println("\nInvalid input!\n");
            }while(answer != 1 && answer != 2);
            
            System.out.print("What is the name of the item: ");
            String name = in.nextLine();

            do
            {
                System.out.print("How many of " + name + " do you have: ");
                quantity = in.nextInt();
                if(quantity < 0.0)
                {
                    System.out.println("\nInvalid quantity. Try again.");
                }
            }while(quantity < 0);
            do
            {
                System.out.print("How much does " + name + " cost: ");
                price = in.nextDouble();
                in.nextLine();
                if(price < 0)
                    System.out.println("That is not a valid price");
            }while(price < 0);
            if(answer == 1)
            {
                System.out.print("What is the brand of the appliance: ");
                String brand = in.nextLine();
                String type = "";
                do
                {
                    System.out.println("What type of Appliances is it?");
                    System.out.println("1. Refrigerator");
                    System.out.println("2. Washers&Dryers");
                    System.out.println("3. Ranges&Ovens");
                    System.out.println("4. Small Appliances");
                    System.out.print("Answer: ");
                    answer = in.nextInt();
                    in.nextLine();
                
                    switch(answer)
                    {
                        case(1): type = "Refrigerator"; break;
                        case(2): type = "Washers&Dryers"; break;
                        case(3): type = "Ranges&Ovens"; break;
                        case(4): type = "Small Appliances"; break;
                        default: System.out.println("That isn't a valid choice");
                    }
                }while(answer < 1 || answer > 4);

                
                list.add(new Appliances(IDNumber.toUpperCase(), name, 
                                        quantity, price, brand, type));
            }
            if(answer == 2)
            {
                while(categoryValidation == false)
                {
                    System.out.println("Which category does it belong too?");
                    System.out.println("1. Door&Window");
                    System.out.println("2. Cabinet&Furniture");
                    System.out.println("3. Fasteners");
                    System.out.println("4. Structural");
                    System.out.println("5. Other");
                    System.out.print("Type: ");
                    categoryTemp = in.nextInt();
                    switch(categoryTemp)
                    {
                        case 1: category = "Door&Window";
                            categoryValidation = true;
                            break;
                        case 2: category = "Cabinet&Furniture";
                            categoryValidation = true;
                            break;
                        case 3: category = "Fasteners";
                            categoryValidation = true;
                            break;
                        case 4: category = "Structural";
                            categoryValidation = true;
                            break;
                        case 5: category = "Other";
                            categoryValidation = true;
                            break;
                        default: System.out.println("Error, Invalid input"); 
                            break;
                    }
                }
                list.add(new SmallHardwareItems(IDNumber.toUpperCase(), name, quantity, price, category));
            }
        }
    }
    /**
     * Asks the user for the ID number for an item to completely delete 
     * from the inventory list. Validates the input
     * 
     * @param list the arraylist to the item inventory.
     */
    static void deleteItem(ArrayList<Inventory> list)
    {
        Scanner in = new Scanner(System.in);
        String id = "";
        boolean found = false;
        int index = -1;
        
        do
        {
            System.out.print("Enter ID of item to delete: ");
            id = in.nextLine();
            if(id.length() != 5)
                System.out.println("ID length must be 5 characters!");
        }while(id.length() != 5);
        for(int i = 0; i < list.size() && !found; i++)
        {
            if(id.toUpperCase().equals(list.get(i).getIDNumber()))
            {
                found = true;
                index = i;
                list.remove(i);
                System.out.println("\nDeleted\n");
            }
        }
        if(!found)
            System.out.println(id + " was not found");
    }
    /**
     * Checks through the inventory for any items that match the provided
     * item or partial name to the item, and returns the current on-hands,
     * prices, and ID number.
     * @param list the arraylist to the inventory of all the items
     */
    static void itemSearch(ArrayList<Inventory> list)
    {
        Scanner in = new Scanner(System.in);
        String name = "";
        
        System.out.print("What would you like to look for: ");
        String find = in.nextLine();
        System.out.println("Result(s):");
        
        for(int i = 0; i < list.size(); i++)
        {
            name = list.get(i).getName();
            if(name.toUpperCase().contains(find.toUpperCase()))
            {
                System.out.println(" -" + list.get(i).getName());
                System.out.println("     Current on-hand: " + list.get(i).getQuantity());
                System.out.println("     Price: " + list.get(i).getPrice());
                System.out.println("     ID #: " + list.get(i).getIDNumber());
            }
        }
        System.out.println();
    }
    /**
     * Gathers quantity input from user and outputs all items, and their
     * quantities for what is equal too or below that quantity
     * @param list is the arraylist for the inventory that it checks through
     */
    static void displayUsers(ArrayList<User> users)
    {
        Integer id, SSN, formatSize = 84;
        String fName, lName;
        float salary;
        for(int i = 0; i < formatSize; i++)
            System.out.print("-");
        System.out.println();
        System.out.println("|  ID # | NAME               | Other Info                                          |");
        
        for(int i = 0; i <formatSize; i++)
            System.out.print("-");
        System.out.println();
        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i).getClass().equals(Employee.class))
            {
                System.out.println("| " + String.format("%-5s", users.get(i).getID())
                                + " | " + String.format("%-18s", users.get(i).getFirstName() 
                                + " " + users.get(i).getLastName())
                                + " | " + String.format("%-51s", "SSN - " + users.get(i).getSSN()
                                + " | Salary = $" + String.format("%-23.2f",users.get(i).getSalary()) 
                                + " | "));
            }
            else
            {
                System.out.println("| " + String.format("%-5s", users.get(i).getID())
                                + " | " + String.format("%-18s", users.get(i).getFirstName() 
                                + " " + users.get(i).getLastName())
                                + " | " + String.format("%-51s", "Phone #: " + users.get(i).getNumber()
                                + " Address: " + users.get(i).getAddress()) 
                                + " | ");
            }
        }
    
        for(int i = 0; i < formatSize; i++)
            System.out.print("-");
        System.out.println();
    }
    /**
     * Gathers info to create the User's profile in the system.
     * 
     * @param users is the arraylist for the User that it modifies
     */
    static void addUser(ArrayList<User> users)
    {
        boolean found = false;
        int id = -1, answer = -1;
        
        Scanner in = new Scanner(System.in);
        System.out.println("What is their first name: ");
        String fName = in.nextLine();
        System.out.println("What is their last name: ");
        String lName = in.nextLine();
        
        do
        {
            System.out.print("What will their ID number be: ");
            id = in.nextInt();
            in.nextLine();
            for(int i = 0; i < users.size(); i++)
            {
                if(id == users.get(i).getID())
                   found = true;
            }
            if(found)
                System.out.println("ID already exists");
            if(id < 0 || id > 99999)
                System.out.println("ID out of bounds choose between 1-99,999");
        }while(id < 0 && id > 100000 && found);
        
        do
        {
            System.out.println("Will this user be an employee or customer?");
            System.out.println("1. Employee");
            System.out.println("2. Customer");
            answer = in.nextInt();
            in.nextLine();
        }while(answer!= 1 && answer != 2);
        
        if(answer == 1)
        {
            String SSN = "";
            float salary;
            do
            {
                System.out.print("\nWhat is the employees SSN: ");
                SSN = in.nextLine();
                if(String.valueOf(SSN).length() != 9)
                    System.out.println("\nInvalid SSN length, supposed to be 9 digits long!");
            }while(String.valueOf(SSN).length() != 9);
            do
            {
                System.out.print("What will be the employees monthly salary: ");
                salary = in.nextFloat();
                in.nextLine();
                if(salary < 0)
                    System.out.println("You can't pay employees negative dollars!!!");
            }while(salary < 0);
            
            users.add(new Employee(id, fName, lName,  SSN, salary));
        }
        if(answer == 2)
        {
            String numba;
            do
            {
                System.out.print("What is the customers phone number (include area code): ");
                numba = in.nextLine();
                in.nextLine();
                if(String.valueOf(numba).length() != 10)
                    System.out.println("Invalid Number *** Length must be 10 digits");
            }while(String.valueOf(numba).length() != 10);
            System.out.println("What is the customer's address: ");
            String dedress = in.nextLine();
            
            users.add(new Customer(id, fName, lName, numba, dedress));
        }
    }
    /**
     * Gets the ID of the user that is to be modified, then asks
     * what needs to be modified and does it accordingly 
     * @param users is the arraylist for the User that it modifies
     */
    public static void updateUser(ArrayList <User> users)
    {
        Scanner in = new Scanner(System.in);
        String id = "";
        boolean found = false;
        int index = -1;
        int choice = -1;
        

        System.out.print("Enter ID of user to update: ");
        id = in.nextLine();

        for(int i = 0; i < users.size() && !found; i++)
        {
            if(id.equals(users.get(i).getID()))
            {
                found = true;
                index = i;
            }
        }
        if(!found)
            System.out.println(id + " was not found");
        else
        {
            if(users.get(index).getClass().equals(Employee.class))
            {
                do
                {
                    System.out.println("What would you like to update?");
                    System.out.println("1. SSN ");
                    System.out.println("2. Monthly Salary");
                    switch(choice)
                    {
                        case(1): String SSN;
                                 do
                                 {                                     
                                     System.out.print("New SSN (9 digits):");
                                     SSN = in.nextLine();
                                     if(SSN.length() != 9)
                                        System.out.println("Invalid SSN length");
                                 }while(SSN.length() != 9);
                                 users.get(index).setSSN(SSN);
                        case(2): float salary;
                                 do
                                 {                                     
                                     System.out.print("New salary: $");
                                     salary = in.nextInt();
                                     in.nextLine();
                                     if(salary < 0)
                                        System.out.println("\nSalary Must be a positive number");
                                 }while(salary < 0);
                                 users.get(index).setSalary(salary);
                        default: System.out.println("Invalid input!");
                    }
                }while(choice != 1 && choice != 2);
            }
            else
            {
                do
                {
                    System.out.println("What would you like to update?");
                    System.out.println("1. Phone Number ");
                    System.out.println("2. Address");
                    switch(choice)
                    {
                        case(1): String phone;
                                 do
                                 {                                     
                                     System.out.print("New Phone Number (10 digits): ");
                                     phone = in.nextLine();
                                     if(phone.length() != 10)
                                        System.out.println("Invalid Number length");
                                 }while(phone.length() != 10);
                                 users.get(index).setNumber(phone);
                        case(2): String address;
                                 do
                                 {                                     
                                     System.out.print("New address: ");
                                     address = in.nextLine();
                                     if(address.length() == 0)
                                        System.out.println("\nYou Didn't enter an address");
                                 }while(address.length() == 0);
                                 users.get(index).setAddress(address);
                        default: System.out.println("Invalid input!");
                    }
                }while(choice != 1 && choice != 2);
            }
        }
    }
    /**
     * Gathers necessary info to complete the transaction, and then 
     * stores it in the transaction arraylist, while updating the on
     * hand count.
     * @param users is the arraylist that data is gathered from
     * @param transaction arraylist of the transactions that have happened at the store
     * @param list arraylist of the inventory at the store
     */
    public static void completeTransaction(ArrayList<Transactions> transaction, ArrayList<User> users, ArrayList<Inventory> list)
    {
        String id = "";
        Integer quantity, customerID, employeeID, index = -1;
        boolean foundItem = false, foundCustomer = false, foundEmployee = false;
        
        Scanner in = new Scanner(System.in);
        
        do
        {
            System.out.print("What item are you selling? (input the id): ");
            id = in.nextLine();
            for(int i = 0; i < list.size() && !foundItem; i++)
            {
                if(id.toUpperCase().equals(list.get(i).getIDNumber()))
                {
                    foundItem = true;
                    index = i;
                }
                if(!foundItem)
                    System.out.println("\nItem not found. Enter a valid ID");
            }
        }while(!foundItem);
        
        Date date = new Date();
        //Timestamp time = new GetCurrentTimeStamp();
        long time = date.getTime();
        
        do
        {
            System.out.print("How much are they buying: ");
            quantity = in.nextInt();
            in.nextLine();
                                  
            if(quantity > list.get(index).getQuantity())
                System.out.println("\nError *** Can't sell more than you have");
            if(quantity < 0)
                System.out.println("Can't sell negative quantities");
        }while(quantity > list.get(index).getQuantity() && quantity < 0);
        
        Integer temp = list.get(index).getQuantity();
        list.get(index).setQuantity(temp-quantity);
        
        do
        {
            System.out.print("Who is buying the item (enter their ID): ");
            customerID = in.nextInt();
            in.nextLine();
            
            for(int i = 0; i < users.size(); i++)
            {
                if(customerID.equals(users.get(i).getID()))
                {
                    foundCustomer = true;
                }
            }
            if(!foundCustomer)
                System.out.println("\nCustomer ID not found, Try again!");
        }while(!foundCustomer);
        
        do
        {
            System.out.print("Who is selling the item (enter your ID): ");
            employeeID = in.nextInt();
            in.nextLine();
            
            for(int i = 0; i < users.size(); i++)
            {
                if(employeeID.equals(users.get(i).getID()))
                {
                    foundEmployee = true;
                }
            }
            if(!foundEmployee)
                System.out.println("\nEmployee ID not found, Try again!");
        }while(!foundEmployee);
                
        transaction.add(new Transactions(id, time, quantity, customerID, employeeID));
    }
    /**
     * displays the transaction list of all the sales.
     * 
     * @param transaction is the arraylist for the transactions to
     * be displayed
     */
    public static void displayTransactions(ArrayList<Transactions> transaction)
    {
        Integer id, SSN, formatSize = 77;
        for(int i = 0; i < formatSize; i++)
            System.out.print("-");
        System.out.println();
        System.out.println("| Item ID | Date                      | Quantity | Customer ID | Employee ID |");
        for(int i = 0; i < transaction.size(); i++)
        {
            System.out.println("| " + String.format("%-6s", transaction.get(i).getIDItem())
                            + " | " + String.format("%-25s", transaction.get(i).getDate())
                            + " | " + String.format("%-8s", transaction.get(i).getQuantity())
                            + " | " + String.format("%-11s", transaction.get(i).getIDCustomer())
                            + " | " + String.format("%-11s", transaction.get(i).getIDEmployee()) + " |");
        }
        for(int i = 0; i < formatSize; i++)
            System.out.print("-");
    }
}