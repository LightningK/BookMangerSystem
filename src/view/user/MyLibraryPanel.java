/*
 * Created by JFormDesigner on Wed Nov 16 23:16:38 CST 2022
 */

package view.user;

import java.awt.event.*;

import dao.BookDao;
import model.Book;
import net.jdbc.utils.FileUtils;
import net.jdbc.utils.MyStringUtils;
import view.BookPlate;
import view.BookPanel;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author 1
 */
public class MyLibraryPanel extends JPanel {
    public MyLibraryPanel() {
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
                bookPanel.setMyLibraryPanel(this);
                bookPanel.bookName.setText(bookList.get(listIndex).getBookname());
                bookPanel.author.setText(bookList.get(listIndex).getAuthor());
                bookPanel.bookid.setText(""+bookList.get(listIndex).getId());
                bookPanel.bookType.setText(""+bookList.get(listIndex).getBooktype());
                bookPanel.btn_borrow.setText("还书");
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
        addBook(BookDao.getMyBook(userID));
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

    private void search() {
        String bookName = textField_search.getText();
        if (MyStringUtils.isEmpty(bookName)){
            addBook(BookDao.getMyBook(userID));
        }else {
            addBook(BookDao.searchMyBook(userID,bookName));
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        myLibraryPanel = new JPanel();
        panel_search = new JPanel();
        textField_search = new JTextField();
        btn_search = new JButton();
        panel_bool = new JPanel();
        btn_PrePage = new JButton();
        btn_NextPage = new JButton();
        label1 = new JLabel();
        lbl_Pages = new JLabel();
        lbl_currentPage = new JLabel();

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
        panel_bool.setBounds(5, 80, 975, 680);

        //---- btn_PrePage ----
        btn_PrePage.setText("\u4e0a\u4e00\u9875");
        btn_PrePage.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        btn_PrePage.addActionListener(e -> btn_PrePage(e));
        add(btn_PrePage);
        btn_PrePage.setBounds(new Rectangle(new Point(305, 760), btn_PrePage.getPreferredSize()));

        //---- btn_NextPage ----
        btn_NextPage.setText("\u4e0b\u4e00\u9875");
        btn_NextPage.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        btn_NextPage.addActionListener(e -> btn_NextPage(e));
        add(btn_NextPage);
        btn_NextPage.setBounds(new Rectangle(new Point(605, 760), btn_NextPage.getPreferredSize()));

        //---- label1 ----
        label1.setText("/");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        add(label1);
        label1.setBounds(475, 760, 35, 30);

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

        setPreferredSize(new Dimension(990, 800));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        cardLayout = new CardLayout();
        panel_bool.setLayout(cardLayout);
        setBounds(0,0,990,800);
        userID = FileUtils.readUserID();
        addBook(BookDao.getMyBook(userID));
        setBackground(Color.white);
        panel_search.setBackground(Color.white);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel myLibraryPanel;
    private JPanel panel_search;
    public JTextField textField_search;
    public JButton btn_search;
    public JPanel panel_bool;
    public JButton btn_PrePage;
    public JButton btn_NextPage;
    public JLabel label1;
    private JLabel lbl_Pages;
    private JLabel lbl_currentPage;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private CardLayout cardLayout;
    private int bookNum;
    private int pages;
    private int currentPage;
    private int userID;
}
