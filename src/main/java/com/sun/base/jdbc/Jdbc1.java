package com.sun.base.jdbc;

import java.sql.*;

/**
 * Created by louis on 2015/5/8.
 */
public class Jdbc1 {
    public static void main(String[] args) {
        Connection conn = null;
        String url = "jdbc:mysql://192.168.0.133/cookbook";
        String username = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("connected.");
            //add transaction
            conn.setAutoCommit(false);
            tryQuery(conn);
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void tryQuery(Connection conn) throws SQLException {
        PreparedStatement ps;
        int count;
        ps = conn.prepareStatement("insert into user values(?,?,?)");
        ps.setString(1, "zzz");
        ps.setInt(2, 22);
        ps.setString(3, "china");
        count = ps.executeUpdate();

        ResultSet resultSet = ps.executeQuery("select * from user ");
        ResultSetMetaData md = resultSet.getMetaData();
        int ncols = md.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= ncols; i++) {
                System.out.print(md.getColumnName(i) + " ");
            }
            System.out.println();
            String name = resultSet.getString(1);
            int age = resultSet.getInt(2);
            String address = resultSet.getString(3);
            System.out.println(String.format("%s, %d, %s", name, age, address));
        }

        ps.close();
        System.out.println("effect count:" + count);
    }
}
