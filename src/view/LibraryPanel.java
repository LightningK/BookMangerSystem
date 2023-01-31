/*
 * Created by JFormDesigner on Mon Nov 14 19:27:27 CST 2022
 */

package view;

import java.awt.event.*;

import dao.BookDao;
import dao.UserDao;
import model.Book;
import net.jdbc.utils.FileUtils;
import net.jdbc.utils.MyStringUtils;
import net.jdbc.utils.UIutils;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.List;

/**
 * @author 1
 */
public class LibraryPanel extends JPanel{
    public LibraryPanel() {
        initComponents();
    }

    public void addBook(List<Book> bookList){
        panel_bool.removeAll();
        bookNum = bookList.size();
        pages = bookNum/6;
        if (pages == 0){
            pages = 1;
        }else if (bookNum%6 != 0){
            pages += 1;
        }
        int listIndex = 0;
        for (int i = 0;i < pages;i++){
            BookPlate bookPlate = new BookPlate();
            for (int j = 0;(j<6) && (bookNum > 0);j++){ //一个面板放6本书
                BookPanel bookPanel = new BookPanel();
                bookPanel.setLibraryPanel(this);
                bookPanel.bookName.setText(bookList.get(listIndex).getBookname());
                bookPanel.author.setText(bookList.get(listIndex).getAuthor());
                bookPanel.bookid.setText(""+bookList.get(listIndex).getId());
                bookPanel.bookType.setText(""+bookList.get(listIndex).getBooktype());
                if (userLimit.equals("0")){ //用户
                    if(bookList.get(listIndex).getIsflag() == 0){
                        bookPanel.btn_borrow.setText("借阅");
                    } else if(bookList.get(listIndex).getIsflag() == 1){
                        bookPanel.btn_borrow.setText("已借阅");
                        bookPanel.btn_borrow.setEnabled(false);
                    }
                } else if (userLimit.equals("1")||userLimit.equals("2")){  //管理员
                    bookPanel.btn_borrow.setText("管理");
                }
                bookPlate.add(bookPanel);
                listIndex++;
                bookNum--;
            }
            panel_bool.add(""+i, bookPlate);
        }
        currentPage = 1;
        lbl_currentPage.setText(""+currentPage);
        lbl_Pages.setText(""+ pages);
        validate();
        repaint();
    }

    public void refreshPanel(){
        addBook(BookDao.getAllBook());
    }

    private void btn_PrePage(ActionEvent e) {
        if (currentPage > 1){
            currentPage--;
            cardLayout.show(panel_bool,""+(currentPage-1));
            lbl_currentPage.setText(""+currentPage);
        }
    }

    private void btn_NextPage(ActionEvent e) {
        if (currentPage < pages){
            currentPage++;
            cardLayout.show(panel_bool,""+(currentPage-1));
            lbl_currentPage.setText(""+currentPage);
        }
    }

    private void btn_search(ActionEvent e) {
        search();
    }

    private void textField_searchKeyTyped(KeyEvent e) {
        if((char)e.getKeyChar()==KeyEvent.VK_ENTER) {
            search();
        }
    }

    private void search(){
        String bookName = textField_search.getText();
        if (MyStringUtils.isEmpty(bookName)){
            addBook(BookDao.getAllBook());
        }else {
            addBook(BookDao.searchBook(bookName));
        }
    }

    private void btn_addNewBook(ActionEvent e) {
        BookDetailPanel bookDetailPanel = new BookDetailPanel();
        bookDetailPanel.setTitle("请输入书本信息");
        bookDetailPanel.setLibraryPanel(this);
        bookDetailPanel.btn_save.setVisible(true);
        bookDetailPanel.btn_delete.setVisible(false);
        bookDetailPanel.btn_update.setVisible(false);
        bookDetailPanel.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel_search = new JPanel();
        textField_search = new JTextField();
        btn_search = new JButton();
        panel_bool = new JPanel();
        btn_PrePage = new JButton();
        btn_NextPage = new JButton();
        label1 = new JLabel();
        lbl_Pages = new JLabel();
        lbl_currentPage = new JLabel();
        btn_addNewBook = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(0, 0));
        setPreferredSize(new Dimension(990, 795));
        setBorder(LineBorder.createBlackLineBorder());
        setLayout(null);

