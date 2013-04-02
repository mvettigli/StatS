/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stats.gui;

import javax.swing.table.AbstractTableModel;
import stats.core.DataType;
import stats.core.Table;

/**
 *
 * @author marco
 */
public class TableModel extends AbstractTableModel {

    private Table table;

    TableModel() {
        this.table = new Table();
    }

    TableModel(String name) {
        this.table = new Table(name);
    }

    TableModel(Table table) {
        this.table = table;
    }

    public String name() {
        return table.name();
    }

    public DataType getColumnType(int column) {
        return table.getColumnType(column);
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
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        table.set(columnIndex, rowIndex, aValue.toString());
    }
}
