/*
 * Created by JFormDesigner on Wed Nov 09 19:51:05 CST 2022
 */

package view.login;

import dao.UserDao;
import net.jdbc.utils.MyStringUtils;
import net.jdbc.utils.UIutils;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 1
 */
public class signInFrm extends JFrame {
    public signInFrm() {
        initComponents();
    }

    /**
     * 返回按钮事件
     * @param e
     */
    private void btn_back(ActionEvent e) {
        this.dispose();
        new LoginFrm().setVisible(true);
    }

    /**
     * 注册按钮事件
     * @param e
     */
    private void btn_signIn(ActionEvent e) {
        String name = this.textField_name.getText();
        String pwd = textField_pwd.getText();
        if(MyStringUtils.isEmpty(name)){
            JOptionPane.showMessageDialog(null,"用户名不能为空！");
            return;
        }
        if(MyStringUtils.isEmpty(pwd)){
            JOptionPane.showMessageDialog(null,"密码不能为空！");
            return;
        }
        int count = UserDao.reg(name,pwd);
        if (count > 0){ //注册成功
            JOptionPane.showMessageDialog(null,"注册成功！");
            this.dispose();
            new LoginFrm().setVisible(true);
        } else {    //注册失败
            JOptionPane.showMessageDialog(null,"注册失败！");
        }
    }

    /**
     * 退出窗口事件
     * @param e
     */
    private void thisWindowClosing(WindowEvent e) {
        new LoginFrm().setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        btn_signIn = new JButton();
        panel1 = new JPanel();
        label3 = new JLabel();
        textField_name = new JTextField();
        textField_pwd = new JTextField();
        label2 = new JLabel();
        btn_back = new JButton();

        //======== this ========
        setTitle("\u6ce8\u518c");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u7528 \u6237 \u6ce8 \u518c");
        label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 30));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label1);
        label1.setBounds(95, 25, 260, 60);

        //---- btn_signIn ----
        btn_signIn.setText("\u6ce8\u518c");
        btn_signIn.addActionListener(e -> btn_signIn(e));
        contentPane.add(btn_signIn);
        btn_signIn.setBounds(100, 220, 85, 40);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- label3 ----
            label3.setText("\u5bc6\u7801\uff1a");
            label3.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
            label3.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(label3);
            label3.setBounds(30, 60, 75, 45);
            panel1.add(textField_name);
            textField_name.setBounds(105, 15, 175, 35);
            panel1.add(textField_pwd);
            textField_pwd.setBounds(105, 65, 175, 35);

            //---- label2 ----
            label2.setText("\u7528\u6237\u540d\uff1a");
            label2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
            panel1.add(label2);
            label2.setBounds(20, 10, 85, 45);
        }
        contentPane.add(panel1);
        panel1.setBounds(60, 95, 320, 115);

        //---- btn_back ----
        btn_back.setText("\u8fd4\u56de");
        btn_back.addActionListener(e -> btn_back(e));
        contentPane.add(btn_back);
        btn_back.setBounds(260, 220, 85, 40);

        contentPane.setPreferredSize(new Dimension(445, 320));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        UIutils.setLaF(this);
        this.getContentPane().setBackground(new Color(245, 245, 245));
        panel1.setBackground(new Color(245, 245, 245));
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JButton btn_signIn;
    private JPanel panel1;
    private JLabel label3;
    private JTextField textField_name;
    private JTextField textField_pwd;
    private JLabel label2;
    private JButton btn_back;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
