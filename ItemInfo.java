/* Siddharth Korukonda
 * 115607752
 * CSE 214.30
 */

package Homework2;

/**
 * Parts of an item in the department store (contains name, price, its RFID Tag Number, its original location, and its current location)
 */
public class ItemInfo {
    private String name;
    private double price;
    private String rfidTagNumber;
    private String OriginalLocation;
    private String CurrentLocation;

    /**
     * Gets the name of the item
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the item
     * @param name of the item
     */
    public void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Please input a name");
        }

        this.name = name;
    }

    /**
     * Gets the price of the item
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the item
     * @param price of the item
     */
    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Please enter a price");
        }

        this.price = price;
    }

    /**
     * Gets the RFID Tag Number of the item
     * @return rfidTagNumber
     */
    public String getRfidTagNumber() {
        return rfidTagNumber;
    }

    /**
     * Sets the RFID Tag Number of the item
     * @param rfidTagNumber of the item
     */
    public void setRfidTagNumber(String rfidTagNumber) {
        if (rfidTagNumber == null || rfidTagNumber.equals("")) {
            throw new IllegalArgumentException("Please input a RFID");
        } else if (rfidTagNumber.length() != 9) {
            throw new IllegalArgumentException("Please input a valid RFID");
        }

        for (int i=0; i<rfidTagNumber.length(); i++) {
            char c = rfidTagNumber.charAt(i);

            if (!((c >= '0' && c <= '9') || (c >= 'A' && c <= 'F'))) {
                throw new IllegalArgumentException("Invalid character found: "+c+"\nPlease input a valid RFID");
            }
        }

        this.rfidTagNumber = rfidTagNumber;
    }

    /**
     * Gets the original location of the item
     * @return OriginalLocation
     */
    public String getOriginalLocation() {
        return OriginalLocation;
    }

    /**
     * Sets the original location of the item
     * @param OriginalLocation of the item
     */
    public void setOriginalLocation(String OriginalLocation) {
        if (OriginalLocation == null || OriginalLocation.equals("")) {
            throw new IllegalArgumentException("Please input the original location");
        } else if (OriginalLocation.length() != 6) {
            throw new IllegalArgumentException("Please input a valid original location");
        } else if (OriginalLocation.charAt(0) != 's') {
            throw new IllegalArgumentException("Original location must start with 's'. Please input a valid original location");
        }

        for (int i=1; i<OriginalLocation.length(); i++) {
            char c = OriginalLocation.charAt(i);

            if (!((c >= '0' && c <= '9'))) {
                throw new IllegalArgumentException("Characters after the 's' should be digits between 0-9. Please input a valid original location");
            }
        }

        this.OriginalLocation = OriginalLocation;
        this.CurrentLocation = CurrentLocation;
    }

    /**
     * Gets the current location of the item
     * @return CurrentLocation
     */
    public String getCurrentLocation() {
        return CurrentLocation;
    }

    /**
     * Sets the current location of the item
     * @param currentLocation of the item
     */
    public void setCurrentLocation(String currentLocation) {
        if (currentLocation == null || currentLocation.equals("")) {
            throw new IllegalArgumentException("Please input a current location");
        } else if (currentLocation.charAt(0) == 's') {
            if (currentLocation.length() != 6) {
                throw new IllegalArgumentException("Please input a valid shelf location in the format sXXXXX");
            }
            for (int i = 1; i < currentLocation.length(); i++) {
                char c = currentLocation.charAt(i);
                if (!(c >= '0' && c <= '9')) {
                    throw new IllegalArgumentException("Characters after the 's' should be digits between 0-9. Please input a valid shelf location in the format sXXXXX");
                }
            }
        } else if (currentLocation.charAt(0) == 'c') {
            if (currentLocation.length() != 4) {
                throw new IllegalArgumentException("Please input a valid cart location in the format cXXX");
            }
            for (int i = 1; i < currentLocation.length(); i++) {
                char c = currentLocation.charAt(i);
                if (!(c >= '0' && c <= '9')) {
                    throw new IllegalArgumentException("Characters after the 'c' should be digits between 0-9. Please input a valid cart location in the format cXXX");
                }
            }
        } else if (currentLocation.equalsIgnoreCase("out")) {
            this.CurrentLocation = currentLocation;
            return;
        } else {
            throw new IllegalArgumentException("The current location must start with either 's' or 'c'");
        }

        this.CurrentLocation = currentLocation;
    }

    /**
     * Constructor for the item
     * @param name of the item
     * @param rfidTagNumber of the item
     * @param price of the item
     * @param OriginalLocation of the item
     */
    public ItemInfo(String name, String rfidTagNumber, double price, String OriginalLocation) {
        setName(name);
        setPrice(price);
        setRfidTagNumber(rfidTagNumber);
        setOriginalLocation(OriginalLocation);
        this.CurrentLocation = OriginalLocation;
    }

    /**
     * Formats the details of the item
     * @return item information as a String datatype
     */
    @Override
    public String toString() {
        String result = "\n";
        result += name;

        while (result.length() < 15) {
            result += " ";
        }

        result += rfidTagNumber;

        while (result.length() < 25) {
            result += " ";
        }

        result += OriginalLocation;

        while (result.length() < 40) {
            result += " ";
        }

        result += CurrentLocation;

        while (result.length() < 55) {
            result += " ";
        }

        String priceStr = String.format("$%.2f", price);

        result += priceStr;
        result += "";
        return result;
    }
}
