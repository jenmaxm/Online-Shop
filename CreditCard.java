// CreditCard class implementing Payment_Method interface
public class CreditCard implements Payment_Method {
    // Private instance variables
    private double amount;           // The amount to be paid
    private Address fullAddress;     // The full address associated with the credit card
    private int card_number;         // The credit card number
    private int security_code;       // The security code of the credit card

    // Constructor to initialize credit card number and security code
    public CreditCard(int card_number, int security_code) {
        this.card_number = card_number;
        this.security_code = security_code;
    }

    // Method to process payment and generate a receipt
    @Override
    public Receipt processPayment(double amount, Address fullAddress) {
        // Create a new receipt object with the given amount, full address, and card number
        Receipt receipt = new Receipt(amount, fullAddress, card_number);
        // Generate receipt for credit card payment
        receipt.generateReceiptCreditCard(card_number);
        return receipt; // Return the generated receipt
    }

    // Getter method for amount
    public double getAmount() {
        return amount;
    }

    // Getter method for full address
    public Address getFullAddress() {
        return fullAddress;
    }
}
