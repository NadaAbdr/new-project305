// njood
package project_305; 

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Project_305 {
      public static void main(String[] args) {
     // Create the main frame for the museum management system
        JFrame frame = new JFrame("Museum Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);

        // Set the layout to null for absolute positioning
        frame.setLayout(null);

        // Create the main content panel for the background image with transparency
        JPanel contentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the background image
                ImageIcon backgroundImage = new ImageIcon("C:\\Users\\Nada2\\OneDrive - King Abdullaziz University\\Desktop\\University\\8 term\\CPIT-305\\project_305\\project_305\\Project-CPIT-305\\project_305\\Museum.jpg"); // Update with your image path
                
                // Set transparency (0.0f is fully transparent, 1.0f is fully opaque)
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f)); // 70% opacity for image
                
                // Draw the background image
                g2d.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                g2d.dispose();
            }
        };
        
        contentPanel.setBounds(0, 0, 900, 600);
        contentPanel.setLayout(null);
        contentPanel.setOpaque(false); // Make the panel transparent

        // Add title for content
        JLabel titleLabel = new JLabel("Welcome to the Museum Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 35));
        titleLabel.setForeground(Color.black);
        titleLabel.setBounds(150, 10, 600, 50);
        contentPanel.add(titleLabel);

        // Create a panel for buttons (horizontally aligned)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Horizontal alignment with spacing
        buttonPanel.setOpaque(false); // Make the button panel transparent

        // Define a button color
        Color buttonColor = new Color(210, 181, 138);  // Sand color
        
        // Create and customize buttons
        JButton registerButton = createButton("Registration / Log In", buttonColor);
        JButton exhibitionsButton = createButton("Exhibitions", buttonColor);
        JButton eventsButton = createButton("Events", buttonColor);

        // Add buttons to the button panel
        buttonPanel.add(registerButton);
        buttonPanel.add(exhibitionsButton);
        buttonPanel.add(eventsButton);

        // Set the button panel's position in the content panel
        buttonPanel.setBounds(150, 350, 600, 50); // Adjust the y coordinate to place the buttons lower
        contentPanel.add(buttonPanel); // Add button panel to content panel

        // Create a new panel for additional buttons (Visitor Information and Contact Us)
        JPanel additionalButtonPanel = new JPanel();
        additionalButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Horizontal alignment with spacing
        additionalButtonPanel.setOpaque(false); // Make the button panel transparent
        
        // Create and customize additional buttons
        JButton visitorInfoButton = createButton("Visitor Information", buttonColor);
        JButton contactButton = createButton("Contact Us", buttonColor);

        // Add additional buttons to the additional button panel
        additionalButtonPanel.add(visitorInfoButton);
        additionalButtonPanel.add(contactButton);

        // Set the additional button panel's position in the content panel
        additionalButtonPanel.setBounds(150, 420, 600, 50); // Position it below the first panel
        contentPanel.add(additionalButtonPanel); // Add additional button panel to content panel

        // Add action listener to the registration/login button to open the login form
        registerButton.addActionListener(e -> openLoginForm());

        // Add the content panel to the frame
        frame.add(contentPanel);
        frame.setVisible(true); // Make the frame visible
    }

    // Method to create buttons with specified text and color
    private static JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.black);
        button.setBorderPainted(false); // Remove the white frame
        button.setFocusPainted(false); // Remove focus border
        button.setPreferredSize(new Dimension(180, 40)); // Set a preferred size for buttons
        return button;
    }

    // Method to open the login form
    private static void openLoginForm() {
        // Create the main frame for login
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 300);
        loginFrame.setLayout(null); // Use null layout for absolute positioning

        // Create a panel for background with transparency
        JPanel loginPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("C:\\Users\\Nada2\\OneDrive - King Abdullaziz University\\Desktop\\University\\8 term\\CPIT-305\\project_305\\project_305\\Project-CPIT-305\\project_305\\Museum.jpg"); // Update with your image path
                
                // Set transparency for background image
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f)); // 70% opacity for image
                
                g2d.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                g2d.dispose();
            }
        };
        loginPanel.setBounds(0, 0, 400, 300);
        loginPanel.setLayout(null); // Set layout to null for custom positioning
        loginPanel.setOpaque(false); // Make the login panel transparent

        // Create a title label
        JLabel titleLabel = new JLabel("Login to Museum System", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setForeground(Color.black);
        titleLabel.setBounds(50, 20, 300, 30); // Position title
        loginPanel.add(titleLabel);

        // Create user label and text field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.BLACK);
        userLabel.setBounds(50, 80, 100, 25);
        loginPanel.add(userLabel);

        JTextField userText = new JTextField();
        userText.setBounds(150, 80, 200, 25);
        loginPanel.add(userText);

        // Create password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 120, 100, 25);
        loginPanel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(150, 120, 200, 25);
        loginPanel.add(passwordText);

        // Create login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 180, 100, 30);
        loginButton.setBackground(new Color(210, 181, 138)); // Sand color
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);

        // Action listener for login button
        loginButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());
            JOptionPane.showMessageDialog(loginFrame, "Login Successful for " + username);
        });

        loginPanel.add(loginButton); // Add login button to panel

        // Add the panel to the frame
        loginFrame.add(loginPanel);
        loginFrame.setLocationRelativeTo(null); // Center the frame on the screen
        loginFrame.setVisible(true); // Make the frame visible
    }
}