package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import service.MedicalHistoryService;

public class PatientHistoryFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

    public PatientHistoryFrame(int patientId, String titleName) {

        setTitle("Medical History - " + titleName);

        String[] cols = { "Diagnosis", "Treatment", "Visit Date" };
        model = new DefaultTableModel(cols, 0);

        table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 20, 450, 250);

        loadData(patientId);

        add(pane);

        setSize(500, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadData(int patientId) {
        try {
            model.setRowCount(0);

            ResultSet rs = MedicalHistoryService.getHistoryByPatient(patientId);

            while (rs.next()) {
                model.addRow(new Object[] {
                        rs.getString("diagnosis"),
                        rs.getString("treatment"),
                        rs.getString("visit_date")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}