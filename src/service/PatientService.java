package service;

import model.Patient;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PatientService {

    // 🔹 ADD PATIENT
    public static String[] addPatient(Patient p) {
        try (Connection con = DBConnection.getConnection()) {

            // ✅ Username & fixed password
            String username = p.getName().toLowerCase().replace(" ", "");
            String password = "1234";

            // STEP 1: Insert into USERS
            String userQuery = "INSERT INTO users(username, password, role) VALUES (?, ?, 'patient')";
            PreparedStatement ps1 = con.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS);

            ps1.setString(1, username);
            ps1.setString(2, password);
            ps1.executeUpdate();

            // Get generated user_id
            ResultSet rs = ps1.getGeneratedKeys();
            int userId = 0;
            if (rs.next()) {
                userId = rs.getInt(1);
            }

            // STEP 2: Insert into PATIENTS
            String patientQuery = "INSERT INTO patients(user_id, name, age, gender, phone, address, blood_group) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps2 = con.prepareStatement(patientQuery);

            ps2.setInt(1, userId);
            ps2.setString(2, p.getName());
            ps2.setInt(3, p.getAge());
            ps2.setString(4, p.getGender());
            ps2.setString(5, p.getPhone());
            ps2.setString(6, p.getAddress());
            ps2.setString(7, p.getBloodGroup());

            ps2.executeUpdate();

            // ✅ Return credentials
            return new String[] { username, password };

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // get patient id
    public static int getPatientIdByUserId(int userId) {
        try (Connection con = DBConnection.getConnection()) {

            String query = "SELECT patient_id FROM patients WHERE user_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("patient_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    // 🔹 GET ALL PATIENTS
    public static ResultSet getAllPatients() {
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM patients";
            PreparedStatement ps = con.prepareStatement(query);
            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 🔹 DELETE PATIENT
    public static boolean deletePatient(int patientId) {
        try (Connection con = DBConnection.getConnection()) {

            String query = "DELETE FROM patients WHERE patient_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, patientId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 UPDATE PATIENT
    public static boolean updatePatient(int patientId, String name, int age,
            String gender, String phone,
            String address, String bloodGroup) {
        try (Connection con = DBConnection.getConnection()) {

            String query = "UPDATE patients SET name=?, age=?, gender=?, phone=?, address=?, blood_group=? WHERE patient_id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, phone);
            ps.setString(5, address);
            ps.setString(6, bloodGroup);
            ps.setInt(7, patientId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 SEARCH PATIENTS
    public static ResultSet searchPatients(String keyword) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM patients WHERE LOWER(name) LIKE LOWER(?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + keyword + "%");

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}