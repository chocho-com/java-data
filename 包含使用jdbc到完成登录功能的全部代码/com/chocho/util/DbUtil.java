package com.chocho.util;

import java.sql.*;

/*数据库工具*/
public class DbUtil {
    private String DbUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=School";
    private String DbUser = "sa";
    private String DbPassword = "123";
    private String JdbcName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    Connection connection = null;

    public Connection getConnection(){
        try {
            Class.forName(JdbcName);
            connection = DriverManager.getConnection(DbUrl, DbUser, DbPassword);
            System.out.println("数据库连接成功");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(){
        try {
            if(connection != null){
                connection.close();
                System.out.println("数据库已关闭");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {//测试jdbc
        DbUtil db = new DbUtil();
        db.getConnection();
        db.closeConnection();
    }
}
