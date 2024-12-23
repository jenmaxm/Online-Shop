import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

// Customer class inheriting from User class
public class Customer extends User {
    // Instance variables
    private HashMap<Product, Integer> Basket; // Customer's shopping basket
    private int houseNumber; // Customer's house number
    private String postcode; // Customer's postcode
    private String city; // Customer's city

    // Constructor
    public Customer(int user_ID, String username, String name, String role,HashMap<Product, Integer> Basket,int houseNumber,
            String postcode,String city) {
        super(user_ID, username, name, role);
        this.houseNumber = houseNumber;
        this.Basket = Basket;
        this.postcode = postcode;
        this.city = city;
    }
    
    // Method to get the list of products
    private static ArrayList<Product> getProductsList() {
        ArrayList<Mouse> MouseList;
        try {
            // Get lists of Mouse and Keyboard products
            MouseList = Mouse.getFileAsObjectListMouse();
            ArrayList<Keyboard> KeyboardList = Keyboard.getFileAsObjectListKeyboard();
            ArrayList<Product> AllProducts = new ArrayList<Product>();
            // Combine both lists into one
            AllProducts.addAll(MouseList);
            AllProducts.addAll(KeyboardList);
            return AllProducts;
        } catch (FileNotFoundException e) {
            // Print stack trace if file not found
            e.printStackTrace();
            return null;
        }
    }

    // Method to view products
    public static ArrayList<String>  ViewProduct() {
        ArrayList<String> outputArray = new ArrayList<String>();
        ArrayList<Product> AllProducts = getProductsList();
            
        Collections.sort(AllProducts, new Comparator<Product>() {
            public int compare(Product o1, Product o2) {
                return Double.compare(o1.getRetailPrice(), o2.getRetailPrice());
            }
        });
        
        
        String output = "";
        for (Product obj: AllProducts){
            if(obj instanceof Mouse){
                output =((Mouse) obj).toStringCustomer()+ '\n'; 
                outputArray.add(output);
            }
            else if(obj instanceof Keyboard){
                output =((Keyboard) obj).toStringCustomer() + '\n'; 
                outputArray.add(output);
            }           
        }
        return outputArray;        
    
    }
    
    // Method to get the customer's basket
    public HashMap<Product, Integer> getBasket(){
        return Basket;
        
    }

    // Method to view the customer's basket
    public ArrayList<String> ViewBasket(HashMap<Product, Integer> Basket) {
        ArrayList<String> outputArray = new ArrayList<String>();
        String output = "";
        for (Product key : Basket.keySet()) {
            if(key instanceof Mouse){
                output =" Barcode: " + ((Mouse) key).getBarcode() + ", Quantity: " + String.valueOf(Basket.get(key)) + ", Buttons: "+((Mouse) key).getButtons() +  '\n';  
                outputArray.add(output);
            }
            else if(key instanceof Keyboard){
                output =" Barcode: " + ((Keyboard) key).getBarcode() + ", Quantity: " +String.valueOf(Basket.get(key)) +", Layout: "+((Keyboard) key).getLayout() +  '\n';
                outputArray.add(output);
                
            }
            
        }
        return outputArray;
    }


    // Getter method for house number
    public int getHouseNumber() {
        return houseNumber;
    }

    // Getter method for postcode
    public String getPostcode() {
        return postcode;
    }

    // Getter method for city
    public String getCity() {
        return city;
    }
    
