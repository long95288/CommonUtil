package com.longquanxiao.sql;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author longquanxiao
 * 连接数据库的工具类
 */
public class ConnectionDB {
    /***
     * logger
     */
    private static Logger logger = Logger.getLogger(ConnectionDB.class);
    /**
     * 驱动类
     * */
    private static String driveClass = null;
    /**
     * 服务器数据库地址
     * */
    private static String url = null;
    /**
     * 数据库的用户名
     * */
    private static String name = null;
    /**
     * 数据库的密码
     * */
    private static String password = null;

    static {
        InputStream inputStream = null;
        try {
            // 1. 创建一个属性配置对象
            Properties properties = new Properties();
            // 获得配置文件的输入流
            inputStream = ConnectionDB.class.getClassLoader().getResourceAsStream("jdbc.properties");

            // 加载配置信息
            properties.load(inputStream);
            // 读取属性
            driveClass = properties.getProperty("driverClass");
            url = properties.getProperty("url");
            name = properties.getProperty("name");
            password = properties.getProperty("password");

        }catch (IOException e){
            logger.error(e.getMessage(),e);
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }
    }
    /**
     * 获得连接对象，可能返回空值
     */
    public static Connection getConnection(){
        Connection connection = null;
        try {
            if(driveClass!=null && url !=null && name !=null && password != null){
                // 加载驱动类
                Class.forName(driveClass);
                connection = DriverManager.getConnection(url, name, password);
            }
        }catch (ClassNotFoundException e){
            logger.error(e.getMessage(),e);
        }catch (SQLException e){
            logger.error(e.getMessage(),e);
        }
        return connection;
    }

    /**
     * 释放连接
     * */
    public static void release(Connection connection, Statement statement, ResultSet resultSet){
        closeResultSet(resultSet);
        closeStatement(statement);
        closeConnection(connection);
    }

    /**
     * 关闭结果集
     * */
    private static void closeResultSet(ResultSet resultSet){
        try{
            if (resultSet != null){
                resultSet.close();
            }
        }catch (SQLException e){
            logger.error(e.getMessage(),e);
        }
    }
    /**
     * close Statement
     * */
    private static void closeStatement(Statement statement){
        try{
            if (statement != null)
            {
                statement.close();
            }
        }catch (SQLException e){
            logger.error(e.getMessage(),e);
        }
    }
    /**
     * 关闭连接
     * */
    private static void closeConnection(Connection connection){
        try {
            if (connection != null)
            {
                connection.close();
            }
        }catch (SQLException e){
            logger.error(e.getMessage(),e);
        }
    }
}
