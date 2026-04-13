package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBConnection;

public class AppointmentService {

    public static ResultSet getAllAppointments() {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT a.appointment_id, p.name AS patient, d.name AS doctor, " +
                    "a.appointment_date, a.status, a.reason " +
                    "FROM appointments a " +
                    "JOIN patients p ON a.patient_id = p.patient_id " +
                    "JOIN doctors d ON a.doctor_id = d.doctor_id";

            PreparedStatement ps = con.prepareStatement(query);
            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getPendingAppointments() {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT a.appointment_id, p.name AS patient, d.name AS doctor, " +
                    "a.appointment_date, a.status, a.reason " +
                    "FROM appointments a " +
                    "JOIN patients p ON a.patient_id = p.patient_id " +
                    "JOIN doctors d ON a.doctor_id = d.doctor_id " +
                    "WHERE a.status = 'Pending'";

            PreparedStatement ps = con.prepareStatement(query);
            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean updateStatus(int id, String status) {
        try (Connection con = DBConnection.getConnection()) {

            String query = "UPDATE appointments SET status=? WHERE appointment_id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, status);
            ps.setInt(2, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean bookAppointment(int patientId, int doctorId, String date, String reason) {
        try (Connection con = DBConnection.getConnection()) {

            String query = "INSERT INTO appointments(patient_id, doctor_id, appointment_date, reason) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, patientId);
            ps.setInt(2, doctorId);
            ps.setString(3, date);
            ps.setString(4, reason);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ResultSet getAppointmentsByPatient(int patientId) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT a.appointment_id, d.name AS doctor, " +
                    "a.appointment_date, a.status, a.reason " +
                    "FROM appointments a " +
                    "JOIN doctors d ON a.doctor_id = d.doctor_id " +
                    "WHERE a.patient_id=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, patientId);

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getAppointmentsByDoctor(int doctorId) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT a.appointment_id, p.patient_id, p.name AS patient_name, " +
                    "a.appointment_date, a.reason " +
                    "FROM appointments a " +
                    "JOIN patients p ON a.patient_id = p.patient_id " +
                    "WHERE a.doctor_id=? AND a.status='Approved'";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, doctorId);

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
