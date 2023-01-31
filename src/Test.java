import dao.BookDao;
import model.Book;
import net.jdbc.utils.FileUtils;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        String ID = FileUtils.read("account.txt").get(0);
        ID = ID.replace("\n","");
        List<Book> bookList = BookDao.getMyBook(Integer.parseInt(ID));
        for (Book book1:bookList){
            System.out.println(book1.getBookname());
        }
//        FileUtils.write("123","account.txt");
//        System.out.println(FileUtils.read("account.txt"));
//        new LoginOnFrm().setVisible(true);
    }
}
