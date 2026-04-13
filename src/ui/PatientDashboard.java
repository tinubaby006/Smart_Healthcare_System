package ui;

import javax.swing.*;

public class PatientDashboard extends JFrame {

    private int patientId;

    public PatientDashboard(int patientId) {

        this.patientId = patientId;

        setTitle("Patient Dashboard");

        JLabel title = new JLabel("Patient Dashboard");
        title.setBounds(100, 20, 200, 30);

        JButton bookBtn = new JButton("Book Appointment");
        bookBtn.setBounds(80, 80, 180, 30);

        JButton viewAppBtn = new JButton("My Appointments");
        viewAppBtn.setBounds(80, 130, 180, 30);

        JButton historyBtn = new JButton("My Medical History");
        historyBtn.setBounds(80, 180, 180, 30);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(80, 230, 180, 30);

        // 🔹 BUTTON ACTIONS

        bookBtn.addActionListener(e -> {
            new BookAppointmentFrame(patientId);
        });

        viewAppBtn.addActionListener(e -> {
            new PatientAppointmentsFrame(patientId);
        });

        historyBtn.addActionListener(e -> {
            new PatientHistoryFrame(patientId, "My History");
        });

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        add(title);
        add(bookBtn);
        add(viewAppBtn);
        add(historyBtn);
        add(logoutBtn);

        setSize(350, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}