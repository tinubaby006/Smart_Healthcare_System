package ui;

import javax.swing.*;

import service.DoctorService;
import service.LoginService;
import service.PatientService;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        setTitle("Login");

        JTextField user = new JTextField();
        user.setBounds(50, 50, 150, 30);

        JPasswordField pass = new JPasswordField();
        pass.setBounds(50, 100, 150, 30);

        JButton login = new JButton("Login");
        login.setBounds(50, 150, 150, 30);

        login.addActionListener(e -> {

            int userId = LoginService.login(
                    user.getText(),
                    new String(pass.getPassword()));

            if (userId == -1) {
                JOptionPane.showMessageDialog(this, "Invalid Login");
                return;
            }

            String role = LoginService.getRole(userId);

            if (role.equals("admin")) {
                new AdminDashboard();
            } else if (role.equals("patient")) {
                int patientId = PatientService.getPatientIdByUserId(userId);
                new PatientDashboard(patientId);
            } else if (role.equals("doctor")) {
                int doctorId = DoctorService.getDoctorIdByUserId(userId);
                new DoctorDashboard(doctorId);

            }

            dispose();
        });

        add(user);
        add(pass);
        add(login);

        setSize(300, 300);
        setLayout(null);
        setVisible(true);
    }
}