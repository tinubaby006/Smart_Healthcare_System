package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import service.AppointmentService;

public class DoctorAppointmentsFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

    public DoctorAppointmentsFrame(int doctorId) {

        setTitle("Doctor Appointments");

        String[] cols = { "Appointment ID", "Patient ID", "Patient Name", "Date", "Reason" };
        model = new DefaultTableModel(cols, 0);

        table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 20, 500, 220);

        JButton hisBtn = new JButton("View Medical History");
        hisBtn.setBounds(140, 260, 180, 30);

        JButton addHisBtn = new JButton("Add Medical History");
        addHisBtn.setBounds(330, 260, 180, 30);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(20, 260, 100, 30);

        // 🔹 LOAD DATA
        loadData(doctorId);

        // 🔹 BUTTON ACTIONS
        backBtn.addActionListener(e -> {
            dispose();
            new DoctorDashboard(doctorId);
        });

        hisBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select an appointment");
                return;
            }

            int patientId = (int) model.getValueAt(row, 1);
            String patient = (String) model.getValueAt(row, 2);

            new PatientHistoryFrame(patientId, patient);
        });

        addHisBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select an appointment");
                return;
            }

            int patientId = (int) model.getValueAt(row, 1);
            String patient = (String) model.getValueAt(row, 2);

            new AddMedicalHistoryFrame(patientId, patient);
        });

        add(pane);
        add(backBtn);
        add(hisBtn);
        add(addHisBtn); // ✅ FIXED

        setSize(550, 330);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadData(int doctorId) {
        try {
            model.setRowCount(0);

            ResultSet rs = AppointmentService.getAppointmentsByDoctor(doctorId);

            while (rs.next()) {
                model.addRow(new Object[] {
                        rs.getInt("appointment_id"),
                        rs.getInt("patient_id"),
                        rs.getString("patient_name"),
                        rs.getString("appointment_date"),
                        rs.getString("reason")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}