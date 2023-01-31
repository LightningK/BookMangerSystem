/*
 * Created by JFormDesigner on Wed Nov 09 23:45:43 CST 2022
 */

package view;

import dao.BookDao;
import dao.BorrowDao;
import net.jdbc.utils.FileUtils;
import view.user.MyLibraryPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author 1
 */
public class BookPanel extends JPanel {
    public BookPanel() {
        initComponents();
    }

    private void btn(ActionEvent e) {
        int id_book = Integer.parseInt(bookid.getText());
        String option = btn_borrow.getText();
        if (option.equals("借阅")){
            //插入数据库借书表
            int ok = BorrowDao.borrowBook(FileUtils.readUserID(),id_book);
            if (ok <= 0){
                JOptionPane.showMessageDialog(null,"请求数据库失败！");
                return;
            }
            JOptionPane.showMessageDialog(null,"借阅成功！");
            //更新书本是否借阅标志
            BookDao.setFlag(id_book,1);
            //设置已借阅
            btn_borrow.setText("已借阅");
            btn_borrow.setEnabled(false);
        }
        else if (option.equals("还书")){
            //更新借书表
            int ok = BorrowDao.returnBook(FileUtils.readUserID(),id_book);
            if (ok <= 0){
                JOptionPane.showMessageDialog(null,"请求数据库失败！");
                return;
            }
            JOptionPane.showMessageDialog(null,"还书成功！");
            //更新借阅标志
            BookDao.setFlag(id_book,0);
            //设置按钮
            btn_borrow.setText("借阅");
            btn_borrow.setEnabled(true);
            myLibraryPanel.refreshPanel();
        }
        else if (option.equals("管理")){
            BookDetailPanel bookDetailPanel = new BookDetailPanel();
            bookDetailPanel.setBookInfo(bookName.getText());
            bookDetailPanel.setLibraryPanel(libraryPanel);
            bookDetailPanel.setVisible(true);
        }
    }

    private void thisMouseEntered(MouseEvent e) {
        setBackground(selectedColor);
        this.setBorder(BorderFactory.createLineBorder(borderColor,3));
    }

    private void thisMouseExited(MouseEvent e) {
        this.setBorder(BorderFactory.createLineBorder(Color.black,1));
        setBackground(initColor);
    }

    private void btn_borrowMouseEntered(MouseEvent e) {
        setBackground(selectedColor);
        this.setBorder(BorderFactory.createLineBorder(borderColor,3));
    }

    private void btn_borrowMouseExited(MouseEvent e) {
        this.setBorder(BorderFactory.createLineBorder(Color.black,1));
        setBackground(initColor);
    }

    private void thisMouseClicked(MouseEvent e) {
        BookDetailPanel bookDetailPanel = new BookDetailPanel();
        bookDetailPanel.setBookInfo(bookName.getText());
        bookDetailPanel.setLibraryPanel(libraryPanel);
        bookDetailPanel.setVisible(true);
    }

    public void setLibraryPanel(LibraryPanel libraryPanel) {
        this.libraryPanel = libraryPanel;
    }

    public void setMyLibraryPanel(MyLibraryPanel myLibraryPanel) {
        this.myLibraryPanel = myLibraryPanel;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        lbl_image = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        btn_borrow = new JButton();
        bookName = new JLabel();
        author = new JLabel();
        bookid = new JLabel();
        label1 = new JLabel();
        bookType = new JLabel();

        //======== this ========
        setPreferredSize(new Dimension(0, 0));
        setForeground(Color.white);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thisMouseClicked(e);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                thisMouseEntered(e);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                thisMouseExited(e);
            }
        });
        setLayout(null);
        add(lbl_image);
        lbl_image.setBounds(80, 5, 150, 150);

        //---- label2 ----
        label2.setText("\u4f5c\u8005\uff1a");
        label2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
        add(label2);
        label2.setBounds(15, 235, 45, 30);

        //---- label3 ----
        label3.setText("\u4e66\u540d\uff1a");
        label3.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
        add(label3);
        label3.setBounds(15, 160, 50, 40);

        //---- label4 ----
        label4.setText("\u7f16\u53f7\uff1a");
        label4.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
        add(label4);
        label4.setBounds(new Rectangle(new Point(15, 280), label4.getPreferredSize()));

        //---- btn_borrow ----
        btn_borrow.setText("\u501f\u9605");
        btn_borrow.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        btn_borrow.addActionListener(e -> btn(e));
        btn_borrow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn_borrowMouseEntered(e);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn_borrowMouseExited(e);
            }
        });
        add(btn_borrow);
        btn_borrow.setBounds(205, 255, 88, 55);

        //---- bookName ----
        bookName.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
        bookName.setText("\u4e09\u56fd\u6f14\u4e49");
        add(bookName);
        bookName.setBounds(60, 165, 225, 30);

        //---- author ----
        author.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
        author.setText("\u7f57\u8d2f\u4e2d");
        add(author);
        author.setBounds(60, 235, 145, 30);

        //---- bookid ----
        bookid.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
        bookid.setText("023134");
        add(bookid);
        bookid.setBounds(60, 275, 145, 30);

        //---- label1 ----
        label1.setText("\u7c7b\u578b\uff1a");
        label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
        add(label1);
        label1.setBounds(15, 205, 50, 25);

        //---- bookType ----
        bookType.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
        bookType.setText("\u5386\u53f2");
        add(bookType);
        bookType.setBounds(60, 200, 225, 30);

        setPreferredSize(new Dimension(300, 325));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        ImageIcon img = new ImageIcon("res/book.png");
        img.setImage(img.getImage().getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(),Image.SCALE_DEFAULT ));
        lbl_image.setIcon(img);
        this.setBorder(BorderFactory.createLineBorder(Color.black,1));
        initColor = new Color(245,245,245);
        selectedColor = new Color(0xFFECF4FC, true);
        borderColor = new Color(0x0C99D0);
        setBackground(initColor);
        setSize(300,325);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    public JLabel lbl_image;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    public JButton btn_borrow;
    public JLabel bookName;
    public JLabel author;
    public JLabel bookid;
    private JLabel label1;
    public JLabel bookType;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private Color initColor;
    private Color selectedColor;
    private Color borderColor;
    private LibraryPanel libraryPanel;
    private MyLibraryPanel myLibraryPanel;
}
