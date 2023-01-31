package dao;

import model.Book;
import net.jdbc.utils.DButils;

import java.util.List;

public class BookDao {

    //获取全部书籍
    public static List<Book> getAllBook(){
        String sql = "select * from t_book";
        return DButils.jdbc_select(sql,Book.class);
    }

    //获取当前用户书籍
    public static List<Book> getMyBook(int userId){
        String sql = "SELECT t_book.Id,bookName,author,sex,price,bookDesc,bookType,userName " +
                "FROM t_book,t_borrow,t_user " +
                "WHERE t_book.Id = t_borrow.bookid " +
                "AND t_user.Id = t_borrow.userid " +
                "AND t_user.Id = ? " +
                "AND Rtime is null";
        return DButils.jdbc_select(sql,Book.class,userId);
    }

    //搜索书名
    public static List<Book> searchBook(String bookName){
        String sql = "select * from t_book where bookName=?";
        return DButils.jdbc_select(sql,Book.class,bookName);
    }

    //搜索我的书
    public static List<Book> searchMyBook(int userId,String bookName){
        String sql = "SELECT t_book.Id,bookName,author,sex,price,bookDesc,bookType,userName " +
                "FROM t_book,t_borrow,t_user " +
                "WHERE t_book.Id = t_borrow.bookid " +
                "AND t_user.Id = t_borrow.userid " +
                "AND t_user.Id = ? " +
                "AND bookName = ? " +
                "AND Rtime is null";
        return DButils.jdbc_select(sql,Book.class,userId,bookName);
    }

    //设置借阅标志
    public static int setFlag(int bookId,int flag){
        String sql = "update t_book set Isflag=? where Id=?";
        return DButils.jdbc_update(sql,flag,bookId);
    }

    //增加书籍
    public static int insertBook(String bookName,String author,String sex,String bookType,Double price,String bookDesc){
        String sql = "insert into t_book(Id,bookName,author,sex,price,bookDesc,bookType,Isflag) value(?,?,?,?,?,?,?,?)";
        String sql2 = "select max(Id) from t_book";
        int maxId = DButils.jdbc_selectMaxId(sql2);
        return DButils.jdbc_update(sql,maxId+1,bookName,author,sex,price,bookDesc,bookType,"0");
    }

    //修改书籍
    public static int updateBook(String bookName,String author,String sex,String bookType,Double price,String bookDesc,int id){
        String sql = "update t_book "+
                "set bookName=?,author=?,sex=?,bookType=?,price=?,bookDesc=? " +
                "where Id=?";
        return DButils.jdbc_update(sql,bookName,author,sex,bookType,price,bookDesc,id);
    }

    //删除书籍
    public static int deleteBook(int Id){
        String sql = "delete from t_book where Id=?";
        return DButils.jdbc_update(sql,Id);
    }
}
