/*
 * Created by JFormDesigner on Thu Nov 17 14:33:17 CST 2022
 */

package view;

import dao.UserDao;
import net.jdbc.utils.FileUtils;
import net.jdbc.utils.MyStringUtils;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author 1
 */
public class MyInfoPanel extends JPanel {
    public MyInfoPanel() {
        initComponents();
    }

    private void btn_save(ActionEvent e) {
        String uesrName = textField_name.getText();
        String oldpwd = textField_oldpwd.getText();
        String newpwd = textField_Newpwd.getText();
        String confpwd = textField_confirm.getText();
        if (!oldpwd.equals(UserDao.getPwd(id))){
            JOptionPane.showMessageDialog(null,"原密码不正确！");
            return;
        }
        if (MyStringUtils.isEmpty(newpwd)){
            JOptionPane.showMessageDialog(null,"新密码不能为空");
            return;
        }
        if (!newpwd.equals(confpwd)){
            JOptionPane.showMessageDialog(null,"两次输入的新密码不一致！");
            return;
        }
        int ok = UserDao.updateInfo(FileUtils.readUserID(),uesrName,newpwd);
        if (ok > 0){
            JOptionPane.showMessageDialog(null,"修改成功！");
            textField_oldpwd.setText("");
            textField_Newpwd.setText("");
            textField_confirm.setText("");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        lbl_icon = new JLabel();
        label2 = new JLabel();
        textField_name = new JTextField();
        label3 = new JLabel();
        textField_oldpwd = new JTextField();
        label4 = new JLabel();
        label5 = new JLabel();
        textField_Newpwd = new JTextField();
        textField_confirm = new JTextField();
        btn_save = new JButton();
        button2 = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(0, 0));
        setPreferredSize(new Dimension(990, 795));
        setBorder(LineBorder.createBlackLineBorder());
        setLayout(null);

        //---- lbl_icon ----
        lbl_icon.setText("\u5934\u50cf");
        lbl_icon.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_icon.setBorder(LineBorder.createBlackLineBorder());
        add(lbl_icon);
        lbl_icon.setBounds(40, 110, 305, 265);

        //---- label2 ----
        label2.setText("\u7528\u6237\u540d\uff1a");
        label2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        add(label2);
        label2.setBounds(470, 105, 85, 45);

        //---- textField_name ----
        textField_name.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        add(textField_name);
        textField_name.setBounds(570, 110, 220, 35);

        //---- label3 ----
        label3.setText("\u539f\u5bc6\u7801\uff1a");
        label3.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        add(label3);
        label3.setBounds(465, 200, 75, 45);

        //---- textField_oldpwd ----
        textField_oldpwd.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        add(textField_oldpwd);
        textField_oldpwd.setBounds(570, 205, 220, 35);

        //---- label4 ----
        label4.setText("\u65b0\u5bc6\u7801\uff1a");
        label4.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        add(label4);
        label4.setBounds(460, 295, 80, 40);

        //---- label5 ----
        label5.setText("\u786e\u8ba4\u65b0\u5bc6\u7801\uff1a");
        label5.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        add(label5);
        label5.setBounds(425, 390, 130, 30);

        //---- textField_Newpwd ----
        textField_Newpwd.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        add(textField_Newpwd);
        textField_Newpwd.setBounds(570, 295, 220, 35);

        //---- textField_confirm ----
        textField_confirm.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        add(textField_confirm);
        textField_confirm.setBounds(570, 390, 220, 30);

        //---- btn_save ----
        btn_save.setText("\u4fdd\u5b58");
        btn_save.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
        btn_save.addActionListener(e -> btn_save(e));
        add(btn_save);
        btn_save.setBounds(430, 630, 110, 60);

        //---- button2 ----
        button2.setText("\u66f4\u6539\u5934\u50cf");
        add(button2);
        button2.setBounds(145, 405, 95, 45);

        setPreferredSize(new Dimension(990, 800));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        setBackground(Color.white);
        ImageIcon img = new ImageIcon("res/userIcon.jpg");
        img.setImage(img.getImage().getScaledInstance(lbl_icon.getWidth(), lbl_icon.getHeight(),Image.SCALE_DEFAULT ));
        lbl_icon.setIcon(img);
        id = FileUtils.readUserID();
        textField_name.setText(UserDao.getName(id));
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel lbl_icon;
    private JLabel label2;
    private JTextField textField_name;
    private JLabel label3;
    private JTextField textField_oldpwd;
    private JLabel label4;
    private JLabel label5;
    private JTextField textField_Newpwd;
    private JTextField textField_confirm;
    private JButton btn_save;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private int id;
}
