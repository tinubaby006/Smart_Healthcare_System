package ui;

import javax.swing.*;

public class DoctorDashboard extends JFrame {

    private int doctorId;

    public DoctorDashboard(int doctorId) {
        this.doctorId = doctorId;

        setTitle("Doctor Dashboard");

        JLabel title = new JLabel("Doctor Dashboard");
        title.setBounds(100, 20, 200, 30);

        JButton viewAppBtn = new JButton("View Appointments");
        viewAppBtn.setBounds(80, 80, 180, 30);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(80, 180, 180, 30);

        // 🔹 BUTTON ACTIONS

        viewAppBtn.addActionListener(e -> {
            new DoctorAppointmentsFrame(doctorId);
        });

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        add(title);
        add(viewAppBtn);
        add(logoutBtn);

        setSize(350, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
