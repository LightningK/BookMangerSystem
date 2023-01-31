package net.jdbc.utils;

import javax.swing.*;
import java.awt.*;

public class UIutils {

    //设置样式
    public static void setLaF(JFrame window) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(window);
        } catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | ClassNotFoundException var3) {
            var3.printStackTrace();
        }
    }

    //设置背景图片
    public static void setbackground(JFrame window,String iconName) {
        ImageIcon img = new ImageIcon(iconName);
        JLabel background = new JLabel(img);
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        window.setSize(img.getIconWidth(), img.getIconHeight());
        window.add(background);
    }

    public static void setBottonIcon(JButton button,String path){
        ImageIcon img = new ImageIcon(path);
        img.setImage(img.getImage().getScaledInstance(button.getWidth()/4, button.getHeight()/3, Image.SCALE_DEFAULT ));
        button.setIcon(img);
        button.setIconTextGap(5);
    }
}
