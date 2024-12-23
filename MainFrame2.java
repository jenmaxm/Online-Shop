import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Window.Type;
import javax.swing.ListModel;
import java.awt.Component;
import java.math.BigDecimal;

public class MainFrame2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_Search;
	private JTextField textField_Filter;
	private JTextField textField_Quantity;
	private JTextField textField_BarcodeKeyboard;
	private JTextField textField_BarcodeMouse;
	private JTextField textField_BrandKeyboard;
	private JTextField textField_BrandMouse;
	private JTextField textField_ColourKeyboard;
	private JTextField textField_ColourMouse;
	private JTextField textField_StockCountKeyboard;
	private JTextField textField_StockCountMouse;
	private JTextField textField_Buttons;
	private JTextField textField_RPPriceMouse;
	private JTextField textField_OGPriceMouse;
	private JTextField textField_RPKeyboard;
	private JTextField textField_OGPriceKeyboard;
    private Customer customer;
    private Admin admin;
    private JTextField textField_Barcode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame2 frame = new MainFrame2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 640, 372);
		contentPane.add(tabbedPane);
		
		JPanel LoginPane = new JPanel();
		tabbedPane.addTab("Login", null, LoginPane, null);
		LoginPane.setLayout(null);
		
		JComboBox LoginBox = new JComboBox();
		LoginBox.setModel(new DefaultComboBoxModel(Users.values()));
		LoginBox.setBounds(236, 5, 99, 27);
		LoginPane.add(LoginBox);
		
		JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Users selectedUser = (Users) LoginBox.getSelectedItem();
                User user = User.login(selectedUser);
                if (user instanceof Customer) {
                    // Set customer details
                    int user_ID = user.getUser_ID();
                    String username = user.getUsername();
                    String name = user.getName();
                    String role = user.getRole();
                    HashMap<Product, Integer> Basket = ((Customer) user).getBasket();
                    int houseNumber = ((Customer) user).getHouseNumber();
                    String postcode = ((Customer) user).getPostcode();
                    String city = ((Customer) user).getCity();

                    // Initialize customer object specific to the customer tab
                    customer = new Customer(user_ID, username, name, role, Basket, houseNumber, postcode, city);

                    // Set tabbed pane to customer tab
                    tabbedPane.setSelectedIndex(1);
                } else if (user instanceof Admin) {
                    // Set admin details
                    int user_ID = user.getUser_ID();
                    String username = user.getUsername();
                    String name = user.getName();
                    String role = user.getRole();

                    // Initialize admin object specific to the admin tab
                    admin = new Admin(user_ID, username, name, role);

                    // Set tabbed pane to admin tab
                    tabbedPane.setSelectedIndex(2);
                }
            }
        });


     // Code for the "Login" panel
        btnLogin.setBounds(224, 33, 117, 29); // Set bounds for the login button
        LoginPane.add(btnLogin); // Add the login button to the login panel

        // Code for the "Customer" panel
        JPanel CustomerPane = new JPanel(); // Create a new panel for the customer section
        tabbedPane.addTab("Customer", null, CustomerPane, null); // Add the customer panel to the tabbed pane
        tabbedPane.setEnabledAt(1, false); // Disable the customer tab initially
        CustomerPane.setLayout(null); // Set layout to null for manual component placement

        // Text field and label for barcode search
        textField_Search = new JTextField(); // Create text field for barcode search
        textField_Search.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
        textField_Search.setBounds(18, 274, 90, 16); // Set bounds for the text field
        CustomerPane.add(textField_Search); // Add the text field to the customer panel
        textField_Search.setColumns(10); // Set the number of columns for the text field
        JLabel lblBarcodeSearch = new JLabel("Search by Barcode :"); // Create label for barcode search
        lblBarcodeSearch.setFont(new Font("Lucida Grande", Font.PLAIN, 9)); // Set font for the label
        lblBarcodeSearch.setBounds(18, 259, 90, 16); // Set bounds for the label
        CustomerPane.add(lblBarcodeSearch); // Add the label to the customer panel

        // Text field and label for button filter
        textField_Filter = new JTextField(); // Create text field for button filter
        textField_Filter.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
        textField_Filter.setBounds(18, 302, 90, 16); // Set bounds for the text field
        CustomerPane.add(textField_Filter); // Add the text field to the customer panel
        textField_Filter.setColumns(10); // Set the number of columns for the text field
        JLabel lblButtonFilter = new JLabel("Filter by Buttons:"); // Create label for button filter
        lblButtonFilter.setFont(new Font("Lucida Grande", Font.PLAIN, 9)); // Set font for the label
        lblButtonFilter.setBounds(18, 289, 90, 16); // Set bounds for the label
        CustomerPane.add(lblButtonFilter); // Add the label to the customer panel

        // Button for barcode search
        JButton btnSearch = new JButton("Search"); // Create search button
        btnSearch.setFont(new Font("Lucida Grande", Font.PLAIN, 9)); // Set font for the button
        btnSearch.setBounds(119, 276, 74, 16); // Set bounds for the button
        CustomerPane.add(btnSearch); // Add the search button to the customer panel
        btnSearch.addActionListener(new ActionListener() { // Add action listener to the search button
            public void actionPerformed(ActionEvent e) { // Define action performed when button is clicked
                int barcode = Integer.valueOf(textField_Search.getText()); // Get barcode value from text field
                String output = customer.Search(barcode); // Perform search operation
                JOptionPane.showMessageDialog(null, output, "Search Result", JOptionPane.PLAIN_MESSAGE); // Display search result in a dialog
            }
        });

        // Button for button filter
        JButton btnFilter = new JButton("Filter"); // Create filter button
        btnFilter.setFont(new Font("Lucida Grande", Font.PLAIN, 9)); // Set font for the button
        btnFilter.setBounds(120, 304, 74, 16); // Set bounds for the button
        CustomerPane.add(btnFilter); // Add the filter button to the customer panel
        btnFilter.addActionListener(new ActionListener() { // Add action listener to the filter button
            public void actionPerformed(ActionEvent e) { // Define action performed when button is clicked
                int button_preference = Integer.valueOf(textField_Filter.getText()); // Get button preference value from text field
                String output = customer.Filter(button_preference); // Perform filter operation
                JOptionPane.showMessageDialog(null, output, "Filter Result", JOptionPane.PLAIN_MESSAGE); // Display filter result in a dialog
            }
        }); // End of button filter action listener

		
		JLabel lblBasket = new JLabel("Basket");
		lblBasket.setBounds(446, 6, 61, 16);
		CustomerPane.add(lblBasket);
		
		
		JScrollPane scrollPane_Basket = new JScrollPane((Component) null); // Create scroll pane for the basket display
		scrollPane_Basket.setBounds(368, 25, 217, 164); // Set bounds for the scroll pane
		CustomerPane.add(scrollPane_Basket); // Add the scroll pane to the customer panel
		
		// List for displaying items in the basket
        JList<String> BasketList = new JList<>();
        BasketList.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
        scrollPane_Basket.setViewportView(BasketList);
		
		// Button for checkout
		JButton btnCheckout = new JButton("Checkout"); // Create checkout button
		btnCheckout.setFont(new Font("Lucida Grande", Font.PLAIN, 9)); // Set font for the button
		btnCheckout.addActionListener(new ActionListener() { // Add action listener to the checkout button
		    public void actionPerformed(ActionEvent e) { // Define action performed when button is clicked
		    	if (customer.getBasket().size() == 0) {
		    		JOptionPane.showMessageDialog(null,"Basket is Empty", "Warning", JOptionPane.PLAIN_MESSAGE);
		    	}
		    	else {
			        // Options for payment method
			        String[] options = {"Credit Card", "PayPal"};
			        // Display dialog to select payment method
			        int choice = JOptionPane.showOptionDialog(null, "Select Payment Method", "Payment", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			        // Handle different payment methods
			        if (choice == 0) { // Credit Card
			            // Variable to check if card details are valid
			            boolean valid_card_details = false;
			            // Input dialog to enter credit card number
			            String cardNumber = JOptionPane.showInputDialog(null, "Enter Credit Card Number:");
			            // Input dialog to enter security code
			            String securityCode = JOptionPane.showInputDialog(null, "Enter Security Code:");
			            // Check if card number and security code are of valid length
			            if(cardNumber.length() == 6 && securityCode.length() == 3) {
			                valid_card_details = true;
			            }
			            // Process payment if card details are valid
			            if(valid_card_details == true) {
			                // Perform credit card payment and generate receipt
			                Receipt receipt = customer.PaymentCreditCard(Integer.valueOf(cardNumber), Integer.valueOf(securityCode));
			                // Display receipt in a dialog
			                JOptionPane.showMessageDialog(null, receipt.generateReceiptCreditCard(Integer.valueOf(cardNumber)) , "Receipt", JOptionPane.INFORMATION_MESSAGE);
			            }
			            // Display warning if card details are invalid
			            else {
			                JOptionPane.showMessageDialog(null,"Invalid Card Details", "Warning", JOptionPane.PLAIN_MESSAGE);
			            }
			        } else if (choice == 1) { // PayPal
			            // Variable to check if email is valid
			            boolean valid_email = false;
			            // Input dialog to enter PayPal email
			            String email = JOptionPane.showInputDialog(null, "Enter PayPal Email:");
			            // Check if email contains "@" symbol
			            if(email.contains("@")) {
			                valid_email = true;
			            }
			            // Process payment if email is valid
			            if(valid_email == true) {
			                // Perform PayPal payment and generate receipt
			                Receipt receipt = customer.PaymentPayPal(email);
			                // Display receipt in a dialog
			                JOptionPane.showMessageDialog(null, receipt.generateReceiptPayPal(email), "Receipt", JOptionPane.INFORMATION_MESSAGE);
			            }
			            // Display warning if email is invalid
			            else {
			                JOptionPane.showMessageDialog(null,"Invalid Email Details", "Warning", JOptionPane.PLAIN_MESSAGE);
			            }
			        }
			    	DefaultListModel<String> listModel = new DefaultListModel<>();
			    	BasketList.setModel(listModel);
			    	customer.Cancel(); // Cancel the items in the basket
			    }
		    }
		    
		}); // End of checkout button action listener

		btnCheckout.setBounds(509, 234, 84, 16); // Set bounds for the button
		CustomerPane.add(btnCheckout); // Add the checkout button to the customer panel

		// Label for quantity
		JLabel lblQuantity = new JLabel("Quantity:"); // Create label for quantity
		lblQuantity.setFont(new Font("Lucida Grande", Font.PLAIN, 9)); // Set font for the label
		lblQuantity.setBounds(378, 285, 46, 16); // Set bounds for the label
		CustomerPane.add(lblQuantity); // Add the label to the customer panel

		// Text field for quantity input
		textField_Quantity = new JTextField(); // Create text field for quantity
		textField_Quantity.setFont(new Font("Lucida Grande", Font.PLAIN, 9)); // Set font for the text field
		textField_Quantity.setBounds(424, 279, 61, 26); // Set bounds for the text field
		CustomerPane.add(textField_Quantity); // Add the text field to the customer panel
		textField_Quantity.setColumns(10); // Set the number of columns for the text field

		// Button for adding to basket
		JButton btnAdd = new JButton("Add to basket"); // Create button for adding to basket
		btnAdd.setFont(new Font("Lucida Grande", Font.PLAIN, 9)); // Set font for the button
		btnAdd.setBounds(284, 275, 90, 16); // Set bounds for the button
		CustomerPane.add(btnAdd); // Add the add to basket button to the customer panel

		
		// Button for logout
		JButton btnLogout = new JButton("Logout"); // Create logout button
		btnLogout.setFont(new Font("Lucida Grande", Font.PLAIN, 9)); // Set font for the button
		btnLogout.setBounds(509, 290, 84, 16); // Set bounds for the button
		CustomerPane.add(btnLogout); // Add the logout button to the customer panel
		btnLogout.addActionListener(new ActionListener() { // Add action listener to the logout button
		    public void actionPerformed(ActionEvent e) { // Define action performed when button is clicked
		        tabbedPane.setSelectedIndex(0);// Switch to the login panel when logout button is clicked
		    	DefaultListModel<String> listModel = new DefaultListModel<>();
		    	BasketList.setModel(listModel);
		    	customer.Cancel(); // Cancel the items in the basket
		       // customer.Cancel(); // Cancel the items in the basket
		        textField_Quantity.setText(null);
		        textField_Search.setText(null);
		        textField_Filter.setText(null);
		        textField_Barcode.setText(null);
		        
		        
		    }
		}); // End of logout button action listener

		// Button for emptying basket
		JButton btnEmpty = new JButton("Empty Basket"); // Create button for emptying basket
		btnEmpty.setFont(new Font("Lucida Grande", Font.PLAIN, 9)); // Set font for the button
		btnEmpty.setBounds(509, 259, 84, 16); // Set bounds for the button
		CustomerPane.add(btnEmpty); // Add the empty basket button to the customer panel
		btnEmpty.addActionListener(new ActionListener() { // Add action listener to the empty basket button
		    public void actionPerformed(ActionEvent e) { // Define action performed when button is clicked
		    	DefaultListModel<String> listModel = new DefaultListModel<>();
		    	BasketList.setModel(listModel);
		        customer.Cancel(); // Cancel the items in the basket
		    }
		}); // End of empty basket button action listener

		// Bounds for empty basket button 
		btnEmpty.setBounds(509, 259, 74, 16); // Set bounds for the button
		CustomerPane.add(btnEmpty); // Add the empty basket button to the customer panel

		// Panel for the Admin section
		JPanel AdminPane = new JPanel(); // Create a new panel for the Admin section
		tabbedPane.addTab("Admin", null, AdminPane, null); // Add the Admin panel to the tabbed pane
		tabbedPane.setEnabledAt(2, false); // Disable the Admin tab initially
		AdminPane.setLayout(null); // Set layout to null for manual component placement

		// DefaultListModel to hold product information for Admin
		DefaultListModel<String> listModel = new DefaultListModel<>(); // Create a DefaultListModel for product information
		ArrayList<String> outputArray = Admin.ViewProduct(); // Retrieve product information from the Admin class
		for (int i = 0; i < outputArray.size(); i++) { // Loop through the product information
		    listModel.addElement(outputArray.get(i)); // Add each product information to the list model
		}

		// DefaultListModel to hold product information for Customer
		DefaultListModel<String> listModel2 = new DefaultListModel<>(); // Create a DefaultListModel for product information
		ArrayList<String> outputArray2 = Customer.ViewProduct(); // Retrieve product information from the Customer class
		for (int i = 0; i < outputArray2.size(); i++) { // Loop through the product information
		    listModel2.addElement(outputArray2.get(i)); // Add each product information to the list model
		}


		// JList for displaying product information for Admin
		JList<String> list_AdminProduct = new JList<>(listModel); // Create JList for displaying product information
		list_AdminProduct.setFont(new Font("Lucida Grande", Font.PLAIN, 9)); // Set font for the JList

		// JList for displaying product information for Customer
		JList<String> ViewCustomerProductList = new JList<>(listModel2); // Create JList for displaying product information
		ViewCustomerProductList.setFont(new Font("Lucida Grande", Font.PLAIN, 9)); // Set font for the JList

		// Action performed when the "Add to basket" button is clicked
		btnAdd.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Check if barcode or quantity is empty
		        if (textField_Barcode.getText().isEmpty() || textField_Quantity.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Barcode or Quantity can't be empty", "Warning", JOptionPane.PLAIN_MESSAGE);
		            return; // Stop further execution if barcode or quantity is empty
		        }
		        int quantity = Integer.parseInt(textField_Quantity.getText()); // Get quantity from text field
		        int barcode = Integer.parseInt(textField_Barcode.getText()); // Get barcode from text field
		        // Check if barcode or quantity is negative
		        if (barcode < 0 || quantity < 0) {
		            JOptionPane.showMessageDialog(null, "Barcode or Quantity can't be negative", "Warning", JOptionPane.PLAIN_MESSAGE);
		            return; // Stop further execution if barcode or quantity is negative
		        }
		        
		        

		        // Check if the product is in stock and can be added to the basket
		        boolean addedToBasket = customer.Add(barcode, quantity);

		        if (addedToBasket) { // If the product is successfully added
		            // Refresh the basket view
		            DefaultListModel<String> listModel = new DefaultListModel<>();
		            ArrayList<String> outputArray = customer.ViewBasket(customer.getBasket());
		            for (int i = 0; i < outputArray.size(); i++) {
		                listModel.addElement(outputArray.get(i));
		            }
		            BasketList.setModel(listModel);
		        } else { // If the product is not in stock
		            JOptionPane.showMessageDialog(null, "Not in Stock", "Warning", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		}); // End of action listener for "Add to basket" button


		
		// Create a JScrollPane to display the list of products for Admin
		JScrollPane scrollPane_AdminProductsList = new JScrollPane(list_AdminProduct);
		scrollPane_AdminProductsList.setBounds(310, 6, 303, 226); // Set bounds for the scroll pane
		AdminPane.add(scrollPane_AdminProductsList); // Add the scroll pane to the Admin panel

		// Create a JScrollPane to display the list of products for Customer
		JScrollPane scrollPane_CustomerProductList = new JScrollPane(ViewCustomerProductList);
		scrollPane_CustomerProductList.setBounds(10, 6, 303, 226); // Set bounds for the scroll pane
		CustomerPane.add(scrollPane_CustomerProductList); // Add the scroll pane to the Customer panel

		// Label for barcode input in Customer section
		JLabel lblNewLabel_4_1 = new JLabel("Barcode:");
		lblNewLabel_4_1.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblNewLabel_4_1.setBounds(378, 253, 46, 16); // Set bounds for the label
		CustomerPane.add(lblNewLabel_4_1); // Add the label to the Customer panel



		// Text field for entering barcode in Customer section
		textField_Barcode = new JTextField();
		textField_Barcode.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		textField_Barcode.setColumns(10);
		textField_Barcode.setBounds(424, 247, 61, 26); // Set bounds for the text field
		CustomerPane.add(textField_Barcode); // Add the text field to the Customer panel
		
		JLabel lbl_Information = new JLabel("Enter Barcode of product and quantity you want and press Add");
		lbl_Information.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lbl_Information.setBounds(321, 191, 298, 16);
		CustomerPane.add(lbl_Information);

		// Labels for keyboard attributes in Admin section
		JLabel lblBarcodeKeyboard = new JLabel("Barcode");
		lblBarcodeKeyboard.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblBarcodeKeyboard.setBounds(6, 13, 61, 9); // Set bounds for the label
		AdminPane.add(lblBarcodeKeyboard); // Add the label to the Admin panel
		// Add other similar labels for other keyboard attributes

		
		// Label for Keyboard attributes in Admin section
		JLabel lblBrandKeyboard = new JLabel("Brand");
		lblBrandKeyboard.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblBrandKeyboard.setBounds(6, 122, 61, 9); // Set bounds for the label
		AdminPane.add(lblBrandKeyboard); // Add the label to the Admin panel

		// Label for Keyboard color in Admin section
		JLabel lblColourKeyboard = new JLabel("Colour");
		lblColourKeyboard.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblColourKeyboard.setBounds(6, 149, 61, 9); // Set bounds for the label
		AdminPane.add(lblColourKeyboard); // Add the label to the Admin panel

		// Label for Keyboard connectivity in Admin section
		JLabel lblConnectivityKeyboard = new JLabel("Connectivity");
		lblConnectivityKeyboard.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblConnectivityKeyboard.setBounds(6, 34, 61, 9); // Set bounds for the label
		AdminPane.add(lblConnectivityKeyboard); // Add the label to the Admin panel

		// Label for Keyboard original cost in Admin section
		JLabel lblOGKeyboardCost = new JLabel("Original Cost");
		lblOGKeyboardCost.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblOGKeyboardCost.setBounds(6, 69, 61, 9); // Set bounds for the label
		AdminPane.add(lblOGKeyboardCost); // Add the label to the Admin panel

		// Label for Keyboard retail price in Admin section
		JLabel lblRPKeyboardCost = new JLabel("Retail Price");
		lblRPKeyboardCost.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblRPKeyboardCost.setBounds(6, 101, 61, 9); // Set bounds for the label
		AdminPane.add(lblRPKeyboardCost); // Add the label to the Admin panel

		// Label for Keyboard type in Admin section
		JLabel lblTypeKeyboard = new JLabel("Type");
		lblTypeKeyboard.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblTypeKeyboard.setBounds(6, 182, 61, 9); // Set bounds for the label
		AdminPane.add(lblTypeKeyboard); // Add the label to the Admin panel

		// Label for Keyboard layout in Admin section
		JLabel lblLayout = new JLabel("Layout");
		lblLayout.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblLayout.setBounds(6, 249, 61, 9); // Set bounds for the label
		AdminPane.add(lblLayout); // Add the label to the Admin panel

		// Label for Keyboard stock count in Admin section
		JLabel lblStockCount = new JLabel("Stock Count");
		lblStockCount.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblStockCount.setBounds(6, 217, 61, 9); // Set bounds for the label
		AdminPane.add(lblStockCount); // Add the label to the Admin panel

		// Label for Mouse barcode in Admin section
		JLabel lblBarcodeMouse = new JLabel("Barcode");
		lblBarcodeMouse.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblBarcodeMouse.setBounds(177, 11, 61, 9); // Set bounds for the label
		AdminPane.add(lblBarcodeMouse); // Add the label to the Admin panel

		// Label for Mouse brand in Admin section
		JLabel lblBrandMouse = new JLabel("Brand");
		lblBrandMouse.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblBrandMouse.setBounds(177, 120, 61, 9); // Set bounds for the label
		AdminPane.add(lblBrandMouse); // Add the label to the Admin panel

		// Label for Mouse color in Admin section
		JLabel lblColourMouse = new JLabel("Colour");
		lblColourMouse.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblColourMouse.setBounds(177, 147, 61, 9); // Set bounds for the label
		AdminPane.add(lblColourMouse); // Add the label to the Admin panel

		// Label for Mouse connectivity in Admin section
		JLabel lblConnectivityMouse = new JLabel("Connectivity");
		lblConnectivityMouse.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblConnectivityMouse.setBounds(163, 32, 61, 9); // Set bounds for the label
		AdminPane.add(lblConnectivityMouse); // Add the label to the Admin panel

		// Label for Mouse original cost in Admin section
		JLabel lblOGMousePrice = new JLabel("Original Cost");
		lblOGMousePrice.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblOGMousePrice.setBounds(177, 67, 61, 9); // Set bounds for the label
		AdminPane.add(lblOGMousePrice); // Add the label to the Admin panel
		// Label for Mouse retail price in Admin section
		JLabel lblRPMousePrice = new JLabel("Retail Price");
		lblRPMousePrice.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblRPMousePrice.setBounds(177, 99, 61, 9); // Set bounds for the label
		AdminPane.add(lblRPMousePrice); // Add the label to the Admin panel

		// Label for Mouse type in Admin section
		JLabel lblTypeMouse = new JLabel("Type");
		lblTypeMouse.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblTypeMouse.setBounds(173, 182, 61, 23); // Set bounds for the label
		AdminPane.add(lblTypeMouse); // Add the label to the Admin panel

		// Label for Mouse buttons in Admin section
		JLabel lblButtons = new JLabel("Buttons");
		lblButtons.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblButtons.setBounds(177, 249, 61, 9); // Set bounds for the label
		AdminPane.add(lblButtons); // Add the label to the Admin panel

		// Label for Mouse stock count in Admin section
		JLabel lblStockCountMouse = new JLabel("Stock Count");
		lblStockCountMouse.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblStockCountMouse.setBounds(177, 217, 61, 9); // Set bounds for the label
		AdminPane.add(lblStockCountMouse); // Add the label to the Admin panel

		
		// Text field for Keyboard barcode in Admin section
		JTextField textField_BarcodeKeyboard = new JTextField();
		textField_BarcodeKeyboard.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		textField_BarcodeKeyboard.setBounds(54, 8, 70, 15); // Set bounds for the text field
		AdminPane.add(textField_BarcodeKeyboard); // Add the text field to the Admin panel
		textField_BarcodeKeyboard.setColumns(10); // Set the number of columns for the text field

		// Text field for Mouse barcode in Admin section
		JTextField textField_BarcodeMouse = new JTextField();
		textField_BarcodeMouse.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		textField_BarcodeMouse.setColumns(10);
		textField_BarcodeMouse.setBounds(223, 6, 70, 15); // Set bounds for the text field
		AdminPane.add(textField_BarcodeMouse); // Add the text field to the Admin panel

		// Text field for Keyboard brand in Admin section
		JTextField textField_BrandKeyboard = new JTextField();
		textField_BrandKeyboard.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		textField_BrandKeyboard.setColumns(10);
		textField_BrandKeyboard.setBounds(41, 122, 70, 15); // Set bounds for the text field
		AdminPane.add(textField_BrandKeyboard); // Add the text field to the Admin panel

		// Text field for Mouse brand in Admin section
		JTextField textField_BrandMouse = new JTextField();
		textField_BrandMouse.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		textField_BrandMouse.setColumns(10);
		textField_BrandMouse.setBounds(213, 120, 70, 15); // Set bounds for the text field
		AdminPane.add(textField_BrandMouse); // Add the text field to the Admin panel

		// Text field for Keyboard colour in Admin section
		JTextField textField_ColourKeyboard = new JTextField();
		textField_ColourKeyboard.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		textField_ColourKeyboard.setColumns(10);
		textField_ColourKeyboard.setBounds(41, 149, 70, 15); // Set bounds for the text field
		AdminPane.add(textField_ColourKeyboard); // Add the text field to the Admin panel

		// Text field for Mouse colour in Admin section
		JTextField textField_ColourMouse = new JTextField();
		textField_ColourMouse.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		textField_ColourMouse.setColumns(10);
		textField_ColourMouse.setBounds(223, 147, 70, 15); // Set bounds for the text field
		AdminPane.add(textField_ColourMouse); // Add the text field to the Admin panel

		// Text field for Keyboard stock count in Admin section
		JTextField textField_StockCountKeyboard = new JTextField();
		textField_StockCountKeyboard.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		textField_StockCountKeyboard.setColumns(10);
		textField_StockCountKeyboard.setBounds(68, 217, 70, 15); // Set bounds for the text field
		AdminPane.add(textField_StockCountKeyboard); // Add the text field to the Admin panel

		// Text field for Mouse stock count in Admin section
		JTextField textField_StockCountMouse = new JTextField();
		textField_StockCountMouse.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		textField_StockCountMouse.setColumns(10);
		textField_StockCountMouse.setBounds(241, 217, 70, 15); // Set bounds for the text field
		AdminPane.add(textField_StockCountMouse); // Add the text field to the Admin panel

		// Text field for number of Mouse buttons in Admin section
		JTextField textField_Buttons = new JTextField();
		textField_Buttons.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		textField_Buttons.setColumns(10);
		textField_Buttons.setBounds(224, 243, 70, 15); // Set bounds for the text field
		AdminPane.add(textField_Buttons); // Add the text field to the Admin panel

		// Text field for Mouse retail price in Admin section
		JTextField textField_RPPriceMouse = new JTextField();
		textField_RPPriceMouse.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		textField_RPPriceMouse.setColumns(10);
		textField_RPPriceMouse.setBounds(241, 99, 70, 15); // Set bounds for the text field
		AdminPane.add(textField_RPPriceMouse); // Add the text field to the Admin panel

		// Text field for Mouse original price in Admin section
		JTextField textField_OGPriceMouse = new JTextField();
		textField_OGPriceMouse.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		textField_OGPriceMouse.setColumns(10);
		textField_OGPriceMouse.setBounds(241, 61, 70, 15); // Set bounds for the text field
		AdminPane.add(textField_OGPriceMouse); // Add the text field to the Admin panel

		// Text field for Keyboard retail price in Admin section
		JTextField textField_RPKeyboard = new JTextField();
		textField_RPKeyboard.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		textField_RPKeyboard.setColumns(10);
		textField_RPKeyboard.setBounds(68, 101, 70, 15); // Set bounds for the text field
		AdminPane.add(textField_RPKeyboard); // Add the text field to the Admin panel

		// Text field for Keyboard original price in Admin section
		JTextField textField_OGPriceKeyboard = new JTextField();
		textField_OGPriceKeyboard.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		textField_OGPriceKeyboard.setColumns(10);
		textField_OGPriceKeyboard.setBounds(79, 69, 70, 15); // Set bounds for the text field
		AdminPane.add(textField_OGPriceKeyboard); // Add the text field to the Admin panel

		
		// Combo box for Keyboard connectivity in Admin section
		JComboBox<ConnectivityType> comboBox_KeyboardConnectivity = new JComboBox();
		comboBox_KeyboardConnectivity.setFont(new Font("Lucida Grande", Font.PLAIN, 9)); // Set font for the combo box
		comboBox_KeyboardConnectivity.setModel(new DefaultComboBoxModel(ConnectivityType.values())); // Set model with ConnectivityType enum values
		comboBox_KeyboardConnectivity.setBounds(68, 34, 84, 15); // Set bounds for the combo box
		AdminPane.add(comboBox_KeyboardConnectivity); // Add the combo box to the Admin panel

		// Combo box for Mouse connectivity in Admin section
		JComboBox<ConnectivityType> comboBox_MouseConnectivity = new JComboBox();
		comboBox_MouseConnectivity.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		comboBox_MouseConnectivity.setModel(new DefaultComboBoxModel(ConnectivityType.values()));
		comboBox_MouseConnectivity.setBounds(223, 30, 84, 15);
		AdminPane.add(comboBox_MouseConnectivity);

		// Combo box for Keyboard type in Admin section
		JComboBox<KeyboardType> comboBox_KeyboardType = new JComboBox();
		comboBox_KeyboardType.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		comboBox_KeyboardType.setModel(new DefaultComboBoxModel(KeyboardType.values()));
		comboBox_KeyboardType.setBounds(50, 179, 102, 26);
		AdminPane.add(comboBox_KeyboardType);

		// Combo box for Mouse type in Admin section
		JComboBox<MouseType> comboBox_MouseType = new JComboBox();
		comboBox_MouseType.setModel(new DefaultComboBoxModel(MouseType.values()));
		comboBox_MouseType.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		comboBox_MouseType.setBounds(205, 187, 106, 15);
		AdminPane.add(comboBox_MouseType);

		// Combo box for Keyboard layout in Admin section
		JComboBox<LayoutType> comboBox_LayoutKeyboard = new JComboBox();
		comboBox_LayoutKeyboard.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		comboBox_LayoutKeyboard.setModel(new DefaultComboBoxModel(LayoutType.values()));
		comboBox_LayoutKeyboard.setBounds(54, 247, 70, 15);
		AdminPane.add(comboBox_LayoutKeyboard);

		
		// Create a button to add a new keyboard to the Admin section
		JButton btnNewButton_5 = new JButton("Add Keyboard");
		btnNewButton_5.setFont(new Font("Lucida Grande", Font.PLAIN, 9)); // Set the font for the button
		btnNewButton_5.setBounds(0, 270, 97, 29); // Set the position and size of the button
		AdminPane.add(btnNewButton_5); // Add the button to the Admin panel

		// Add action listener to the button to handle the "Add Keyboard" functionality
		btnNewButton_5.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Parse and retrieve values from text fields and combo boxes
		            int barcode = Integer.valueOf(textField_BarcodeKeyboard.getText());
		            KeyboardType keyboardType = (KeyboardType) comboBox_KeyboardType.getSelectedItem();
		            String brand = textField_BrandKeyboard.getText();
		            String colour = textField_ColourKeyboard.getText();
		            ConnectivityType connectivityType = (ConnectivityType) comboBox_MouseConnectivity.getSelectedItem();
		            int stock = Integer.valueOf(textField_StockCountKeyboard.getText());
		            BigDecimal retail_price = new BigDecimal(textField_RPKeyboard.getText());
		            BigDecimal price = new BigDecimal(textField_OGPriceKeyboard.getText());


		            // Get the selected layout type from the combo box
		            LayoutType layoutType = (LayoutType) comboBox_LayoutKeyboard.getSelectedItem();

		            // Check for valid input values
		            
		            if (price.scale() != 2) {
		                throw new IllegalArgumentException("Price is not formatted to 2 decimal places");
		            }
		            if (retail_price.scale() != 2) {
		            	throw new IllegalArgumentException("Price is not formatted to 2 decimal places");
		            }
		            if (barcode <= 0) {
		                throw new IllegalArgumentException("Barcode must be a positive integer.");
		            }
		            if (String.valueOf(barcode).length() !=6) {
		            	throw new IllegalArgumentException("Barcode must be six digits long");
		            }
		            if(admin.check_existence(barcode) == true){
		            	throw new IllegalArgumentException("Barcode is already in database");
		            }
		            if (brand.isEmpty()) {
		                throw new IllegalArgumentException("Brand cannot be empty.");
		            }
		            if (colour.isEmpty()) {
		                throw new IllegalArgumentException("Colour cannot be empty.");
		            }
		            if (stock < 0) {
		                throw new IllegalArgumentException("Stock must be a non-negative integer.");
		            }
		            if (layoutType == null) {
		                throw new IllegalArgumentException("Please select a layout type.");
		            }
		            if (price.compareTo(BigDecimal.ZERO) < 0) {
		                throw new IllegalArgumentException("Price must be a non-negative number.");
		            }

		            if (retail_price.compareTo(BigDecimal.ZERO) < 0) {
		                throw new IllegalArgumentException("Price must be a non-negative number.");
		            }

		            // Create a new Keyboard object with the retrieved values
		            Keyboard keyboard = new Keyboard(
		                    barcode,
		                    ProductCategory.KEYBOARD,
		                    keyboardType,
		                    brand,
		                    colour,
		                    connectivityType,
		                    stock,
		                    price.doubleValue(),
		                    retail_price.doubleValue(),
		                    layoutType
		            );

		            // Write the new keyboard to file
		            keyboard.WriteToFile(keyboard);

		            // Update the list of products displayed in the Admin panel
		            DefaultListModel<String> listModel = new DefaultListModel<>();
		            ArrayList<String> outputArray = admin.ViewProduct();
		            for (int i = 0; i < outputArray.size(); i++) {
		                listModel.addElement(outputArray.get(i));
		            }
		            JList<String> list = new JList<>(listModel);
		            list.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		            scrollPane_AdminProductsList.setViewportView(list);
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Invalid numeric input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
		        } catch (IllegalArgumentException ex) {
		            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});



		
		// Create a button to add a new mouse to the Admin section
		JButton btnNewButton_6 = new JButton("Add Mouse");
		btnNewButton_6.setFont(new Font("Lucida Grande", Font.PLAIN, 9)); // Set the font for the button
		btnNewButton_6.setBounds(177, 273, 97, 23); // Set the position and size of the button
		AdminPane.add(btnNewButton_6); // Add the button to the Admin panel

		// Create a button to logout from the Admin section
		JButton btnNewButton_3_1 = new JButton("Logout");
		btnNewButton_3_1.setFont(new Font("Lucida Grande", Font.PLAIN, 9)); // Set the font for the button
		btnNewButton_3_1.setBounds(528, 290, 74, 16); // Set the position and size of the button
		AdminPane.add(btnNewButton_3_1); // Add the button to the Admin panel

		// Add action listener to the "Logout" button to switch to the login tab when clicked
		btnNewButton_3_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        tabbedPane.setSelectedIndex(0); // Switch to the login tab when the "Logout" button is clicked
		        textField_BarcodeMouse.setText(null);
		    	textField_BrandKeyboard.setText(null);
		    	textField_BrandMouse.setText(null);
		    	textField_ColourKeyboard.setText(null);
		    	textField_ColourMouse.setText(null);
		    	textField_StockCountKeyboard.setText(null);
		    	textField_StockCountMouse.setText(null);
		    	textField_Buttons.setText(null);
		    	textField_RPPriceMouse.setText(null);
		    	textField_OGPriceMouse.setText(null);
		    	textField_RPKeyboard.setText(null);
		    	textField_OGPriceKeyboard.setText(null);
		    	textField_BarcodeKeyboard.setText(null);
		    	textField_BarcodeMouse.setText(null);
		        
		    }
		});


		
		
		
		// Add action listener to the "Add Mouse" button to handle mouse addition
		btnNewButton_6.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Parse and retrieve values from text fields and combo boxes
		            int barcode = Integer.valueOf(textField_BarcodeMouse.getText());
		            MouseType keyboardType = (MouseType) comboBox_MouseType.getSelectedItem();
		            String brand = textField_BrandMouse.getText();
		            String colour = textField_ColourMouse.getText();
		            ConnectivityType connectivityType = (ConnectivityType) comboBox_MouseConnectivity.getSelectedItem();
		            int stock = Integer.valueOf(textField_StockCountMouse.getText());
		            BigDecimal price = new BigDecimal(textField_OGPriceMouse.getText());
		            BigDecimal retail_price = new BigDecimal(textField_RPPriceMouse.getText());
		            int buttons = Integer.valueOf(textField_Buttons.getText());
		           
		         // Check for valid input values
		            if (price.scale() != 2) {
		                throw new IllegalArgumentException("Price is not formatted to 2 decimal places");
		            }
		            if (retail_price.scale() != 2) {
		            	throw new IllegalArgumentException("Price is not formatted to 2 decimal places");
		            }
		            if (barcode <= 0) {
		                throw new IllegalArgumentException("Product ID must be a positive integer.");
		            }
		            if(admin.check_existence(barcode) == true){
		            	throw new IllegalArgumentException("Barcode is already in database");
		            }
		            if (String.valueOf(barcode).length() !=6) {
		            	throw new IllegalArgumentException("Barcode must be six digits long");
		            }
		            if (brand.isEmpty()) {
		                throw new IllegalArgumentException("Brand cannot be empty.");
		            }
		            if (colour.isEmpty()) {
		                throw new IllegalArgumentException("Colour cannot be empty.");
		            }
		            if (stock < 0) {
		                throw new IllegalArgumentException("Stock must be a non-negative integer.");
		            }
		            if (price.compareTo(BigDecimal.ZERO) < 0) {
		                throw new IllegalArgumentException("Price must be a non-negative number.");
		            }

		            if (retail_price.compareTo(BigDecimal.ZERO) < 0) {
		                throw new IllegalArgumentException("Price must be a non-negative number.");
		            }

		            if (buttons <= 0) {
		                throw new IllegalArgumentException("Buttons must be a positive number.");
		            }
		          

		            // Create a new Mouse object with the retrieved values
		            Mouse mouse = new Mouse(
		                    barcode,
		                    ProductCategory.MOUSE,
		                    keyboardType,
		                    brand,
		                    colour,
		                    connectivityType,
		                    stock,
		                    price.doubleValue(),
		                    retail_price.doubleValue(),
		                    buttons
		            );

		            // Write the new mouse to file
		            mouse.WriteToFile(mouse);

		            // Update the admin product list view
		            DefaultListModel<String> listModel = new DefaultListModel<>();
		            ArrayList<String> outputArray = admin.ViewProduct();
		            for (int i = 0; i < outputArray.size(); i++) {
		                listModel.addElement(outputArray.get(i));
		            }
		            JList<String> list = new JList<>(listModel);
		            list.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		            scrollPane_AdminProductsList.setViewportView(list);
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Invalid numeric input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
		        } catch (IllegalArgumentException ex) {
		            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }

		    }
		});

		
		
		

}
}