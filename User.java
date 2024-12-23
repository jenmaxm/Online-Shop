import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Abstract class representing a User
public abstract class User {
    private int user_ID; // User ID
    private String username; // Username
    private String name; // Name
    private String role; // Role

    // Constructor
    public User(int user_ID, String username, String name, String role) {
        this.user_ID = user_ID;
        this.username = username;
        this.name = name;
        this.role = role;
    }

    // Method to read user accounts data from file
    private static ArrayList<String[]> getFileAsList() {
        ArrayList<String[]> Users = new ArrayList<String[]>();
        File inputFile = new File("UserAccounts.txt");
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(inputFile);
            while (fileScanner.hasNextLine()) {
                String[] userDetails = fileScanner.nextLine().split(",");
                Users.add(userDetails);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Users;
    }

    // Getter method for user ID
    public int getUser_ID() {
        return user_ID;
    }

    // Getter method for username
    public String getUsername() {
        return username;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Getter method for role
    public String getRole() {
        return role;
    }

    // Method to perform login based on user selection
    public static User login(Users selectedUser) {
        ArrayList<String[]> users = getFileAsList();
        for (int i = 0; i < users.size(); i++) {
            String[] userDetails = users.get(i);
            if (userDetails[2].trim().equals(String.valueOf(selectedUser))) {
                if (userDetails[6].trim().equals("admin")) {
                    return new Admin(Integer.valueOf(userDetails[0].trim()), userDetails[1], userDetails[2], userDetails[6]);
                } else if (userDetails[6].trim().equals("customer")) {
                    HashMap<Product, Integer> basket = new HashMap<>();
                    return new Customer(Integer.valueOf(userDetails[0].trim()), userDetails[1], userDetails[2], userDetails[6], basket,
                            Integer.valueOf(userDetails[3].trim()), userDetails[4], userDetails[5]);
                }
            }
        }
        return null;
    }

}
