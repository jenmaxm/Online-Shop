// Abstract class representing a product
public abstract class Product {
    
    // Attributes
    private final int barcode; // Unique identifier for the product
    private String brand; // Brand of the product
    private String color; // Colour of the product
    private ConnectivityType connectivity; // Connectivity type of the product
    private int quantityInStock; // Quantity of the product in stock
    private double originalCost; // Original cost of the product
    private double retailPrice; // Retail price of the product
    private ProductCategory category; // Category of the product

    // Constructor
    public Product(int barcode, String brand, String color, ConnectivityType connectivity,
                   int quantityInStock, double originalCost, double retailPrice, ProductCategory category) {
        this.barcode = barcode;
        this.brand = brand;
        this.color = color;
        this.connectivity = connectivity;
        this.quantityInStock = quantityInStock;
        this.originalCost = originalCost;
        this.retailPrice = retailPrice;
        this.category = category;
    }

    // Getters and setters
    public int getBarcode() {
        return barcode;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public ConnectivityType getConnectivity() {
        return connectivity;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public double getOriginalCost() {
        return originalCost;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public ProductCategory getCategory() {
        return category;
    }

    // Abstract method to be implemented by subclasses to provide string representation
    public abstract String toString();
}


