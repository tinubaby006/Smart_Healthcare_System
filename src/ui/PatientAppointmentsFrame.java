package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import service.AppointmentService;

public class PatientAppointmentsFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

    public PatientAppointmentsFrame(int patientId) {

        setTitle("My Appointments");

        String[] cols = { "Appointment ID", "Doctor", "Date", "Status", "Reason" };
        model = new DefaultTableModel(cols, 0);

        table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 20, 500, 250);

        loadData(patientId);

        add(pane);

        setSize(550, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadData(int patientId) {
        try {
            model.setRowCount(0);

            ResultSet rs = AppointmentService.getAppointmentsByPatient(patientId);

            while (rs.next()) {
                model.addRow(new Object[] {
                        rs.getInt("appointment_id"),
                        rs.getString("doctor"),
                        rs.getString("appointment_date"),
                        rs.getString("status"),
                        rs.getString("reason")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}