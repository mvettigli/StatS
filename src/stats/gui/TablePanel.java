/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stats.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.plaf.metal.MetalBorders;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import stats.core.DataType;
import stats.core.Table;

/**
 *
 * @author marco
 */
public class TablePanel extends javax.swing.JPanel {

/**
 * Creates new form TablePanel
 */
public TablePanel() {
    // initialize TablePanel
    initComponents();
    // initialize Table object
    table = new Table();
    // initialize column and table models
    table_model = new MainTableModel(table);
    column_model = new MainTableColumnModel(table);
    // initialize main and row JTable objects
    initMainTable();
    initRowTable();
    // initialize table main button
    table_main_button.setText(table.name());
    table_main_button.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(table);
    }
    });
    // setting of popup menus
    initPopUpMenuColumns();
    initPopupMenuRows();
    main_table.getTableHeader().addMouseListener(new MouseListener() {
    @Override
    public void mouseClicked(MouseEvent e) {


        // get the column pointed by the mouse
        int col = main_table.getTableHeader()
                .columnAtPoint(e.getPoint());
        if (col == -1)
        {
            if (e.getButton() == MouseEvent.BUTTON1
                    && e.getClickCount() == 2)
            {
                column_model.insertColumns(
                        main_table.getColumnCount() - 1,
                        DataType.CHARACTER, 1);
            }
            return;

        }
        boolean isColumnSelected = main_table.isColumnSelected(col);

        if (e.getButton() == MouseEvent.BUTTON1
                && e.getClickCount() == 1)
        {
            // if control key is not pressed clear the selection
            if (!e.isControlDown())
            {
                main_table.clearSelection();
                isColumnSelected = false;
            }
            // select or unselect column based on current selection
            if (isColumnSelected)
                main_table.removeColumnSelectionInterval(col, col);
            else main_table.addColumnSelectionInterval(col, col);

        }
        // check if 
        if (e.getButton() == MouseEvent.BUTTON3)
        {
            if (!isColumnSelected)
            {
                main_table.clearSelection();
                main_table.addColumnSelectionInterval(col, col);
            }
            //popup_columns.setLocation(e.getPoint());
            popup_columns.show(e.getComponent(), e.getX(), e.getY());
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    });
    row_table.setComponentPopupMenu(popup_rows);
    // initialialize column list
    columns_list.setFocusable(false);
    columns_list.setModel(new DefaultListModel());
    columns_list.setCellRenderer(new ColumnListRenderer());
    columns_list.addMouseListener(new MouseListener() {
    @Override
    public void mouseClicked(MouseEvent e) {
        // get the column pointed by the mouse and if selected
        int index = columns_list.locationToIndex(e.getPoint());
        boolean isColumnSelected = main_table.isColumnSelected(index);
        // handle column selection
        if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 1)
        {
            // if control key is not pressed clear the selection
            if (!e.isControlDown())
            {
                main_table.clearSelection();
                isColumnSelected = false;
            }
            // select or unselect column based on current selection
            if (isColumnSelected)
                main_table.removeColumnSelectionInterval(index, index);
            else main_table.addColumnSelectionInterval(index, index);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    });
    // update column list
    updateColumnList();
    updateColumnListSelection();
    // initialize row list
    rows_list.setModel(new DefaultListModel());
    // update row list
    updateRowList();
    // setting column model
    column_model.addColumnModelListener(new TableColumnModelListener() {
    @Override
    public void columnAdded(TableColumnModelEvent e) {
        updateColumnList();
        updateColumnListSelection();
    }

    @Override
    public void columnRemoved(TableColumnModelEvent e) {
        updateColumnList();
        updateColumnListSelection();
    }

    @Override
    public void columnMoved(TableColumnModelEvent e) {
        updateColumnList();
        updateColumnListSelection();

    }

    @Override
    public void columnMarginChanged(ChangeEvent e) {
    }

    @Override
    public void columnSelectionChanged(ListSelectionEvent e) {
        // repaint the table header to update seletion
        updateColumnListSelection();
        main_table.getTableHeader().repaint();
    }
    });
}

/**
 * This method is called from within the constructor to initialize the form.
 * WARNING: Do NOT modify this code. The content of this method is always
 * regenerated by the Form Editor.
 */
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        rows_list = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        columns_list = new javax.swing.JList();
        table_main_button = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();

        setAutoscrolls(true);
        setPreferredSize(new java.awt.Dimension(600, 600));

        jSplitPane1.setDividerLocation(150);
        jSplitPane1.setDividerSize(2);

        jSplitPane2.setDividerLocation(350);
        jSplitPane2.setDividerSize(2);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setResizeWeight(0.5);

        rows_list.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rows", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jScrollPane3.setViewportView(rows_list);

        jSplitPane2.setRightComponent(jScrollPane3);

        columns_list.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Columns", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jScrollPane4.setViewportView(columns_list);

        table_main_button.setText("TableName");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(table_main_button, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(table_main_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
        );

        jSplitPane2.setLeftComponent(jPanel1);

        jSplitPane1.setLeftComponent(jSplitPane2);
        jSplitPane1.setRightComponent(scrollPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList columns_list;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JList rows_list;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JButton table_main_button;
    // End of variables declaration//GEN-END:variables
private Table table;
private JTable row_table;
private JTable main_table;
private MainTableModel table_model;
private MainTableColumnModel column_model;
private JPopupMenu popup_columns;
private JPopupMenu popup_rows;
/**
 * Foreground color of table headers.
 */
private static Color header_forecolor = new Color(0, 0, 0);
/**
 * Background color of unselected row and column headers.
 */
private static Color header_unselected_color = new Color(230, 230, 230);
/**
 * Background color of selected row and column headers.
 */
private static Color header_selected_color = new Color(200, 200, 200);

private void initPopUpMenuColumns() {
    // initialize popup elements
    popup_columns = new JPopupMenu();
    JMenuItem popup_cols_properties = new JMenuItem("Properties");
    JMenuItem popup_cols_insert = new JMenuItem("Insert");
    JMenuItem popup_cols_delete = new JMenuItem("Delete");
    // assign listeners
    popup_cols_insert.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // ask user the number of columns to be added
        String col_number = JOptionPane.showInputDialog(
                "Number of columns to be added:", 1);
        // handle user cancel
        if (col_number == null) return;
        // try parsing String answer as integer
        int n;
        try
        {
            n = Integer.parseInt(col_number);
        } catch (NumberFormatException ex)
        {
            // show error message and return
            JOptionPane.showMessageDialog(popup_columns,
                    "\"" + col_number + "\" is not a valid number.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // check if number is valid
        if (n <= 0)
        {
            JOptionPane.showMessageDialog(popup_columns,
                    "Cannot insert " + n + " columns.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // add one column after each selected column
        int[] sel_cols = column_model.getSelectedColumns();
        for (int i = 0; i < sel_cols.length; i++)
            column_model.insertColumns(sel_cols[i] + n * i,
                    DataType.CHARACTER, n);
        // shift selection based on column addition
        ListSelectionModel sel_model = column_model.getSelectionModel();
        for (int i = sel_cols.length - 1; i > 0; i--)
        {
            sel_model.addSelectionInterval(
                    sel_cols[i] + n * i,
                    sel_cols[i] + n * i);
            sel_model.removeSelectionInterval(sel_cols[i], sel_cols[i]);
        }

    }
    });
    popup_cols_delete.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
    }
    });
    // build the column popup
    popup_columns.add(popup_cols_properties);
    popup_columns.addSeparator();
    popup_columns.add(popup_cols_insert);
    popup_columns.add(popup_cols_delete);

}

private void initPopupMenuRows() {
    popup_rows = new JPopupMenu("Rows");
    JMenuItem popup_rows_marker = new JMenuItem("Marker");
    JMenuItem popup_rows_size = new JMenuItem("Size");
    JMenuItem popup_rows_insert = new JMenuItem("Insert");
    JMenuItem popup_rows_delete = new JMenuItem("Delete");

    popup_rows_insert.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        //table.addRow();
        //main_table.repaint();
        //row_table.repaint();
    }
    });
    popup_rows.add(popup_rows_marker);
    popup_rows.add(popup_rows_size);
    popup_rows.addSeparator();
    popup_rows.add(popup_rows_insert);
    popup_rows.add(popup_rows_delete);
}

