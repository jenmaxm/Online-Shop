import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Keyboard class extending Product
public class Keyboard extends Product{
    private KeyboardType type; // Type of keyboard
    private LayoutType layout; // Layout of the keyboard
    

    // Constructor
    public Keyboard(int barcode, ProductCategory category, KeyboardType type, String brand, String color, ConnectivityType connectivity, int quantityInStock,
            double originalCost, double retailPrice, LayoutType layout){
        super(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category);
        this.type = type;
        this.layout = layout;
    }

    // Method to return string representation of a keyboard
    public String toString() {
        return "Barcode: " + String.valueOf(getBarcode()) + ",Product Category:Keyboard" + ",Keyboard Type:" + String.valueOf(getKeyboardType())
                + ",Brand:" + getBrand() + ",Colour:" + getColor() + ", Connectivity Type:" + String.valueOf(getConnectivity()) + ",Quantity:" + String.valueOf(getQuantityInStock())
                + ",Original Cost:" + String.valueOf(getOriginalCost()) + ", Retail Price:" + String.valueOf(getRetailPrice()) + ",Layout:"
                + String.valueOf(getLayout());

    }

    // Method to return string representation of a mouse for file output
    public String toStringFile() {
        return String.valueOf(getBarcode()) + ", keyboard" + ", " + String.valueOf(getKeyboardType()).toLowerCase()
                + ", " + getBrand().toLowerCase() + "," + getColor().toLowerCase() + ", " + String.valueOf(getConnectivity()).toLowerCase()
                + ", " + String.valueOf(getQuantityInStock()).toLowerCase()
                + ", " +  String.valueOf(getOriginalCost()) + ", " +  String.valueOf(getRetailPrice()) + ", "
                + String.valueOf(getLayout());

    }

    // Method to return string representation of a mouse for customer view
    public String toStringCustomer() {
        return "Barcode: " + String.valueOf(getBarcode()) + ",Product Category:Keyboard" + ",Keyboard Type:" + String.valueOf(getKeyboardType())
                + ",Brand:" + getBrand() + ",Colour:" + getColor() + ", Connectivity Type:" + String.valueOf(getConnectivity()) + ",Quantity:" + String.valueOf(getQuantityInStock())
                + ", Retail Price:" +  String.valueOf(getRetailPrice()) + ",Layout:"
                + String.valueOf(getLayout());

    }
    
    // Getter method for keyboard type
    public KeyboardType getKeyboardType() {
        return type;
                
    }

    // Method to read keyboard data from file
    public static ArrayList<Keyboard> getFileAsObjectListKeyboard() throws FileNotFoundException {
        File inputFile = new File("Stock.txt");
        Scanner fileScanner = new Scanner(inputFile);
        ArrayList<Keyboard> keyboardArray = new ArrayList<>();

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] keyboardDetails = line.split(",");
            
            if (keyboardDetails.length < 10) {
                continue; // Skip this line and move to the next one
            }
            
            if ("keyboard".equals(keyboardDetails[1].trim())) {
                Keyboard keyboard = new Keyboard(
                        Integer.parseInt(keyboardDetails[0].trim()),
                        ProductCategory.KEYBOARD,
                        KeyboardType.valueOf(keyboardDetails[2].trim().toUpperCase()),
                        keyboardDetails[3].trim(),
                        keyboardDetails[4].trim(),
                        ConnectivityType.valueOf(keyboardDetails[5].trim().toUpperCase()),
                        Integer.parseInt(keyboardDetails[6].trim()),
                        Double.parseDouble(keyboardDetails[7].trim()),
                        Double.parseDouble(keyboardDetails[8].trim()),
                        LayoutType.valueOf(keyboardDetails[9].trim()));
                keyboardArray.add(keyboard);
            }
        }

        fileScanner.close();
        return keyboardArray;
    }

    
    // Getter method for layout
    public LayoutType getLayout() {
        return layout;
    }
    
    // Method to write keyboard data to file
    public void WriteToFile(Keyboard keyboard) {
        try {
            
            FileWriter writer = new FileWriter("Stock.txt",true);
            String FileData = '\n' + String.valueOf(keyboard.getBarcode()) + ", keyboard, " + String.valueOf(keyboard.getKeyboardType()).toLowerCase() 
            + ", " + String.valueOf(keyboard.getBrand()) + ", " + String.valueOf(keyboard.getColor()) + "," + String.valueOf(keyboard.getConnectivity()).toLowerCase()
            + ", " + String.valueOf(keyboard.getQuantityInStock()) + ", " + String.format("%.2f", keyboard.getOriginalCost()) + ", " 
            + String.format("%.2f", keyboard.getRetailPrice())
            + ", " + String.valueOf(keyboard.getLayout());
           
            writer.write(FileData);
            writer.close();
            
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}

