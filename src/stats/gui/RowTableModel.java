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
public class RowTableModel extends AbstractTableModel {

private Table table;

RowTableModel(Table table) {
    this.table = table;
}

@Override
public int getRowCount() {
    return table.rows();
}

@Override
public int getColumnCount() {
    return 1;
}

@Override
public Object getValueAt(int rowIndex, int columnIndex) {
    return rowIndex + 1;
}

}
