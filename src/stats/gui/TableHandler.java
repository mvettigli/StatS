/* ----------------------------------------------------------------------------
 * File: TableHandler.java
 * Date: May 16th, 2013
 * ----------------------------------------------------------------------------
 */
package stats.gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalBorders;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import stats.core.*;

/**
 *
 * @author marco
 */
public class TableHandler extends javax.swing.JPanel {

/**
 * Table object holding data.
 */
private stats.core.Table table;

/**
 * Read-only status of the TableHandler object.
 */
private boolean isEditable;

/**
 * The JTable object showing data contained in table object.
 */
private javax.swing.JTable main_table;

/**
 * The JTable object showing row numbers.
 */
private javax.swing.JTable row_table;

/**
 * Row selection status.
 */
private ListSelectionModel row_selectionModel;

/**
 * Column selection status.
 */
private ListSelectionModel col_selectionModel;

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

/**
 * Foreground color of table headers.
 */
private static Color cell_forecolor = new Color(0, 0, 0);

/**
 * Background color of selected cells.
 */
private static Color cell_selected_color = new Color(0, 191, 255);

/**
 * Background color of unselected cells.
 */
private static Color cell_unselected_color = new Color(255, 255, 255);

/**
 * Background color of shaded cells.
 */
private static Color cell_shaded_color = new Color(191, 239, 255);

/**
 * Creates new form TableHandler
 */
public TableHandler() {

    // initialize form components
    initComponents();

    // initialize table object and read-only status
    table = new Table();
    isEditable = true;      // TODO: to be set false by default

    // initialize table name label
    tableNameLabel.setText(table.name());

    // initialize selection model
    row_selectionModel = new DefaultListSelectionModel();
    col_selectionModel = new DefaultListSelectionModel();
    row_selectionModel.addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        main_table.repaint();
    }
    });
    col_selectionModel.addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        main_table.getTableHeader().repaint();
        main_table.repaint();
    }
    });

    // initialize row_table
    row_table = new JTable(new RowTableModel(table)) {
    /**
     * The method {@code changeSelection} is overridden in order to handle
     * multiple toggle selection.
     */
    @Override
    public void changeSelection(int rowIndex, int columnIndex,
            boolean toggle, boolean extend) {

        // check if shift key is pressed for range selection
        if (extend)
        {
            int lastIndex = row_selectionModel.getLeadSelectionIndex();
            row_selectionModel.addSelectionInterval(lastIndex, rowIndex);
            return;
        }
        // check if control key is pressed for range deselection
        if (toggle)
        {
            int lastIndex = row_selectionModel.getLeadSelectionIndex();
            row_selectionModel.removeSelectionInterval(lastIndex, rowIndex);
            return;
        }
        // select or unselect based on current selection
        if (row_selectionModel.isSelectedIndex(rowIndex))
            row_selectionModel.removeSelectionInterval(rowIndex, rowIndex);
        else row_selectionModel.addSelectionInterval(rowIndex, rowIndex);
    }
    };
    row_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    row_table.setSelectionModel(row_selectionModel);
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


    // initialize main_table object
    main_table = new JTable(new MainTableModel(table, isEditable));
    main_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    main_table.setSelectionModel(row_selectionModel);
    main_table.getColumnModel().setSelectionModel(col_selectionModel);
    main_table.setDefaultRenderer(Object.class, new TableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        Data data = (Data) value;
        JLabel label = new JLabel();
        label.setOpaque(true);
        // set cell content based on Data value
        if (data.isEmpty() && data.type() == DataType.NUMERIC)
            label.setText(".");
        else label.setText(data.toString());
        // set cell foreground and background based on selection
        label.setForeground(cell_forecolor);
        if (isSelected) label.setBackground(cell_selected_color);
        else if (row_selectionModel.isSelectedIndex(row)
                || col_selectionModel.isSelectedIndex(column))
            label.setBackground(cell_shaded_color);
        else label.setBackground(cell_unselected_color);
        // set alignment based on Data type
        switch (data.type())
        {
            case NUMERIC:
                label.setHorizontalAlignment(JLabel.RIGHT);
                break;
            case CHARACTER:
            case UNDEFINED:
                label.setHorizontalAlignment(JLabel.LEFT);
        }
        // return the JLabel
        return label;
    }
    });
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
        label.setPreferredSize(new Dimension(50, 30));
        /* because the method returns always false, the column selection 
         * must be checked through JTable object. */
        isSelected = main_table.isColumnSelected(column);
        if (isSelected) label.setBackground(header_selected_color);
        else label.setBackground(header_unselected_color);
        // return the JLabel
        return label;
    }
    });
    main_table.getTableHeader().addMouseListener(new MouseListener() {
    @Override
    public void mouseClicked(MouseEvent e) {
        // get index of clicked column
        int colIndex = main_table.getTableHeader().columnAtPoint(e.getPoint());
        // manage click on null column
        if (colIndex == -1)
        {
            // manage column insertion at the end of the table
            if (isEditable
                    && e.getButton() == MouseEvent.BUTTON1
                    && e.getClickCount() == 2)
            {
                int new_colIndex = table.columns();
                table.addColumn();
                TableColumn tableColumn = new TableColumn();
                tableColumn.setModelIndex(new_colIndex);
                tableColumn.setHeaderValue(table.getColumnName(new_colIndex));
                main_table.addColumn(tableColumn);
            }
            // this return statement prevents improper usage of null index
            return;
        }
        // manage column selection
        if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 1)
        {
            // check if shift button is pressed to select a range
            if (e.getModifiersEx() == MouseEvent.SHIFT_DOWN_MASK)
            {
                int lastIndex = col_selectionModel.getLeadSelectionIndex();
                col_selectionModel.addSelectionInterval(lastIndex, colIndex);
                return;

            }
            // check if control button is pressed to unselect a range
            if (e.getModifiersEx() == MouseEvent.CTRL_DOWN_MASK)
            {
                int lastIndex = col_selectionModel.getLeadSelectionIndex();
                col_selectionModel.removeSelectionInterval(lastIndex, colIndex);
                return;
            }
            // manage single column selection
            if (!col_selectionModel.isSelectedIndex(colIndex))
                col_selectionModel.addSelectionInterval(colIndex, colIndex);
            else col_selectionModel.removeSelectionInterval(colIndex, colIndex);
        }
        // manage popup call
        if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 1)
        {
            // if clicked column is not selected set it as unique selection
            if (!col_selectionModel.isSelectedIndex(colIndex))
            {
                col_selectionModel.clearSelection();
                col_selectionModel.addSelectionInterval(colIndex, colIndex);
            }
            // set menu item enable state
            col_character.setEnabled(isEditable);
            col_numeric.setEnabled(isEditable);
            col_rename.setEnabled(isEditable);
            col_insert.setEnabled(isEditable);
            col_delete.setEnabled(isEditable);
            // set column type on selection
            int[] selected_cols = getSelectedColumns();
            if (selected_cols.length == 1)
            {
                // if single column is selected show its type
                col_character.setSelected(table.getColumnType(selected_cols[0])
                        == DataType.CHARACTER);
                col_numeric.setSelected(table.getColumnType(selected_cols[0])
                        == DataType.NUMERIC);
            } else
            {
                // for multiple selection let both unselected
                col_character.setSelected(false);
                col_numeric.setSelected(false);
            }
            // show the column popup
            cols_PopUp.show(main_table.getTableHeader(), e.getX(), e.getY());
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

    JViewport row_view = new JViewport() {
    /**
     * Override getPreferredSize in order to allow proper scrolling.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(row_table.getPreferredSize());
    }
    };
    row_view.setView(row_table);
    scrollPane.setRowHeaderView(row_view);
    scrollPane.setViewportView(main_table);
    scrollPane.getRowHeader().addMouseListener(new MouseListener() {
    @Override
    public void mouseClicked(MouseEvent e) {
        // manage row insertion at the end of the table
        if (isEditable
                && e.getButton() == MouseEvent.BUTTON1
                && e.getClickCount() == 2)
        {
            // add row to Table object
            int rowIndex = table.rows();
            table.addRow();
            // update table models
            RowTableModel row_model = (RowTableModel) row_table.getModel();
            MainTableModel main_model = (MainTableModel) main_table.getModel();
            row_model.fireTableRowsInserted(rowIndex, rowIndex);
            main_model.fireTableRowsInserted(rowIndex, rowIndex);
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

}

/**
 * Returns the row selection model of the {@code TableHandler}.
 *
 * @return the row selection model.
 */
public ListSelectionModel getRowSelectionModel() {
    return row_selectionModel;
}

/**
 * Returns the column selection model of the {@code TableHandler}.
 *
 * @return the column selection model.
 */
public ListSelectionModel getColSelectionModel() {
    return col_selectionModel;
}

/**
 * Returns a list of indexes representing currently selected columns.
 *
 * @return a list of selected column indexes.
 */
public int[] getSelectedColumns() {
    // store all indexes of selected columns in a list
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < table.columns(); i++)
        if (col_selectionModel.isSelectedIndex(i)) list.add(i);
    // convert a list of Integer to an int array
    int[] result = new int[list.size()];
    for (int i = 0; i < list.size(); i++)
        result[i] = list.get(i);
    return result;
}

/**
 * Returns a list of indexes representing currently selected rows.
 *
 * @return a list of selected row indexes.
 */
public int[] getSelectedRows() {
    // store all indexes of selected columns in a list
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < table.rows(); i++)
        if (row_selectionModel.isSelectedIndex(i)) list.add(i);
    // convert a list of Integer to an int array
    int[] result = new int[list.size()];
    for (int i = 0; i < list.size(); i++)
        result[i] = list.get(i);
    return result;
}

/**
 * This method is called from within the constructor to
 * initialize the form.
 * WARNING: Do NOT modify this code. The content of this method is
 * always regenerated by the Form Editor.
 */
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cols_PopUp = new javax.swing.JPopupMenu();
        col_rename = new javax.swing.JMenuItem();
        col_type = new javax.swing.JMenu();
        col_character = new javax.swing.JRadioButtonMenuItem();
        col_numeric = new javax.swing.JRadioButtonMenuItem();
        col_separator = new javax.swing.JPopupMenu.Separator();
        col_insert = new javax.swing.JMenuItem();
        col_delete = new javax.swing.JMenuItem();
        rows_PopUp = new javax.swing.JPopupMenu();
        scrollPane = new javax.swing.JScrollPane();
        toolBar = new javax.swing.JToolBar();
        tableNameLabel = new javax.swing.JLabel();
        toolBarFiller = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));

        col_rename.setText("Rename");
        col_rename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                col_renameActionPerformed(evt);
            }
        });
        cols_PopUp.add(col_rename);

        col_type.setText("Column type");

        col_character.setSelected(true);
        col_character.setText("Character");
        col_type.add(col_character);

        col_numeric.setSelected(true);
        col_numeric.setText("Numeric");
        col_type.add(col_numeric);

        cols_PopUp.add(col_type);
        cols_PopUp.add(col_separator);

        col_insert.setText("Insert");
        cols_PopUp.add(col_insert);

        col_delete.setText("Delete");
        cols_PopUp.add(col_delete);

        tableNameLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableNameLabel.setText("Table name");
        toolBar.add(tableNameLabel);
        toolBar.add(toolBarFiller);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane)
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

