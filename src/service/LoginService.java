package service;

import java.sql.*;
import db.DBConnection;

public class LoginService {

    // 🔹 LOGIN → return user_id (NOT role)
    public static int login(String username, String password) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT user_id FROM users WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("user_id"); //
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    // 🔹 GET ROLE
    public static String getRole(int userId) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT role FROM users WHERE user_id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("role");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}