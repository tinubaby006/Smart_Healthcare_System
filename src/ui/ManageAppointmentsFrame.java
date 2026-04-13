package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import service.AppointmentService;

public class ManageAppointmentsFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ManageAppointmentsFrame() {

        setTitle("Manage Appointments");

        String[] cols = {
                "ID", "Patient", "Doctor", "Date", "Status", "Reason"
        };

        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 20, 550, 250);

        JButton approveBtn = new JButton("Approve");
        approveBtn.setBounds(100, 280, 120, 30);

        JButton rejectBtn = new JButton("Reject");
        rejectBtn.setBounds(250, 280, 120, 30);

        loadPendingData(); // only pending

        // ✅ APPROVE
        approveBtn.addActionListener(e -> updateStatus("Approved"));

        // ❌ REJECT
        rejectBtn.addActionListener(e -> updateStatus("Rejected"));

        add(pane);
        add(approveBtn);
        add(rejectBtn);

        setSize(600, 370);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadPendingData() {
        try {
            model.setRowCount(0);

            ResultSet rs = AppointmentService.getPendingAppointments();

            while (rs.next()) {
                model.addRow(new Object[] {
                        rs.getInt("appointment_id"),
                        rs.getString("patient"),
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

    private void updateStatus(String status) {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select an appointment");
            return;
        }

        int id = (int) model.getValueAt(row, 0);

        if (AppointmentService.updateStatus(id, status)) {
            JOptionPane.showMessageDialog(this, status + "!");
            loadPendingData(); // refresh
        }
    }
}
