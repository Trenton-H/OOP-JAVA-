/*
 * Hardware Store Management Software v0.1
 * Developed for CS3354: Object Oriented Design and Programming.
 * Copyright: Junye Wen (j_w236@txstate.edu)
 * GUI implimented by Trenton Hohle
 */
 

// import hardwarestore.Item;
// import hardwarestore.User;

import java.io.*;;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This is the main class of the Hardware Store database manager. It provides a
 * console for a user to use the 5 main commands.
 *
 * @author Trenton Hohle
 * 
 */
public class MainApp {

    // This object will allow us to interact with the methods of the class HardwareStore
    private final HardwareStore hardwareStore;
    private static final Scanner CONSOLE_INPUT = new Scanner(System.in); // Used to read from System's standard input

    /**
     * Default constructor. Initializes a new object of type HardwareStore
     *
     * @throws IOException
     */
    public MainApp() throws IOException {
        hardwareStore = new HardwareStore();
    }

    //Function 1
    /**
     * This method shows all items in the inventory.
     */
    public void showAllItems() {
        // We need to sort the item list first
        HardwareStore.sortItemList();
        // int size = hardwareStore.getItemListSize();
        // SimpleFrame frame = new SimpleFrame("Inventory List");
        // ArrayList<Item> tester = hardwareStore.getAllItemsFormatted();
        
        // Vector<String> header = new Vector<String>();
        // header.add("Item ID");
        // header.add("Name");
        // header.add("Quantity");
        // header.add("Price");
        // header.add("Item Type");
        // header.add("Category / Brand and type");
                
        // Vector entire = new Vector();
        // Vector eachPart = new Vector();
        
        // //tester = hardwareStore.getAllItemsFormatted();
        // Vector v = new Vector (tester); 
        
        // for(int i = 0; i < size; i++)
        // {
            // if(tester.get(i).getClass().equals(SmallHardwareItems.class))
            // {
                // eachPart.add(tester.get(i).getIdNumber());
                // eachPart.add(tester.get(i).getName());
                // eachPart.add(tester.get(i).getQuantity());
                // eachPart.add(tester.get(i).getPrice());
                // //eachPart.add(tester.get(i).getCategory());
                // //eachPart.add(tester.get(i).getName());
                // entire.add(eachPart);
                // eachPart = new Vector();
            // }
            // else
            // {
                // eachPart.add(tester.get(i).getIdNumber());
                // eachPart.add(tester.get(i).getName());
                // eachPart.add(tester.get(i).getQuantity());
                // eachPart.add(tester.get(i).getPrice());
                // //eachPart.add(tester.get(i).getBrand());
                // //eachPart.add(tester.get(i).getType());
                // entire.add(eachPart);
                // eachPart = new Vector();
            // }
        // }
        
        // JTable inventory = new JTable(v, header);
        
        // frame.add(new JScrollPane(inventory));
        
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.pack();
        // frame.setVisible(true);
        
        System.out.print(hardwareStore.getAllItemsFormatted());
    }

