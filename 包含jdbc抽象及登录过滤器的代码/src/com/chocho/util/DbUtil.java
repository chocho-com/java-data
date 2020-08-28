package com.chocho.util;

import java.sql.*;

public class DbUtil {
    private String DbUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=School";
    private String DbUser = "sa";
    private String DbPassword = "123";
    private String JdbcName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    Connection connection = null;
    public Connection getConnection(){
        try {
            Class.forName(JdbcName);
            connection = DriverManager.getConnection(DbUrl,DbUser,DbPassword);
            System.out.println("数据库连接成功！");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("数据库连接关闭！");
            e.printStackTrace();
        }
    }
    //用于测试
    /*
    public static void main(String args[]){
        DbUtil dbUtil = new DbUtil();
        Connection con = dbUtil.getConnection();
    }*/
}
