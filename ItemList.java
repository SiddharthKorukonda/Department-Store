/* Siddharth Korukonda
 * 115607752
 * CSE 214.30
 */

package Homework2;

/**
 * Doubly linked list of the list of items in the department store
 */
public class ItemList {
    private ItemInfoNode head;
    private ItemInfoNode tail;
    private ItemInfoNode cursor;

    public ItemList() {
        head = null;
        tail = null;
        cursor = null;
    }

    /**
     * Inserts an item into the list of items in the department store (sorted by RFID Tag Number)
     * @param name of the item
     * @param rfidTag of the item
     * @param price of the item
     * @param initPosition of the item
     */
    public void insertInfo(String name, String rfidTag, double price, String initPosition) {
        ItemInfo newItem = new ItemInfo(name, rfidTag, price, initPosition);
        ItemInfoNode newNode = new ItemInfoNode();
        newNode.setInfo(newItem);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            ItemInfoNode current = head;
            ItemInfoNode previous = null;

            while (current != null  && current.getInfo().getRfidTagNumber().compareTo(rfidTag) < 0) {
                previous = current;
                current = current.getNext();
            }

            if (previous == null) {
                newNode.setNext(head);
                head.setPrev(newNode);
                head = newNode;
            } else if (current == null) {
                previous.setNext(newNode);
                newNode.setPrev(previous);
                tail = newNode;
            } else {
                current.setPrev(newNode);
                newNode.setPrev(previous);
                newNode.setNext(current);
                previous.setNext(newNode);
            }
        }

