package net.jdbc.utils;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 数据库操作工具类
 */
public class DButils {
    /**
     * 增删改
     */
    public static int jdbc_update(String sql,Object ...objects){
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;  //更新语句执行数
        try {
            //获取连接
            conn = JDBCUtils.getConnection();
            //预编译
            ps = conn.prepareStatement(sql);
            // 给SQL赋值
            for (int i = 0;i < objects.length;i++){
                ps.setObject(i + 1,objects[i]);
            }
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn,ps,null);
        }
        return count;
    }

    /**
     * 查询
     */
    public static <T> List<T> jdbc_select(String sql,Class<T> clazz,Object ...objects){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> objList = new ArrayList<T>();
        try {
            //获取连接
            conn = JDBCUtils.getConnection();
            //预编译
            ps = conn.prepareStatement(sql);
            // 给SQL赋值
            for (int i = 0;i < objects.length;i++){
                ps.setObject(i + 1,objects[i]);
            }
            //获取结果集
            rs = ps.executeQuery();
            //获取结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取列数
            int col_count = rsmd.getColumnCount();
            //获取每列的名字
            List<String> col_names = new ArrayList<String>();
            for (int i = 0; i < col_count; i++) {
                col_names.add(rsmd.getColumnName(i + 1));
            }
            while (rs.next()){
                //创建对象
                T obj = clazz.getDeclaredConstructor().newInstance();
                for (int i = 0; i < col_count; i++){
                    //获取每一列的列名 转化为小写
                    String name = col_names.get(i).toLowerCase(Locale.ROOT);
                    //根据列名获取属性
                    Field field = clazz.getDeclaredField(name);
                    field.setAccessible(true);
                    //获取属性的类型 并给属性赋值
                    String type = field.getType().getName();
//                    System.out.println(type);
                    switch (type) {
                        case "int":
                        case "java.lang.Integer": {
                            int val = rs.getInt(name);
                            field.set(obj, val);
                            break;
                        }
                        case "double":
                        case "java.lang.Double": {
                            double val = rs.getDouble(name);
                            field.set(obj, val);
                            break;
                        }
                        case "float":
                        case "java.lang.Float": {
                            float val = rs.getFloat(name);
                            field.set(obj, val);
                            break;
                        }
                        case "java.lang.String": {
                            String val = rs.getString(name);
                            field.set(obj, val);
                            break;
                        }
                        case "java.util.Date": {
                            Date val = rs.getDate(name);
                            field.set(obj, val);
                            break;
                        }
                        case "boolean": {
                            boolean val = rs.getBoolean(name);
                            field.set(obj, val);
                            break;
                        }
                    }
                }
                objList.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            JDBCUtils.release(conn,ps,rs);
        }
        return objList;
    }

    /**
     * 查询最大Id
     */
    public static int jdbc_selectMaxId(String sql){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        try {
            conn = JDBCUtils.getConnection();
            //预编译
            ps = conn.prepareStatement(sql);
            //获取结果集
            rs = ps.executeQuery();
            rs.next();
            result = rs.getInt("max(Id)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn,ps,rs);
        }
        return result;
    }

}
