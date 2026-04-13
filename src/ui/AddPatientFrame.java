package ui;

import model.Patient;
import service.PatientService;

import javax.swing.*;
import java.awt.*;

public class AddPatientFrame extends JFrame {

    private JTextField nameField, ageField, genderField,
            phoneField, addressField, bloodGroupField;

    public AddPatientFrame() {

        setTitle("Add Patient");
        setSize(400, 450);
        setLayout(new GridLayout(7, 2, 10, 10));
        setLocationRelativeTo(null); // center screen
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Fields
        nameField = new JTextField();
        ageField = new JTextField();
        genderField = new JTextField();
        phoneField = new JTextField();
        addressField = new JTextField();
        bloodGroupField = new JTextField();

        // UI
        add(new JLabel("Name:"));
        add(nameField);

        add(new JLabel("Age:"));
        add(ageField);

        add(new JLabel("Gender:"));
        add(genderField);

        add(new JLabel("Phone:"));
        add(phoneField);

        add(new JLabel("Address:"));
        add(addressField);

        add(new JLabel("Blood Group:"));
        add(bloodGroupField);

        JButton addBtn = new JButton("Add Patient");
        addBtn.addActionListener(e -> addPatient());

        add(new JLabel());
        add(addBtn);

        setVisible(true);
    }

    private void addPatient() {
        try {
            // Get values
            String name = nameField.getText().trim();
            String ageText = ageField.getText().trim();
            String gender = genderField.getText().trim();
            String phone = phoneField.getText().trim();
            String address = addressField.getText().trim();
            String bloodGroup = bloodGroupField.getText().trim();

            // 🔴 Basic validation
            if (name.isEmpty() || ageText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and Age are required!");
                return;
            }

            int age = Integer.parseInt(ageText);

            // Create Patient object
            Patient p = new Patient(name, age, gender, phone, address, bloodGroup);

            String[] creds = PatientService.addPatient(p);

            if (creds != null) {
                JOptionPane.showMessageDialog(this,
                        "Patient Added!\nUsername: " + creds[0] +
                                "\nPassword: " + creds[1]);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add patient!");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Age must be a number!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred!");
        }
    }
}