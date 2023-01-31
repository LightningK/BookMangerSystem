package dao;

import model.BorrowBook;
import net.jdbc.utils.DButils;

import java.util.Date;
import java.util.List;

public class BorrowDao {
    //获取用户借书表单
    public static List<BorrowBook> getMyBorrowBook(int userId){
        String sql = "SELECT t_borrow.Id,bookName,author,bookType,Btime,Rtime,userid,userName\n" +
                "FROM t_book,t_borrow,t_user\n" +
                "WHERE t_book.Id = t_borrow.bookid\n" +
                "AND t_borrow.userid = t_user.Id\n" +
                "AND userid = ?";
        return DButils.jdbc_select(sql, BorrowBook.class,userId);
    }

    //借阅书籍
    public static int borrowBook(int userid, int bookid){
        String sql = "insert into t_borrow(Id,userid,bookid,Btime) value(?,?,?,?)";
        String sql2 = "select max(Id) from t_borrow";
        int maxId = DButils.jdbc_selectMaxId(sql2);
        return DButils.jdbc_update(sql,maxId+1,userid,bookid,new Date(System.currentTimeMillis()));
    }

    //还书
    public static int returnBook(int userid, int bookid){
        String sql = "update t_borrow set Rtime=? where userid=? and bookid=? ";
        return DButils.jdbc_update(sql,new Date(System.currentTimeMillis()),userid,bookid);
    }

    //获取整个借书表
    public static List<BorrowBook> getAllBorrowBook(){
        String sql = "SELECT t_borrow.Id,bookName,bookid,author,Btime,Rtime,userid,userName\n" +
                "FROM t_book,t_borrow,t_user\n" +
                "WHERE t_book.Id = t_borrow.bookid\n" +
                "AND t_borrow.userid = t_user.Id ";
        return DButils.jdbc_select(sql,BorrowBook.class);
    }

    //删除记录
    public static int deleteBorrowBook(int id){
        String sql = "delete from t_borrow where Id=?";
        return DButils.jdbc_update(sql,id);
    }

    //修改记录
    public static int updateBorrowBook(int id,Date Bdate,Date Rdate){
        String sql = "update t_borrow set Btime=?,Rtime=? where Id=? ";
        return DButils.jdbc_update(sql,Bdate,Rdate,id);
    }
}
