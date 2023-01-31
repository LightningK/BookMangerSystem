/*
 * Created by JFormDesigner on Thu Nov 10 01:39:18 CST 2022
 */

package view.admin;

import java.awt.event.*;
import javax.swing.border.*;

import net.jdbc.utils.UIutils;
import view.LibraryPanel;
import view.login.LoginFrm;
import view.MyInfoPanel;

import java.awt.*;
import javax.swing.*;

/**
 * @author 1
 */
public class AdminFrm extends JFrame {
    public AdminFrm() {
        initComponents();
    }

    private void btn_Manage_Book(ActionEvent e) {
        libraryPanel.refreshPanel();
        cardLayout.show(mainPanel,"libraryPanel");
    }

    private void btn_Manage_User(ActionEvent e) {
        manageUserPanel.refreshPanel();
        cardLayout.show(mainPanel,"manageUserPanel");
    }

    private void btn_Manage_Borrow(ActionEvent e) {
        manageHistoryPanel.refreshPanel();
        cardLayout.show(mainPanel,"mangeHistoryPanel");
    }

    private void btn_myInfo(ActionEvent e) {
        cardLayout.show(mainPanel,"myInfo");
    }

    private void btn_lognOut(ActionEvent e) {
        //是-0 否-1
        int x = JOptionPane.showConfirmDialog(null,"是否退出登录");
        if(x == 0){
            dispose();
            new LoginFrm().setVisible(true);
        }
    }

    public LibraryPanel getLibraryPanel() {
        return libraryPanel;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        btn_myInfo = new JButton();
        btn_lognOut = new JButton();
        mainPanel = new JPanel();
        btn_Manage_Book = new JButton();
        btn_Manage_Borrow = new JButton();
        btn_Manage_User = new JButton();

        //======== this ========
        setTitle("\u7ba1\u7406\u5458\u7cfb\u7edf");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- btn_myInfo ----
        btn_myInfo.setText("\u6211\u7684\u8d44\u6599");
        btn_myInfo.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 26));
        btn_myInfo.addActionListener(e -> btn_myInfo(e));
        contentPane.add(btn_myInfo);
        btn_myInfo.setBounds(15, 440, 135, 60);

        //---- btn_lognOut ----
        btn_lognOut.setText("\u9000\u51fa\u767b\u5f55");
        btn_lognOut.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 26));
        btn_lognOut.addActionListener(e -> btn_lognOut(e));
        contentPane.add(btn_lognOut);
        btn_lognOut.setBounds(15, 535, 135, 60);

        //======== mainPanel ========
        {
            mainPanel.setPreferredSize(new Dimension(1000, 810));
            mainPanel.setBorder(LineBorder.createBlackLineBorder());
            mainPanel.setAlignmentX(0.0F);
            mainPanel.setAlignmentY(0.0F);
            mainPanel.setFont(mainPanel.getFont().deriveFont(mainPanel.getFont().getStyle() | Font.BOLD));
            mainPanel.setLayout(new CardLayout());
        }
        contentPane.add(mainPanel);
        mainPanel.setBounds(160, 5, 990, 800);

        //---- btn_Manage_Book ----
        btn_Manage_Book.setText("\u7ba1\u7406\u56fe\u4e66");
        btn_Manage_Book.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 26));
        btn_Manage_Book.addActionListener(e -> btn_Manage_Book(e));
        contentPane.add(btn_Manage_Book);
        btn_Manage_Book.setBounds(15, 110, 135, 60);

        //---- btn_Manage_Borrow ----
        btn_Manage_Borrow.setText("\u7ba1\u7406\u501f\u9605");
        btn_Manage_Borrow.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 26));
        btn_Manage_Borrow.addActionListener(e -> btn_Manage_Borrow(e));
        contentPane.add(btn_Manage_Borrow);
        btn_Manage_Borrow.setBounds(15, 335, 135, 60);

        //---- btn_Manage_User ----
        btn_Manage_User.setText("\u7ba1\u7406\u7528\u6237");
        btn_Manage_User.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 26));
        btn_Manage_User.addActionListener(e -> btn_Manage_User(e));
        contentPane.add(btn_Manage_User);
        btn_Manage_User.setBounds(15, 220, 135, 60);

        contentPane.setPreferredSize(new Dimension(1160, 815));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UIutils.setLaF(this);
        this.getContentPane().setBackground(new Color(245, 245, 245));
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        libraryPanel = new LibraryPanel();
        manageUserPanel = new ManageUserPanel();
        myInfo = new MyInfoPanel();
        manageHistoryPanel = new ManageHistoryPanel();
        mainPanel.add("libraryPanel",libraryPanel);
        mainPanel.add("manageUserPanel", manageUserPanel);
        mainPanel.add("mangeHistoryPanel", manageHistoryPanel);
        mainPanel.add("myInfo",myInfo);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JButton btn_myInfo;
    private JButton btn_lognOut;
    private JPanel mainPanel;
    private JButton btn_Manage_Book;
    private JButton btn_Manage_Borrow;
    private JButton btn_Manage_User;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private CardLayout cardLayout;
    private LibraryPanel libraryPanel;
    private ManageUserPanel manageUserPanel;
    private ManageHistoryPanel manageHistoryPanel;
    private MyInfoPanel myInfo;

}
