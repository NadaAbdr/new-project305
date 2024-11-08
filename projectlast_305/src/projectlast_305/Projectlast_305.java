
package projectlast_305;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
public class Projectlast_305 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
    Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter your name:");
        String name = scanner.nextLine();

        System.out.print("Enter your email:");
        String email = scanner.nextLine();

        // Input phone number with validation for exactly 10 digits
        String phone;
        while (true) {
            System.out.print("Enter your phone number (10 digits):");
            phone = scanner.nextLine();
            if (isValidPhoneNumber(phone)) {
                break;
            } else {
                System.out.print("Invalid phone number. Please enter exactly 10 digits.");
            }
        }

        System.out.print("Enter your age:");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter visit date (YYYY-MM-DD):");
        String visitDate = scanner.nextLine();

        // Database connection setup
        String url = "jdbc:mysql://localhost:3306/museum_db";
        String user = "root";  
        String password = "Njood_2030";  
        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection(url, user, password);

            // Prepare SQL statement to insert data
            String sql = "INSERT INTO visitors (name, email, phone, age, visit_date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, phone);
            statement.setInt(4, age);
            statement.setString(5, visitDate);

            // Execute the query
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Visit registered successfully!");
            }

            // Close the connection
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    // Method to validate phone number to exactly 10 digits
    public static boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\d{10}");
    }

}