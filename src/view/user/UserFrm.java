/*
 * Created by JFormDesigner on Wed Nov 09 22:33:59 CST 2022
 */

package view.user;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import net.jdbc.utils.UIutils;
import view.LibraryPanel;
import view.login.LoginFrm;
import view.MyInfoPanel;
//import org.jdesktop.swingx.*;

/**
 * @author 1
 */
public class UserFrm extends JFrame {
    public UserFrm() {
        initComponents();
    }

    private void btn_library(ActionEvent e) {
        libraryPanel.refreshPanel();
        cardLayout.show(mainPanel,"libraryPanel");
    }

    private void btn_myBook(ActionEvent e) {
        myLibraryPanel.refreshPanel();
        cardLayout.show(mainPanel,"myLibraryPanel");
    }

    private void btn_history(ActionEvent e) {
        historyPanel.addBook();
        cardLayout.show(mainPanel,"historyPanel");
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

    public static LibraryPanel getLibraryPanel() {
        return libraryPanel;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        btn_library = new JButton();
        btn_myBook = new JButton();
        btn_history = new JButton();
        btn_myInfo = new JButton();
        btn_lognOut = new JButton();
        mainPanel = new JPanel();

        //======== this ========
        setTitle("\u56fe\u4e66\u7ba1\u7406\u7cfb\u7edf");
        setBackground(new Color(0xebeef0));
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- btn_library ----
        btn_library.setText("\u56fe\u4e66\u9986");
        btn_library.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 26));
        btn_library.addActionListener(e -> btn_library(e));
        contentPane.add(btn_library);
        btn_library.setBounds(15, 105, 135, 60);

        //---- btn_myBook ----
        btn_myBook.setText("\u6211\u7684\u4e66\u67b6");
        btn_myBook.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 26));
        btn_myBook.addActionListener(e -> btn_myBook(e));
        contentPane.add(btn_myBook);
        btn_myBook.setBounds(15, 210, 135, 60);

        //---- btn_history ----
        btn_history.setText("\u501f\u4e66\u5386\u53f2");
        btn_history.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 26));
        btn_history.addActionListener(e -> btn_history(e));
        contentPane.add(btn_history);
        btn_history.setBounds(15, 305, 135, 60);

        //---- btn_myInfo ----
        btn_myInfo.setText("\u6211\u7684\u8d44\u6599");
        btn_myInfo.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 26));
        btn_myInfo.addActionListener(e -> btn_myInfo(e));
        contentPane.add(btn_myInfo);
        btn_myInfo.setBounds(15, 410, 135, 60);

        //---- btn_lognOut ----
        btn_lognOut.setText("\u9000\u51fa\u767b\u5f55");
        btn_lognOut.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 26));
        btn_lognOut.addActionListener(e -> btn_lognOut(e));
        contentPane.add(btn_lognOut);
        btn_lognOut.setBounds(15, 515, 135, 60);

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
        myLibraryPanel = new MyLibraryPanel();
        myInfo = new MyInfoPanel();
        historyPanel = new HistoryPanel();
        mainPanel.add("libraryPanel",libraryPanel);
        mainPanel.add("myLibraryPanel",myLibraryPanel);
        mainPanel.add("historyPanel",historyPanel);
        mainPanel.add("myInfo",myInfo);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JButton btn_library;
    private JButton btn_myBook;
    private JButton btn_history;
    private JButton btn_myInfo;
    private JButton btn_lognOut;
    private JPanel mainPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private CardLayout cardLayout;
    private MyLibraryPanel myLibraryPanel;
    private static LibraryPanel libraryPanel;
    private HistoryPanel historyPanel;
    private MyInfoPanel myInfo;
}
