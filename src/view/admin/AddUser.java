/*
 * Created by JFormDesigner on Tue Nov 22 01:27:55 CST 2022
 */

package view.admin;

import dao.UserDao;
import net.jdbc.utils.MyStringUtils;
import view.login.LoginFrm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 1
 */
public class AddUser extends JFrame {
    public AddUser() {
        initComponents();
    }

    private void btn_add(ActionEvent e) {
        String newName = txt_UserName.getText();
        String newPwd = txt_pwd.getText();
        if(MyStringUtils.isEmpty(newName)){
            JOptionPane.showMessageDialog(null,"用户名不能为空！");
            return;
        }
        if(MyStringUtils.isEmpty(newPwd)){
            JOptionPane.showMessageDialog(null,"密码不能为空！");
            return;
        }
        int count = UserDao.reg(newName,newPwd);
        if (count > 0){
            JOptionPane.showMessageDialog(null,"添加成功！");
            this.dispose();
            manageUserPanel.refreshPanel();
        } else {
            JOptionPane.showMessageDialog(null,"请求数据库失败！");
        }
    }

    public void setManageUserPanel(ManageUserPanel manageUserPanel) {
        this.manageUserPanel = manageUserPanel;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel2 = new JPanel();
        label1 = new JLabel();
        txt_UserName = new JTextField();
        panel3 = new JPanel();
        label2 = new JLabel();
        txt_pwd = new JTextField();
        btn_add = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

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
            txt_UserName.setBounds(80, 30, 190, txt_UserName.getPreferredSize().height);
        }
        contentPane.add(panel2);
        panel2.setBounds(40, 10, 310, 70);

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
            txt_pwd.setBounds(100, 30, 190, txt_pwd.getPreferredSize().height);
        }
        contentPane.add(panel3);
        panel3.setBounds(20, 80, 355, 70);

        //---- btn_add ----
        btn_add.setText("\u6dfb\u52a0");
        btn_add.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        btn_add.addActionListener(e -> btn_add(e));
        contentPane.add(btn_add);
        btn_add.setBounds(150, 175, 100, 55);

        contentPane.setPreferredSize(new Dimension(405, 275));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        setTitle("请输入用户信息");
        this.getRootPane().setDefaultButton(btn_add);
        this.getContentPane().setBackground(new Color(245, 245, 245));
        panel2.setBackground(new Color(245, 245, 245));
        panel3.setBackground(new Color(245, 245, 245));
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel2;
    private JLabel label1;
    private JTextField txt_UserName;
    private JPanel panel3;
    private JLabel label2;
    private JTextField txt_pwd;
    private JButton btn_add;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private ManageUserPanel manageUserPanel;
}
