/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stats.gui;

import javax.swing.table.AbstractTableModel;
import stats.core.Table;

/**
 *
 * @author marco
 */
public class MainTableModel extends AbstractTableModel {

    private Table table;
    boolean isEditable;

    MainTableModel(Table table, boolean isEditable) {
        this.table = table;
        this.isEditable = isEditable;
    }
    
    @Override
    public int getRowCount() {
        return table.rows();
    }

    @Override
    public int getColumnCount() {
        return table.columns();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return table.get(columnIndex, rowIndex);
    }

    @Override
    public String getColumnName(int column) {
        return table.getColumnName(column);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return isEditable;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        table.set(columnIndex, rowIndex, aValue.toString());
    }
    
}
