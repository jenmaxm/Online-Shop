public class Address{
    // Private instance variables to store address information
    private int houseNumber;
    private String postcode;
    private String city;

    // Constructor to initialize the address with house number, postcode, and city
    public Address(int houseNumber, String postcode, String city) {
        this.houseNumber = houseNumber;
        this.postcode = postcode;
        this.city = city;
    }

    // Method to get the full address as a formatted string
    public String getAddress() {
        // Construct the address string by concatenating house number, postcode, and city
        String address = String.valueOf(getHouseNumber()) + "," + getPostcode() + "," + getCity();
        return address;
    }

    // Private method to get the house number
    private int getHouseNumber() {
        return houseNumber;
    }

    // Private method to get the postcode
    private String getPostcode() {
        return postcode;
    }

    // Private method to get the city
    private String getCity() {
        return city;
    }

}
