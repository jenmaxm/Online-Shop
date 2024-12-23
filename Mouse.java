import java.io.*;
import java.util.*;

// Class representing a mouse product, extending the Product class
public class Mouse extends Product {
    private int buttons; // Number of buttons
    private MouseType type; // Type of mouse

    // Constructor
    public Mouse(int barcode, ProductCategory category, MouseType type, String brand, String color, ConnectivityType connectivity, int quantityInStock,
                 double originalCost, double retailPrice, int buttons) {
        super(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category);
        this.buttons = buttons;
        this.type = type;
    }

    // Method to return string representation of a mouse
    public String toString() {
        return "Barcode: " + String.valueOf(getBarcode()) + ",Product Category:Mouse" + ",Mouse Type:" + String.valueOf(getMouseType())
                + ",Brand:" + getBrand() + ",Colour:" + getColor() + ", Connectivity Type:" + String.valueOf(getConnectivity()) + ",Quantity:" + String.valueOf(getQuantityInStock())
                + ",Original Cost:" + String.valueOf(getOriginalCost()) + ", Retail Price:" + String.valueOf(getRetailPrice()) + ",Buttons:"
                + String.valueOf(getButtons());

    }

    // Method to return string representation of a mouse for file output
    public String toStringFile() {
        return String.valueOf(getBarcode()) + ", mouse" + ", " + String.valueOf(getMouseType()).toLowerCase()
                + ", " + getBrand().toLowerCase() + "," + getColor().toLowerCase() + ", " + String.valueOf(getConnectivity()).toLowerCase()
                + ", " + String.valueOf(getQuantityInStock()).toLowerCase()
                + ", " +  String.valueOf(getOriginalCost()) + ", " +  String.valueOf(getRetailPrice()) + ", "
                + String.valueOf(getButtons());

    }

    // Method to return string representation of a mouse for customer view
    public String toStringCustomer() {
        return "Barcode: " + String.valueOf(getBarcode()) + ",Product Category:Mouse" + ",Mouse Type:" + String.valueOf(getMouseType())
                + ",Brand:" + getBrand() + ",Colour:" + getColor() + ", Connectivity Type:" + String.valueOf(getConnectivity()) + ",Quantity:" + String.valueOf(getQuantityInStock())
                + ", Retail Price:" +  String.valueOf(getRetailPrice()) + ",Buttons:"
                + String.valueOf(getButtons());

    }

    // Getter method for buttons
    public int getButtons() {
        return buttons;
    }

    // Getter method for mouse type
    public MouseType getMouseType() {
        return type;
    }

    // Method to read mouse products data from file and return as objects
    public static ArrayList<Mouse> getFileAsObjectListMouse() throws FileNotFoundException {
        File inputFile = new File("Stock.txt");
        Scanner fileScanner = new Scanner(inputFile);
        ArrayList<Mouse> mouseArray = new ArrayList<>();

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] mouseDetails = line.split(",");

            if (mouseDetails.length < 10) {
                continue; // Skip this line and move to the next one
            }

            if ("mouse".equals(mouseDetails[1].trim())) {
                Mouse mouse = new Mouse(
                        Integer.parseInt(mouseDetails[0].trim()),
                        ProductCategory.MOUSE,
                        MouseType.valueOf(mouseDetails[2].trim().toUpperCase()),
                        mouseDetails[3].trim(),
                        mouseDetails[4].trim(),
                        ConnectivityType.valueOf(mouseDetails[5].trim().toUpperCase()),
                        Integer.parseInt(mouseDetails[6].trim()),
                        Double.parseDouble(mouseDetails[7].trim()),
                        Double.parseDouble(mouseDetails[8].trim()),
                        Integer.parseInt(mouseDetails[9].trim()));
                mouseArray.add(mouse);
            }
        }

        fileScanner.close();
        return mouseArray;
    }

    // Method to write mouse object to file
    public void WriteToFile(Mouse mouse) {
        try {
            FileWriter writer = new FileWriter("Stock.txt", true);
            String FileData ='\n' + String.valueOf(mouse.getBarcode()) + ", mouse, " + String.valueOf(mouse.getMouseType()).toLowerCase()
                    + ", " + String.valueOf(mouse.getBrand()) + ", " + String.valueOf(mouse.getColor()) + "," + String.valueOf(mouse.getConnectivity()).toLowerCase()
                    + ", " + String.valueOf(mouse.getQuantityInStock()) + ", " + String.format("%.2f", mouse.getOriginalCost()) + ", " 
                    + String.format("%.2f", mouse.getRetailPrice())
                    + ", " + String.valueOf(mouse.getButtons());

            writer.write(FileData);
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

}





