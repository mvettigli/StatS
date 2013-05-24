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

public RowTableModel(Table table) {
    this.table = table;
    // TODO: check if table is null
}

/**
 * Returns the number of rows in the table.
 */
@Override
public int getRowCount() {
    return table.rows();
}

/**
 * Returns always one, because {@code row_table} is a single
 * column-table holding rows number.
 */
@Override
public int getColumnCount() {
    return 1;
}

/**
 * Returns new index of row plus one, because {@code rowIndex} starts
 * from zero.
 */
@Override
public Object getValueAt(int rowIndex, int columnIndex) {
    return rowIndex + 1;
}

}
