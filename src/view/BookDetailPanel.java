/*
 * Created by JFormDesigner on Sun Nov 20 16:29:34 CST 2022
 */

package view;

import java.awt.event.*;
import dao.BookDao;
import dao.UserDao;
import model.Book;
import net.jdbc.utils.FileUtils;
import net.jdbc.utils.MyStringUtils;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author 1
 */
public class BookDetailPanel extends JFrame {
    public BookDetailPanel() {
        initComponents();
    }

    private void btn_update(ActionEvent e) {
        int bookId = book.getId();
        String newBookName = textField_bookName.getText();
        String newAuthor = textField_author.getText();
        String newSex = textField_sex.getText();
        String newPrice = textField_price.getText();
        String newBookType = textField_type.getText();
        String newBookDesc = textPane_desc.getText();
        if(MyStringUtils.isEmpty(newBookName)){
            JOptionPane.showMessageDialog(null,"书名不能为空！");
            return;
        }
        if(MyStringUtils.isEmpty(newPrice)){  //价格为空
            newPrice = "0";
        }
        Double newBookPrice = Double.valueOf(newPrice);
        int x = JOptionPane.showConfirmDialog(null,"是否修改该书籍！");
        if(x == 0){
            int ok = BookDao.updateBook(newBookName,newAuthor,newSex,newBookType,newBookPrice,newBookDesc,bookId);
            if (ok <= 0){
                JOptionPane.showMessageDialog(null,"请求数据库失败！");
                return;
            }
            JOptionPane.showMessageDialog(null,"修改成功！");
            //更新界面
            libraryPanel.refreshPanel();
        }
    }

    private void btn_save(ActionEvent e) {
        String newBookName = textField_bookName.getText();
        String newAuthor = textField_author.getText();
        String newSex = textField_sex.getText();
        String newPrice = textField_price.getText();
        String newBookType = textField_type.getText();
        String newBookDesc = textPane_desc.getText();
        if(MyStringUtils.isEmpty(newBookName)){
            JOptionPane.showMessageDialog(null,"书名不能为空！");
            return;
        }
        if(MyStringUtils.isEmpty(newPrice)){  //价格为空
            newPrice = "0";
        }
        Double newBookPrice = Double.valueOf(newPrice);
        int x = JOptionPane.showConfirmDialog(null,"是否添加该书籍！");
        if(x == 0){
            int ok = BookDao.insertBook(newBookName,newAuthor,newSex,newBookType,newBookPrice,newBookDesc);
            if (ok <= 0){
                JOptionPane.showMessageDialog(null,"请求数据库失败！");
                return;
            }
            JOptionPane.showMessageDialog(null,"添加成功！");
            //更新界面
            libraryPanel.refreshPanel();
        }
    }

    private void btn_delete(ActionEvent e) {
        int x = JOptionPane.showConfirmDialog(null,"是否删除该书籍！");
        if(x == 0){
            int ok = BookDao.deleteBook(book.getId());
            if (ok <= 0){
                JOptionPane.showMessageDialog(null,"请求数据库失败！");
                return;
            }
            JOptionPane.showMessageDialog(null,"删除成功！");
            dispose();
            //更新界面
            libraryPanel.refreshPanel();
        }
    }

    public void setLibraryPanel(LibraryPanel libraryPanel) {
        this.libraryPanel = libraryPanel;
    }

    public void setBookInfo(String bookName){
        this.bookName = bookName;
        setTitle(bookName+"-书本详细介绍");
        book = BookDao.searchBook(bookName).get(0);
        textField_bookName.setText(book.getBookname());
        textField_author.setText(book.getAuthor());
        textField_sex.setText(book.getSex());
        textField_type.setText(book.getBooktype());
        textField_ID.setText(""+book.getId());
        textField_price.setText(""+book.getPrice());
        textPane_desc.setText(book.getBookdesc());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label_img = new JLabel();
        panel1 = new JPanel();
        label2 = new JLabel();
        textField_bookName = new JTextField();
        panel2 = new JPanel();
        label3 = new JLabel();
        textField_author = new JTextField();
        label7 = new JLabel();
        textField_sex = new JTextField();
        panel3 = new JPanel();
        label4 = new JLabel();
        textField_type = new JTextField();
        panel4 = new JPanel();
        label5 = new JLabel();
        textField_ID = new JTextField();
        label6 = new JLabel();
        scrollPane1 = new JScrollPane();
        textPane_desc = new JTextPane();
        btn_delete = new JButton();
        btn_update = new JButton();
        panel5 = new JPanel();
        label8 = new JLabel();
        textField_price = new JTextField();
        btn_save = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label_img ----
        label_img.setText("\u5c01\u9762");
        label_img.setHorizontalAlignment(SwingConstants.CENTER);
        label_img.setBorder(new LineBorder(Color.black, 2));
        contentPane.add(label_img);
        label_img.setBounds(30, 30, 310, 265);

        //======== panel1 ========
        {
            panel1.setAlignmentX(0.0F);
            panel1.setAlignmentY(0.0F);
            panel1.setLayout(null);

            //---- label2 ----
            label2.setText("\u4e66\u540d\uff1a");
            label2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            label2.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label2);
            label2.setBounds(15, 25, 70, 40);

            //---- textField_bookName ----
            textField_bookName.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            panel1.add(textField_bookName);
            textField_bookName.setBounds(80, 25, 370, 35);
        }
        contentPane.add(panel1);
        panel1.setBounds(350, 15, 470, 60);

