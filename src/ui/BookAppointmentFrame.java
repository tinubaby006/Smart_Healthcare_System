package ui;

import javax.swing.*;
import java.sql.*;
import db.DBConnection;
import service.AppointmentService;

public class BookAppointmentFrame extends JFrame {

    private JComboBox<String> doctorBox;
    private JTextField dateField;
    private JTextField reasonField;

    private int patientId;

    public BookAppointmentFrame(int patientId) {

        this.patientId = patientId;

        setTitle("Book Appointment");

        JLabel docLabel = new JLabel("Doctor:");
        docLabel.setBounds(50, 50, 100, 30);

        doctorBox = new JComboBox<>();
        doctorBox.setBounds(150, 50, 150, 30);

        loadDoctors(); // 🔥 load doctors

        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateLabel.setBounds(50, 100, 150, 30);

        dateField = new JTextField();
        dateField.setBounds(200, 100, 120, 30);

        JLabel reasonLabel = new JLabel("Reason:");
        reasonLabel.setBounds(50, 150, 100, 30);

        reasonField = new JTextField();
        reasonField.setBounds(150, 150, 150, 30);

        JButton bookBtn = new JButton("Book");
        bookBtn.setBounds(100, 220, 120, 30);

        bookBtn.addActionListener(e -> bookAppointment());

        add(docLabel);
        add(doctorBox);
        add(dateLabel);
        add(dateField);
        add(reasonLabel);
        add(reasonField);
        add(bookBtn);

        setSize(400, 320);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // 🔹 Load doctors into dropdown
    private void loadDoctors() {
        try (Connection con = DBConnection.getConnection()) {

            String query = "SELECT doctor_id, name FROM doctors";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                doctorBox.addItem(rs.getInt("doctor_id") + " - " + rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 Book appointment
    private void bookAppointment() {
        try {
            String selected = (String) doctorBox.getSelectedItem();

            int doctorId = Integer.parseInt(selected.split(" - ")[0]);
            String date = dateField.getText();
            String reason = reasonField.getText();

            if (AppointmentService.bookAppointment(patientId, doctorId, date, reason)) {
                JOptionPane.showMessageDialog(this, "Appointment Requested!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error!");
        }
    }
}