    //Function 2
    //Should work but says I added this container to parent container, can't figure out what it means....
    /**
     * This method will add items quantity with given number. If the item does
     * not exist, it will call another method to add it.
     *
     */
    public void addItemQuantity() {
        JFrame frameQuantity = new JFrame();
        JFrame itemFound = new JFrame();
        JFrame frameItem = new JFrame();
        
        final int FIELD_WIDTH = 20;
        final JTextField textID = new JTextField(FIELD_WIDTH);
        textID.setText("Enter ID Here");        
        final JTextField textQuantity = new JTextField(FIELD_WIDTH);
        textQuantity.setText("Enter ID Here");        
        
        final JTextField id = new JTextField(FIELD_WIDTH);
        id.setText("");
        final JTextField nameItem = new JTextField(FIELD_WIDTH);
        nameItem.setText("");
        final JTextField quantity = new JTextField(FIELD_WIDTH);
        quantity.setText("");
        final JTextField price = new JTextField(FIELD_WIDTH);
        price.setText("");
        String [] typeItem = {"Small Hardware Item", "Appliance"};
        JComboBox type = new JComboBox(typeItem);
        String [] catType = {"Door&Window", "Cabinet&Furniture", "Fasteners", "Structural", "Other"};
        JComboBox info = new JComboBox(catType);
        final JTextField brandInfor = new JTextField(FIELD_WIDTH);
        brandInfor.setText("");
        String [] appType = {"Refrigerator", "Washer & Dryer", "Ranges&Ovens", "Small Appliances"};
        JComboBox typeInfor = new JComboBox(appType);
        
        
        
        ActionListener buttonListener = new
        ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                if(event.getActionCommand().equals(" "))
                {
                    String text = textID.getText();
                    int index = hardwareStore.findItemIndex(text);
                
                    if (!text.matches("[A-Za-z0-9]{5}"))
                    {
                        textID.setText("Invalid Format");
                        return;
                    }
                    int itemIndex = hardwareStore.findItemIndex(text);
                    if(itemIndex == -1)
                    {
                        textID.setText(text + " Not Found.");
                        frameQuantity.setVisible(false);
                        frameItem.setVisible(true);                        
                    }
                    else
                    {
                        frameQuantity.setVisible(false);
                        textID.setText("Current Quantity: " + hardwareStore.findItem(text).getQuantity());
                        itemFound.setVisible(true);
                    }
                }
            }
        };
        
        ActionListener buttonListener2 = new
        ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                if(event.getActionCommand().equals(" "))
                {
                    String text = textQuantity.getText();
                    int quantity = Integer.getInteger(text, null);
                    
                    if(text == null)
                        textQuantity.setText("Invalid input");
                    else
                    {
                        String text2 = textID.getText();
                        hardwareStore.addQuantity(hardwareStore.findItemIndex(text2), quantity);
                        itemFound.setVisible(false);
                        itemFound.dispose();
                    }
                }
            }
        };
        
        ActionListener buttonListener3 = new
        ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                if(event.getActionCommand().equals(" "))
                {
                    boolean pass = true;
                    
                    String typeOfItem = (String)type.getSelectedItem();
                    String idText = id.getText();
                    String itemName = nameItem.getText();
                    int quantityItem = Integer.getInteger(quantity.getText());
                    float priceItem = Float.parseFloat(price.getText());
                    String categoryOfItem = (String)info.getSelectedItem();
                    String brandItem = brandInfor.getText();
                    String typeOfTheItem = (String) typeInfor.getSelectedItem();
                    
                    if(!idText.matches("[A-Za-z0-9]{5}"))
                    {
                        id.setText("Invalid Input");
                        pass = false;
                    }
                    if(quantityItem < 0)
                    {
                        quantity.setText("Quantity can't be less than 0!");
                        pass = false;
                    }
                    if(priceItem < 0)
                    {
                        price.setText("Price must be over 0.00!");
                        pass = false;
                    }
                    if(pass && typeOfItem == "Small Hardware Item")
                    {
                        hardwareStore.addNewSmallHardwareItem(idText, itemName, quantityItem, priceItem, categoryOfItem);
                        frameItem.setVisible(false);
                        frameItem.dispose();
                    }
                    if(pass && typeOfItem == "Appliance")
                    {
                        hardwareStore.addNewAppliance(idText, itemName, quantityItem, priceItem, brandItem, typeOfTheItem);
                        frameItem.setVisible(false);
                        frameItem.dispose();
                    }
                }
            }
        };
        
        ActionListener buttonListener4 = new
        ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                if(event.getActionCommand().equals("a"))
                {
                    frameItem.setVisible(false);
                    frameItem.dispose();
                }
            }
        };
        
        JButton findItem = new JButton ("Find Item");
        findItem.setActionCommand(" ");
        findItem.addActionListener(buttonListener);
        
        JLabel opening = new JLabel("Enter ID of item to find in the system");
        JLabel toFind = new JLabel("Find: ");        
        frameQuantity.add(opening, BorderLayout.NORTH);
        frameQuantity.add(toFind, BorderLayout.WEST);
        frameQuantity.add(textID, BorderLayout.EAST);
        frameQuantity.add(findItem, BorderLayout.SOUTH);
        frameQuantity.setLayout(new BorderLayout());
        frameQuantity.pack();
        frameQuantity.setVisible(true);
        
        //////////////////////////////////////////
        
        JButton updateQuantity = new JButton ("Update Quantity");
        updateQuantity.setActionCommand(" ");
        updateQuantity.addActionListener(buttonListener2);
        JLabel opening2 = new JLabel("Enter ID of item to find in the system");
        JLabel quantityToAdd = new JLabel("Added Quantity: ");
        itemFound.add(opening2, BorderLayout.NORTH);
        itemFound.add(quantityToAdd, BorderLayout.WEST);
        itemFound.add(textQuantity, BorderLayout.EAST);
        itemFound.add(updateQuantity, BorderLayout.SOUTH);
        itemFound.setLayout(new BorderLayout());
        itemFound.pack();
        itemFound.setVisible(false);
        
        JButton addQuantity = new JButton ("Add Quantity");
        addQuantity.setActionCommand(" ");
        addQuantity.addActionListener(buttonListener2);
        
        //////////////////////////////////////////////
        
        JLabel opening3 = new JLabel("Item Not Found... Adding To System");
        JLabel idNumber = new JLabel("ID Number: ");
        JPanel idInfo = new JPanel(new FlowLayout());
        idInfo.add(idNumber);
        idInfo.add(Box.createRigidArea(new Dimension(10,0)));
        idInfo.add(id);
        
        JLabel name = new JLabel("Name: ");
        JPanel itemName = new JPanel(new FlowLayout());
        itemName.add(name);
        itemName.add(Box.createRigidArea(new Dimension(10,0)));
        itemName.add(nameItem);
        
        JLabel Quantity = new JLabel("Quantity: ");        
        JPanel quantityInfo = new JPanel(new FlowLayout());
        quantityInfo.add(Quantity);
        quantityInfo.add(Box.createRigidArea(new Dimension(10,0)));
        quantityInfo.add(quantity);
        
        JLabel Price = new JLabel("Price: ");
        JPanel priceInfo = new JPanel(new FlowLayout());
        priceInfo.add(Price);
        priceInfo.add(Box.createRigidArea(new Dimension(10,0)));
        priceInfo.add(price);
        
        JLabel itemType = new JLabel("Item Type: ");
        JPanel itemInfo = new JPanel(new FlowLayout());
        itemInfo.add(itemType);
        itemInfo.add(Box.createRigidArea(new Dimension(10,0)));
        itemInfo.add(type);
        
        JLabel smallHWI = new JLabel("~Small Hardware Items only~");
        
        JLabel category = new JLabel("Category: ");
        JPanel categoryInfo = new JPanel(new FlowLayout());
        categoryInfo.add(category);
        categoryInfo.add(Box.createRigidArea(new Dimension(10,0)));
        categoryInfo.add(info);
        
        JLabel appliances = new JLabel("~Appliances only~");
        
        JLabel brand = new JLabel("Brand: ");
        JPanel brandInfo = new JPanel(new FlowLayout());
        brandInfo.add(brand);
        brandInfo.add(Box.createRigidArea(new Dimension(10,0)));
        brandInfo.add(brandInfor);
        
        JLabel typeOfAppliance = new JLabel("Type: ");
        JPanel typeInfo = new JPanel(new FlowLayout());
        typeInfo.add(typeOfAppliance);
        typeInfo.add(Box.createRigidArea(new Dimension(10,0)));
        typeInfo.add(typeInfo);
        
        
        itemFound.add(opening2, BorderLayout.NORTH);
        itemFound.add(quantityToAdd, BorderLayout.WEST);
        itemFound.add(textQuantity, BorderLayout.EAST);
        itemFound.add(updateQuantity, BorderLayout.SOUTH);
        itemFound.setLayout(new BorderLayout());
        
        itemFound.pack();
        itemFound.setVisible(false);
        
        frameItem.setLayout(new BoxLayout(frameItem, BoxLayout.LINE_AXIS));
        frameItem.add(opening3);
        frameItem.add(itemType);
        frameItem.add(idInfo);
        frameItem.add(itemName);
        frameItem.add(quantityInfo);
        frameItem.add(priceInfo);
        frameItem.add(smallHWI);
        frameItem.add(categoryInfo);
        frameItem.add(appliances);
        frameItem.add(brandInfo);
        frameItem.add(typeInfo);
        
        JButton addItem = new JButton ("Add Item");
        addItem.setActionCommand(" ");
        addItem.addActionListener(buttonListener3);
        JButton cancel = new JButton ("cancel");
        addItem.setActionCommand("a");
        addItem.addActionListener(buttonListener4);
        
        frameItem.pack();
        frameItem.setVisible(false);               
        }
        

    //Function 3 updated with GUI
    /**
     * This method will remove the item with given ID.
     * If the item does not exist, it will show an appropriate message.
     */
    public void removeItem() {
        JFrame frame = new JFrame();
        
        final int FIELD_WIDTH = 20;
        final JTextField textField = new JTextField(FIELD_WIDTH);
        textField.setText("Enter ID Here");
        
        ActionListener buttonListener = new
        ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                if(event.getActionCommand().equals(" "))
                {
                    String text = textField.getText();
                
                    if (!text.matches("[A-Za-z0-9]{5}"))
                    {
                        textField.setText("Invalid Format");
                        return;
                    }
                    int itemIndex = hardwareStore.findItemIndex(text);
                    if(itemIndex == -1)
                    {
                        textField.setText(text + " Not Found");
                        return;
                    }
                    else
                    {
                        hardwareStore.removeItem(itemIndex);
                        textField.setText(text + " was deleted");
                        
                    }
                }
            }
        };
        
        JButton updateSystem = new JButton ("Update system");
        updateSystem.setActionCommand(" ");
        updateSystem.addActionListener(buttonListener);
        
        frame.setLayout(new BorderLayout());
        
        JLabel opening = new JLabel("Enter ID of item to remove from system");
        JLabel toRemove = new JLabel("Remove: ");
        
        frame.add(opening, BorderLayout.NORTH);
        frame.add(toRemove, BorderLayout.WEST);
        frame.add(textField, BorderLayout.EAST);
        frame.add(updateSystem, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    //Function 4 updated with GUI
    /**
     * This method can search item by a given name (part of name.
     * Case-insensitive.) Will display all items with the given name.
     */
    public void searchItemByName() {
        JFrame frame = new JFrame();
        
        final int FIELD_WIDTH = 20;
        final JTextField textField = new JTextField(FIELD_WIDTH);
        textField.setText("Enter Item Name Here");
        
        ActionListener buttonListener = new
        ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                if(event.getActionCommand().equals(" "))
                {
                    String text = textField.getText();
                
                    String output = hardwareStore.getMatchingItemsByName(text);
                    if (output == null) 
                    {
                        textField.setText(text + " not found.");
                        return;
                    } else 
                    {  
                        System.out.println(output);
                        return;
                    }
                }
            }
        };
        
        JButton findItem = new JButton ("Find Item");
        findItem.setActionCommand(" ");
        findItem.addActionListener(buttonListener);
        
        frame.setLayout(new BorderLayout());
        
        JLabel opening = new JLabel("Enter name of item to find in the system");
        JLabel toRemove = new JLabel("Find: ");
        
        frame.add(opening, BorderLayout.NORTH);
        frame.add(toRemove, BorderLayout.WEST);
        frame.add(textField, BorderLayout.EAST);
        frame.add(findItem, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        
        
        // System.out.println("Please input the name of item.");
        // String name = CONSOLE_INPUT.nextLine();

        // String output = hardwareStore.getMatchingItemsByName(name);
        // if (output == null) {
            // System.out.println("Item not found with: " + name + ".");
        // } else {
            // System.out.println(output);
        // }
    }

    //Function 5
    /**
     * This method shows all users in the system.
     */
    public void showAllUsers() {
        System.out.print(hardwareStore.getAllUsersFormatted());
    }
    //Function 6
    /**
     * This method will add a user (customer or employee) to the system.
     *
     */
    public void addUser() {
        int selection = 0;

        String firstName = "";
        String lastName = "";
        // First select if you want to add customer or employee
        while (true) {
            System.out.println("Please select the type of user.");
            System.out.println("1: Employee\n2: Customer");
            try {
                selection = CONSOLE_INPUT.nextInt();
                CONSOLE_INPUT.nextLine();

                switch (selection) {
                    case 1:
                        // Add Employee
                        System.out.println("Please input the first name (String):");
                        firstName = CONSOLE_INPUT.nextLine();
                        System.out.println("Please input the last name (String):");
                        lastName = CONSOLE_INPUT.nextLine();
                        System.out.println("Please input the SSN (9-digit integer, no other characters):");
                        int socialSecurityNumber = 0;
                        try {
                            socialSecurityNumber = CONSOLE_INPUT.nextInt();
                            CONSOLE_INPUT.nextLine();
                            if (socialSecurityNumber <= 100000000 || socialSecurityNumber > 999999999) {
                                System.out.println("Invalid social security number. "
                                        + "SSN is a 9-digit integer.");
                                continue;
                            }
                        } catch (Exception e) {
                            System.out.println("Illegal input: Must input an integer.");
                            CONSOLE_INPUT.nextLine();
                            continue;
                        }

                        System.out.println("Please input the monthly salary (float):");
                        float monthlySalary = 0;
                        try {
                            monthlySalary = CONSOLE_INPUT.nextFloat();
                            CONSOLE_INPUT.nextLine();
                            if (monthlySalary < 0) {
                                System.out.println("Invalid salary."
                                        + "It must be (at least) 0.");
                                continue;
                            }
                        } catch (Exception e) {
                            System.out.println("Illegal input: Must input a float.");
                            CONSOLE_INPUT.nextLine();
                            continue;
                        }

                        hardwareStore.addEmployee(firstName,lastName, socialSecurityNumber, monthlySalary);
                        return;

                    case 2:
                        // Add Customer
                        System.out.println("Please input the first name (String):");
                        firstName = CONSOLE_INPUT.nextLine();
                        System.out.println("Please input the last name (String):");
                        lastName = CONSOLE_INPUT.nextLine();
                        System.out.println("Please input the phone number (String):");
                        String phoneNumber = CONSOLE_INPUT.nextLine();
                        System.out.println("Please input the address (String):");
                        String address = CONSOLE_INPUT.nextLine();
                        hardwareStore.addCustomer(firstName, lastName, phoneNumber, address);
                        return;
                    default:
                        System.out.println("Invalid input.");
                        continue;
                }
            } catch (Exception e) {
                System.out.println("Illegal input: Must input an integer.");
                CONSOLE_INPUT.nextLine();
                continue;
            }
        }


    }

    //Function 7
    /**
     * This method will edit a user (customer or employee).
     *
     */
    public void editUser() {
        int idInput = 0;
        while (true) {
            System.out.println("Please input the ID of user.");
            try {
                idInput = CONSOLE_INPUT.nextInt();
                CONSOLE_INPUT.nextLine();
            } catch (Exception e) {
                System.out.println("Illegal input: Must input an integer.");
                CONSOLE_INPUT.nextLine();
                continue;
            }
            break;
        }


        User editUser = hardwareStore.findUser(idInput);
        if (editUser == null) {
            System.out.println("User not found.");
            System.out.println("Will return to main menu.");
            return;
        }
        String text = " -------------------------------------------------------------------------------------------------\n" +
                String.format("| %-10s| %-9s| %-12s| %-12s| %-45s|%n", "User Type", "User ID", "First Name", "Last Name", "Special") +
                " -------------------------------------------------------------------------------------------------\n";
        text += editUser.getFormattedText();
        text += " -------------------------------------------------------------------------------------------------\n";

        System.out.println("Current user information:");
        System.out.println(text);
        String firstName = "";
        String lastName = "";
        if (editUser.isEmployee) {
            //User is employee
            int socialSecurityNumber = 0;
            float monthlySalary = 0;
            while (true) {
                System.out.println("Please input the first name (String):");
                firstName = CONSOLE_INPUT.nextLine();
                System.out.println("Please input the last name (String):");
                lastName = CONSOLE_INPUT.nextLine();
                System.out.println("Please input the SSN (9-digit integer, no other characters):");

                try {
                    socialSecurityNumber = CONSOLE_INPUT.nextInt();
                    CONSOLE_INPUT.nextLine();
                    if (socialSecurityNumber <= 100000000 || socialSecurityNumber > 999999999) {
                        System.out.println("Invalid social security number. "
                                + "SSN is a 9-digit integer.");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("Illegal input: Must input an integer.");
                    CONSOLE_INPUT.nextLine();
                    continue;
                }

                System.out.println("Please input the monthly salary (float):");
                try {
                    monthlySalary = CONSOLE_INPUT.nextFloat();
                    CONSOLE_INPUT.nextLine();
                    if (monthlySalary < 0) {
                        System.out.println("Invalid salary."
                                + "It must be (at least) 0.");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("Illegal input: Must input a float.");
                    CONSOLE_INPUT.nextLine();
                    continue;
                }
                break;
            }

            hardwareStore.editEmployeeInformation(idInput, firstName,lastName, socialSecurityNumber, monthlySalary);
            return;

        } else {
            //User is customer
            System.out.println("Please input the first name (String):");
            firstName = CONSOLE_INPUT.nextLine();
            System.out.println("Please input the last name (String):");
            lastName = CONSOLE_INPUT.nextLine();
            System.out.println("Please input the phone number (String):");
            String phoneNumber = CONSOLE_INPUT.nextLine();
            System.out.println("Please input the address (String):");
            String address = CONSOLE_INPUT.nextLine();
            hardwareStore.editCustomerInformation(idInput, firstName, lastName, phoneNumber, address);
            return;
        }
    }

    //Function 8
    /**
     * This method will lead user to complete a transaction.
     */
    public void finishTransaction(){
        String itemID = "";
        int itemIndex = 0;
        int saleQuantity = 0;
        //Get the item ID. Will not break unless got a valid input.
        while (true) {
            System.out.println("Please input the item ID:");
            itemID=CONSOLE_INPUT.nextLine();
            itemIndex = hardwareStore.findItemIndex(itemID);
            if (itemIndex == -1) {
                System.out.println("Item not found. Will return to main menu.");
                return;
            } else {
                Item tempItem = hardwareStore.findItem(itemID);
                System.out.println("Please input the amount of items sold in this transaction (int)");
                System.out.println("Maximum number: " + tempItem.getQuantity());
                try {
                    saleQuantity = CONSOLE_INPUT.nextInt();
                    CONSOLE_INPUT.nextLine();
                    if (saleQuantity <= 0) {
                        System.out.println("Invalid input: must be greater than 0.");
                        continue;
                    } else if (saleQuantity > tempItem.getQuantity()) {
                        System.out.println("Invalid input: Number too big. Transaction cannot progress.");
                        continue;
                    }
                    break;

                } catch (Exception e) {
                    System.out.println("Amount of items sold input invalid: not an integer");
                    continue;
                }
            }

        }

        //Get employee ID. Will check the input: must be a user, and employee.
        int employeeID = 0;
        while (true) {
            System.out.println("Please input the id of the employee.");
            try {
                employeeID = CONSOLE_INPUT.nextInt();
                CONSOLE_INPUT.nextLine();
                if (hardwareStore.findUserIndex(employeeID) == -1) {
                    System.out.println("User not found.");
                    continue;
                } else if (!hardwareStore.findUser(employeeID).isEmployee) {
                    System.out.println("This user is not an employee.");
                }
                break;

            } catch (Exception e) {
                System.out.println("Illegal input: Must input an integer.");
                CONSOLE_INPUT.nextLine();
                continue;
            }
        }

        //Get customer ID. Will check the input: must be a user, and customer.
        int customerID = 0;
        while (true) {
            System.out.println("Please input the id of the customer.");
            try {
                customerID = CONSOLE_INPUT.nextInt();
                CONSOLE_INPUT.nextLine();
                if (hardwareStore.findUserIndex(customerID) == -1) {
                    System.out.println("User not found.");
                    continue;
                } else if (hardwareStore.findUser(customerID).isEmployee) {
                    System.out.println("This user is not a customer.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Illegal input: Must input an integer.");
                CONSOLE_INPUT.nextLine();
                continue;
            }
        }

        hardwareStore.progressTransaction(itemID, saleQuantity, customerID, employeeID, itemIndex);
        System.out.println("Transaction complete.");

    }

    //Function 9
    public void showAllTransactions(){
        System.out.print(hardwareStore.getAllTransactionsFormatted());
    }

    //Function 10
    /**
     * These method is called to save the database before exit the system.
     * @throws IOException
     */
    public void saveDatabase() throws IOException {
        hardwareStore.writeDatabase();
    }

    /**
     * This method will begin the user interface console. Main uses a loop to
     * continue executing commands until the user types '6'.
     *
     * @param args this program expects no command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        MainApp app = new MainApp();
        
        SimpleFrame frame = new SimpleFrame("Hardware Store");
        
        JPanel inventoryPanel = new JPanel();
        JPanel userPanel = new JPanel();
        JPanel transactionPanel = new JPanel();
        
        //String [] options = {"Inventory", "Users", "Transactions"};
        
        
        ActionListener buttonListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                if(event.getActionCommand().equals("show"))
                    app.showAllItems();
                else if(event.getActionCommand().equals("add item"))
                    app.addItemQuantity();
                else if(event.getActionCommand().equals("remove item"))
                    app.removeItem();
                else if(event.getActionCommand().equals("search item"))
                    app.searchItemByName();
                else if(event.getActionCommand().equals("show users"))
                    app.showAllUsers();
                else if(event.getActionCommand().equals("add user"))
                    app.addUser();
                else if(event.getActionCommand().equals("edit user"))
                    app.editUser();
                else if(event.getActionCommand().equals("finish transaction"))
                    app.finishTransaction();
                else if(event.getActionCommand().equals("show transactions"))
                    app.showAllTransactions();
            }
        };
        
        JButton showInventory = new JButton("Show Inventory");
        showInventory.setActionCommand("show");
        showInventory.addActionListener(buttonListener);
        JButton addItem = new JButton("Add Item");
        addItem.setActionCommand("add item");
        addItem.addActionListener(buttonListener);
        JButton removeItem = new JButton("Remove Item");
        removeItem.setActionCommand("remove item");
        removeItem.addActionListener(buttonListener);
        JButton searchItem = new JButton("Item Search");
        searchItem.setActionCommand("search item");
        searchItem.addActionListener(buttonListener);
        JButton showUsers = new JButton("Show Users");
        showUsers.setActionCommand("show users");
        showUsers.addActionListener(buttonListener);
        JButton addUser = new JButton("Add User");
        addUser.setActionCommand("add user");
        addUser.addActionListener(buttonListener);
        JButton editUser = new JButton("Edit User");
        editUser.setActionCommand("edit user");
        editUser.addActionListener(buttonListener);
        JButton finishTransaction = new JButton("Finish Transaction");
        finishTransaction.setActionCommand("show");
        finishTransaction.addActionListener(buttonListener);
        JButton showTransactions = new JButton("Show Transactions");
        showTransactions.setActionCommand("show transactions");
        showTransactions.addActionListener(buttonListener);
        
        frame.setLayout(new BorderLayout());

        inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.PAGE_AXIS));
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.PAGE_AXIS));
        transactionPanel.setLayout(new BoxLayout(transactionPanel, BoxLayout.PAGE_AXIS));
        
        JLabel inventoryLabel = new JLabel("Inventory");
        JLabel userLabel = new JLabel("User");
        JLabel transactionLabel = new JLabel("Transaction");
        
        inventoryPanel.add(inventoryLabel);
        inventoryPanel.add(showInventory);
        inventoryPanel.add(addItem);
        inventoryPanel.add(removeItem);
        inventoryPanel.add(searchItem);

        userPanel.add(userLabel);
        userPanel.add(showUsers);
        userPanel.add(addUser);
        userPanel.add(editUser);

        transactionPanel.add(transactionLabel);
        transactionPanel.add(showTransactions);
        transactionPanel.add(finishTransaction);
        
        
        frame.add(inventoryPanel, BorderLayout.WEST);
        frame.add(userPanel, BorderLayout.CENTER);
        frame.add(transactionPanel, BorderLayout.EAST);
        //frame.add(menuList, BorderLayout.NORTH);
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        app.saveDatabase();

    }
}

class SimpleFrame extends JFrame
{
    public SimpleFrame(String title)
    {
        super(title);
        
    }
}