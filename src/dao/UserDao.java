package dao;

import model.User;
import net.jdbc.utils.DButils;

import java.util.List;

public class UserDao {

    //登录
    public static List<User> login(String name, String pwd) {
        String sql = "select * from t_user where userName= ? and password= ? ";
        return DButils.jdbc_select(sql,User.class,name,pwd);
    }

    //注册
    public static int reg(String name, String pwd) {
        String sql = "insert into t_user(Id,userName,password,userType) value(?,?,?,?)";
        String sql2 = "select max(Id) from t_user";
        int maxId = DButils.jdbc_selectMaxId(sql2);
        return DButils.jdbc_update(sql, maxId+1,name,pwd,"0");
    }

    //获取用户名
    public static String getName(int id){
        String sql = "select * from t_user where Id= ?";
        return DButils.jdbc_select(sql,User.class,id).get(0).getUsername();
    }

    //获取用户密码
    public static String getPwd(int id){
        String sql = "select * from t_user where Id= ?";
        return DButils.jdbc_select(sql,User.class,id).get(0).getPassword();
    }

    //获取用户权限
    public static String getLimit(int id){
        String sql = "select * from t_user where Id= ?";
        return DButils.jdbc_select(sql,User.class,id).get(0).getUsertype();
    }

    //修改信息
    public static int updateInfo(int id,String newName,String newPwd){
        String sql = "update t_user set userName=?, password=? where Id=?";
        return DButils.jdbc_update(sql,newName,newPwd,id);
    }

    //修改信息-超级管理员
    public static int updateInfo_super(int id,String newName,String newPwd,String newLimit){
        String sql = "update t_user set userName=?, password=?, userType=? where Id=?";
        return DButils.jdbc_update(sql,newName,newPwd,newLimit,id);
    }

    //获取全部非超级管理员用户
    public static List<User> getAllUser(){
        String sql = "select * from t_user where userType != \"2\" ";
        return DButils.jdbc_select(sql,User.class);
    }

    //获取全部用户
    public static List<User> getAllUser_super(){
        String sql = "select * from t_user";
        return DButils.jdbc_select(sql,User.class);
    }

    //删除用户
    public static int deleteUser(int id){
        String sql = "delete from t_user where Id=?";
        return DButils.jdbc_update(sql,id);
    }
}
