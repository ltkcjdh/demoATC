package com.example.demo21;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;


public class ConnectSql {

    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.1.103:3306/UsersATS", "isp17", "isp17");
            JOptionPane.showMessageDialog(null, "Соединение установлено");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }


    public static ObservableList<usersATS> getDatausers() {
        Connection conn = ConnectDb();
        ObservableList<usersATS> list = FXCollections.observableArrayList();
        try {
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select * from Sotrudniki");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new usersATS(Integer.parseInt(rs.getString("user_id")), rs.getString("User_name"), rs.getString("Login_user"), rs.getString("Pass_user"), rs.getString("Email_user"), rs.getString("Pass2_user")));
            }
        } catch (Exception ignored) {
        }
        return list;

    }

    public static ObservableList<BazaATS> getBazaATS() {
        Connection conn1 = ConnectDb();
        ObservableList<BazaATS> list = FXCollections.observableArrayList();
        try {
            assert conn1 != null;
            PreparedStatement ps1 = conn1.prepareStatement("select * from BazaATS");
            ResultSet rs1 = ps1.executeQuery();

            while (rs1.next()) {
                list.add(new BazaATS(Integer.parseInt(rs1.getString("id")), rs1.getString("tip"), rs1.getString("proizvoditil"), rs1.getString("model"), rs1.getString("nomer")));
            }
        } catch (Exception ignored) {
        }
        return list;
    }
}
