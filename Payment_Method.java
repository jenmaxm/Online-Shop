// Interface representing a payment method
public interface Payment_Method {
    
    // Method signature to process a payment and generate a receipt
    Receipt processPayment(double amount, Address fullAddress);
}
