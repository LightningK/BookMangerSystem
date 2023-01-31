/*
 * Created by JFormDesigner on Wed Nov 09 16:10:48 CST 2022
 */

package view.login;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

import dao.UserDao;
import model.User;
import net.jdbc.utils.FileUtils;
import net.jdbc.utils.MyStringUtils;
import net.jdbc.utils.UIutils;
import view.admin.AdminFrm;
import view.user.UserFrm;
//import net.miginfocom.swing.*;
//import org.jdesktop.swingx.*;

/**
 * @author 1
 */
public class LoginFrm extends JFrame {
    public LoginFrm() {
        initComponents();
    }

    /**
     * 登录按钮事件
     * @param e
     */
    private void btn_login(ActionEvent e) {
        String name = this.txtField_username.getText();
        String pwd = this.textField_pwd.getText();
        if(MyStringUtils.isEmpty(name)){
            JOptionPane.showMessageDialog(null,"用户名不能为空！");
            return;
        }
        if(MyStringUtils.isEmpty(pwd)){
            JOptionPane.showMessageDialog(null,"密码不能为空！");
            return;
        }
        List<User> userList = UserDao.login(name,pwd);
        if (userList.size() != 0){  //登录成功
            JOptionPane.showMessageDialog(null,"登录成功！");
            dispose();
            String limit = userList.get(0).getUsertype();
            Integer userID = (Integer) userList.get(0).getId();
            FileUtils.write(userID.toString(),"account.txt");
            if (limit.equals("0")){ //用户登录
                new UserFrm().setVisible(true);
            } else if (limit.equals("1")||limit.equals("2")){  //管理员登录
                new AdminFrm().setVisible(true);
            }
        } else {    //登录失败
            JOptionPane.showMessageDialog(null,"用户名或密码错误！");
        }
    }

    /**
     * 注册按钮事件
     * @param e
     */
    private void btn_signin(ActionEvent e) {
        this.dispose();
        new signInFrm().setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        btn_login = new JButton();
        btn_signin = new JButton();
        textField_pwd = new JTextField();
        label3 = new JLabel();
        txtField_username = new JTextField();
        label2 = new JLabel();

        //======== this ========
        setTitle("\u767b\u5f55");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("STU \u56fe \u4e66 \u7ba1 \u7406 \u7cfb \u7edf");
        label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 36));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label1);
        label1.setBounds(50, 115, 430, 95);

        //---- btn_login ----
        btn_login.setText("\u767b\u5f55");
        btn_login.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 26));
        btn_login.addActionListener(e -> btn_login(e));
        contentPane.add(btn_login);
        btn_login.setBounds(105, 435, 120, 65);

        //---- btn_signin ----
        btn_signin.setText("\u6ce8\u518c");
        btn_signin.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 26));
        btn_signin.addActionListener(e -> btn_signin(e));
        contentPane.add(btn_signin);
        btn_signin.setBounds(315, 435, 120, 65);
        contentPane.add(textField_pwd);
        textField_pwd.setBounds(190, 335, 240, 45);

        //---- label3 ----
        label3.setText("\u5bc6\u7801\uff1a");
        label3.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 26));
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label3);
        label3.setBounds(85, 325, 110, 65);
        contentPane.add(txtField_username);
        txtField_username.setBounds(190, 260, 240, 45);

        //---- label2 ----
        label2.setText("\u7528\u6237\u540d\uff1a");
        label2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 24));
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label2);
        label2.setBounds(70, 250, 130, 65);

        contentPane.setPreferredSize(new Dimension(545, 650));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UIutils.setLaF(this);
        this.getContentPane().setBackground(new Color(245, 245, 245));
        //UIutils.setbackground(this,"res/bg.png"); //设置背景
        this.getRootPane().setDefaultButton(btn_login);
        UIutils.setBottonIcon(btn_login,"res/登录.png");
        UIutils.setBottonIcon(btn_signin,"res/注册.png");
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JButton btn_login;
    private JButton btn_signin;
    private JTextField textField_pwd;
    private JLabel label3;
    private JTextField txtField_username;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
