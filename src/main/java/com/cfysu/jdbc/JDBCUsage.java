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
            //jdbcUsage.readBinaryData(connection);
            long start = System.currentTimeMillis();
            jdbcUsage.insertUser(connection);
            long end = System.currentTimeMillis();
            System.out.println("耗时：" + (end -start));
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
            inputStream = resultSet.getBinaryStream("io");
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

    public void insertUser(Connection connection){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `bbc_order` (`orderId`,`bpin`,`cpin`,`userId`,`shopId`,`openid`,`orderDesc`,`orderType`,`orderPayType`,`freightFreeFee`,`orderPayFeeShipping`,`productAmount`,`productDiscountAmount`,`couponId`,`couponAmount`,`couponSettledStatus`,`couponSettledTime`,`orderPayFeeTotal`,`activityId`,`activitySourceId`,`orderState`,`orderCreateTime`,`orderCreateTimeNew`,`orderCancelTime`,`orderEndTime`,`orderEndTimeNew`,`lastUpdateTime`,`whoPayShippingfee`,`deliveryType`,`itemTitleList`,`mailType`,`payId`,`sellerConsignmentTimeNew`,`payTime`,`payReturnTime`,`orderSource`,`sellerNick`,`buyerNick`,`buyerBuyRemark`,`orderVersion`,`hideFlag`,`shopContact`,`provinceId`,`cityId`,`countyId`,`townId`,`userName`,`address`,`telphone`,`closeReason`,`closeReasonDesc`,`tradeCount`,`userIp`,`timeoutItemFlag`,`timeoutStartTime`,`timeLeft`,`syncStatus`,`SkutotalAmount`,`sellerConsignmentTime`,`mOrderNo`,`custAmount`,`zlAmount`,`mlAmount`,`cashier`,`isPrint`,`printTime`,`isDelete`) VALUES (?,'xtl_zyvpSSH','112233',17979,6,'oSdS_s6FwWwcY77bXdzArfCNV8xQ','[11867]',0,1,0,0,0,0,NULL,NULL,NULL,NULL,180,NULL,NULL,6,1503985395,'2017-10-10 13:24:53',NULL,1504160609,NULL,1504160609,0,0,'',0,11868,NULL,0,0,1,'','','',1,0,'18912345678',0,0,0,0,'朱涛','洪山区珞雄路19号','13971144810',100,'订单流程正常结束',1,'127.0.0.1',0,1499408374,172800,0,180,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);");

            //insert into bbc.bbc_user (id,wxOpenId, wxUserName, wxSex, wxHeadImgUrl, wxCountry, wxProvince, wxCity, jdUserName, remark, createTime, updateTime,cardNum)" +
            //"values(null, ?, 'xxxx', 1, 'http://wx.qlogo.cn', 'zhong', 'hb', 'wh', null, 'remark', now(), now(),null);
            for(int i = 0;i < 3000;i++){
                preparedStatement.setInt(1, 890000 + i);
                preparedStatement.execute();
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/bbc", "root", "root");
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
