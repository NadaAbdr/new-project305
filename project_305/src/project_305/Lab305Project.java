package project_305;
//f
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;

public class Lab305Project {

    public static void main(String[] args) {
        // Create the main frame
        JFrame mainFrame = new JFrame("Museum Detector System");
        mainFrame.setSize(600, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);  // Use null layout for absolute positioning

        // Create a content panel with a background image and transparency
        JPanel contentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the background image using a relative path (Ensure the image is in the project directory)
                ImageIcon backgroundImage = new ImageIcon("Museum.jpg");
                Graphics2D g2d = (Graphics2D) g.create();
                // Set transparency (0.0f is fully transparent, 1.0f is fully opaque)
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f)); // 70% opacity
                g2d.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                g2d.dispose();
            }
        };
        contentPanel.setBounds(0, 0, mainFrame.getWidth(), mainFrame.getHeight());  // Set panel size to match frame

        // Set the content panel to the frame
        mainFrame.setContentPane(contentPanel);
        contentPanel.setLayout(null);  // Use null layout to place components manually

        // Define a button color
        Color buttonColor = new Color(210, 181, 138);  // Sand color

        // Create buttons for navigation and set their properties
        JButton exhibitionsButton = new JButton("Exhibitions");
        exhibitionsButton.setBounds(100, 200, 150, 50);
        exhibitionsButton.setBackground(buttonColor);
        exhibitionsButton.setForeground(Color.black);
        exhibitionsButton.setBorderPainted(false);
        exhibitionsButton.setFocusPainted(false);

        JButton eventsButton = new JButton("Events");
        eventsButton.setBounds(300, 200, 150, 50);
        eventsButton.setBackground(buttonColor);
        eventsButton.setForeground(Color.black);
        eventsButton.setBorderPainted(false);
        eventsButton.setFocusPainted(false);

        // Add action listeners to the buttons
        exhibitionsButton.addActionListener(e -> showExhibitionsFrame());
        eventsButton.addActionListener(e -> showEventsFrame());

        // Add buttons to the content panel
        contentPanel.add(exhibitionsButton);
        contentPanel.add(eventsButton);

        // Set the frame visible
        mainFrame.setVisible(true);
    }

    private static void showExhibitionsFrame() {
        JFrame exhibitionsFrame = new JFrame("Exhibitions");
        exhibitionsFrame.setSize(600, 400);
        exhibitionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        exhibitionsFrame.setLayout(new BorderLayout());

        // Define a button color
        Color buttonColor = new Color(210, 181, 138);  // Sand color

        // Create table model for exhibitions
        String[] columns = {"Exhibition Name", "Date", "Time", "Location", "Description", "Capacity", "Available Tickets"};
        Object[][] data = {
            {"Saudi Art Exhibition", "2024-10-01", "10:00 AM", "Riyadh", "A showcase of contemporary Saudi art.", 100, 50},
            {"History of Saudi Arabia", "2024-10-15", "11:00 AM", "Jeddah", "Explore the rich history of Saudi Arabia.", 150, 100},
            {"Science and Technology Expo", "2024-10-20", "12:00 PM", "Dhahran", "Discover innovative technologies and their impacts.", 200, 150}
        };
        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable exhibitionTable = new JTable(model);

        // Custom cell renderer for the event table
        exhibitionTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                // Set alternating row colors
                cell.setBackground(row % 2 == 0 ? new Color(255, 255, 224) : new Color(224, 255, 255));
                cell.setForeground(Color.black); // Set text color
                return cell;
            }
        });

        // Create buttons and set color
        JButton viewDetailsButton = new JButton("View Details");
        viewDetailsButton.setBackground(buttonColor);
        viewDetailsButton.setForeground(Color.WHITE);
        viewDetailsButton.setBorderPainted(false);
        viewDetailsButton.setFocusPainted(false);

        JButton reserveTicketButton = new JButton("Reserve Ticket");
        reserveTicketButton.setBackground(buttonColor);
        reserveTicketButton.setForeground(Color.WHITE);
        reserveTicketButton.setBorderPainted(false);
        reserveTicketButton.setFocusPainted(false);

        JButton trackLocationButton = new JButton("Track Location");
        trackLocationButton.setBackground(buttonColor);
        trackLocationButton.setForeground(Color.WHITE);
        trackLocationButton.setBorderPainted(false);
        trackLocationButton.setFocusPainted(false);

        // Add action listeners
        viewDetailsButton.addActionListener(e -> {
            int selectedRow = exhibitionTable.getSelectedRow();
            if (selectedRow != -1) {
                StringBuilder details = new StringBuilder();
                for (int i = 0; i < columns.length; i++) {
                    details.append(columns[i]).append(": ")
                            .append(exhibitionTable.getValueAt(selectedRow, i)).append("\n");
                }
                showDetailsFrame("Exhibition Details", details.toString());
            } else {
                JOptionPane.showMessageDialog(exhibitionsFrame, "Please select an exhibition.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        reserveTicketButton.addActionListener(e -> {
            int selectedRow = exhibitionTable.getSelectedRow();
            if (selectedRow != -1) {
                int availableTickets = (int) exhibitionTable.getValueAt(selectedRow, 6);
                if (availableTickets > 0) {
                    exhibitionTable.setValueAt(availableTickets - 1, selectedRow, 6);
                    JOptionPane.showMessageDialog(exhibitionsFrame, "Ticket reserved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(exhibitionsFrame, "No tickets available!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(exhibitionsFrame, "Please select an exhibition.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        trackLocationButton.addActionListener(e -> {
            int selectedRow = exhibitionTable.getSelectedRow();
            if (selectedRow != -1) {
                String location = (String) exhibitionTable.getValueAt(selectedRow, 3);
                showLocationFrame(location);
            } else {
                JOptionPane.showMessageDialog(exhibitionsFrame, "Please select an exhibition.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Add components to the frame
        exhibitionsFrame.add(new JScrollPane(exhibitionTable), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewDetailsButton);
        buttonPanel.add(reserveTicketButton);
        buttonPanel.add(trackLocationButton);
        exhibitionsFrame.add(buttonPanel, BorderLayout.SOUTH);
        exhibitionsFrame.setVisible(true);
    }

    private static void showEventsFrame() {
        JFrame eventsFrame = new JFrame("Events");
        eventsFrame.setSize(600, 400);
        eventsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        eventsFrame.setLayout(new BorderLayout());

        // Define a button color
        Color buttonColor = new Color(210, 181, 138);  // Sand color

        // Create table model for events
        String[] columns = {"Event Name", "Date", "Time", "Location", "Description", "Capacity", "Available Tickets"};
        Object[][] data = {
            {"Saudi National Day Celebration", "2024-09-23", "5:00 PM", "King Abdulaziz Park, Riyadh", "A grand celebration of Saudi National Day.", 500, 300},
            {"International Book Fair", "2024-11-01", "9:00 AM", "Jeddah", "A fair showcasing local and international books.", 1000, 700},
            {"Cultural Festival", "2024-10-10", "3:00 PM", "Dhahran", "Explore Saudi culture through art and food.", 300, 150}
        };
        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable eventTable = new JTable(model);

        // Custom cell renderer for the event table
        eventTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                // Set alternating row colors
                cell.setBackground(row % 2 == 0 ? new Color(255, 255, 224) : new Color(224, 255, 255));
                cell.setForeground(Color.black); // Set text color
                return cell;
            }
        });

        // Create buttons and set color
        JButton viewDetailsButton = new JButton("View Details");
        viewDetailsButton.setBackground(buttonColor);
        viewDetailsButton.setForeground(Color.WHITE);
        viewDetailsButton.setBorderPainted(false);
        viewDetailsButton.setFocusPainted(false);

        JButton reserveTicketButton = new JButton("Reserve Ticket");
        reserveTicketButton.setBackground(buttonColor);
        reserveTicketButton.setForeground(Color.WHITE);
        reserveTicketButton.setBorderPainted(false);
        reserveTicketButton.setFocusPainted(false);

        JButton trackLocationButton = new JButton("Track Location");
        trackLocationButton.setBackground(buttonColor);
        trackLocationButton.setForeground(Color.WHITE);
        trackLocationButton.setBorderPainted(false);
        trackLocationButton.setFocusPainted(false);

        // Add action listeners
        viewDetailsButton.addActionListener(e -> {
            int selectedRow = eventTable.getSelectedRow();
            if (selectedRow != -1) {
                StringBuilder details = new StringBuilder();
                for (int i = 0; i < columns.length; i++) {
                    details.append(columns[i]).append(": ")
                            .append(eventTable.getValueAt(selectedRow, i)).append("\n");
                }
                showDetailsFrame("Event Details", details.toString());
            } else {
                JOptionPane.showMessageDialog(eventsFrame, "Please select an event.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        reserveTicketButton.addActionListener(e -> {
            int selectedRow = eventTable.getSelectedRow();
            if (selectedRow != -1) {
                int availableTickets = (int) eventTable.getValueAt(selectedRow, 6);
                if (availableTickets > 0) {
                    eventTable.setValueAt(availableTickets - 1, selectedRow, 6);
                    JOptionPane.showMessageDialog(eventsFrame, "Ticket reserved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(eventsFrame, "No tickets available!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(eventsFrame, "Please select an event.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        trackLocationButton.addActionListener(e -> {
            int selectedRow = eventTable.getSelectedRow();
            if (selectedRow != -1) {
                String location = (String) eventTable.getValueAt(selectedRow, 3);
                showLocationFrame(location);
            } else {
                JOptionPane.showMessageDialog(eventsFrame, "Please select an event.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Add components to the frame
        eventsFrame.add(new JScrollPane(eventTable), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewDetailsButton);
        buttonPanel.add(reserveTicketButton);
        buttonPanel.add(trackLocationButton);
        eventsFrame.add(buttonPanel, BorderLayout.SOUTH);
        eventsFrame.setVisible(true);
    }

    private static void showDetailsFrame(String title, String details) {
        JFrame detailsFrame = new JFrame(title);
        detailsFrame.setSize(400, 300);
        detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea detailsTextArea = new JTextArea(details);
        detailsTextArea.setWrapStyleWord(true);
        detailsTextArea.setLineWrap(true);
        detailsTextArea.setEditable(false);

        detailsFrame.add(new JScrollPane(detailsTextArea));
        detailsFrame.setVisible(true);
    }

    private static void showLocationFrame(String location) {
        JFrame locationFrame = new JFrame("Location Tracker");
        locationFrame.setSize(400, 300);
        locationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel locationLabel = new JLabel("Tracking Location: " + location);
        locationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        locationFrame.add(locationLabel);
        locationFrame.setVisible(true);
    }
}