    // Method to search for a product by barcode
    public String Search(int barcode) {
        ArrayList<Product> AllProducts = getProductsList();
        
        if(barcode < 0) {
            throw new IllegalArgumentException("Barcode cannot be negative");
        }
        
        String output = "";
        for (Product obj: AllProducts){ 
            if(obj.getBarcode() == barcode) {
                    if(obj instanceof Mouse){
                        output = output + ((Mouse) obj).toStringCustomer()+ '\n';           
                    }
                    else if(obj instanceof Keyboard){
                        output = output + ((Keyboard) obj).toStringCustomer() + '\n'; 
                    }
                    
                }

                
            } 
        return output;

    }

    
    // Method to filter products by button preference (applicable only to mice)
    public String Filter(int button_preference) {
        
        if(button_preference < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        String output = "";
        try {
            ArrayList<Mouse> Mouses = Mouse.getFileAsObjectListMouse();
            for (Mouse mouse: Mouses) {
                if(mouse.getButtons() == button_preference) {
                    output = output + mouse.toStringCustomer()+ '\n'; 
                }
            }
            
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return output;

        
    }
    
    // Method to check if a product is in stock
    protected static boolean check_stock(int barcode,int quantity) {
        boolean existence = false;
        ArrayList<Product> AllProducts = getProductsList();
        
        for (Product obj: AllProducts){ 
            if(obj.getBarcode() == barcode) {
                int stock = obj.getQuantityInStock() - quantity;
                if(stock >= 0) {
                    existence = true;
                }
            } 
            else {
                continue;
            }


    }
        return existence;
    }
    
    
            
    // Method to add a product to the basket
    public boolean Add(int barcode, int quantity) {
        
        boolean out_of_stock = false;
        
        if(barcode < 0 || quantity < 0) {
            throw new IllegalArgumentException("Quantity or Barcode cannot be negative");
        }

       if(check_stock(barcode,quantity) == true) {
           ArrayList<Product> AllProducts = getProductsList();
            for (Product obj: AllProducts){ 
                if(obj.getBarcode() == barcode) {
                    Product product = obj;
                    Basket.put(product, quantity);
                    update_stock(Basket);
                }
        else {
            out_of_stock = true;
                }
            }
       }
       return out_of_stock;
   }

    
    
    // Method to cancel the order
    public HashMap<Product, Integer> Cancel() {
        update_stock_clear(getBasket());
        Basket.clear();
        return Basket;
    }
    
    // Method to calculate the total cost of the products in the basket
    protected static double CalculateCost(HashMap<Product, Integer> Basket) {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : Basket.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getRetailPrice() * quantity;
        }
        return total;
    }

    
    // Method to process payment using PayPal
    public Receipt PaymentPayPal(String PayPalEmail) {
        double amount = CalculateCost(Basket);

        Address fullAddress = new Address(getHouseNumber(),getPostcode(),getCity());
        PayPal paypal = new PayPal(PayPalEmail);
        Receipt receipt = paypal.processPayment(amount, fullAddress);


        Basket.clear();
        
        return receipt;

    }
    
    
    // Method to process payment using Credit Card
    public Receipt PaymentCreditCard(int card_number,int security_code) {
        
        double amount = CalculateCost(Basket);
        
        Address fullAddress = new Address(getHouseNumber(),getPostcode(),getCity());
        CreditCard credit = new CreditCard(card_number,security_code);
        Receipt receipt = credit.processPayment(amount, fullAddress);
        
        Basket.clear();
        
        return receipt;

    }
    
    // Method to update stock when cancelling an order
    private static void update_stock_clear(HashMap<Product, Integer> basket) {
        ArrayList<Product> allProducts = getProductsList();
        basket.forEach((key, value) -> {
            for (Product product : allProducts) {
                if (key.getBarcode() == product.getBarcode()) {
                    int quantityInStock = product.getQuantityInStock() + value;
                    product.setQuantityInStock(quantityInStock);
                    alter_file(allProducts);
                    
                    
                }
            }
        });
    }
    
    // Method to update stock when adding a product to the basket
    private static void update_stock(HashMap<Product, Integer> basket) {
        ArrayList<Product> allProducts = getProductsList();
        basket.forEach((key, value) -> {
            for (Product product : allProducts) {
                if (key.getBarcode() == product.getBarcode()) {
                    int quantityInStock = product.getQuantityInStock() - value;
                    product.setQuantityInStock(quantityInStock);
                    alter_file(allProducts);
                    
                    
                }
            }
        });
    }

    // Method to alter the stock file
    private static void alter_file(ArrayList<Product> allProducts) {
        try {
            FileWriter myWriter = new FileWriter("Stock.txt");
            String output = ""; 
            for (Product obj : allProducts) {
                if(obj instanceof Mouse){
                    output = output + ((Mouse) obj).toStringFile()+ '\n'; 
                }
                else if(obj instanceof Keyboard){
                    output = output + ((Keyboard) obj).toStringFile() + '\n'; 
                }     
            }
            myWriter.write(output);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

        
}


