package ui;

import javax.swing.*;
import service.MedicalHistoryService;

public class AddMedicalHistoryFrame extends JFrame {

    public AddMedicalHistoryFrame(int patientId, String patientName) {

        setTitle("Add History - " + patientName);

        JLabel diagLabel = new JLabel("Diagnosis:");
        diagLabel.setBounds(20, 20, 100, 25);

        JTextField diagField = new JTextField();
        diagField.setBounds(120, 20, 200, 25);

        JLabel treatLabel = new JLabel("Treatment:");
        treatLabel.setBounds(20, 60, 100, 25);

        JTextField treatField = new JTextField();
        treatField.setBounds(120, 60, 200, 25);

        JButton saveBtn = new JButton("Save");
        saveBtn.setBounds(120, 110, 100, 30);

        saveBtn.addActionListener(e -> {

            String diagnosis = diagField.getText();
            String treatment = treatField.getText();

            if (diagnosis.isEmpty() || treatment.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill all fields");
                return;
            }

            boolean success = MedicalHistoryService.addHistory(patientId, diagnosis, treatment);

            if (success) {
                JOptionPane.showMessageDialog(this, "Saved Successfully");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error saving data");
            }
        });

        add(diagLabel);
        add(diagField);
        add(treatLabel);
        add(treatField);
        add(saveBtn);

        setSize(370, 200);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}