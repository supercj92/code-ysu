package com.cfysu.jdbc;

import java.sql.*;

/**
 * Created by cj on 17-9-3.
 */
public class JDBCUsage {

    public static void main(String[] args){
        //1.加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2.获取连接
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/ssm", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(connection == null){
            System.out.println("获取数据库连接为空");
            return;
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()){
                System.out.println("name:" + resultSet.getString("user_name"));
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(connection != null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
