package com.chocho.dao;

import com.chocho.model.User;
import com.chocho.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    DbUtil dbUtil = new DbUtil();

    /*
    * 登录验证
    * */
    public User login(User user){

        User resultUser = null;
        String sql = "select * from t_user where db_name = ? and db_password = ?";
        try {
            PreparedStatement ps = dbUtil.getConnection().prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ResultSet results = ps.executeQuery();
            if(results.next()){
                resultUser = new User();
                resultUser.setName(results.getString("db_name"));
                resultUser.setPassword(results.getString("db_password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbUtil.closeConnection();
        }
        return resultUser;
    }

    public List<User> query(String name){
        String sql = "";

        if(name.length() == 0){
            sql = "select * from t_user";
        }else {
            sql = "select * from t_user where db_name like '%' + ? + '%'";
        }
        ResultSet results = null;
        try {
            PreparedStatement ps = null;
            if("".equals(name) || name == null){
                ps = dbUtil.getConnection().prepareStatement(sql);

            }else {
                ps = dbUtil.getConnection().prepareStatement(sql);
                ps.setString(1, name);
            }
            results = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("查询出错!");
            e.printStackTrace();
        }finally {
            dbUtil.closeConnection();
        }

        List<User> userList = new ArrayList<>();
        try {
            while(results.next()){
                User user = new User();
                user.setId(results.getInt("db_id"));
                user.setName(results.getString("db_name"));
                user.setPassword(results.getString("db_password"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void add(User user){
        String sql = "insert into t_user values(?,?,?)";
        try {
            PreparedStatement ps = dbUtil.getConnection().prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("添加出错！");
            e.printStackTrace();
        }finally {
            dbUtil.closeConnection();
        }
    }

    public void delete(String number){
        String sql = "delete from t_user where db_id ="+ number;
        try {
            PreparedStatement ps = dbUtil.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("删除出错！");
            e.printStackTrace();
        }finally {
            dbUtil.closeConnection();
        }
    }

    public void edit(String way, String value, String number){
        PreparedStatement ps;
        String sql = "";

        sql += "update t_user set " + way + " = ? where db_id = ?";
        try {
            ps = dbUtil.getConnection().prepareStatement(sql);
            ps.setString(1, value);
            ps.setInt(2, Integer.parseInt(number));
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("修改出错!");
            e.printStackTrace();
        }finally {
            dbUtil.closeConnection();
        }
    }
}
