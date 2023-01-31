/*
 * Created by JFormDesigner on Mon Nov 21 23:28:24 CST 2022
 */

package view.admin;

import java.awt.event.*;
import dao.BorrowDao;
import dao.UserDao;
import model.BorrowBook;
import model.User;
import net.jdbc.utils.FileUtils;
import net.jdbc.utils.MyStringUtils;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * @author 1
 */
public class ManageUserPanel extends JPanel {
    public ManageUserPanel() {
        initComponents();
    }

    public void addBook(){
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0);
        List<User> userList = null;
        if (userlimit.equals("1")){ //普通管理员
            userList = UserDao.getAllUser();
        } else if (userlimit.equals("2")){  //超级管理员
            userList = UserDao.getAllUser_super();
        }
        for(User user : userList){
            Vector vector = new Vector<>();
            vector.add(user.getId());
            vector.add(user.getUsername());
            vector.add(user.getPassword());
            vector.add(MyStringUtils.transLimit(user.getUsertype()));
            dtm.addRow(vector);
        }
        validate();
        repaint();
    }

    public void refreshPanel(){
        addBook();
    }

    private void tableMousePressed(MouseEvent e) {
        int row = table.getSelectedRow();
        txt_ID.setText(String.valueOf(table.getValueAt(row,0)));
        txt_UserName.setText(String.valueOf(table.getValueAt(row,1)));
        txt_pwd.setText(String.valueOf(table.getValueAt(row,2)));
        txt_limit.setText(String.valueOf(table.getValueAt(row,3)));
    }

    private void btn_update(ActionEvent e) {
        int ok = 0;
        int Id = Integer.parseInt(txt_ID.getText());
        String newName = txt_UserName.getText();
        String newPwd = txt_pwd.getText();
        String newlimit = txt_limit.getText();
        int x = JOptionPane.showConfirmDialog(null,"确认修改该用户信息？");
        if (x == 1){
            return;
        }
        if (userlimit.equals("2")){ //超级管理员
            ok = UserDao.updateInfo_super(Id,newName,newPwd,MyStringUtils.transLimit(newlimit));
        } else if (userlimit.equals("1")){
            ok = UserDao.updateInfo(Id,newName,newPwd);
        }
        if (ok < 1){
            JOptionPane.showMessageDialog(null,"数据库请求失败！");
            return;
        }
        JOptionPane.showMessageDialog(null,"修改成功！");
        refreshPanel();
    }

    private void btn_add(ActionEvent e) {
        AddUser addUser = new AddUser();
        addUser.setManageUserPanel(this);
        addUser.setVisible(true);
    }

    private void bth_delete(ActionEvent e) {
        int x = JOptionPane.showConfirmDialog(null,"确认删除该用户？");
        if (x == 1){
            return;
        }
        UserDao.deleteUser(Integer.parseInt(txt_ID.getText()));
        JOptionPane.showMessageDialog(null,"删除成功！");
        refreshPanel();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        scrollPane1 = new JScrollPane();
        table = new JTable();
        panel1 = new JPanel();
        panel2 = new JPanel();
        label1 = new JLabel();
        txt_UserName = new JTextField();
        panel3 = new JPanel();
        label2 = new JLabel();
        txt_pwd = new JTextField();
        btn_add = new JButton();
        panel4 = new JPanel();
        label3 = new JLabel();
        txt_limit = new JTextField();
        btn_update = new JButton();
        bth_delete = new JButton();
        panel5 = new JPanel();
        label4 = new JLabel();
        txt_ID = new JTextField();

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
                    {null, null, null, ""},
                },
                new String[] {
                    "\u7f16\u53f7", "\u59d3\u540d", "\u5bc6\u7801", "\u6743\u9650"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, false, false, false
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            table.setBorder(LineBorder.createBlackLineBorder());
            table.setRowHeight(40);
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    tableMousePressed(e);
                }
            });
            scrollPane1.setViewportView(table);
        }
        add(scrollPane1);
        scrollPane1.setBounds(0, 0, 990, 615);

        //======== panel1 ========
        {
            panel1.setBorder(LineBorder.createBlackLineBorder());
            panel1.setLayout(null);

            //======== panel2 ========
            {
                panel2.setLayout(null);

                //---- label1 ----
                label1.setText("\u59d3\u540d\uff1a");
                label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
                label1.setHorizontalTextPosition(SwingConstants.RIGHT);
                panel2.add(label1);
                label1.setBounds(5, 30, 75, 35);

                //---- txt_UserName ----
                txt_UserName.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
                panel2.add(txt_UserName);
                txt_UserName.setBounds(80, 30, 180, txt_UserName.getPreferredSize().height);
            }
            panel1.add(panel2);
            panel2.setBounds(190, 5, 265, 70);

            //======== panel3 ========
            {
                panel3.setLayout(null);

                //---- label2 ----
                label2.setText("\u5bc6\u7801\uff1a");
                label2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
                label2.setHorizontalTextPosition(SwingConstants.LEFT);
                label2.setHorizontalAlignment(SwingConstants.RIGHT);
                panel3.add(label2);
                label2.setBounds(15, 30, 65, 35);

                //---- txt_pwd ----
                txt_pwd.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
                panel3.add(txt_pwd);
                txt_pwd.setBounds(80, 30, 190, txt_pwd.getPreferredSize().height);
            }
            panel1.add(panel3);
            panel3.setBounds(460, 5, 280, 70);

            //---- btn_add ----
            btn_add.setText("\u6dfb\u52a0");
            btn_add.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            btn_add.addActionListener(e -> btn_add(e));
            panel1.add(btn_add);
            btn_add.setBounds(160, 110, 100, 55);

            //======== panel4 ========
            {
                panel4.setLayout(null);

                //---- label3 ----
                label3.setText("\u6743\u9650\uff1a");
                label3.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
                label3.setHorizontalTextPosition(SwingConstants.LEFT);
                label3.setHorizontalAlignment(SwingConstants.RIGHT);
                panel4.add(label3);
                label3.setBounds(5, 30, 75, 35);

                //---- txt_limit ----
                txt_limit.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
                txt_limit.setEnabled(false);
                panel4.add(txt_limit);
                txt_limit.setBounds(80, 30, 130, txt_limit.getPreferredSize().height);
            }
            panel1.add(panel4);
            panel4.setBounds(740, 5, 215, 70);

            //---- btn_update ----
            btn_update.setText("\u4fee\u6539");
            btn_update.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            btn_update.addActionListener(e -> btn_update(e));
            panel1.add(btn_update);
            btn_update.setBounds(440, 110, 100, 55);

            //---- bth_delete ----
            bth_delete.setText("\u5220\u9664");
            bth_delete.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
            bth_delete.addActionListener(e -> bth_delete(e));
            panel1.add(bth_delete);
            bth_delete.setBounds(700, 105, 100, 55);

            //======== panel5 ========
            {
                panel5.setLayout(null);

                //---- label4 ----
                label4.setText("\u7f16\u53f7\uff1a");
                label4.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
                label4.setHorizontalTextPosition(SwingConstants.LEFT);
                label4.setHorizontalAlignment(SwingConstants.RIGHT);
                panel5.add(label4);
                label4.setBounds(5, 30, 75, 35);

                //---- txt_ID ----
                txt_ID.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
                txt_ID.setEnabled(false);
                panel5.add(txt_ID);
                txt_ID.setBounds(80, 30, 70, txt_ID.getPreferredSize().height);
            }
            panel1.add(panel5);
            panel5.setBounds(15, 5, 165, 70);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        add(panel1);
        panel1.setBounds(0, 620, 990, 180);

        setPreferredSize(new Dimension(990, 800));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        table.getTableHeader().setReorderingAllowed(false);
        scrollPane1.getViewport().setBackground(Color.WHITE);
        panel1.setBackground(Color.white);
        panel2.setBackground(Color.white);
        panel3.setBackground(Color.white);
        panel4.setBackground(Color.white);
        panel5.setBackground(Color.white);
        setBackground(Color.white);
        userlimit = UserDao.getLimit(FileUtils.readUserID());
        addBook();
        if (userlimit.equals("2")){
            txt_limit.setEnabled(true);
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane scrollPane1;
    private JTable table;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel label1;
    private JTextField txt_UserName;
    private JPanel panel3;
    private JLabel label2;
    private JTextField txt_pwd;
    private JButton btn_add;
    private JPanel panel4;
    private JLabel label3;
    private JTextField txt_limit;
    private JButton btn_update;
    private JButton bth_delete;
    private JPanel panel5;
    private JLabel label4;
    private JTextField txt_ID;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private String userlimit;
}
