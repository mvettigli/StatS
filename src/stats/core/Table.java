/* ----------------------------------------------------------------------------
 * File: Table.java
 * Date: April 1st, 2013
 * ----------------------------------------------------------------------------
 */
package stats.core;

import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class Table {

    private String table_name;              // name of the table

    private int table_cols;                 // number of columns in the table

    private int table_rows;                 // number of rows in the table

    private ArrayList<Array> columns;       // stores the columns as an array of Arrays

    private static int untitled_column = 1; // unique identifier for column naming

    private static int untitled_table = 1;  // unique identifier for table naming

    private static int DEFAULT_ROWS = 10;   // default number of rows

    public Table() {
        this(newUntitledName());
    }

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

    public int columns() {
        return table_cols;
    }

    public int rows() {
        return table_rows;
    }

    public String name() {
        return table_name;
    }

    public boolean setName(String name) {
        if (name.isEmpty()) return false;
        table_name = name;
        return true;
    }

    public String getColumnName(int col) {
        return columns.get(col).name();
    }

    public boolean setColumnName(int col, String name) {
        // check if index is valid
        if (!isColumnIndex(col)) return false;
        // check if name column is calid
        if (name.isEmpty()) return false;
        // check if the name is already used as column name in the table
        for (int i = 0; i < table_cols; i++)
            if (columns.get(i).name().equals(name) && i != col) return false;
        columns.get(col).setName(name);
        return true;
    }

    public DataType getColumnType(int col) {
        if (!isColumnIndex(col)) throw new ArrayIndexOutOfBoundsException();
        return columns.get(col).type();
    }

    public boolean isColumnIndex(int col) {
        return (col >= 0 && col < table_cols);
    }

    public boolean isRowIndex(int row) {
        return (row >= 0 && row < table_rows);
    }

    public boolean insertColumn() {
        return this.insertColumns(0, DataType.CHARACTER, 1);
    }

    public boolean insertColumn(DataType type) {
        return this.insertColumns(0, type, 1);
    }

    public boolean insertColumn(int col) {
        return this.insertColumns(col, DataType.CHARACTER, 1);
    }

    public boolean insertColumn(int col, DataType type) {
        return this.insertColumns(col, type, 1);
    }

    public boolean insertColumns(int number) {
        return this.insertColumns(0, DataType.CHARACTER, number);
    }

    public boolean insertColumns(DataType type, int number) {
        return this.insertColumns(0, type, number);
    }

    public boolean insertColumns(int col, DataType type, int number) {
        // check if index is valid
        if (!isColumnIndex(col)) throw new ArrayIndexOutOfBoundsException();

        // check if number is valid
        if (number < 1) return false;
        // insert new columns to the table
        for (int i = 0; i < number; i++)
            columns.add(col + i, this.getEmptyArray(type));
        table_cols += number;
        return true;
    }

    public boolean addColumn() {
        return this.addColumns(DataType.CHARACTER, 1);
    }

    public boolean addColumn(DataType type) {
        return this.addColumns(type, 1);
    }

    public boolean addColumns(int number) {
        return this.addColumns(DataType.CHARACTER, number);
    }

    public boolean addColumns(DataType type, int number) {
        // check if number is valid
        if (number < 1) return false;
        // add new columns to the table
        for (int i = 0; i < number; i++)
            columns.add(this.getEmptyArray(type));
        table_cols += number;
        return true;
    }

    public boolean removeColumn(int col) {
        return this.removeColumns(col, 1);
    }

    public boolean removeColumns(int col, int number) {
        // check if row index is valid
        if (!isColumnIndex(col))
            throw new ArrayIndexOutOfBoundsException();
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

    public boolean addRow() {
        return this.addRows(1);
    }

    public boolean addRows(int number) {
        // check if number is valid
        if (number < 1) return false;
        // insert rows for each column of the table
        for (int i = 0; i < table_cols; i++)
            columns.get(i).add(number);
        table_rows += number;
        return true;
    }

    public boolean insertRow() {
        return this.insertRows(0, 1);
    }

    public boolean insertRow(int row) {
        return this.insertRows(row, 1);
    }

    public boolean insertRows(int number) {
        return this.insertRows(0, number);
    }

    public boolean insertRows(int row, int number) {
        // check if index is valid
        if (!isRowIndex(row)) throw new ArrayIndexOutOfBoundsException();
        // check if number is valid
        if (number < 1) return false;
        // insert rows for each column of the table
        for (int i = 0; i < table_cols; i++)
            columns.get(i).insert(row, number);
        table_rows += number;
        return true;
    }

    public boolean removeRow(int row) {
        return this.removeRows(row, 1);
    }

    public boolean removeRows(int row, int number) {
        // check if row index is valid
        if (!isRowIndex(row)) return false;
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

    public Data get(int col, int row) {
        // check if row and col indexes are valid
        if (!isColumnIndex(col) || !isRowIndex(row))
            throw new ArrayIndexOutOfBoundsException();
        // return the content of the cell
        return columns.get(col).get(row);
    }

    public boolean set(int col, int row, Data data) {
        // check if col and row indexes are valid
        if (!isColumnIndex(col) || !isRowIndex(row))
            throw new ArrayIndexOutOfBoundsException();
        // check if DataType of data is valid
        if (columns.get(col).type() != data.type()) return false;
        // set content of the cell to new value
        columns.get(col).set(row, data.clone());
        return true;
    }

    public boolean set(int col, int row, String data) {
        // check if col and row indexes are valid
        if (!isColumnIndex(col) || !isRowIndex(row))
            throw new ArrayIndexOutOfBoundsException();
        // set content of the cell to new value
        return columns.get(col).set(row, data);
    }

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
            if (col_name.length() > 7)
                col_name = col_name.substring(0, 6) + "~";
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

    private String getUntitledColumn() {
        // initialize variables
        boolean is_name_unique;
        String untitled_name;
        do
        {
            // assign an untitled name..
            is_name_unique = true;
            untitled_name = "Column" + untitled_column++;
            // ..and check if the name is unique inside the Table
            for (int i = 0; i < table_cols; i++)
                is_name_unique &= (untitled_name != columns.get(i).name());
            // repeat the process until a unique untitled name is found
        } while (!is_name_unique);
        return untitled_name;
    }

    private static String newUntitledName() {
        return "Table" + untitled_table++;
    }

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
