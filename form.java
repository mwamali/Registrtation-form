import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegistrationForm extends JFrame {
    private JTextField idField, nameField, addressField, contactField;
    private JRadioButton maleRadio, femaleRadio;
    private JButton exitButton, registerButton;
    private JTable dataTable;
    private DefaultTableModel tableModel;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/formentry";
    private static final String DB_USER = "brian";
    private static final String DB_PASSWORD = "password";

    public RegistrationForm() {
        setTitle("Registration Form");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        JPanel formPanel = new JPanel(new GridLayout(7, 2));
        JPanel tablePanel = new JPanel(new BorderLayout());

        // Form components
        formPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        formPanel.add(idField);

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        formPanel.add(genderPanel);

        formPanel.add(new JLabel("Address:"));
        addressField = new JTextField();
        formPanel.add(addressField);

        formPanel.add(new JLabel("Contact:"));
        contactField = new JTextField();
        formPanel.add(contactField);

        exitButton = new JButton("Exit");
        registerButton = new JButton("Register");
        formPanel.add(exitButton);
        formPanel.add(registerButton);

        // Table
        String[] columnNames = {"ID", "Name", "Gender", "Address", "Contact"};
        tableModel = new DefaultTableModel(columnNames, 0);
        dataTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(dataTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Add panels to frame
        add(formPanel);
        add(tablePanel);

        // Button listeners
        exitButton.addActionListener(e -> System.exit(0));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        setVisible(true);
    }

    private void registerUser() {
        String id = idField.getText();
        String name = nameField.getText();
        String gender = maleRadio.isSelected() ? "Male" : "Female";
        String address = addressField.getText();
        String contact = contactField.getText();

        // Insert into database
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO users (id, name, gender, address, contact) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, gender);
            pstmt.setString(4, address);
            pstmt.setString(5, contact);
            pstmt.executeUpdate();

            // Add to table
            tableModel.addRow(new Object[]{id, name, gender, address, contact});

            // Clear form fields
            idField.setText("");
            nameField.setText("");
            genderGroup.clearSelection();
            addressField.setText("");
            contactField.setText("");

            JOptionPane.showMessageDialog(this, "User registered successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error registering user: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegistrationForm();
            }
        });
    }
}