import java.time.LocalDate;

// Class representing a receipt for a payment
public class Receipt {
    private double amount; // Amount paid
    private Address fullAddress; // Address for delivery

    // Constructor for PayPal payment
    public Receipt(double amount, Address fullAddress, String PayPalEmail) {
        this.amount = amount;
        this.fullAddress = fullAddress;
    }

    // Constructor for credit card payment
    public Receipt(double amount, Address fullAddress, int card_number) {
        this.amount = amount;
        this.fullAddress = fullAddress;
    }

    // Method to generate receipt for PayPal payment
    public String generateReceiptPayPal(String PayPalEmail) {
        LocalDate currentDate = LocalDate.now(); // Get current date
        String StringAmount = String.format("%.2f", amount); // Format amount as string with 2 decimal places
        // Construct receipt string
        String Receipt = StringAmount + " paid by PayPal using " + PayPalEmail + " on "
                + currentDate + ", and the delivery address is " + fullAddress.getAddress();
        return Receipt; // Return receipt string
    }

    // Method to generate receipt for credit card payment
    public String generateReceiptCreditCard(int card_number) {
        LocalDate currentDate = LocalDate.now(); // Get current date
        String StringAmount = String.format("%.2f", amount); // Format amount as string with 2 decimal places
        // Construct receipt string
        String Receipt = StringAmount + " paid by Credit Card using " + String.valueOf(card_number) + " on "
                + currentDate + ", and the delivery address is " + fullAddress.getAddress();
        return Receipt; // Return receipt string
    }
}
