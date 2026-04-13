package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBConnection;

public class DoctorService {

    public static int getDoctorIdByUserId(int userId) {
        try (Connection con = DBConnection.getConnection()) {

            String query = "SELECT doctor_id FROM doctors WHERE user_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("doctor_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

}
