/**
 *  Hardware Store Software for managing their inventory.
 *  @author Trenton Hohle
 *  @version 1.1
 *  @since 1.1
 */



import java.io.*;
import java.util.*;
import java.lang.*;

/**
 * Controls the files modification, menu, all the functions the modify the 
 * items on the inventory and inventory itself.
 */
public class Hardward_Store_Software_Part1
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
        Inventory idGenerator = new Inventory();
        ArrayList <Inventory> list = new ArrayList<Inventory>();
        Scanner in = new Scanner(System.in);
        try
        {
            BufferedReader inFile = new BufferedReader(new FileReader("database.txt"));
            String line;
            while((line = inFile.readLine()) != null)
            {
                String[] words = line.split("~");
                String id = words[0];
                String name = words[1];
                String category = words[2];
                Integer quantity = Integer.parseInt(words[3]);
                Double price = Double.parseDouble(words[4]);
                Inventory product = new Inventory(id, name, category, quantity, 
                                                  price);
                list.add(product);
            }
            inFile.close();
        }catch(FileNotFoundException ex)
        {
            System.out.println("Unable to open file, or file wasn't found.");
        }
        
        do
        {
            System.out.println("1. Show all existing items in stock and their quantities");
            System.out.println("2. Add a new quantity of a specific item to the stock.");
            System.out.println("3. Remove a certain quantity of a specific item type.");
            System.out.println("4. Search for an item (given its name or part of its name)");
            System.out.println("5. Show a list of all items below a certain quantity.");
            System.out.println("6. Exit program.");
            System.out.print("Your answer: ");
            answer = in.nextInt();
            switch (answer)
            {
                case(1): displayInventory(list); break;
                case(2): addToInventory(list); 
                         break;
                case(3): reduceOnHand(list); break;
                case(4): itemSearch(list); break;
                case(5): checkByQuantity(list); break;
                case(6): break;
                default: System.out.println("\nThat is not a valid answer.\n");
                         break;
            }
        }while(answer != 6);
        
        PrintWriter outFile = new PrintWriter("database.txt");
        for(int i = 0; i<list.size(); i++)
        {
            String id = list.get(i).getIDNumber(), 
                   name = list.get(i).getName(), 
                   category = list.get(i).getCategory();
            int quantity = list.get(i).getQuantity();
            Double price = list.get(i).getPrice();
            outFile.println(id + "~" + name + "~" + category + "~" + 
                            quantity + "~" + price);
        }
        outFile.close();
    }
    /**
     * Outputs the item inventory while formating it for the user to read easier
     */
    static void  displayInventory(ArrayList<Inventory> list)
    {
        String idTemp, categoryTemp, quantityTemp, priceTemp;
        Integer formatSize = 72;
        for(int i = 0; i < formatSize; i++)
            System.out.print("-");
        System.out.println();
        System.out.println("|  ID # | NAME               | CATEGORY           | Quantity  | PRICE  |");
        
        for(int i = 0; i <formatSize; i++)
            System.out.print("-");
        System.out.println();
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println("| " + String.format("%-5s", list.get(i).getIDNumber())
                            + " | " + String.format("%-18s", list.get(i).getName())
                            + " | " + String.format("%-18s",list.get(i).getCategory()) 
                            + " | " + String.format("%-9s", list.get(i).getQuantity()) 
                            + " | " + String.format("%6.2f", list.get(i).getPrice())
                            + " | ");
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
        Inventory idGeneration = new Inventory();
        
        System.out.print("What is the ID # of the product: ");
        IDNumber = in.nextLine();
        for(int i = 0; i < list.size(); i++)
        {
            String tempIDHolder = list.get(i).getIDNumber();
            if(IDNumber.equals(tempIDHolder))
            {
                do
                {
                    System.out.print("What would you like to change the quantity to: ");
                    changedQuantity = in.nextInt();
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
            System.out.println("\nThe ID you looked up wasn't found so we will add it to the inventory.");
            System.out.print("What is the name of the item: ");
            String name = in.nextLine();
            
            idGeneration.generateIDNumber();
            
            String ID = idGeneration.getIDNumber();
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
                if(price < 0)
                    System.out.println("That is not a valid price");
            }while(price < 0);
            Inventory product = new Inventory(ID, name, category, 
                                              quantity, price);
                                              list.add(product);
        }
    }
    /**
     * Asks the item the user would like to reduce the on hand for, Checks
     * that the item is on the inventory list, then allows the user to
     * modify the inventory. Validates all data.
     * @param list the arraylist to the item inventory.
     */
    static void reduceOnHand(ArrayList<Inventory> list)
    {
        Scanner in = new Scanner(System.in);
        String ID = "";
        Integer count, newCount;
        Boolean found = false;
        
        System.out.print("Which item would you like reduced (provide ID #): ");
        String find = in.nextLine();
        
        for(int i = 0; i < list.size(); i++)
        {
            ID = list.get(i).getIDNumber();
            if(find.equals(ID))
            {
                do
                {
                    System.out.println("How much would you like to reduce it by: ");
                    count = in.nextInt();
                    newCount = ((list.get(i).getQuantity())-count);
                    if(newCount < 0)
                       System.out.println("You can't make the on hand negative!");
                }while(newCount < 0);
                list.get(i).setQuantity(newCount);
                found = true;
                break;
            }
        }
        if(!found)
            System.out.println("Error *** ID not found!");
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
    static void checkByQuantity(ArrayList<Inventory> list)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("What quantity do you want checked for: ");
        Integer count = in.nextInt();
        
        System.out.println("\nThis is the list of all quantities under " + count + ": ");
        for(int i = 0; i <list.size(); i++)
        {
            if(list.get(i).getQuantity() <= count)
            {
                System.out.println(" -:" + list.get(i).getName() + " - Current stock: " + list.get(i).getQuantity());
            }
        }
        System.out.println();
    }
}

/**
 * Class to manage and alter all the informations for each individual item
 * in the store inventory.
 */
class Inventory
{
    private String idNumber;
    private String name;
    private String category;
    private Integer quantity;
    private Double price;
    
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
    Inventory(String id, String n, String c, Integer q, Double p)
    {
        idNumber = id;
        name = n;
        category = c;
        quantity = q;
        price = p;
    }
    /**
     * Uses an array library and a random number generator to create a 
     * random 5 character string that it then defines as the ID Number 
     * for a given item.
     */
    public void generateIDNumber()
    {
        String [] options = {"A","B","C","D","E","F","G","H","I","J","K","L",
                             "M","N","O","P","Q","R","S","T","U","V","W","X",
                             "Y","Z","0","1","2","3","4","5","6","7","8","9"};
        int x = 0;
        boolean checker = false;
        String temp = "";
        for(int i = 0; i <5; i++)
        {
            int random = (int)(Math.random() * (35+1));
            temp +=options[random];
        }
        idNumber = temp;
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
     * defines category as a temp variable c that is passed to it
     * @param c is the category associated with the item
     */
    public void setCategory(String c)
    {
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
}