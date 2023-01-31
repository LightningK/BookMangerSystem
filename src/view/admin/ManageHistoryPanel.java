/*
 * Created by JFormDesigner on Mon Nov 21 13:26:26 CST 2022
 */

package view.admin;

import java.awt.event.*;
import dao.BorrowDao;
import dao.UserDao;
import model.BorrowBook;
import net.jdbc.utils.FileUtils;
import net.jdbc.utils.MyStringUtils;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * @author 1
 */
public class ManageHistoryPanel extends JPanel {
    public ManageHistoryPanel() {
        initComponents();
    }

    public void addBorrowBook(){
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0);
        List<BorrowBook> borrowBookList = BorrowDao.getAllBorrowBook();
        for(BorrowBook borrowBook : borrowBookList){
            Vector vector = new Vector<>();
            vector.add(borrowBook.getId());
            vector.add(borrowBook.getUsername());
            vector.add(borrowBook.getUserid());
            vector.add(borrowBook.getBookname());
            vector.add(borrowBook.getBookid());
            vector.add(borrowBook.getAuthor());
            vector.add(borrowBook.getBtime());
            vector.add(borrowBook.getRtime());
            dtm.addRow(vector);
        }
        validate();
        repaint();
    }

    public void refreshPanel(){
        addBorrowBook();
    }

    private void btn_delete(ActionEvent e) {
        int x = JOptionPane.showConfirmDialog(null,"确认删除该条记录？");
        if (x == 1){
            return;
        }
        int row = table.getSelectedRow();
        int id = (int) table.getValueAt(row,0);
        int ok = BorrowDao.deleteBorrowBook(id);
        if (ok < 1){
            JOptionPane.showMessageDialog(null,"请求数据库失败！");
            return;
        }
        JOptionPane.showMessageDialog(null,"删除成功！");
        refreshPanel();
    }

    private void btn_save(ActionEvent e) {
        if (MyStringUtils.isEmpty(txt_UserName.getText())){
            JOptionPane.showMessageDialog(null,"请选择一行记录！");
            return;
        }
        int x = JOptionPane.showConfirmDialog(null,"确认修改该条记录？");
        if (x == 1){
            return;
        }
        int row = table.getSelectedRow();
        int id = (int) table.getValueAt(row,0);
        String Byear,Bmonth,Bday;
        String Ryear,Rmonth,Rday;
        Byear = txt_Byear.getText();
        Bmonth = txt_Bmonth.getText();
        Bday = txt_Bday.getText();
        Ryear = txt_Ryear.getText();
        Rmonth = txt_Rmonth.getText();
        Rday = txt_Rday.getText();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Date Bdate = null;
        Date Rdate = null;
        try {
            Bdate = sdf.parse(Byear+"-"+Bmonth+"-"+Bday);
            Rdate = sdf.parse(Ryear+"-"+Rmonth+"-"+Rday);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
//        System.out.println(Bdate);
//        System.out.println(Rdate);
        int ok = BorrowDao.updateBorrowBook(id,Bdate,Rdate);
        if (ok < 1){
            JOptionPane.showMessageDialog(null,"请求数据库失败！");
            return;
        }
        JOptionPane.showMessageDialog(null,"修改成功！");
        txt_UserName.setText("");
        txt_BookName.setText("");
        txt_Byear.setText("");txt_Bmonth.setText("");txt_Bday.setText("");
        txt_Ryear.setText("");txt_Rmonth.setText("");txt_Rday.setText("");
        refreshPanel();
    }

    private void tableMousePressed(MouseEvent e) {
        int row = table.getSelectedRow();
        txt_UserName.setText(String.valueOf(table.getValueAt(row,1)));
        txt_BookName.setText(String.valueOf(table.getValueAt(row,3)));
        if (!String.valueOf(table.getValueAt(row,6)).equals("null")){
            String[] Bdate = new SimpleDateFormat("yyyy-MM-dd").format((Date) table.getValueAt(row,6)).toString().split("-");
            txt_Byear.setText(Bdate[0]);
            txt_Bmonth.setText(Bdate[1]);
            txt_Bday.setText(Bdate[2]);
        }
        if (!String.valueOf(table.getValueAt(row,7)).equals("null")){
            String[] Rdate = new SimpleDateFormat("yyyy-MM-dd").format((Date) table.getValueAt(row,7)).toString().split("-");
            txt_Ryear.setText(Rdate[0]);
            txt_Rmonth.setText(Rdate[1]);
            txt_Rday.setText(Rdate[2]);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        scrollPane1 = new JScrollPane();
        table = new JTable();
        btn_save = new JButton();
        btn_delete = new JButton();
        panel2 = new JPanel();
        label1 = new JLabel();
        txt_UserName = new JTextField();
        panel3 = new JPanel();
        label2 = new JLabel();
        txt_BookName = new JTextField();
        panel4 = new JPanel();
        label3 = new JLabel();
        txt_Byear = new JTextField();
        label4 = new JLabel();
        txt_Bmonth = new JTextField();
        label5 = new JLabel();
        txt_Bday = new JTextField();
        label6 = new JLabel();
        panel5 = new JPanel();
        label7 = new JLabel();
        txt_Ryear = new JTextField();
        label8 = new JLabel();
        txt_Rmonth = new JTextField();
        label9 = new JLabel();
        txt_Rday = new JTextField();
        label10 = new JLabel();

        //======== this ========
        setMinimumSize(new Dimension(0, 0));
        setPreferredSize(new Dimension(990, 795));
        setBorder(LineBorder.createBlackLineBorder());
        setLayout(null);

        //======== scrollPane1 ========
        {

            //---- table ----
            table.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, "", null, "", null, null},
                },
                new String[] {
                    "\u7f16\u53f7", "\u501f\u4e66\u4eba", "\u501f\u4e66\u4eba\u7f16\u53f7", "\u4e66\u540d", "\u4e66\u7c4d\u7f16\u53f7", "\u4f5c\u8005", "\u501f\u4e66\u65e5\u671f", "\u8fd8\u4e66\u65e5\u671f"
                }
            ) {
                Class<?>[] columnTypes = new Class<?>[] {
                    Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Date.class, Date.class
                };
                boolean[] columnEditable = new boolean[] {
                    false, false, false, false, false, false, false, false
                };
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return columnTypes[columnIndex];
                }
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            table.setBorder(LineBorder.createBlackLineBorder());
            table.setRowHeight(30);
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    tableMousePressed(e);
                }
            });
            scrollPane1.setViewportView(table);
        }
        add(scrollPane1);
        scrollPane1.setBounds(0, 0, 990, 575);

        //---- btn_save ----
        btn_save.setText("\u4fdd\u5b58");
        btn_save.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        btn_save.addActionListener(e -> btn_save(e));
        add(btn_save);
        btn_save.setBounds(255, 730, 100, 55);

        //---- btn_delete ----
        btn_delete.setText("\u5220\u9664");
        btn_delete.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        btn_delete.addActionListener(e -> btn_delete(e));
        add(btn_delete);
        btn_delete.setBounds(580, 730, 100, 55);

        //======== panel2 ========
        {
            panel2.setLayout(null);

            //---- label1 ----
            label1.setText("\u501f\u4e66\u4eba\uff1a");
            label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            label1.setHorizontalTextPosition(SwingConstants.RIGHT);
            panel2.add(label1);
            label1.setBounds(5, 30, 75, 35);

            //---- txt_UserName ----
            txt_UserName.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            txt_UserName.setEditable(false);
            panel2.add(txt_UserName);
            txt_UserName.setBounds(80, 30, 180, txt_UserName.getPreferredSize().height);
        }
        add(panel2);
        panel2.setBounds(40, 575, 265, 70);

        //======== panel3 ========
        {
            panel3.setLayout(null);

            //---- label2 ----
            label2.setText("\u4e66\u540d\uff1a");
            label2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            label2.setHorizontalTextPosition(SwingConstants.RIGHT);
            panel3.add(label2);
            label2.setBounds(15, 30, 65, 35);

            //---- txt_BookName ----
            txt_BookName.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            txt_BookName.setEditable(false);
            panel3.add(txt_BookName);
            txt_BookName.setBounds(80, 30, 180, txt_BookName.getPreferredSize().height);
        }
        add(panel3);
        panel3.setBounds(40, 640, 275, 70);

        //======== panel4 ========
        {
            panel4.setLayout(null);

            //---- label3 ----
            label3.setText("\u501f\u4e66\u65e5\u671f\uff1a");
            label3.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            label3.setHorizontalTextPosition(SwingConstants.RIGHT);
            panel4.add(label3);
            label3.setBounds(5, 30, 100, 35);

            //---- txt_Byear ----
            txt_Byear.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            panel4.add(txt_Byear);
            txt_Byear.setBounds(95, 30, 85, txt_Byear.getPreferredSize().height);

            //---- label4 ----
            label4.setText("\u5e74");
            label4.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            label4.setHorizontalTextPosition(SwingConstants.RIGHT);
            panel4.add(label4);
            label4.setBounds(190, 30, 30, 35);

            //---- txt_Bmonth ----
            txt_Bmonth.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            panel4.add(txt_Bmonth);
            txt_Bmonth.setBounds(220, 30, 45, 34);

            //---- label5 ----
            label5.setText("\u6708");
            label5.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            label5.setHorizontalTextPosition(SwingConstants.RIGHT);
            panel4.add(label5);
            label5.setBounds(275, 30, 30, 35);

            //---- txt_Bday ----
            txt_Bday.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            panel4.add(txt_Bday);
            txt_Bday.setBounds(305, 30, 45, 34);

            //---- label6 ----
            label6.setText("\u65e5");
            label6.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            label6.setHorizontalTextPosition(SwingConstants.RIGHT);
            panel4.add(label6);
            label6.setBounds(360, 30, 30, 35);
        }
        add(panel4);
        panel4.setBounds(470, 575, 405, 70);

        //======== panel5 ========
        {
            panel5.setLayout(null);

            //---- label7 ----
            label7.setText("\u8fd8\u4e66\u65e5\u671f\uff1a");
            label7.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            label7.setHorizontalTextPosition(SwingConstants.RIGHT);
            panel5.add(label7);
            label7.setBounds(5, 30, 100, 35);

            //---- txt_Ryear ----
            txt_Ryear.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            panel5.add(txt_Ryear);
            txt_Ryear.setBounds(95, 30, 85, txt_Ryear.getPreferredSize().height);

            //---- label8 ----
            label8.setText("\u5e74");
            label8.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            label8.setHorizontalTextPosition(SwingConstants.RIGHT);
            panel5.add(label8);
            label8.setBounds(190, 30, 30, 35);

            //---- txt_Rmonth ----
            txt_Rmonth.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            panel5.add(txt_Rmonth);
            txt_Rmonth.setBounds(220, 30, 45, 34);

            //---- label9 ----
            label9.setText("\u6708");
            label9.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            label9.setHorizontalTextPosition(SwingConstants.RIGHT);
            panel5.add(label9);
            label9.setBounds(275, 30, 30, 35);

            //---- txt_Rday ----
            txt_Rday.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            panel5.add(txt_Rday);
            txt_Rday.setBounds(305, 30, 45, 34);

            //---- label10 ----
            label10.setText("\u65e5");
            label10.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            label10.setHorizontalTextPosition(SwingConstants.RIGHT);
            panel5.add(label10);
            label10.setBounds(360, 30, 30, 35);
        }
        add(panel5);
        panel5.setBounds(470, 645, 405, 70);

        setPreferredSize(new Dimension(990, 800));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        addBorrowBook();
        table.getTableHeader().setReorderingAllowed(false);
        scrollPane1.getViewport().setBackground(Color.WHITE);
        panel2.setBackground(Color.WHITE);
        panel3.setBackground(Color.WHITE);
        panel4.setBackground(Color.WHITE);
        panel5.setBackground(Color.WHITE);
        setBackground(Color.white);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane scrollPane1;
    private JTable table;
    private JButton btn_save;
    private JButton btn_delete;
    private JPanel panel2;
    private JLabel label1;
    private JTextField txt_UserName;
    private JPanel panel3;
    private JLabel label2;
    private JTextField txt_BookName;
    private JPanel panel4;
    private JLabel label3;
    private JTextField txt_Byear;
    private JLabel label4;
    private JTextField txt_Bmonth;
    private JLabel label5;
    private JTextField txt_Bday;
    private JLabel label6;
    private JPanel panel5;
    private JLabel label7;
    private JTextField txt_Ryear;
    private JLabel label8;
    private JTextField txt_Rmonth;
    private JLabel label9;
    private JTextField txt_Rday;
    private JLabel label10;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
