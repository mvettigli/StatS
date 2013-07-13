/* ============================================================================
 *                   StatS - STatistical Analysis ToolS
 * ============================================================================
 *
 *   File: RowTableModel.java
 *   Date: Apr 2, 2013
 *
 * ---------------------------------------------------------------------------- 
 *
 * Copyright (C) 2013 M. Vettigli <m.vettigli@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * ----------------------------------------------------------------------------
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