private void updateColumnList() {
    // initialize variables
    DefaultListModel list_model = (DefaultListModel) columns_list.getModel();
    // fill the list model with column type and name. It will be parsed by
    // ColumnListRenderer to show a label with column type icon
    list_model.clear();
    for (int i = 0; i < table.columns(); i++)
    {
        String element = table.getColumnType(i) + "_" + table.getColumnName(i);
        list_model.addElement(element);
    }
}

private void updateColumnListSelection() {
    int[] selected_columns = column_model.getSelectedColumns();
    TitledBorder border = (TitledBorder) columns_list.getBorder();
    // set column list title with selected and current column number
    border.setTitle("Columns(" + selected_columns.length + "/"
            + table.columns() + ")");
    columns_list.setSelectedIndices(selected_columns);
    columns_list.repaint();
}

private void updateRowList() {
}

private void initMainTable() {

    main_table = new JTable(table_model, column_model);
    main_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    main_table.setAutoCreateColumnsFromModel(true);
    main_table.getTableHeader().setPreferredSize(new Dimension(100, 30));
    // set the render of the table header
    main_table.getTableHeader().setDefaultRenderer(new TableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        // Create new JLabel to show the content of the header
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setText(value.toString());    // actual class is String
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(header_forecolor);
        label.setBorder(new MetalBorders.TableHeaderBorder());
        /* because the method returns always false, the column selection 
         * must be checked through JTable object. */
        isSelected = main_table.isColumnSelected(column);
        if (isSelected) label.setBackground(header_selected_color);
        else label.setBackground(header_unselected_color);
        // return the JLabel
        return label;
    }
    });
    // set the table in the scrollpane
    scrollPane.setViewportView(main_table);

}

private void initRowTable() {
    // initialize row table and define the model
    row_table = new JTable(new AbstractTableModel() {
    @Override
    public int getRowCount() {
        return table.rows();
    }

    @Override
    public int getColumnCount() {
        return 1;   // single-column table
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rowIndex + 1;    // returns row index
    }
    });
    row_table.setSelectionModel(main_table.getSelectionModel());
    row_table.setDefaultRenderer(Object.class, new TableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setText(value.toString());
        label.setForeground(header_forecolor);
        if (isSelected) label.setBackground(header_selected_color);
        else label.setBackground(header_unselected_color);
        label.setHorizontalAlignment(JLabel.RIGHT);
        return label;
    }
    });
    // initialize the viewport for 
    Dimension fixedSize = new Dimension(60, 0);
    JViewport row_view = new JViewport();
    row_view.setView(row_table);
    row_view.setPreferredSize(fixedSize);
    row_view.setMaximumSize(fixedSize);

    scrollPane.setRowHeaderView(row_view);
}

}
