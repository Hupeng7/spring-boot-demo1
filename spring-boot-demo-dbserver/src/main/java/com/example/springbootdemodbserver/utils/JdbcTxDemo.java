package com.example.springbootdemodbserver.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName JdbcDemo
 * @Description
 * @Author leo
 * @Date 2020/3/25 11:15
 * @Version 1.0
 */
public class JdbcTxDemo {
    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";
        String dbName = "mydb";
        String userName = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/" + dbName;

        String sql = "insert into mydb.user(age,birthday,ctime,name) values(?,?,?,?)";
        int totalCount = 100000;
        Long startTime = System.currentTimeMillis();

        System.out.println(new Date());
//        try {
//            // 1.加载驱动
//            Class.forName(driver);
//            // 2.提供JDBC连接的URL
//            // 3.创建数据库的连接
//            Connection connection = DriverManager.getConnection(url, userName, password);
//
//            // 4.创建一个Statement/PreparedStatement
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            for (int k = 0; k < totalCount; k++) {
//                System.out.println("insert number is: " + k);
//                preparedStatement.setInt(1, new Random().nextInt(100));
//                preparedStatement.setDate(2, new Date(System.currentTimeMillis()));
//                preparedStatement.setDate(3, new Date(System.currentTimeMillis()));
//                preparedStatement.setString(4, "test" + k);
//                // 加入批处理
//                preparedStatement.addBatch();
//                if (sql.trim().length() > 1) {
//                    // 5.执行sql语句
//                    // 执行批处理
//                    preparedStatement.executeBatch();
//                }
//                if (k > 0 && (k % 1000 == 0 || k == totalCount - 1)) {
//                    // 若是事务 则在此提交 开启
//                }
//
//            }
//
//            // 6.关闭JDBC对象
//            // 关闭声明
//            preparedStatement.close();
//            // 关闭连接对象
//            connection.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Long endTime = System.currentTimeMillis();
        System.out.println(totalCount + " 条数据插入耗时：" + (endTime - startTime) + " ms");

    }


}
