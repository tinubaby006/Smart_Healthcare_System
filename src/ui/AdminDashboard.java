package ui;

import javax.swing.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {
        setTitle("Admin Dashboard");

        JLabel title = new JLabel("Admin Dashboard");
        title.setBounds(90, 20, 200, 30);

        JButton addPatient = new JButton("Add Patient");
        addPatient.setBounds(80, 80, 150, 30);

        JButton viewPatients = new JButton("View Patients");
        viewPatients.setBounds(80, 130, 150, 30);

        JButton manageAppointmentsBtn = new JButton("Manage Appointments");
        manageAppointmentsBtn.setBounds(80, 180, 180, 30);

        JButton logout = new JButton("Logout");
        logout.setBounds(80, 230, 150, 30);
        // Actions

        addPatient.addActionListener(e -> {
            new AddPatientFrame();
        });

        manageAppointmentsBtn.addActionListener(e -> {
            new ManageAppointmentsFrame();
        });

        viewPatients.addActionListener(e -> {
            new ViewPatientsFrame();
        });

        logout.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });

        add(title);
        add(addPatient);
        add(viewPatients);
        add(manageAppointmentsBtn);
        add(logout);

        setSize(300, 300);
        setLayout(null);
        setVisible(true);
    }
}