        //======== panel2 ========
        {
            panel2.setLayout(null);

            //---- label3 ----
            label3.setText("\u4f5c\u8005\uff1a");
            label3.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            label3.setHorizontalAlignment(SwingConstants.CENTER);
            panel2.add(label3);
            label3.setBounds(15, 25, 70, 40);

            //---- textField_author ----
            textField_author.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            panel2.add(textField_author);
            textField_author.setBounds(80, 25, 250, 35);

            //---- label7 ----
            label7.setText("\u6027\u522b\uff1a");
            label7.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            label7.setHorizontalAlignment(SwingConstants.CENTER);
            panel2.add(label7);
            label7.setBounds(345, 25, 70, 40);

            //---- textField_sex ----
            textField_sex.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            panel2.add(textField_sex);
            textField_sex.setBounds(410, 25, 40, 35);
        }
        contentPane.add(panel2);
        panel2.setBounds(350, 70, 470, 65);

        //======== panel3 ========
        {
            panel3.setLayout(null);

            //---- label4 ----
            label4.setText("\u7c7b\u578b\uff1a");
            label4.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            label4.setHorizontalAlignment(SwingConstants.CENTER);
            panel3.add(label4);
            label4.setBounds(15, 25, 70, 40);

            //---- textField_type ----
            textField_type.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            panel3.add(textField_type);
            textField_type.setBounds(80, 25, 370, 35);
        }
        contentPane.add(panel3);
        panel3.setBounds(350, 135, 470, 65);

        //======== panel4 ========
        {
            panel4.setLayout(null);

            //---- label5 ----
            label5.setText("\u7f16\u53f7\uff1a");
            label5.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            label5.setHorizontalAlignment(SwingConstants.CENTER);
            panel4.add(label5);
            label5.setBounds(15, 25, 70, 40);

            //---- textField_ID ----
            textField_ID.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            textField_ID.setEnabled(false);
            panel4.add(textField_ID);
            textField_ID.setBounds(80, 25, 370, 35);
        }
        contentPane.add(panel4);
        panel4.setBounds(350, 200, 470, 65);

        //---- label6 ----
        label6.setText("\u7b80\u8981\u63cf\u8ff0\uff1a");
        label6.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        label6.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label6);
        label6.setBounds(30, 305, 120, 40);

        //======== scrollPane1 ========
        {

            //---- textPane_desc ----
            textPane_desc.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            scrollPane1.setViewportView(textPane_desc);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(35, 350, 670, 240);

        //---- btn_delete ----
        btn_delete.setText("\u5220\u9664");
        btn_delete.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        btn_delete.addActionListener(e -> btn_delete(e));
        contentPane.add(btn_delete);
        btn_delete.setBounds(730, 525, 85, 60);

        //---- btn_update ----
        btn_update.setText("\u4fee\u6539");
        btn_update.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        btn_update.addActionListener(e -> btn_update(e));
        contentPane.add(btn_update);
        btn_update.setBounds(730, 440, 85, 60);

        //======== panel5 ========
        {
            panel5.setLayout(null);

            //---- label8 ----
            label8.setText("\u4ef7\u683c\uff1a");
            label8.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            label8.setHorizontalAlignment(SwingConstants.CENTER);
            panel5.add(label8);
            label8.setBounds(15, 25, 70, 40);

            //---- textField_price ----
            textField_price.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            panel5.add(textField_price);
            textField_price.setBounds(80, 25, 370, 35);
        }
        contentPane.add(panel5);
        panel5.setBounds(350, 265, 470, 65);

        //---- btn_save ----
        btn_save.setText("\u4fdd\u5b58");
        btn_save.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        btn_save.setVisible(false);
        btn_save.addActionListener(e -> btn_save(e));
        contentPane.add(btn_save);
        btn_save.setBounds(730, 360, 85, 60);

        contentPane.setPreferredSize(new Dimension(835, 605));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on\
        color = new Color(245, 245, 245);
        this.getContentPane().setBackground(color);
        panel1.setBackground(color);
        panel2.setBackground(color);
        panel3.setBackground(color);
        panel4.setBackground(color);
        panel5.setBackground(color);
        ImageIcon img = new ImageIcon("res/book.png");
        img.setImage(img.getImage().getScaledInstance(label_img.getWidth(), label_img.getHeight(),Image.SCALE_DEFAULT ));
        label_img.setIcon(img);
        String limit = UserDao.getLimit(FileUtils.readUserID());
        if (limit.equals("0")){   //用户
            textField_bookName.setEditable(false);
            textField_author.setEditable(false);
            textField_sex.setEditable(false);
            textField_type.setEditable(false);
            textField_price.setEditable(false);
            textPane_desc.setEditable(false);
            btn_save.setVisible(false);
            btn_update.setVisible(false);
            btn_delete.setVisible(false);
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label_img;
    private JPanel panel1;
    private JLabel label2;
    private JTextField textField_bookName;
    private JPanel panel2;
    private JLabel label3;
    private JTextField textField_author;
    private JLabel label7;
    private JTextField textField_sex;
    private JPanel panel3;
    private JLabel label4;
    private JTextField textField_type;
    private JPanel panel4;
    private JLabel label5;
    private JTextField textField_ID;
    private JLabel label6;
    private JScrollPane scrollPane1;
    private JTextPane textPane_desc;
    public JButton btn_delete;
    public JButton btn_update;
    private JPanel panel5;
    private JLabel label8;
    private JTextField textField_price;
    public JButton btn_save;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private String bookName;
    private Color color;
    private Book book;
    private LibraryPanel libraryPanel;
}
