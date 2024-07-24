/* Siddharth Korukonda
 * 115607752
 * CSE 214.30
 */

package Homework2;

import java.util.Scanner;

/**
 * Menu of options for managing the department store
 */
public class DepartmentStore {
    private static ItemList itemList = new ItemList();

    /**
     * Inserts an item into the list of items in the department store
     */
    private static void insertItem() {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter the name: ");
        String name = s.nextLine();
        System.out.print("Enter the RFID tag number: ");
        String rfidTagNumber = s.nextLine().toUpperCase();
        System.out.print("Enter the original location: ");
        String originalLocation = s.nextLine().toLowerCase();
        System.out.print("Enter the price: ");
        double price = s.nextDouble();
        s.nextLine();

        try {
            itemList.insertInfo(name, rfidTagNumber, price, originalLocation);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Moves an item from one location to another in the department store
     */
    private static void moveItem() {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter the RFID tag number: ");
        String rfidTagNumber = s.nextLine().toUpperCase();
        System.out.print("Enter the original location: ");
        String source = s.nextLine().toLowerCase();
        System.out.print("Enter the new location: ");
        String dest = s.nextLine().toLowerCase();

        try {
            boolean success = itemList.moveItem(rfidTagNumber, source, dest);
            if (!success) {
                System.out.println("Item not found or invalid location.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Lists all the items at a specific location in the department store
     */
    private static void listByLocation() {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter the location: ");
        String location = s.nextLine().toLowerCase();

        try {
            itemList.printByLocation(location);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints all the items in the list of items at the department store
     */
    private static void printAllItems() {
        try {
            itemList.printAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks out all items from a specific cart number
     */
    private static void checkOut() {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter the cart number: ");
        String cartNumber = s.nextLine().toLowerCase();

        try {
            itemList.checkOut(cartNumber);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Relocates all moved items to its original locations
     */
    private static void cleanStore() {
        try {
            itemList.cleanStore();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints all the items with a specified RFID Tag Number at the department store
     * EXTRA CREDIT
     */
    private static void printByRFID() {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter the RFID tag number: ");
        String rfidTagNumber = s.nextLine().toUpperCase();

        try {
            itemList.printByRFIDTagNumber(rfidTagNumber);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes all items that have been checked out
     */
    public static void updateInventory() {
        try {
            itemList.removeAllPurchased();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    /**
     * Main method
     * @param args command line
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean menu = true;

        while (menu) {
            System.out.println("\nMenu:\n" +
                    "C -  Clean store\n" +
                    "I - Insert an item into the list\n" +
                    "M - Move an item in the store\n" +
                    "L -  List by location\n" +
                    "O -  Checkout\n" +
                    "P -  Print all items in store\n" +
                    "R -  Print by RFID tag number\n" +
                    "U -  Update inventory system\n" +
                    "Q - Exit the program\n\n" +
                    "Enter an option: ");

            String option = s.next().toUpperCase();

            switch (option) {
                case "I" ->
                        insertItem();
                case "M" ->
                        moveItem();
                case "L" ->
                        listByLocation();
                case "P" ->
                        printAllItems();
                case "O" ->
                        checkOut();
                case "C" ->
                        cleanStore();
                case "U" ->
                        updateInventory();
                case "R" ->
                        printByRFID();
                case "Q"->
                        menu = false;
                default ->
                        System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
