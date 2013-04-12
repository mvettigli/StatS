/* ----------------------------------------------------------------------------
 * File: MainTableColumnModel.java
 * Date: April 5th, 2013
 * ----------------------------------------------------------------------------
 */
package stats.gui;

import javax.swing.event.TableColumnModelEvent;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import stats.core.DataType;
import stats.core.Table;

/**
 *
 * @author marco
 */
public class MainTableColumnModel extends DefaultTableColumnModel {

private Table table;

public MainTableColumnModel(Table table) {
    this.table = table;
    setColumnSelectionAllowed(true);
}

public void insertColumns(int index, DataType type, int number) {


    // insert columns in the Table object
    boolean areColsInserted = false,
            areColsAppended = false;
    index++;
    if (index == table.columns())
    {
        areColsInserted = table.addColumns(type, number);
        areColsAppended = true;
    } else areColsInserted = table.insertColumns(index, type, number);
    if (!areColsInserted) return;

    // translate current columns indexes in the model
    if (!areColsAppended)
    {
        for (int i = index; i < table.columns() - number; i++)
            tableColumns.elementAt(i).setModelIndex(i + number);
    }
    // insert columns in the model       
    for (int i = index; i < index + number; i++)
    {
        TableColumn aColumn = new TableColumn();
        aColumn.setIdentifier(table.getColumnName(i));
        aColumn.setHeaderValue(table.getColumnName(i));
        aColumn.setModelIndex(i);
        tableColumns.insertElementAt(aColumn, i);
        aColumn.addPropertyChangeListener(this);
    }
    // Post columnAdded event notification
    fireColumnAdded(new TableColumnModelEvent(this, 0,
            getColumnCount() - 1));
}

public void removeColumns(int index, int number) {

    if (!table.removeColumns(index, number)) return;

    for (int i = index + number; i < table.columns(); i++)
        tableColumns.elementAt(i).setModelIndex(i - number);

    for (int i = index; i < index + number; i++)
    {
        tableColumns.get(i).removePropertyChangeListener(this);
        tableColumns.removeElementAt(i);
    }


    // Post columnRemoved event notification.
    fireColumnRemoved(new TableColumnModelEvent(this,
            index, index + number));
}



}