        //======== panel_search ========
        {
            panel_search.setLayout(null);

            //---- textField_search ----
            textField_search.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    textField_searchKeyTyped(e);
                }
            });
            panel_search.add(textField_search);
            textField_search.setBounds(25, 20, 525, 45);

            //---- btn_search ----
            btn_search.setText("\u641c\u7d22\u4e66\u540d");
            btn_search.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            btn_search.addActionListener(e -> btn_search(e));
            panel_search.add(btn_search);
            btn_search.setBounds(585, 15, 125, 50);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel_search.getComponentCount(); i++) {
                    Rectangle bounds = panel_search.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel_search.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel_search.setMinimumSize(preferredSize);
                panel_search.setPreferredSize(preferredSize);
            }
        }
        add(panel_search);
        panel_search.setBounds(new Rectangle(new Point(140, 5), panel_search.getPreferredSize()));

        //======== panel_bool ========
        {
            panel_bool.setLayout(new CardLayout());
        }
        add(panel_bool);
        panel_bool.setBounds(10, 75, 975, 680);

        //---- btn_PrePage ----
        btn_PrePage.setText("\u4e0a\u4e00\u9875");
        btn_PrePage.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        btn_PrePage.addActionListener(e -> btn_PrePage(e));
        add(btn_PrePage);
        btn_PrePage.setBounds(new Rectangle(new Point(305, 755), btn_PrePage.getPreferredSize()));

        //---- btn_NextPage ----
        btn_NextPage.setText("\u4e0b\u4e00\u9875");
        btn_NextPage.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        btn_NextPage.addActionListener(e -> btn_NextPage(e));
        add(btn_NextPage);
        btn_NextPage.setBounds(new Rectangle(new Point(605, 755), btn_NextPage.getPreferredSize()));

        //---- label1 ----
        label1.setText("/");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        add(label1);
        label1.setBounds(480, 760, 25, 30);

        //---- lbl_Pages ----
        lbl_Pages.setText("1");
        lbl_Pages.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        lbl_Pages.setHorizontalAlignment(SwingConstants.LEFT);
        add(lbl_Pages);
        lbl_Pages.setBounds(505, 760, 60, 30);

        //---- lbl_currentPage ----
        lbl_currentPage.setText("1");
        lbl_currentPage.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        lbl_currentPage.setHorizontalAlignment(SwingConstants.RIGHT);
        add(lbl_currentPage);
        lbl_currentPage.setBounds(435, 760, 45, 30);

        //---- btn_addNewBook ----
        btn_addNewBook.setText("\u6dfb\u52a0\u4e66\u7c4d");
        btn_addNewBook.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        btn_addNewBook.addActionListener(e -> btn_addNewBook(e));
        add(btn_addNewBook);
        btn_addNewBook.setBounds(805, 755, 135, 35);

        setPreferredSize(new Dimension(990, 800));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        setBackground(Color.white);
        panel_search.setBackground(Color.white);
        cardLayout = new CardLayout();
        panel_bool.setLayout(cardLayout);
        setBounds(0,0,990,800);
        userLimit = UserDao.getLimit(FileUtils.readUserID());
        if(userLimit.equals("0")){
            btn_addNewBook.setVisible(false);
        }
        addBook(BookDao.getAllBook());
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel_search;
    public JTextField textField_search;
    public JButton btn_search;
    public JPanel panel_bool;
    public JButton btn_PrePage;
    public JButton btn_NextPage;
    public JLabel label1;
    private JLabel lbl_Pages;
    private JLabel lbl_currentPage;
    public JButton btn_addNewBook;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private CardLayout cardLayout;
    private int bookNum;
    private int pages;
    private int currentPage;
    private String userLimit;
}
