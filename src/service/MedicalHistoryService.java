package service;

import db.DBConnection;
import java.sql.*;

public class MedicalHistoryService {

    public static ResultSet getHistoryByPatient(int patientId) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT diagnosis, treatment, visit_date " +
                    "FROM medical_history WHERE patient_id=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, patientId);

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getHistoryByDoctor(int doctorId) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT diagnosis, treatment, visit_date " +
                    "FROM medical_history WHERE patient_id IN " +
                    "(SELECT patient_id FROM appointments WHERE doctor_id=?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, doctorId);

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean addHistory(int patientId, String diagnosis, String treatment) {
        try (Connection con = DBConnection.getConnection()) {

            String query = "INSERT INTO medical_history(patient_id, diagnosis, treatment, visit_date) VALUES (?, ?, ?, CURDATE())";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, patientId);
            ps.setString(2, diagnosis);
            ps.setString(3, treatment);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}