/**
 *
 * @param evt
 */
    private void col_renameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_col_renameActionPerformed
        // get selected columns and check for empty selection
        int[] selected_cols = getSelectedColumns();
        if (selected_cols.length == 0)
            throw new IllegalArgumentException("Empty column selection.");
        // ask for a new column name
        String rootName = JOptionPane.showInputDialog(this,
                "Insert new column name", "Rename column(s)...",
                JOptionPane.QUESTION_MESSAGE);
        // check if rename operation was aborted
        if (rootName == null) return;
        // check if all column names are valid
        String columnName = rootName;
        String error_log = new String();
        boolean error_state = false;
        for (int i = 0; i < selected_cols.length; i++)
        {
            if (selected_cols.length != 1) columnName = rootName + (i + 1);
            // generate an error message if column name is not valid
            if (!table.isColumnNameValid(columnName))
            {
                error_state = true;
                error_log += "ERROR: " + (columnName.isEmpty()
                        ? "Empty name" : "\"" + columnName + "\"\n");
            }
        }
        // show an error message with details of unvalid column names
        if (error_state)
        {
            JOptionPane.showMessageDialog(this,
                    "Column name(s) must be unique inside the table and "
                    + "cannot be empty.\n\n" + error_log,
                    "Invalid column name(s)",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        // for each selected column change column name
        columnName = rootName;
        for (int i = 0; i < selected_cols.length; i++)
        {
            if (selected_cols.length != 1) columnName = rootName + (i + 1);
            // change column name in the table and update TableColumnModel
            table.setColumnName(selected_cols[i], columnName);
            TableColumn column = main_table.getColumnModel()
                    .getColumn(selected_cols[i]);
            column.setHeaderValue(columnName);
        }
        // update main table
        main_table.getTableHeader().repaint();
    }//GEN-LAST:event_col_renameActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButtonMenuItem col_character;
    private javax.swing.JMenuItem col_delete;
    private javax.swing.JMenuItem col_insert;
    private javax.swing.JRadioButtonMenuItem col_numeric;
    private javax.swing.JMenuItem col_rename;
    private javax.swing.JPopupMenu.Separator col_separator;
    private javax.swing.JMenu col_type;
    private javax.swing.JPopupMenu cols_PopUp;
    private javax.swing.JPopupMenu rows_PopUp;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel tableNameLabel;
    private javax.swing.JToolBar toolBar;
    private javax.swing.Box.Filler toolBarFiller;
    // End of variables declaration//GEN-END:variables

}
