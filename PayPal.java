// Class representing a PayPal payment method
public class PayPal implements Payment_Method {
    private double amount; // Amount to be paid
    private Address fullAddress; // Address for delivery
    private String PayPalEmail; // PayPal email associated with the payment

    // Constructor for PayPal payment method
    public PayPal(String PayPalEmail) {
        this.PayPalEmail = PayPalEmail;
    }

    // Method to process the PayPal payment and generate a receipt
    @Override
    public Receipt processPayment(double amount, Address fullAddress) {
        // Create a new receipt object with the provided amount, address, and PayPal email
        Receipt receipt = new Receipt(amount, fullAddress, PayPalEmail);
        // Generate the receipt for the PayPal payment
        receipt.generateReceiptPayPal(PayPalEmail);
        // Return the generated receipt
        return receipt;
    }

    // Getter method to retrieve the amount
    public double getAmount() {
        return amount;
    }

    // Getter method to retrieve the full address
    public Address getFullAddress() {
        return fullAddress;
    }
}
