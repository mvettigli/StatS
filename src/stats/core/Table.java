/* ----------------------------------------------------------------------------
 * File: Table.java
 * Date: April 1st, 2013
 * ----------------------------------------------------------------------------
 */
package stats.core;

import java.util.ArrayList;

/**
 * The {@code Table} class is the fundamental data structure used in
 * StatS. It is an implementation of a two-dimensional array of {@link Data}
 * objects. The data are organized in columns each with a defined data type
 * (see {@link DataType}. Each column is named and unique inside a {@code
 * Table object}
 *
 * @author M. Vettigli
 * @version 1.0
 */
public class Table {

/**
 * Unique identifier for column naming.
 */
private static int untitled_column = 1;

/**
 * Unique identifier for table naming.
 */
private static int untitled_table = 1;

/**
 * Default number of rows inserted during initialization.
 */
private static int DEFAULT_ROWS = 10;

/**
 * Variable storing the name of the table.
 */
private String table_name;

/**
 * Variable storing the current number of columns.
 */
private int table_cols;

/**
 * Variable storing the current number of rows.
 */
private int table_rows;

/**
 * The {@code columns} array stores the actual data table as a collection
 * of {@code Array} objects.
 */
private ArrayList<Array> columns;

/**
 * Default constructor for {@code Table} class. The table is initialized
 * with untitled name and one {@code DataType.CHARACTER} column of 10 rows.
 */
public Table() {
    this(newUntitledName());
}

/**
 * Constructor for {@code Table} class with name argument. If the provided
 * table name is null, an untitled one is used.
 *
 * @param name the name of the table.
 */
public Table(String name) {
    // initialize column array with a single Array 
    // of DEFAULT_ROWS Characher elements
    columns = new ArrayList<>();
    Array first_column = new Array(this.getUntitledColumn());
    first_column.add(new Character());
    first_column.fill(DEFAULT_ROWS);
    columns.add(first_column);
    // initialize table_rows and table_cols
    table_cols = 1;
    table_rows = DEFAULT_ROWS;
    // initialize table_name
    if (name.isEmpty())
    {
        table_name = newUntitledName();
    } else
    {
        table_name = name;
    }
}

/**
 * The function returns the number of columns in the {@code Table}
 * object.
 *
 * @return current number of columns.
 */
public int columns() {
    return table_cols;
}

/**
 * The function returns the number of rows in the {@code Table} object.
 *
 * @return current number of rows.
 */
public int rows() {
    return table_rows;
}

/**
 * The function returns the name of the {@code Table} object.
 *
 * @return the name of the table.
 */
public String name() {
    return table_name;
}

/**
 * The function sets the name of the {@code Table} object. Null names are
 * not allowed.
 *
 * @param name the new name of the table.
 * @return true if successful, else false.
 */
public boolean setName(String name) {
    if (name.isEmpty()) return false;
    table_name = name;
    return true;
}

/**
 * The function returns the name of the column at a given position in
 * the {@code Table} object.
 *
 * @param col the index of the column.
 * @return the name of the column.
 * @throws ArrayIndexOutOfBoundsException if the column index is not valid.
 */
public String getColumnName(int col) {
    return columns.get(col).name();
}

/**
 * Sets the name of a column of {@code Table} object at a specified
 * position.
 *
 * @param col the index of the column.
 * @param name new name of the column.
 * @return true is successful, else false.
 * @throws ArrayIndexOutOfBoundsException if the column index is not valid.
 */
public boolean setColumnName(int col, String name) {
    // check if index is valid
    if (!isColumnIndex(col)) throw new ArrayIndexOutOfBoundsException(
                "col=" + col + " is not a valid column index in " + table_name);
    // check if name column is calid
    if (name.isEmpty()) return false;
    // check if the name is already used as column name in the table
    for (int i = 0; i < table_cols; i++)
        if (columns.get(i).name().equals(name) && i != col) return false;
    columns.get(col).setName(name);
    return true;
}

/**
 * Returns the type of data stored in a given column in the {@code
 * Table} object. The returned type is a member of the {@link
 * stats.core.DataType} enumeration.
 *
 * @param col index of the column.
 * @return data type of the column as {@link stats.core.DataType}.
 * @throws ArrayIndexOutOfBoundsException if the column index is not valid.
 */
public DataType getColumnType(int col) {
    if (!isColumnIndex(col)) throw new ArrayIndexOutOfBoundsException(
                "col=" + col + " is not a valid column index in " + table_name);
    return columns.get(col).type();
}

/**
 * Checks if the integer number is a valid column index for the the
 * {@code Table} object.
 *
 * @param col index of the column.
 * @return true if valid, else false.
 */
public boolean isColumnIndex(int col) {
    return (col >= 0 && col < table_cols);
}

/**
 * Checks if the integer number is a valid row index for the the
 * {@code Table} object.
 *
 * @param row index of the row.
 * @return true if valid, else false.
 */
public boolean isRowIndex(int row) {
    return (row >= 0 && row < table_rows);
}

/**
 * Checks if the string is a valid column name for renaming or insertion.
 *
 * @param name the name of the column
 * @return true if unique and not null, else false.
 */
public boolean isColumnNameValid(String name) {
    boolean uniqueness = true;
    // check if column name is unique in the table
    for (int i = 0; i < table_cols; i++)
        uniqueness &= !name.equals(columns.get(i).name());
    // check if it is null and return result
    uniqueness &= !name.isEmpty();
    return uniqueness;
}

/**
 * Inserts a new column of at the beginning of the {@code Table} object.
 * The type of the column is {@code DataType.CHARACTER} by default.
 *
 * @return true if successful, else false.
 */
public boolean insertColumn() {
    return this.insertColumns(0, DataType.CHARACTER, 1);
}

/**
 * Inserts a new column of at the beginning of the {@code Table} object.
 * The type of the column is specified by the user.
 *
 * @param type the type of new column.
 * @return true if successful, else false.
 */
public boolean insertColumn(DataType type) {
    return this.insertColumns(0, type, 1);
}

/**
 * Inserts a new column at a specified position in the {@code Table}
 * object. The type of the column is {@code DataType.CHARACTER} by default.
 *
 * @param col position in the table for insertion.
 * @return true if successful, else false.
 * @throws ArrayIndexOutOfBoundsException if the column index is not valid.
 */
public boolean insertColumn(int col) {
    return this.insertColumns(col, DataType.CHARACTER, 1);
}

/**
 * Inserts a new column of at a specified position in the {@code Table}
 * object. The type of the column is specified by the user.
 *
 * @param col position in the table for insertion.
 * @param type type of the new column.
 * @return true if successful, else false.
 * @throws ArrayIndexOutOfBoundsException if the column index is not valid.
 */
public boolean insertColumn(int col, DataType type) {
    return this.insertColumns(col, type, 1);
}

/**
 * Inserts a given number of columns at the beginning of the {@code
 * Table} object. The type of the columns is {@code DataType.CHARACTER} by
 * default.
 *
 * @param number number of columns to be inserted.
 * @return true if successful, else false.
 */
public boolean insertColumns(int number) {
    return this.insertColumns(0, DataType.CHARACTER, number);
}

/**
 * Inserts a given number of columns at the beginning of the {@code
 * Table} object. The type of the columns is specified by the user.
 *
 * @param type type of the new columns.
 * @param number number of columns to be inserted.
 * @return true if successful, else false.
 */
public boolean insertColumns(DataType type, int number) {
    return this.insertColumns(0, type, number);
}

/**
 * Inserts a given number of columns at a specific position of the
 * {@code Table} object. The type of the columns is specified by the user.
 *
 * @param col the position of the insertion.
 * @param type type of the new columns.
 * @param number number of columns to be inserted.
 * @return true if successful, else false.
 * @throws ArrayIndexOutOfBoundsException if the column index is not valid.
 */
public boolean insertColumns(int col, DataType type, int number) {
    // check if index is valid
    if (!isColumnIndex(col)) throw new ArrayIndexOutOfBoundsException(
                "col=" + col + " is not a valid column index in " + table_name);
    // check if number is valid
    if (number < 1) return false;
    // insert new columns to the table
    for (int i = 0; i < number; i++)
        columns.add(col + i, this.getEmptyArray(type));
    table_cols += number;
    return true;
}

/**
 * Appends a {@code DataType.CHARACTER} column at the end of the
 * {@code Table} object.
 *
 * @return true if successful, else false.
 */
public boolean addColumn() {
    return this.addColumns(DataType.CHARACTER, 1);
}

/**
 * Appends a column at the end of the {@code Table} object. The data type
 * is specified by the user.
 *
 * @param type the data type of the column to be added.
 * @return true if successful, else false.
 */
public boolean addColumn(DataType type) {
    return this.addColumns(type, 1);
}

/**
 * Appends a given number of {@code DataType.CHARACTER} columns at the
 * end of the {@code Table} object.
 *
 * @param number number of columns to be added.
 * @return true if successful, else false.
 */
public boolean addColumns(int number) {
    return this.addColumns(DataType.CHARACTER, number);
}

/**
 * Appends a given number of columns at the end of the {@code Table}
 * object. The column type is specified by the user.
 *
 * @param type the type of columns to be added.
 * @param number number of columns to be added.
 * @return true if successful, else false.
 */
public boolean addColumns(DataType type, int number) {
    // check if number is valid
    if (number < 1) return false;
    // add new columns to the table
    for (int i = 0; i < number; i++)
        columns.add(this.getEmptyArray(type));
    table_cols += number;
    return true;
}

/**
 * Removes a column at a given position in the {@code Table} object. The
 * underlying {@code Array} data is not deleted but it is dereferenced.
 *
 * @param col the position of the column in the table.
 * @return true if successful, else false.
 * @throws ArrayIndexOutOfBoundsException if the column index is not valid.
 */
public boolean removeColumn(int col) {
    return this.removeColumns(col, 1);
}

/**
 * Removes a given number of columns at a specified position in the
 * {@code Table object. The underlying {@code Array} data is not deleted but
 * it is dereferenced.
 *
 * @param col the position of the first column to be removed.
 * @param number number of columns to be removed.
 * @return true if successful, else false.
 * @throws ArrayIndexOutOfBoundsException if the column index is not valid.
 */
public boolean removeColumns(int col, int number) {
    // check if row index is valid
    if (!isColumnIndex(col)) throw new ArrayIndexOutOfBoundsException(
                "col=" + col + " is not a valid column index in " + table_name);
    // check if number is valid
    if (number < 1) return false;
    // check if there are enough columns to be deleted
    if (col + number > table_cols) return false;
    if (table_cols < number) return false;
    // remove the columns
    for (int i = col; i < col + number; i++)
        columns.remove(col);
    table_cols -= number;
    return true;
}

/**
 * Appends a row at the end of the {@code Table} object. A null element is
 * added to all columns of the table.
 *
 * @return true if successful, else false.
 */
public boolean addRow() {
    return this.addRows(1);
}

/**
 * Appends a given number of rows at the end of the {@code Table} object.
 * The new rows are filled with null elements in each column.
 *
 * @param number number of rows to be added.
 * @return true if successful, else false.
 */
public boolean addRows(int number) {
    // check if number is valid
    if (number < 1) return false;
    // insert rows for each column of the table
    for (int i = 0; i < table_cols; i++)
        columns.get(i).add(number);
    table_rows += number;
    return true;
}

/**
 * Inserts a row at the beginning of the {@code Table} object. Null
 * elements are used to fill each column of the table.
 *
 * @return true if successful, else false.
 */
public boolean insertRow() {
    return this.insertRows(0, 1);
}

/**
 * Inserts a row at a specific position of the {@code Table} object. Null
 * elements are used to fill each column of the table.
 *
 * @param row the position of the row to be inserted.
 * @return true if successful, else false.
 * @throws ArrayIndexOutOfBoundsException if the row index is not valid.
 */
public boolean insertRow(int row) {
    return this.insertRows(row, 1);
}

/**
 * Inserts a given number of rows at the beginning of the {@code Table}
 * object. Null elements are used to fill each column of the table.
 *
 * @param number number of rows to be inserted.
 * @return true if successful, else false.
 * @throws ArrayIndexOutOfBoundsException if the row index is not valid.
 */
public boolean insertRows(int number) {
    return this.insertRows(0, number);
}

/**
 * Inserts a given number of rows at a specific position of the {@code
 * Table} object. Null elements are used to fill each column of the table.
 *
 * @param row the position of the first row to be inserted.
 * @param number number of rows to be inserted.
 * @return true if successful, else false.
 * @throws ArrayIndexOutOfBoundsException if the row index is not valid.
 */
public boolean insertRows(int row, int number) {
    // check if index is valid
    if (!isRowIndex(row)) throw new ArrayIndexOutOfBoundsException(
                "row=" + row + " is not a valid row index in " + table_name);
    // check if number is valid
    if (number < 1) return false;
    // insert rows for each column of the table
    for (int i = 0; i < table_cols; i++)
        columns.get(i).insert(row, number);
    table_rows += number;
    return true;
}

/**
 * Removes a row at a specific position in the {@code Table} object. All
 * underlying data are not deleted but dereferenced.
 *
 * @param row the position of the row to be removed.
 * @return true if successful, else false.
 * @throws ArrayIndexOutOfBoundsException if the row index is not valid.
 */
public boolean removeRow(int row) {
    return this.removeRows(row, 1);
}

/**
 * Removes a given number of rows at a specific position in the {@code
 * Table} object. All underlying data are not deleted but dereferenced.
 *
 * @param row the position of the first row to be removed.
 * @param number number of rows to be removed.
 * @return true if successful, else false.
 * @throws ArrayIndexOutOfBoundsException if the row index is not valid.
 */
public boolean removeRows(int row, int number) {
    // check if row index is valid
    if (!isRowIndex(row)) throw new ArrayIndexOutOfBoundsException(
                "row=" + row + " is not a valid row index in " + table_name);
    // check if number is valid
    if (number < 1) return false;
    // check if there are enough rows to be deleted
    if (row + number >= table_rows) return false;
    if (table_rows < number) return false;
    // remove the elements for each column
    for (int i = 0; i < table_cols; i++)
        for (int j = 0; j < number; j++)
            columns.get(i).remove(row);
    table_rows -= number;
    return true;
}

/**
 * Returns the data stored in the cell pointed by given column and row
 * indexes. The return value is a {@link Data} reference.
 *
 * @param col the column index of the cell.
 * @param row the row index of the cell.
 * @return a {@link Data} reference to the content of the cell.
 * @throws ArrayIndexOutOfBoundsException if the column and row indexes are
 * not valid.
 */
public Data get(int col, int row) {
    // check if row and col indexes are valid
    if (!isColumnIndex(col)) throw new ArrayIndexOutOfBoundsException(
                "col=" + col + " is not a valid column index in " + table_name);
    if (!isRowIndex(row)) throw new ArrayIndexOutOfBoundsException(
                "row=" + row + " is not a valid row index in " + table_name);
    // return the content of the cell
    return columns.get(col).get(row);
}

/**
 * Sets the content of the cell pointed by column and row indexes.
 * The new content is provided by means of a {@link Data} reference. A check
 * for type consistency is done before the assignment.
 *
 * @param col the column index of the cell.
 * @param row the row index of the cell.
 * @param data new {@link Data} reference of the cell.
 * @return true if successful, else false.
 * @throws ArrayIndexOutOfBoundsException if the column and row indexes are
 * not valid.
 *
 */
public boolean set(int col, int row, Data data) {
    // check if col and row indexes are valid
    if (!isColumnIndex(col)) throw new ArrayIndexOutOfBoundsException(
                "col=" + col + " is not a valid column index in " + table_name);
    if (!isRowIndex(row)) throw new ArrayIndexOutOfBoundsException(
                "row=" + row + " is not a valid row index in " + table_name);
    // check if DataType of data is valid
    if (columns.get(col).type() != data.type()) return false;
    // set content of the cell to new value
    columns.get(col).set(row, data.clone());
    return true;
}

/**
 * Sets the content of the cell pointed by column and row indexes parsing
 * a string data.
 *
 * @param col the column index of the cell.
 * @param row the row index of the cell.
 * @param data the string content to be parsed.
 * @return true if successful, else false.
 * @throws ArrayIndexOutOfBoundsException if the column and row indexes are
 * not valid.
 *
 */
public boolean set(int col, int row, String data) {
    // check if col and row indexes are valid
    if (!isColumnIndex(col)) throw new ArrayIndexOutOfBoundsException(
                "col=" + col + " is not a valid column index in " + table_name);
    if (!isRowIndex(row)) throw new ArrayIndexOutOfBoundsException(
                "row=" + row + " is not a valid row index in " + table_name);
    // set content of the cell to new value
    return columns.get(col).set(row, data);
}

/**
 * Builds a string representation of the {@code Table} object. This
 * function is used for debugging purpose.
 *
 * @return a string representation of the table.
 */
@Override
public String toString() {
    // Initialize the StringBuilder
    StringBuilder sb = new StringBuilder();
    // build table header
    sb.append("== ").append(table_name).append(" == ")
            .append(table_cols).append("C, ").append(table_rows)
            .append("R ==");
    // extend table header
    int header_lenght = table_cols * 8 - sb.length();
    for (int i = 0; i < header_lenght; i++)
        sb.append("=");
    sb.append("\n");
    // build column names header
    for (int i = 0; i < table_cols; i++)
    {
        String col_name = columns.get(i).name();
        if (col_name.length() > 7) col_name = col_name.substring(0, 6) + "~";
        sb.append(col_name).append("\t");
    }
    // add column header separator
    sb.append("\n");
    for (int i = 0; i < table_cols; i++)
        sb.append("--------");
    sb.append("\n");
    // build table content        
    for (int i = 0; i < table_rows; i++)
    {
        for (int j = 0; j < table_cols; j++)
        {
            if (columns.get(j).get(i).isEmpty())
            {
                switch (columns.get(j).get(i).type())
                {
                    case CHARACTER:
                        sb.append("_");
                        break;
                    case NUMERIC:
                        sb.append(".");
                        break;
                }
            } else
            {
                String data = columns.get(j).get(i).toString();
                if (data.length() > 7) data = data.substring(0, 6) + "~";
                sb.append(data);
            }
            sb.append("\t");
        }
        sb.append("\n");
    }
    // add end separator
    for (int i = 0; i < table_cols; i++)
        sb.append("========");
    sb.append("\n");
    // return the string from StringBuilder
    return sb.toString();
}

/**
 * The function returns a untitled column name. It uses a counter to
 * generate unique column names such as {@code Column1} or {@code Column57}.
 * Before returning, it check that the name of the column is unique inside the
 * {@code Table} object.
 *
 * @return a unique untitled column name.
 */
private String getUntitledColumn() {
    // initialize variables
    String untitled_name;
    // check if untitle name is valid, else add a progressive number to its end
    do
        untitled_name = "Column" + untitled_column++;
    while (!isColumnNameValid(untitled_name));
    return untitled_name;
}

/**
 * The function returns a new untitled table name. It uses a counter to
 * keep track of newly created table but it doesn't check for uniqueness of the
 * name before returning.
 *
 * @return an untitled table name.
 */
private static String newUntitledName() {
    return "Table" + untitled_table++;
}

/**
 * The function returns an empty {@link Array} of given data type. The
 * number of rows is equal to the current table row number. It is used for
 * column insertion. When implementing new data types, this method must be
 * modified.
 *
 * @param type the data type of the column.
 * @return an {@link Array} of empty data object.
 */
private Array getEmptyArray(DataType type) {
    // based on DataType add a column to the table
    Array new_array = new Array(getUntitledColumn());
    switch (type)
    {
        case NUMERIC:
            new_array.add(new Numeric());
            break;
        case CHARACTER:
        case UNDEFINED:
        default:
            new_array.add(new Character());
            break;
    }
    new_array.fill(table_rows);
    return new_array;
}

}
