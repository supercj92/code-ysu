package com.cfysu.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;

/**
 * Created by cj on 17-9-3.
 */
public class JDBCUsage {

    public static void main(String[] args){
        JDBCUsage jdbcUsage = new JDBCUsage();
        Connection connection = jdbcUsage.getConnection();
        try {
            //jdbcUsage.insertBinaryData(connection);
            jdbcUsage.readBinaryData(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readBinaryData(Connection connection) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\iceAge_from_db.jpg");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT file FROM binary_file WHERE id = 1");
        ResultSet resultSet = preparedStatement.executeQuery();

        //inputStream.read()
        //fileOutputStream.write();
        byte[] buffer = new byte[1024];
        int count;
        InputStream inputStream = null;
        while(resultSet.next()){
            inputStream = resultSet.getBinaryStream("file");
            while ((count = inputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer, 0, count);
            }
        }
        fileOutputStream.close();
        if(inputStream != null){
            inputStream.close();
        }
        resultSet.close();
        System.out.println("----从数据库读取二进制文件结束----");

    }

    public void insertBinaryData(Connection connection) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("E:\\iceAge.jpg");
        PreparedStatement statement = connection.prepareStatement("UPDATE binary_file SET file = ? WHERE id = 1");
        statement.setBinaryStream(1,fileInputStream);
        statement.execute();
        fileInputStream.close();
        statement.close();
        System.out.println("-------将二进制文件写入数据库结束----------");
    }

    public void queryByJDBC(Connection connection){
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

    public Connection getConnection(){
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
            return connection;
        }
        return connection;
    }
}
