/*
 * Created by JFormDesigner on Thu Nov 17 19:50:24 CST 2022
 */

package view.user;

import java.util.*;
import dao.BorrowDao;
import model.BorrowBook;
import net.jdbc.utils.FileUtils;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.util.List;
import java.util.Vector;

/**
 * @author 1
 */
public class HistoryPanel extends JPanel {
    public HistoryPanel() {
        initComponents();
    }

    public void addBook(){
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0);
        List<BorrowBook> borrowBookList = BorrowDao.getMyBorrowBook(FileUtils.readUserID());
        for(BorrowBook borrowBook : borrowBookList){
            Vector vector = new Vector<>();
            vector.add(borrowBook.getId());
            vector.add(borrowBook.getBookname());
            vector.add(borrowBook.getAuthor());
            vector.add(borrowBook.getBooktype());
            vector.add(borrowBook.getBtime());
            vector.add(borrowBook.getRtime());
            dtm.addRow(vector);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        scrollPane1 = new JScrollPane();
        table = new JTable();

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
                    {null, "", null, null, null, null},
                },
                new String[] {
                    "\u7f16\u53f7", "\u4e66\u540d", "\u4f5c\u8005", "\u7c7b\u578b", "\u501f\u4e66\u65e5\u671f", "\u8fd8\u4e66\u65e5\u671f"
                }
            ) {
                Class<?>[] columnTypes = new Class<?>[] {
                    Object.class, Object.class, Object.class, Object.class, Date.class, Date.class
                };
                boolean[] columnEditable = new boolean[] {
                    false, false, false, false, false, false
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
            table.setRowHeight(40);
            scrollPane1.setViewportView(table);
        }
        add(scrollPane1);
        scrollPane1.setBounds(10, 5, 965, 780);

        setPreferredSize(new Dimension(990, 800));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        addBook();
        table.getTableHeader().setReorderingAllowed(false);
        setBackground(Color.white);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane scrollPane1;
    private JTable table;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
