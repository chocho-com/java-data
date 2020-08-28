package com.chocho.Dao;

import com.chocho.util.DbUtil;
import java.sql.*;

/*
* 基本Dao，封装基本操作
* */
public class BaseDao {
    private DbUtil dbUtil = new DbUtil();

    public void closeConnection(){
        dbUtil.closeConnection();
    }

    //查询
    public ResultSet query(String sql){
        try {
            PreparedStatement preparedStatement = dbUtil.getConnection().prepareStatement(sql);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
