package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import service.PatientService;

public class ViewPatientsFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ViewPatientsFrame() {
        setTitle("View Patients");

        String[] cols = {
                "Patient ID", "User ID", "Name", "Age",
                "Gender", "Phone", "Address", "Blood Group"
        };

        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 40, 450, 200);

        JTextField searchField = new JTextField();
        searchField.setBounds(20, 10, 200, 25);

        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(230, 10, 100, 25);

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(50, 260, 100, 30);

        JButton updateBtn = new JButton("Update");
        updateBtn.setBounds(200, 260, 100, 30);

        JButton historyBtn = new JButton("View History");
        historyBtn.setBounds(350, 260, 120, 30);

        loadData();

        // 🔍 SEARCH
        searchBtn.addActionListener(e -> {
            model.setRowCount(0);

            try {
                ResultSet rs = PatientService.searchPatients(searchField.getText());

                while (rs.next()) {
                    model.addRow(new Object[] {
                            rs.getInt("patient_id"),
                            rs.getInt("user_id"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("gender"),
                            rs.getString("phone"),
                            rs.getString("address"),
                            rs.getString("blood_group")
                    });
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // 🗑 DELETE
        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a row");
                return;
            }

            int id = (int) model.getValueAt(row, 0);

            if (PatientService.deletePatient(id)) {
                model.removeRow(row);
                JOptionPane.showMessageDialog(this, "Deleted!");
            }
        });

        // 👁️ HISTORY
        historyBtn.addActionListener(e -> {
            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a patient");
                return;
            }

            int patientId = (int) model.getValueAt(row, 0);
            String patientName = (String) model.getValueAt(row, 2);

            new PatientHistoryFrame(patientId, patientName);
        });

        // ✏️ UPDATE
        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a row");
                return;
            }

            int id = (int) model.getValueAt(row, 0);

            String name = JOptionPane.showInputDialog("New Name:", model.getValueAt(row, 2));
            int age = Integer.parseInt(JOptionPane.showInputDialog("New Age:", model.getValueAt(row, 3)));
            String gender = JOptionPane.showInputDialog("New Gender:", model.getValueAt(row, 4));
            String phone = JOptionPane.showInputDialog("New Phone:", model.getValueAt(row, 5));
            String address = JOptionPane.showInputDialog("New Address:", model.getValueAt(row, 6));
            String bloodGroup = JOptionPane.showInputDialog("New Blood Group:", model.getValueAt(row, 7));

            if (PatientService.updatePatient(id, name, age, gender, phone, address, bloodGroup)) {

                // Update table UI
                model.setValueAt(name, row, 2);
                model.setValueAt(age, row, 3);
                model.setValueAt(gender, row, 4);
                model.setValueAt(phone, row, 5);
                model.setValueAt(address, row, 6);
                model.setValueAt(bloodGroup, row, 7);

                JOptionPane.showMessageDialog(this, "Updated!");
            }
        });

        add(pane);
        add(searchField);
        add(searchBtn);
        add(deleteBtn);
        add(updateBtn);
        add(historyBtn);

        setSize(500, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadData() {
        try {
            ResultSet rs = PatientService.getAllPatients();

            while (rs.next()) {
                model.addRow(new Object[] {
                        rs.getInt("patient_id"),
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("blood_group")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}