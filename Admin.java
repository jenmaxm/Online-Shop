import java.io.*;
import java.util.*;

// Admin class inheriting from User class
public class Admin extends User {

    // Constructor for Admin class
    public Admin(int user_ID, String username, String name, String role) {
        super(user_ID, username, name, role);
    }

    // Method to get the list of products
    private static ArrayList<Product> getProductsList() {
        try {
            // Get lists of Mouse and Keyboard products
            ArrayList<Mouse> MouseList = Mouse.getFileAsObjectListMouse();
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
    public static ArrayList<String> ViewProduct() {
        ArrayList<Product> AllProducts = getProductsList();
        ArrayList<String> outputArray = new ArrayList<String>();
        // Sort products by retail price
        Collections.sort(AllProducts, new Comparator<Product>() {
            public int compare(Product o1, Product o2) {
                return Double.compare(o1.getRetailPrice(), o2.getRetailPrice());
            }
        });

        String output = "";
        // Convert products to string and add to output array
        for (Product obj : AllProducts) {
            output = obj.toString() + '\n';
            outputArray.add(output);
        }

        return outputArray;
    }

    // Method to add a mouse product
    public void AddMouse() {
        Scanner scanner = new Scanner(System.in);
        try {
            // Get user input for mouse details
            System.out.println("Enter the barcode: ");
            int barcode = scanner.nextInt();
            scanner.nextLine();

            ProductCategory category = ProductCategory.MOUSE;

            System.out.println("Enter the type of mouse: ");
            String mouse_type = scanner.nextLine();
            MouseType mouse = MouseType.valueOf(mouse_type.toUpperCase());

            System.out.println("Enter the brand: ");
            String brand = scanner.nextLine();

            System.out.println("Enter the connectivity: ");
            String connectivity_type = scanner.nextLine();
            ConnectivityType connectivity = ConnectivityType.valueOf(connectivity_type.toUpperCase());

            System.out.println("Enter the colour: ");
            String colour = scanner.nextLine();

            System.out.println("Enter the quantity in stock: ");
            int quantityInStock = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the original cost: ");
            double originalCost = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Enter the retail price: ");
            double retailPrice = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Enter the number of buttons: ");
            int buttons = scanner.nextInt();
            scanner.nextLine();

            // Check if barcode already exists, if yes, prompt user again
            if (check_existence(barcode) == true) {
                System.out.println("This barcode already exists");
                this.AddMouse();
            }

            // Create a new Mouse object and write to file
            Mouse newMouse = new Mouse(barcode, category, mouse, brand, colour, connectivity, quantityInStock, originalCost, retailPrice, buttons);

            newMouse.WriteToFile(newMouse);

        } catch (Exception e) {
            // Handle invalid input
            System.out.println("Invalid input. Please enter a valid input type.");
            this.AddMouse();
        } finally {
            scanner.close();
        }
    }

    // Method to add a keyboard product
    public void AddKeyboard() {
        Scanner scanner = new Scanner(System.in);
        try {
            // Get user input for keyboard details
            System.out.println("Enter the barcode: ");
            int barcode = scanner.nextInt();
            scanner.nextLine();

            ProductCategory category = ProductCategory.KEYBOARD;

            System.out.println("Enter the type of keyboard: ");
            String keyboard_type = scanner.nextLine();
            KeyboardType keyboard = KeyboardType.valueOf(keyboard_type.toUpperCase());

            System.out.println("Enter the brand: ");
            String brand = scanner.nextLine();

            System.out.println("Enter the connectivity: ");
            String connectivity_type = scanner.nextLine();
            ConnectivityType connectivity = ConnectivityType.valueOf(connectivity_type.toUpperCase());

            System.out.println("Enter the colour: ");
            String colour = scanner.nextLine();

            System.out.println("Enter the quantity in stock: ");
            int quantityInStock = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the original cost: ");
            double originalCost = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Enter the retail price: ");
            double retailPrice = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Enter the type of layout: ");
            String layout1 = scanner.nextLine();
            LayoutType layout = LayoutType.valueOf(layout1.toUpperCase());
            scanner.nextLine();

            // Check if barcode already exists, if yes, prompt user again
            if (check_existence(barcode) == true) {
                System.out.println("This barcode already exists");
                this.AddKeyboard();
            }

            // Create a new Keyboard object and write to file
            Keyboard newKeyboard = new Keyboard(barcode, category, keyboard, brand, colour, connectivity, quantityInStock, originalCost, retailPrice, layout);

            newKeyboard.WriteToFile(newKeyboard);

        } catch (Exception e) {
            // Handle invalid input
            System.out.println("Invalid input. Please enter a valid input type.");
            this.AddKeyboard();
        } finally {
            scanner.close();
        }
    }

    // Method to check if a product with the given barcode already exists
    public static boolean check_existence(int barcode) {
        boolean existence = false;
        ArrayList<Product> AllProducts = getProductsList();

        // Iterate through products and check if barcode exists
        for (Product obj : AllProducts) {
            if (obj.getBarcode() == barcode) {
                existence = true;
            }
        }
        return existence;
    }


}
