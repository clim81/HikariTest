package com.example;

//Source: https://www.baeldung.com/hikaricp


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HikariCP{

    public static List<String> fetchData() {
        final String SQL_QUERY = "select * from Students";
        List<String> information = new ArrayList<String>();
        try (Connection con = DataSource.getConnection(); PreparedStatement pst = con.prepareStatement(SQL_QUERY); ResultSet rs = pst.executeQuery();) {
            String info = "";
            while (rs.next()) {
                for (int i = 1; i <= 4; i++) {
                    info += rs.getString(i) + ":";
                }
                System.out.println(info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return information;
    }

    public static void main(String[] args) {
        System.out.println("Hello");
        fetchData();
    }

}