        cursor = newNode;
    }

    /**
     * Removes all purchased items from the list of items in the department store
     * @throws LinkedListEmptyException if the list is empty
     */
    public void removeAllPurchased() throws LinkedListEmptyException {
        if (head == null) {
            throw new LinkedListEmptyException("The list is empty.");
        }

        System.out.print("The following item(s) have been removed from the system: \n\n");

        System.out.print("Item Name         " +
                "RFID       " +
                "Original Location " +
                "Current Location  " +
                "Price   \n" +
                "-----------------------------------------------------------------------");

        ItemInfoNode cursor = head;

        while (cursor != null) {
            if (cursor.getInfo().getCurrentLocation().equalsIgnoreCase("out")) {
                System.out.print(cursor.getInfo().toString());

                ItemInfoNode remove = cursor;

                if (remove.getPrev() != null) {
                    remove.getPrev().setNext(remove.getNext());
                } else {
                    head = remove.getNext();
                }

                if (remove.getNext() != null) {
                    remove.getNext().setPrev(remove.getPrev());
                } else {
                    tail = remove.getPrev();
                }
            }
            cursor = cursor.getNext();
        }
    }

    /**
     * Moves an item from the previous location to the new location
     * @param rfidTag of the item
     * @param source is the previous location of the item
     * @param dest is the new location of the item
     * @return true if the item is found and moved successfully. False if the item is not found or is not moved successfully
     * @throws LinkedListEmptyException if the list is empty
     */
    public boolean moveItem(String rfidTag, String source, String dest) throws LinkedListEmptyException {
        if (head == null) {
            throw new LinkedListEmptyException("The list is empty.");
        }

        if (source == null || source.isEmpty()) {
            throw new IllegalArgumentException("Source cannot be null or empty");
        } else if (source.equalsIgnoreCase("out")) {
            throw new IllegalArgumentException("Source cannot be 'out'");
        } else if (source.charAt(0) == 's') {
            if (source.length() != 6) {
                throw new IllegalArgumentException("Please input a valid shelf location in the format sXXXXX");
            }
            for (int i=1; i<source.length(); i++) {
                char c = source.charAt(i);
                if (!(c >= '0' && c <= '9')) {
                    throw new IllegalArgumentException("Characters after 's' should be digits. Please input a valid shelf location in the format sXXXXX");
                }
            }
        } else if (source.charAt(0) == 'c') {
            if (source.length() != 4) {
                throw new IllegalArgumentException("Please input a valid cart location in the format cXXX");
            }
            for (int i=1; i<source.length(); i++) {
                char c = source.charAt(i);
                if (!(c >= '0' && c <= '9')) {
                    throw new IllegalArgumentException("Characters after 'c' should be digits. Please input a valid cart location in the format cXXX");
                }
            }
        } else {
            throw new IllegalArgumentException("Source must be a shelf (sXXXXX) or cart (cXXX)");
        }

        if (dest == null || dest.isEmpty()) {
            throw new IllegalArgumentException("Destination cannot be null or empty");
        } else if (dest.charAt(0) == 's') {
            if (dest.length() != 6) {
                throw new IllegalArgumentException("Please input a valid shelf location in the format sXXXXX");
            }
            for (int i=1; i<dest.length(); i++) {
                char c = dest.charAt(i);
                if (!(c >= '0' && c <= '9')) {
                    throw new IllegalArgumentException("Characters after 's' should be digits. Please input a valid shelf location in the format sXXXXX");
                }
            }
        } else if (dest.charAt(0) == 'c') {
            if (dest.length() != 4) {
                throw new IllegalArgumentException("Please input a valid cart location in the format cXXX");
            }
            for (int i=1; i<dest.length(); i++) {
                char c = dest.charAt(i);
                if (!(c >= '0' && c <= '9')) {
                    throw new IllegalArgumentException("Characters after 'c' should be digits. Please input a valid cart location in the format cXXX");
                }
            }
        } else if (!dest.equalsIgnoreCase("out")) {
            throw new IllegalArgumentException("Destination must be a shelf (sXXXXX), cart (cXXX), or 'out'");
        }

        ItemInfoNode cursor = head;
        while (cursor != null) {
            if (cursor.getInfo().getRfidTagNumber().equalsIgnoreCase(rfidTag) &&
                    cursor.getInfo().getCurrentLocation().equalsIgnoreCase(source)) {
                cursor.getInfo().setCurrentLocation(dest);
                return true;
            }
            cursor = cursor.getNext();
        }
        return false;
    }

    /**
     * Prints all items in the list of items in the department store
     * @throws LinkedListEmptyException if the list is empty
     */
    public void printAll() throws LinkedListEmptyException {
        if (head == null) {
            throw new LinkedListEmptyException("The list is empty.");
        }

        System.out.print("Item Name         " +
                "RFID       " +
                "Original Location " +
                "Current Location  " +
                "Price   \n" +
                "-----------------------------------------------------------------------");

        ItemInfoNode cursor = head;

        while (cursor != null) {
            System.out.print(cursor.getInfo().toString());
            cursor = cursor.getNext();
        }
    }

    /**
     * Prints all the items at a specific location
     * @param location specified
     * @throws LinkedListEmptyException if the list is empty
     */
    public void printByLocation(String location) throws LinkedListEmptyException {
        if (head == null) {
            throw new LinkedListEmptyException("The list is empty.");
        }

        System.out.print("Item Name         " +
                "RFID       " +
                "Original Location " +
                "Current Location  " +
                "Price   \n" +
                "-----------------------------------------------------------------------");

        ItemInfoNode cursor = head;

        while (cursor != null) {
            if (cursor.getInfo().getCurrentLocation().equalsIgnoreCase(location)) {
                System.out.print(cursor.getInfo().toString());
            }

            cursor = cursor.getNext();
        }
    }

    /**
     * Moves all moved items back to its original locations
     * @throws LinkedListEmptyException if the list is empty
     */
    public void cleanStore() throws LinkedListEmptyException {
        if (head == null) {
            throw new LinkedListEmptyException("The list is empty.");
        }

        System.out.print("Item Name         " +
                "RFID       " +
                "Original Location " +
                "Current Location  " +
                "Price   \n" +
                "-----------------------------------------------------------------------");

        ItemInfoNode cursor = head;

        while (cursor != null) {
            if (!cursor.getInfo().getCurrentLocation().equalsIgnoreCase(cursor.getInfo().getOriginalLocation()) && !cursor.getInfo().getCurrentLocation().startsWith("c") && !cursor.getInfo().getCurrentLocation().equalsIgnoreCase("out")) {
                System.out.println(cursor.getInfo().toString());
                cursor.getInfo().setCurrentLocation(cursor.getInfo().getOriginalLocation());
            }
            cursor = cursor.getNext();
        }
    }

    /**
     * Checks out the items in a cart and gives the total price of all the items in the cart
     * @param cartNumber of the order
     * @return total price of the purchase
     * @throws LinkedListEmptyException if the list is empty
     */
    public double checkOut(String cartNumber) throws LinkedListEmptyException {
        if (head == null) {
            throw new LinkedListEmptyException("The list is empty.");
        }

        if (cartNumber.charAt(0) != 'c' || cartNumber.length() != 4) {
            throw new IllegalArgumentException("Please input a valid entry");
        }

        for (int i=1; i<cartNumber.length(); i++) {
            char c = cartNumber.charAt(i);

            if (!("0123456789".indexOf(c) >= 0)) {
                throw new IllegalArgumentException("Characters after the 'c' should be digits between 0-9. Please input a valid current location");
            }
        }

        double totalCost = 0.00;

        System.out.print("Item Name         " +
                "RFID       " +
                "Original Location " +
                "Current Location  " +
                "Price   \n" +
                "-----------------------------------------------------------------------");

        ItemInfoNode cursor = head;
        while (cursor != null) {
            if (cursor.getInfo().getCurrentLocation().equalsIgnoreCase(cartNumber)) {
                System.out.print(cursor.getInfo().toString());
                cursor.getInfo().setCurrentLocation("out");
                totalCost += cursor.getInfo().getPrice();
            }
            cursor = cursor.getNext();
        }
        System.out.printf("\nThe total cost for all merchandise in cart %s is $%.2f%n", cartNumber, totalCost);
        return totalCost;
    }

    /**
     * Prints the items in the list of items in the department store by its RFID Tag Number
     * EXTRA CREDIT
     * @param rfidTagNumber of the item
     * @throws LinkedListEmptyException if the list is empty
     */
    public void printByRFIDTagNumber(String rfidTagNumber) throws LinkedListEmptyException {
        if (head == null) {
            throw new LinkedListEmptyException("The list is empty.");
        }

        System.out.print("\nItem Name         " +
                "RFID       " +
                "Original Location " +
                "Current Location  " +
                "Price   \n" +
                "-----------------------------------------------------------------------");

        ItemInfoNode cursor = head;
        while (cursor != null) {
            if (cursor.getInfo().getRfidTagNumber().equalsIgnoreCase(rfidTagNumber)) {
                System.out.println(cursor.getInfo().toString());
            }
            cursor = cursor.getNext();
        }
    }
}
