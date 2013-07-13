/* ============================================================================
 *                   StatS - STatistical Analysis ToolS
 * ============================================================================
 *
 *   File: MainTableModel.java
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
import stats.core.Data;
import stats.core.Table;

/**
 * The {@code MainTableModel} class implements an {@link AbstractTableModel}
 * for model-view pattern. to be used as interface for handle {@link Table}
 * content with {@link JTable} objects.
 *
 * @author M. Vettigli
 * @version 1.0
 */
public class MainTableModel extends AbstractTableModel {

  /**
   * Reference to table object storing actual data.
   */
  private Table table;

  /**
   * Status variable for read and write privileges.
   */
  boolean isEditable;

  /**
   * Constructor for {@code MainTableModel} object given source table
   * and read-write status variable. The read-only status determines whether
   * or not the table content is editable.
   *
   * @param table the underlying data table.
   * @param isEditable true if table is editable, else false.
   */
  MainTableModel(Table table, boolean isEditable) {
    // initialize instance variables
    this.table = table;
    this.isEditable = isEditable;
  }

  /**
   * Returns the number of rows currently stored in the table.
   *
   * @return number of rows.
   */
  @Override
  public int getRowCount() {
    return table.rows();
  }

  /**
   * Returns the number of columns currently stored in the table.
   *
   * @return number of columns.
   */
  @Override
  public int getColumnCount() {
    return table.columns();
  }

  /**
   * Returns the name of the column reference by index.
   *
   * @param column the index of the column.
   * @return the name of the column.
   */
  @Override
  public String getColumnName(int column) {
    return table.getColumnName(column);
  }

  /**
   * Checks if the cell content is editable. By default, editable status is
   * referred to the whole table and not to single cells.
   *
   * @param rowIndex row index of the cell.
   * @param columnIndex column index of the cell.
   * @return true if cell is editable, else false.
   */
  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return isEditable;
  }

  /**
   * Returns the content of a cell stored in the table object. For
   * retrieving the data, the table getter function is called. Further
   * formatting must be performed through {@link JTable} interface.
   *
   * @param rowIndex row index of the cell.
   * @param columnIndex column index of the cell.
   * @return cell content as {@link Data} object.
   */
  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    return table.get(columnIndex, rowIndex);
  }

  /**
   * Set the content of a given cell to defined value. The new value is
   * parsed as {@code toString()} method of the passed argument object.
   *
   * @param aValue new value to be set.
   * @param rowIndex row index of the cell.
   * @param columnIndex column index of the cell.
   */
  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    table.set(columnIndex, rowIndex, aValue.toString());
  }

}
