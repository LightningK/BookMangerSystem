package net.jdbc.utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * 数据库连接工具类
 */
public class JDBCUtils {

    //数据库url、用户名和密码
    private static String driver;//Ctrl+Alt+F抽取全局静态变量
    private static String url;
    private static String username;
    private static String password;

    /*读取属性文件，获取jdbc信息*/
    static{
        ResourceBundle bundle = ResourceBundle.getBundle("resource/db");
        driver = bundle.getString("driver");
        url = bundle.getString("url");
        username = bundle.getString("username");
        password = bundle.getString("password");
        //1、注册JDBC驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException{
        Connection connection = null;
        /* 2、获取数据库连接 */
        connection = DriverManager.getConnection(url,username,password);
        if(!connection.isClosed()){
            System.out.println("数据库连接成功！");
        }
        return connection;
    }

    /*关闭结果集、数据库操作对象、数据库连接*/
    public static void release(Connection connection, Statement statement, ResultSet resultSet) {

        if(resultSet!=null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement!=null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
