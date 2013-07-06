/* ----------------------------------------------------------------------------
 * File: TableHandler.java
 * Date: May 16th, 2013
 * ----------------------------------------------------------------------------
 */
package stats.gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;
import javax.swing.plaf.metal.MetalBorders;
import javax.swing.table.*;
import stats.core.*;
import stats.gui.dialogs.*;

/**
 *
 * @author marco
 */
public class TableHandler extends javax.swing.JPanel {

  /**
   * Foreground color of table headers.
   */
  private final static Color FORECOLOR_CELL =
          new Color(0, 0, 0);

  /**
   * Foreground color of table headers.
   */
  private final static Color FORECOLOR_HEADER =
          new Color(0, 0, 0);

  /**
   * Background color of selected cells.
   */
  private final static Color BACKCOLOR_SELECTED_CELL =
          new Color(0, 191, 255);

  /**
   * Background color of unselected cells.
   */
  private final static Color BACKCOLOR_UNSELECTED_CELL =
          new Color(255, 255, 255);

  /**
   * Background color of shaded cells.
   */
  private final static Color BACKCOLOR_SHADED_CELL =
          new Color(191, 239, 255);

  /**
   * Background color of selected row and column headers.
   */
  private final static Color BACKCOLOR_SELECTED_HEADER =
          new Color(200, 200, 200);

  /**
   * Background color of unselected row and column headers.
   */
  private final static Color BACKCOLOR_UNSELECTED_HEADER =
          new Color(230, 230, 230);

  /**
   * Table object holding data.
   */
  private Table table;

  /**
   * List of selection groups associated to the table.
   */
  private ArrayList<SelectionGroup> selectionGroups;

  /**
   * Read-only status of the TableHandler object.
   */
  private boolean editable;

  /**
   * The JTable object showing data contained in table object.
   */
  private JTable mainTable;

  /**
   * The JTable object showing row numbers.
   */
  private JTable rowTable;

  /**
   * Row selection status.
   */
  private ListSelectionModel rowSelectionModel;

  /**
   * Column selection status.
   */
  private ListSelectionModel colSelectionModel;

  /**
   * Storage variable for width of lateral pane.
   */
  private int lateralPaneWidth;

  /**
   * Default constructor for creating new TableHandler form.
   */
  public TableHandler() {
    this(new Table(), false);
  }

  /**
   * Constructor for creating new {@link TableHandler} form given underling
   * table and editable status.
   *
   * @param table the underlying {@link Table} storing data.
   * @param editable true if editing is allowed, else false.
   */
  public TableHandler(Table table, boolean editable) {
    // initialize form components    
    initComponents();

    /* initialize table object, selection groups and read-only status */
    this.table = table;
    this.selectionGroups = new ArrayList<>();
    this.editable = editable;

    /* initialize all components */
    initSelectionModels();
    initRowTable();
    initMainTable();
    initScrollPane();
    initToolBar();
    initLateralPane();
    initColorPaletteMenu();
  }

  /**
   * Returns the row selection model of the {@code TableHandler}.
   *
   * @return the row selection model.
   */
  public ListSelectionModel getRowSelectionModel() {
    return rowSelectionModel;
  }

  /**
   * Returns the column selection model of the {@code TableHandler}.
   *
   * @return the column selection model.
   */
  public ListSelectionModel getColSelectionModel() {
    return colSelectionModel;
  }

  /**
   * Returns a list of indexes representing currently selected columns.
   *
   * @return a list of selected column indexes.
   */
  public int[] getSelectedColumns() {
    // store all indexes of selected columns in a list
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = colSelectionModel.getMinSelectionIndex();
            i < colSelectionModel.getMaxSelectionIndex() + 1; i++)
      if (colSelectionModel.isSelectedIndex(i)) list.add(i);
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
    for (int i = rowSelectionModel.getMinSelectionIndex();
            i < rowSelectionModel.getMaxSelectionIndex() + 1; i++)
      if (rowSelectionModel.isSelectedIndex(i)) list.add(i);
    // convert a list of Integer to an int array
    int[] result = new int[list.size()];
    for (int i = 0; i < list.size(); i++)
      result[i] = list.get(i);
    return result;
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    colsPopup = new javax.swing.JPopupMenu();
    menuRenameCols = new javax.swing.JMenuItem();
    menuInsertCols = new javax.swing.JMenuItem();
    menuDeleteCols = new javax.swing.JMenuItem();
    separatorColsMenu = new javax.swing.JPopupMenu.Separator();
    menuType = new javax.swing.JMenu();
    menuCharacter = new javax.swing.JRadioButtonMenuItem();
    menuNumeric = new javax.swing.JRadioButtonMenuItem();
    rowsPopup = new javax.swing.JPopupMenu();
    menuInsertRows = new javax.swing.JMenuItem();
    menuDeleteRows = new javax.swing.JMenuItem();
    separatorRowsMenu = new javax.swing.JPopupMenu.Separator();
    menuGroup = new javax.swing.JMenuItem();
    menuColor = new javax.swing.JMenu();
    menuSize = new javax.swing.JMenu();
    radioTiny = new javax.swing.JRadioButtonMenuItem();
    radioSmall = new javax.swing.JRadioButtonMenuItem();
    radioMedium = new javax.swing.JRadioButtonMenuItem();
    radioLarge = new javax.swing.JRadioButtonMenuItem();
    radioHuge = new javax.swing.JRadioButtonMenuItem();
    menuMarker = new javax.swing.JMenu();
    menuColorPalette = new stats.graphics.Palette();
    menuMoreColors = new javax.swing.JMenuItem();
    toolBar = new javax.swing.JToolBar();
    buttonSidePane = new javax.swing.JButton();
    separatoToolBar = new javax.swing.JToolBar.Separator();
    splitMain = new javax.swing.JSplitPane();
    scrollPane = new javax.swing.JScrollPane();
    splitLateral = new javax.swing.JSplitPane();
    scrollColumns = new javax.swing.JScrollPane();
    listColumns = new javax.swing.JList();
    scrollRows = new javax.swing.JScrollPane();
    listRows = new javax.swing.JList();

    menuRenameCols.setText("Rename");
    menuRenameCols.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuRenameColsActionPerformed(evt);
      }
    });
    colsPopup.add(menuRenameCols);

    menuInsertCols.setText("Insert");
    menuInsertCols.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuInsertColsActionPerformed(evt);
      }
    });
    colsPopup.add(menuInsertCols);

    menuDeleteCols.setText("Delete");
    menuDeleteCols.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuDeleteColsActionPerformed(evt);
      }
    });
    colsPopup.add(menuDeleteCols);
    colsPopup.add(separatorColsMenu);

    menuType.setText("Column type");

    menuCharacter.setSelected(true);
    menuCharacter.setText("Character");
    menuCharacter.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuCharacterActionPerformed(evt);
      }
    });
    menuType.add(menuCharacter);

    menuNumeric.setSelected(true);
    menuNumeric.setText("Numeric");
    menuNumeric.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuNumericActionPerformed(evt);
      }
    });
    menuType.add(menuNumeric);

    colsPopup.add(menuType);

    menuInsertRows.setText("Insert");
    menuInsertRows.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuInsertRowsActionPerformed(evt);
      }
    });
    rowsPopup.add(menuInsertRows);

    menuDeleteRows.setText("Delete");
    menuDeleteRows.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuDeleteRowsActionPerformed(evt);
      }
    });
    rowsPopup.add(menuDeleteRows);
    rowsPopup.add(separatorRowsMenu);

    menuGroup.setText("Group");
    rowsPopup.add(menuGroup);

    menuColor.setText("Color");
    rowsPopup.add(menuColor);

    menuSize.setText("Size");

    radioTiny.setSelected(true);
    radioTiny.setText("Tiny");
    radioTiny.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        radioTinyActionPerformed(evt);
      }
    });
    menuSize.add(radioTiny);

    radioSmall.setSelected(true);
    radioSmall.setText("Small");
    radioSmall.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        radioSmallActionPerformed(evt);
      }
    });
    menuSize.add(radioSmall);

    radioMedium.setSelected(true);
    radioMedium.setText("Medium");
    radioMedium.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        radioMediumActionPerformed(evt);
      }
    });
    menuSize.add(radioMedium);

    radioLarge.setSelected(true);
    radioLarge.setText("Large");
    radioLarge.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        radioLargeActionPerformed(evt);
      }
    });
    menuSize.add(radioLarge);

    radioHuge.setSelected(true);
    radioHuge.setText("Huge");
    radioHuge.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        radioHugeActionPerformed(evt);
      }
    });
    menuSize.add(radioHuge);

    rowsPopup.add(menuSize);

    menuMarker.setText("Marker");
    rowsPopup.add(menuMarker);

    menuColorPalette.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuColorPaletteActionPerformed(evt);
      }
    });

    menuMoreColors.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stats/gui/images/palette.png"))); // NOI18N
    menuMoreColors.setText("More colors...");
    menuMoreColors.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuMoreColorsActionPerformed(evt);
      }
    });

    toolBar.setFloatable(false);

    buttonSidePane.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stats/gui/images/application_side_boxes.png"))); // NOI18N
    buttonSidePane.setToolTipText("Show/hide lateral pane");
    buttonSidePane.setFocusable(false);
    buttonSidePane.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    buttonSidePane.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    buttonSidePane.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonSidePaneActionPerformed(evt);
      }
    });
    toolBar.add(buttonSidePane);
    toolBar.add(separatoToolBar);

    splitMain.setBorder(null);
    splitMain.setDividerLocation(150);

    scrollPane.setBorder(null);
    splitMain.setRightComponent(scrollPane);

    splitLateral.setBorder(null);
    splitLateral.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
    splitLateral.setResizeWeight(0.5);
    splitLateral.setMinimumSize(new java.awt.Dimension(0, 0));

    scrollColumns.setBorder(javax.swing.BorderFactory.createTitledBorder("Columns"));
    scrollColumns.setViewportView(listColumns);

    splitLateral.setTopComponent(scrollColumns);

    scrollRows.setBorder(javax.swing.BorderFactory.createTitledBorder("Rows"));
    scrollRows.setViewportView(listRows);

    splitLateral.setRightComponent(scrollRows);

    splitMain.setLeftComponent(splitLateral);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(splitMain, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(splitMain, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

  /**
   * Handles column renaming on current selection. Because the methods uses
   * currently selected columns from {@code col_selectionModel}, an
   * {@code IllegalArgumentException} will be thrown if selection is null. The
   * method will ask for a column name to be used for single or multiple column
   * renaming. For multiple column renaming a progressive number, starting from
   * one, will be used. Before actual renaming, the method will check if column
   * name(s) are allowed in the table. If one of them is not allowed, the
   * renaming operation will be aborted.
   *
   * @param evt the action event.
   * @throws IllegalArgumentException if no column is selected.
   */
  private void menuRenameColsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRenameColsActionPerformed
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
      TableColumn column = mainTable.getColumnModel()
              .getColumn(selected_cols[i]);
      column.setHeaderValue(columnName);
    }
    // update main table and column list
    mainTable.getTableHeader().updateUI();
    listColumns.updateUI();
  }//GEN-LAST:event_menuRenameColsActionPerformed

  /**
   * Handles column insertion upon selection. Because the methods uses currently
   * selected columns from {@code col_selectionModel}, an {@code
   * IllegalArgumentException} will be thrown if selection is null. The
   * insertion is performed based on column type, number and insertion position.
   * When multiple selection or number of columns is present, the name of the
   * columns is built using the root name given by the user, followed by (one or
   * two) indexes. If the names of the column to be inserted is not valid, the
   * operation is aborted.
   *
   * @param evt the action event.
   * @throws IllegalArgumentException if no column is selected.
   */
  private void menuInsertColsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInsertColsActionPerformed
    // get selected columns and check for empty selection
    int[] selected_cols = getSelectedColumns();
    if (selected_cols.length == 0) throw new IllegalArgumentException(
              "Empty column selection.");
    // create a dialog for column insertion
    JFrame mainFrame = (JFrame) SwingUtilities.getRoot(this);
    DialogInsertCols dialog = new DialogInsertCols(mainFrame, true);
    dialog.setLocationRelativeTo(mainFrame);
    // show the dialog and exit if operation is aborted
    if (!dialog.showDialog()) return;
    // define variable for convenience
    TableColumnModel model = mainTable.getColumnModel();
    String rootName = dialog.getColumnName();
    int number = dialog.getColumnNumber();
    int type = dialog.getColumnType();
    // check if the names of the new columns are valid
    boolean error_state = true;
    String error_log = new String();
    // for each column name to be inserted,
    // check its validity in the table object
    for (int i = 0; i < selected_cols.length; i++)
      for (int j = 0; j < number; j++)
      {
        String columnName = rootName
                + ((selected_cols.length != 1) ? (i + 1) : "")
                + ((number != 1) ? (j + 1) : "");
        if (!table.isColumnNameValid(columnName))
        {
          error_log += "ERROR: " + (columnName.isEmpty()
                  ? "Empty name" : "\"" + columnName + "\"\n");
          error_state = false;
        }
      }
    // show an error message with a log 
    // reporting all invalid column names
    if (!error_state)
    {
      JOptionPane.showMessageDialog(this,
              "Column name(s) must be unique inside the table and "
              + "cannot be empty.\n\n" + error_log,
              "Invalid column name(s)",
              JOptionPane.ERROR_MESSAGE);
      return;
    }
    // based on insertion position modify the selection  
    int[] newSelection = new int[1];
    colSelectionModel.clearSelection();
    switch (dialog.getColumnPosition())
    {
      case DialogInsertCols.POSITION_BEGIN:
        newSelection[0] = 0;
        selected_cols = newSelection;
        break;
      case DialogInsertCols.POSITION_END:
        newSelection[0] = model.getColumnCount();
        selected_cols = newSelection;
        break;
      case DialogInsertCols.POSITION_AFTER:
        for (int i = 0; i < selected_cols.length; i++)
          selected_cols[i] += 1;
      case DialogInsertCols.POSITION_BEFORE:
      default:
    }
    // insert the columns 
    for (int i = 0; i < selected_cols.length; i++)
    {
      int index = selected_cols[i];
      // insert and rename columns in the Table object
      if (index == table.columns()) table.addColumns(type, number);
      else table.insertColumns(index, type, number);
      for (int j = 0; j < number; j++)
        table.setColumnName(index + j, rootName
                + ((selected_cols.length != 1) ? (i + 1) : "")
                + ((number != 1) ? (j + 1) : ""));
      // shift all next columns forward by n positions
      for (int j = 0; j < model.getColumnCount(); j++)
      {
        int currIndex = model.getColumn(j).getModelIndex();
        if (currIndex >= index)
          model.getColumn(j).setModelIndex(currIndex + number);
      }
      // add columns to JTable object
      for (int j = 0; j < number; j++)
      {
        TableColumn tableColumn = new TableColumn();
        tableColumn.setModelIndex(index + j);
        tableColumn.setHeaderValue(table.getColumnName(index + j));
        model.addColumn(tableColumn);
        // the model adds columns at the end of the table with defined
        // model index, they must be moved to the correct position
        model.moveColumn(model.getColumnCount() - 1, index + j);
      }
      // select newly created columns
      colSelectionModel.addSelectionInterval(
              selected_cols[i],
              selected_cols[i] + number - 1);
      // update next selected index to shift the selection
      if (i != selected_cols.length - 1)
        selected_cols[i + 1] += number * (i + 1);
    }
    // update main table and column list    
    listColumns.updateUI();
  }//GEN-LAST:event_menuInsertColsActionPerformed

  /**
   * Handles column removal upon selection. Because the methods uses currently
   * selected columns from {@code col_selectionModel}, an {@code
   * IllegalArgumentException} will be thrown if selection is null. At least one
   * column must be present in the table. This method will parse current column
   * selection and remove both data and {@link TableColumn} objects. Other
   * column indexes must be shift to match table model.
   *
   * @param evt the action event.
   * @throws IllegalArgumentException if no column is selected.
   */
  private void menuDeleteColsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDeleteColsActionPerformed
    // get selected columns and check for empty selection
    int[] selected_cols = getSelectedColumns();
    if (selected_cols.length == 0)
      throw new IllegalArgumentException("Empty column selection.");
    // check for maximum number of columns to be deleted
    if (selected_cols.length == table.columns())
    {
      JOptionPane.showMessageDialog(this,
              "Cannot delete all column in the table",
              "Delete error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    // parse the selected indexes for column removal
    TableColumnModel model = mainTable.getColumnModel();
    for (int i = selected_cols.length - 1; i >= 0; i--)
    {
      // remove selected column
      int index = selected_cols[i];
      model.removeColumn(model.getColumn(index));
      // shift back next columns by one position
      for (int j = index; j < model.getColumnCount(); j++)
      {
        int currIndex = model.getColumn(j).getModelIndex();
        model.getColumn(j).setModelIndex(currIndex - 1);
      }
      // remove selection and actual column from table
      colSelectionModel.removeIndexInterval(index, index);
      table.removeColumn(index);
    }
    // update column list
    updateColumnList();
  }//GEN-LAST:event_menuDeleteColsActionPerformed

  /**
   * Handles row insertion upon selection. Because the methods uses currently
   * selected rows from {@code row_selectionModel}, an {@code
   * IllegalArgumentException} will be thrown if selection is null. The
   * insertion is performed based on number and insertion position.
   *
   * @param evt the action event.
   * @throws IllegalArgumentException if no row is selected.
   */
  private void menuInsertRowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInsertRowsActionPerformed
    // check if row selection is null
    int[] selected_rows = getSelectedRows();
    if (selected_rows.length == 0) throw new IllegalArgumentException(
              "Empty row selection");
    //
    JFrame mainFrame = (JFrame) SwingUtilities.getRoot(this);
    DialogInsertRows dialog = new DialogInsertRows(mainFrame, true);
    dialog.setLocationRelativeTo(mainFrame);
    // show the dialog and exit if operation is aborted
    if (!dialog.showDialog()) return;
    // define variables for convenience
    int number = dialog.getRowNumber();
    int position = dialog.getRowPosition();
    int[] new_selection = new int[1];
    // based on insertion position, modify selected indexes
    switch (position)
    {
      case DialogInsertRows.POSITION_BEGIN:
        new_selection[0] = 0;
        selected_rows = new_selection;
        break;
      case DialogInsertRows.POSITION_END:
        new_selection[0] = table.rows();
        selected_rows = new_selection;
        break;
      case DialogInsertRows.POSITION_AFTER:
        for (int i = 0; i < selected_rows.length; i++)
          selected_rows[i] += 1;
      case DialogInsertRows.POSITION_BEFORE:
      default:
    }
    // define variables for convenience
    RowTableModel rowModel = (RowTableModel) rowTable.getModel();
    MainTableModel mainModel = (MainTableModel) mainTable.getModel();
    // clear row selection and insert new rows
    rowSelectionModel.clearSelection();
    for (int i = 0; i < selected_rows.length; i++)
    {
      // insert rows in the Table object
      if (selected_rows[i] != table.rows())
        table.insertRows(selected_rows[i], number);
      else table.addRows(number);
      // fire row insetion in row and main table
      rowModel.fireTableRowsInserted(selected_rows[i],
              selected_rows[i] + number - 1);
      mainModel.fireTableRowsInserted(selected_rows[i],
              selected_rows[i] + number - 1);
      // select newly created rows
      rowSelectionModel.addSelectionInterval(
              selected_rows[i],
              selected_rows[i] + number - 1);
      // update next selected index to shift the selection
      if (i != selected_rows.length - 1)
        selected_rows[i + 1] += number * (i + 1);
    }
    // update row list
    updateRowList();
  }//GEN-LAST:event_menuInsertRowsActionPerformed

  /**
   * Handles row removal upon selection. Because the methods uses currently
   * selected rows from {@code row_selectionModel}, an {@code
   * IllegalArgumentException} will be thrown if selection is null. At least one
   * row must be present in the table, if user attempts to remove all rows, the
   * operation will be aborted. At the end of the operation the row selection
   * will be cleared.
   *
   * @param evt the action event.
   * @throws IllegalArgumentException if no row is selected.
   */
  private void menuDeleteRowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDeleteRowsActionPerformed
    // check if row selection is null
    int[] selected_rows = getSelectedRows();
    if (selected_rows.length == 0) throw new IllegalArgumentException(
              "Empty row selection");
    // check if at least one row remains in the table
    if (selected_rows.length == table.rows())
    {
      JOptionPane.showMessageDialog(this,
              "Cannot delete all rows in the table",
              "Delete error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    // define row and main table model for firing deletion events
    RowTableModel rowModel = (RowTableModel) rowTable.getModel();
    MainTableModel mainModel = (MainTableModel) mainTable.getModel();
    // remove rows from the Table object
    for (int i = 0; i < selected_rows.length; i++)
    {
      table.removeRow(selected_rows[i]);
      // fire row deletion events
      rowModel.fireTableRowsDeleted(selected_rows[i], selected_rows[i]);
      mainModel.fireTableRowsDeleted(selected_rows[i], selected_rows[i]);
      // shift backward the next selection
      if (i != selected_rows.length - 1) selected_rows[i + 1] -= i + 1;
    }
    // clear row selection and update rows list
    rowSelectionModel.clearSelection();
    updateRowList();
  }//GEN-LAST:event_menuDeleteRowsActionPerformed

  /**
   * Handles lateral panel visibility. The lateral panel is shown or hidden
   * based on its current state. When user hides the pane, its width is store in
   * the local variable {@code lateralPaneWidth}. This variable is used to
   * restore panel width when user set it visible.
   *
   * @param evt the action event.
   */
  private void buttonSidePaneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSidePaneActionPerformed
    // check if the side panel is already hidden and hide/show it
    if (splitMain.getDividerLocation() != 0)
    {
      // store the current width in the local variable
      lateralPaneWidth = splitMain.getDividerLocation();
      splitMain.setDividerLocation(0);
      // restore the width of the lateral panel
    } else splitMain.setDividerLocation(lateralPaneWidth);
  }//GEN-LAST:event_buttonSidePaneActionPerformed

  /**
   * Handles the conversion of currently selected columns to character type. The
   * function checks if all columns are convertible without data loss. Because
   * at this stage all data types are convertible to character type without data
   * loss, the waring message will never be shown. This structure is maintained
   * for future development.
   *
   * @param evt the action event.
   */
  private void menuCharacterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCharacterActionPerformed
    // check if character menu item is already toggled
    if (!menuCharacter.isSelected()) return;
    // check if column selection is empty
    int[] selectedCols = getSelectedColumns();
    if (selectedCols.length == 0) throw new IllegalArgumentException(
              "Empty column selection.");
    // check for data integrity
    boolean areColumnContertible = true;
    for (int i = 0; i < selectedCols.length; i++)
      if (table.getColumnType(selectedCols[i]) != Data.CHARACTER)
        areColumnContertible &= table.isColumnConvertible(
                selectedCols[i], Data.CHARACTER);
    // show confirmation message if data loss will occur
    if (!areColumnContertible)
    {
      int result = JOptionPane.showConfirmDialog(this, "Data loss will occur "
              + "upon convertion. \nDo you want to force convertion, ",
              "Data integrity", JOptionPane.YES_NO_OPTION,
              JOptionPane.WARNING_MESSAGE);
      if (result == JOptionPane.NO_OPTION) return;
    }
    // convert all selected columns to character type
    for (int i = 0; i < selectedCols.length; i++)
      table.convertColumn(selectedCols[i], Data.CHARACTER);
    // update column list and scroll pane
    listColumns.updateUI();
    scrollPane.updateUI();
  }//GEN-LAST:event_menuCharacterActionPerformed

  /**
   * Handles the conversion of currently selected columns to numeric type. The
   * function checks if all columns are convertible without data loss. The user
   * can force the conversion even if data loss will occur.
   *
   * @param evt the action event.
   */
  private void menuNumericActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNumericActionPerformed
    // check if numeric menu item is already toggled
    if (!menuNumeric.isSelected()) return;
    // check if column selection is empty
    int[] selectedCols = getSelectedColumns();
    if (selectedCols.length == 0) throw new IllegalArgumentException(
              "Empty column selection.");
    // check for data integrity
    boolean areColumnContertible = true;
    for (int i = 0; i < selectedCols.length; i++)
      if (table.getColumnType(selectedCols[i]) != Data.NUMERIC)
        areColumnContertible &= table.isColumnConvertible(
                selectedCols[i], Data.NUMERIC);
    // show confirmation message if data loss will occur
    if (!areColumnContertible)
    {
      int result = JOptionPane.showConfirmDialog(this, "Data loss will occur "
              + "upon convertion. \nDo you want to force convertion?",
              "Data integrity", JOptionPane.YES_NO_OPTION,
              JOptionPane.WARNING_MESSAGE);
      if (result == JOptionPane.NO_OPTION) return;
    }
    // convert all selected columns to numeric type
    for (int i = 0; i < selectedCols.length; i++)
      table.convertColumn(selectedCols[i], Data.NUMERIC);
    // update column list and scroll pane
    listColumns.updateUI();
    scrollPane.updateUI();
  }//GEN-LAST:event_menuNumericActionPerformed

  /**
   * Handles the click on "more columns" menu item from row pop-up menu. A {@code
   * JColorChooser} will be used to allow the user to pick from a wider range of
   * colors. The result will be stored as color property of {@code
   * menuColorPalette} object.
   *
   * @param evt the action event.
   */
  private void menuMoreColorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMoreColorsActionPerformed
    // Create new JColorChooser with no preview
    JColorChooser colorChooser = new JColorChooser();
    colorChooser.setPreviewPanel(new JPanel());
    colorChooser.setColor(Color.BLACK);
    // show the color chooser with confirm dialog
    int result = JOptionPane.showConfirmDialog(this, colorChooser,
            "Choose color...", JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE);
    // if operation was cancelled return
    if (result != JOptionPane.OK_OPTION) return;
    // set color of palette object and call action performed
    menuColorPalette.setColor(colorChooser.getColor());
    menuColorPaletteActionPerformed(evt);
  }//GEN-LAST:event_menuMoreColorsActionPerformed

  private void menuColorPaletteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuColorPaletteActionPerformed
    // close the popup menu and reset color selection
    rowsPopup.setVisible(false);
    Color color = menuColorPalette.getColor();
    menuColorPalette.resetSelection();
    // check if color is null
    if (color == null) return;
    changeRowSelectionGroup(getSelectedRows(), -1, color, -1);
  }//GEN-LAST:event_menuColorPaletteActionPerformed

  private void radioTinyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioTinyActionPerformed
    changeRowSelectionGroup(getSelectedRows(),
            -1, null, SelectionGroup.SIZE_TINY);
  }//GEN-LAST:event_radioTinyActionPerformed

  private void radioSmallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioSmallActionPerformed
    changeRowSelectionGroup(getSelectedRows(),
            -1, null, SelectionGroup.SIZE_SMALL);
  }//GEN-LAST:event_radioSmallActionPerformed

  private void radioMediumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioMediumActionPerformed
    changeRowSelectionGroup(getSelectedRows(),
            -1, null, SelectionGroup.SIZE_MEDIUM);
  }//GEN-LAST:event_radioMediumActionPerformed

  private void radioLargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioLargeActionPerformed
    changeRowSelectionGroup(getSelectedRows(),
            -1, null, SelectionGroup.SIZE_LARGE);
  }//GEN-LAST:event_radioLargeActionPerformed

  private void radioHugeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioHugeActionPerformed
    changeRowSelectionGroup(getSelectedRows(),
            -1, null, SelectionGroup.SIZE_HUGE);
  }//GEN-LAST:event_radioHugeActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonSidePane;
  private javax.swing.JPopupMenu colsPopup;
  private javax.swing.JList listColumns;
  private javax.swing.JList listRows;
  private javax.swing.JRadioButtonMenuItem menuCharacter;
  private javax.swing.JMenu menuColor;
  private stats.graphics.Palette menuColorPalette;
  private javax.swing.JMenuItem menuDeleteCols;
  private javax.swing.JMenuItem menuDeleteRows;
  private javax.swing.JMenuItem menuGroup;
  private javax.swing.JMenuItem menuInsertCols;
  private javax.swing.JMenuItem menuInsertRows;
  private javax.swing.JMenu menuMarker;
  private javax.swing.JMenuItem menuMoreColors;
  private javax.swing.JRadioButtonMenuItem menuNumeric;
  private javax.swing.JMenuItem menuRenameCols;
  private javax.swing.JMenu menuSize;
  private javax.swing.JMenu menuType;
  private javax.swing.JRadioButtonMenuItem radioHuge;
  private javax.swing.JRadioButtonMenuItem radioLarge;
  private javax.swing.JRadioButtonMenuItem radioMedium;
  private javax.swing.JRadioButtonMenuItem radioSmall;
  private javax.swing.JRadioButtonMenuItem radioTiny;
  private javax.swing.JPopupMenu rowsPopup;
  private javax.swing.JScrollPane scrollColumns;
  private javax.swing.JScrollPane scrollPane;
  private javax.swing.JScrollPane scrollRows;
  private javax.swing.JToolBar.Separator separatoToolBar;
  private javax.swing.JPopupMenu.Separator separatorColsMenu;
  private javax.swing.JPopupMenu.Separator separatorRowsMenu;
  private javax.swing.JSplitPane splitLateral;
  private javax.swing.JSplitPane splitMain;
  private javax.swing.JToolBar toolBar;
  // End of variables declaration//GEN-END:variables

  /**
   * Initializes both row and column selection model.
   */
  private void initSelectionModels() {

    /* initialize row and column selection models */
    rowSelectionModel = new DefaultListSelectionModel();
    colSelectionModel = new DefaultListSelectionModel();

    /* refresh components upon selection change */
    rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
      /**
       * Refreshes main table and row list upon row selection.
       */
      @Override
      public void valueChanged(ListSelectionEvent e) {
        mainTable.repaint();
        updateRowList();
      }
    });
    colSelectionModel.addListSelectionListener(new ListSelectionListener() {
      /**
       * Refreshes main table, header and column list upon column selection.
       */
      @Override
      public void valueChanged(ListSelectionEvent e) {
        mainTable.getTableHeader().repaint();
        mainTable.repaint();
        updateColumnList();
      }
    });

  }

  /**
   * Initializes the row table for number labeling.
   */
  private void initRowTable() {
    // initialize row table with its table model
    rowTable = new JTable(new RowTableModel(table));
    // set row table properties for autoresizing and selection model
    rowTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    rowTable.setSelectionModel(rowSelectionModel);
    // set the default renderer for visual appereance
    rowTable.setDefaultRenderer(Object.class, new TableCellRenderer() {
      /**
       * When displayed, the content of the table is formatted according to the
       * selection state. The default alignment is right because the table
       * contains
       * row numbers.
       */
      @Override
      public Component getTableCellRendererComponent(
              JTable table, Object value, boolean isSelected,
              boolean hasFocus, int row, int column) {
        // create new label for showing row number        
        JLabel cell = new JLabel();
        cell.setOpaque(true);
        cell.setText(value.toString());
        cell.setHorizontalAlignment(JLabel.RIGHT);
        // set group icon
        for (int i = 0; i < selectionGroups.size(); i++)
          if (selectionGroups.get(i).hasIndex(row))
          {
            cell.setIcon(selectionGroups.get(i).getImageIcon());
            break;
          }
        // set foreground color 
        cell.setForeground(FORECOLOR_HEADER);
        // set background color upon selection
        if (isSelected) cell.setBackground(BACKCOLOR_SELECTED_HEADER);
        else cell.setBackground(BACKCOLOR_UNSELECTED_HEADER);
        // align icon to the left side of the cell
        FontMetrics metrics = cell.getFontMetrics(cell.getFont());
        Rectangle2D bounds = metrics.getStringBounds(
                cell.getText(), table.getGraphics());
        cell.setIconTextGap(60 - (int) bounds.getWidth()
                - SelectionGroup.DEFAULT_SIZE);
        // returns the newly created label
        return cell;
      }
    });
    rowTable.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        // get the index of clicked row
        int rowIndex = rowTable.rowAtPoint(e.getPoint());
        // manage click on null row
        if (rowIndex == -1) return;
        // manage popup call
        if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 1)
        {
          // if clicked row is not selected set it as unique selection
          if (!rowSelectionModel.isSelectedIndex(rowIndex))
          {
            rowSelectionModel.clearSelection();
            rowSelectionModel.addSelectionInterval(rowIndex, rowIndex);
          }
          // set menu item enable state
          menuInsertRows.setEnabled(editable);
          menuDeleteRows.setEnabled(editable);
          menuMarker.setEnabled(true);
          menuColor.setEnabled(true);
          menuSize.setEnabled(true);
          // reset size selection
          radioTiny.setSelected(false);
          radioSmall.setSelected(false);
          radioMedium.setSelected(false);
          radioLarge.setSelected(false);
          radioHuge.setSelected(false);
          // get a list of selected rows
          int[] selected_rows = getSelectedRows();
          // check if selection has a single element
          int size = SelectionGroup.SIZE_MEDIUM;
          if (selected_rows.length == 1)
          {
            // find size of the selection group containg the selected row
            int group = findRowSelectionGroup(selected_rows[0]);
            if (group != -1) size = selectionGroups.get(group).getSize();
            // based on group size, select the radio button
            switch (size)
            {
              case SelectionGroup.SIZE_TINY:
                radioTiny.setSelected(true);
                break;
              case SelectionGroup.SIZE_SMALL:
                radioSmall.setSelected(true);
                break;
              case SelectionGroup.SIZE_MEDIUM:
                radioMedium.setSelected(true);
                break;
              case SelectionGroup.SIZE_LARGE:
                radioLarge.setSelected(true);
                break;
              case SelectionGroup.SIZE_HUGE:
                radioHuge.setSelected(true);
                break;
              default:
                throw new IllegalArgumentException(size
                        + "is not a valid marker size.");
            }
          }
          // show the row popup
          rowsPopup.show(rowTable, e.getX(), e.getY());
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
   * Initializes the main table for data display and editing.
   */
  private void initMainTable() {
    // initialize main_table with its data model
    mainTable = new JTable(new MainTableModel(table, editable));
    // set main table properties for autoresizing 
    // and row-column selection model
    mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    mainTable.setColumnSelectionAllowed(true);
    mainTable.setSelectionModel(rowSelectionModel);
    mainTable.getColumnModel().setSelectionModel(colSelectionModel);
    // set default cell and table header renderers for visual appearance
    mainTable.setDefaultRenderer(Object.class, new TableCellRenderer() {
      /**
       * The content of the table is formatted according to data type and
       * selection
       * state. The cell alignment depends on column data type, default is left
       * for
       * character and right for numeric.
       */
      @Override
      public Component getTableCellRendererComponent(
              JTable table, Object value, boolean isSelected,
              boolean hasFocus, int row, int column) {
        // convert input in Data object
        Data data = (Data) value;
        // create new label for showing cell content
        JLabel cell = new JLabel();
        cell.setOpaque(true);
        // set alignment based on Data type
        switch (data.type())
        {
          case Data.NUMERIC:
            cell.setHorizontalAlignment(JLabel.RIGHT);
            break;
          case Data.CHARACTER:
          case Data.UNDEFINED:
            cell.setHorizontalAlignment(JLabel.LEFT);
        }
        // set cell content based on Data value and type
        if (data.isEmpty() && data.type() == Data.NUMERIC)
          cell.setText(".");
        else cell.setText(data.toString());
        // set cell foreground and background based on current selection
        cell.setForeground(FORECOLOR_CELL);
        if (isSelected) cell.setBackground(BACKCOLOR_SELECTED_CELL);
        else if (rowSelectionModel.isSelectedIndex(row)
                || colSelectionModel.isSelectedIndex(column))
          cell.setBackground(BACKCOLOR_SHADED_CELL);
        else cell.setBackground(BACKCOLOR_UNSELECTED_CELL);
        // return the newly created label
        return cell;
      }
    });
    mainTable.getTableHeader().setDefaultRenderer(new TableCellRenderer() {
      /**
       * The content of the table header is rendered based on type and
       * selection.
       * The default alignment for table header is centered.
       */
      @Override
      public Component getTableCellRendererComponent(
              JTable table, Object value, boolean isSelected,
              boolean hasFocus, int row, int column) {
        // create new label to show the content of the header
        JLabel header = new JLabel();
        header.setOpaque(true);
        header.setText(value.toString());    // actual class is String
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setBorder(new MetalBorders.TableHeaderBorder());
        header.setPreferredSize(new Dimension(50, 30));
        // set foreground and background color based on column selection
        header.setForeground(FORECOLOR_HEADER);
        /* because the method returns always false, the column selection 
         * must be checked through column selection model. */
        isSelected = colSelectionModel.isSelectedIndex(column);
        if (isSelected) header.setBackground(BACKCOLOR_SELECTED_HEADER);
        else header.setBackground(BACKCOLOR_UNSELECTED_HEADER);
        // return the newly created label
        return header;
      }
    });
    // set table header behaviour on mouse input
    mainTable.getTableHeader().addMouseListener(new MouseListener() {
      /**
       * Handles column selection, column addition to the end of the table and
       * pop-up menu. If user clicks on the table header but not pointing a
       * column a
       * new column is added to the end of the table. Pressing CTRL key allow
       * for
       * multiple column addition. When user click on column headers, it select
       * them. Thanks to key modifiers (CTRL, SHIFT) the selection mode changes
       * to
       * toggle and range selection.
       */
      @Override
      public void mouseClicked(MouseEvent e) {
        // get index of clicked column
        int colIndex = mainTable.getTableHeader().columnAtPoint(e.getPoint());
        // manage click on null column
        if (colIndex == -1)
        {
          // on double left-click inset a column at the end of the table
          if (editable && e.getButton() == MouseEvent.BUTTON1
                  && e.getClickCount() == 2)
          {
            // get number of columns to be inserted
            int colNumber = 1;
            // check if control is pressed to add more columns at once
            if (e.getModifiersEx() == MouseEvent.CTRL_DOWN_MASK)
            {
              try
              {
                // ask for column number and check if it is positive
                colNumber = Integer.parseInt(JOptionPane.showInputDialog(
                        scrollPane, "Insert number of columns to be added",
                        "Add columns...", JOptionPane.QUESTION_MESSAGE));
                if (colNumber <= 0) throw new NumberFormatException();
              } catch (NumberFormatException ex)
              {
                JOptionPane.showMessageDialog(scrollPane,
                        "You must insert a positive integer number.",
                        "Validation error", JOptionPane.ERROR_MESSAGE);
                return;
              }
            }
            // add columns to the table object, by default of character type
            table.addColumns(colNumber);
            // update table column model of main table
            for (int i = colNumber; i > 0; i--)
            {
              int index = table.columns() - i;
              TableColumn tableColumn = new TableColumn();
              tableColumn.setModelIndex(index);
              tableColumn.setHeaderValue(table.getColumnName(index));
              mainTable.addColumn(tableColumn);
            }
            // update column list
            updateColumnList();
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
            int lastIndex = colSelectionModel.getLeadSelectionIndex();
            colSelectionModel.addSelectionInterval(lastIndex, colIndex);
            return;

          }
          // check if control button is pressed to toggle select
          if (e.getModifiersEx() == MouseEvent.CTRL_DOWN_MASK)
          {
            // select or unselect based on current selection
            if (colSelectionModel.isSelectedIndex(colIndex))
              colSelectionModel.removeSelectionInterval(colIndex, colIndex);
            else colSelectionModel.addSelectionInterval(colIndex, colIndex);
            return;
          }
          // unselect the clicked column is alrealdy selected
          if (colSelectionModel.isSelectedIndex(colIndex))
            colSelectionModel.removeSelectionInterval(colIndex, colIndex);
          // else reset column selection and select clicked column
          else
          {
            colSelectionModel.clearSelection();
            colSelectionModel.addSelectionInterval(colIndex, colIndex);
          }
        }
        // manage popup call
        if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 1)
        {
          // if clicked column is not selected set it as unique selection
          if (!colSelectionModel.isSelectedIndex(colIndex))
          {
            colSelectionModel.clearSelection();
            colSelectionModel.addSelectionInterval(colIndex, colIndex);
          }
          // set menu item enable state
          menuCharacter.setEnabled(editable);
          menuNumeric.setEnabled(editable);
          menuRenameCols.setEnabled(editable);
          menuInsertCols.setEnabled(editable);
          menuDeleteCols.setEnabled(editable);
          // set column type menu on current selection
          int[] selected_cols = getSelectedColumns();
          if (selected_cols.length == 1)
          {
            // if single column is selected show its type
            menuCharacter.setSelected(table.getColumnType(selected_cols[0])
                    == Data.CHARACTER);
            menuNumeric.setSelected(table.getColumnType(selected_cols[0])
                    == Data.NUMERIC);
          } else
          {
            // for multiple selection let both unselected
            menuCharacter.setSelected(false);
            menuNumeric.setSelected(false);
          }
          // show the column popup
          colsPopup.show(mainTable.getTableHeader(), e.getX(), e.getY());
        }

      }

      /**
       * Method not implemented.
       */
      @Override
      public void mousePressed(MouseEvent e) {
      }

      /**
       * Method not implemented.
       */
      @Override
      public void mouseReleased(MouseEvent e) {
      }

      /**
       * Method not implemented.
       */
      @Override
      public void mouseEntered(MouseEvent e) {
      }

      /**
       * Method not implemented.
       */
      @Override
      public void mouseExited(MouseEvent e) {
      }
    });
  }

  /**
   * Initializes the scroll pane.
   */
  private void initScrollPane() {
    // initialize viewport to layout row table
    JViewport row_view = new JViewport() {
      /**
       * Override getPreferredSize in order to allow proper scrolling.
       */
      @Override
      public Dimension getPreferredSize() {
        return rowTable.getPreferredSize();
      }
    };
    row_view.setView(rowTable);
    // set scrollpane components
    scrollPane.setRowHeaderView(row_view);
    scrollPane.setViewportView(mainTable);
    // set the row header behaviour upon mouse input
    scrollPane.getRowHeader().addMouseListener(new MouseListener() {
      /**
       * Handles row addition at the end of the table. By double left-click on
       * row
       * header a new row is added at the end of the table. Pressing CTRL key
       * allow
       * for multiple row addition.
       */
      @Override
      public void mouseClicked(MouseEvent e) {
        // manage row addition at the end of the table
        if (editable && e.getButton() == MouseEvent.BUTTON1
                && e.getClickCount() == 2)
        {
          // get number of rows to be inserted
          int rowNumber = 1;
          // check if control is pressed to add more rows at once
          if (e.getModifiersEx() == MouseEvent.CTRL_DOWN_MASK)
          {
            try
            {
              // ask for row number and check it is positive
              rowNumber = Integer.parseInt(JOptionPane.showInputDialog(
                      scrollPane, "Insert number of rows to be added",
                      "Add rows...", JOptionPane.QUESTION_MESSAGE));
              if (rowNumber <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex)
            {
              JOptionPane.showMessageDialog(scrollPane,
                      "You must insert a positive integer number.",
                      "Validation error", JOptionPane.ERROR_MESSAGE);
              return;
            }
          }
          // add row to Table object
          table.addRows(rowNumber);
          // update row and main table models
          int lastRow = table.rows() - rowNumber;
          RowTableModel row_model = (RowTableModel) rowTable.getModel();
          MainTableModel main_model = (MainTableModel) mainTable.getModel();
          row_model.fireTableRowsInserted(lastRow, table.rows() - 1);
          main_model.fireTableRowsInserted(lastRow, table.rows() - 1);
        }

      }

      /**
       * Method not implemented.
       */
      @Override
      public void mousePressed(MouseEvent e) {
      }

      /**
       * Method not implemented.
       */
      @Override
      public void mouseReleased(MouseEvent e) {
      }

      /**
       * Method not implemented.
       */
      @Override
      public void mouseEntered(MouseEvent e) {
      }

      /**
       * Method not implemented.
       */
      @Override
      public void mouseExited(MouseEvent e) {
      }
    });
  }

  /**
   * Initializes the tool bar.
   */
  private void initToolBar() {
    // TODO: to be implemented.
  }

  /**
   * Initializes the lateral panel.
   */
  private void initLateralPane() {
    // initialize lateral pane width
    lateralPaneWidth = 100;

    /* initialize columns list */
    listColumns.setSelectionModel(colSelectionModel);
    listColumns.setModel(new ListModel() {
      /**
       * Returns the number of columns in the Table object.
       */
      @Override
      public int getSize() {
        return table.columns();
      }

      /**
       * Returns the column name at index-th position. The method is not
       * actually
       * used to generate and output. The element content is rendered through
       * ListCellRenderer.
       */
      @Override
      public Object getElementAt(int index) {
        return null;
      }

      /**
       * Method not implemented.
       */
      @Override
      public void addListDataListener(ListDataListener l) {
      }

      /**
       * Method not implemented.
       */
      @Override
      public void removeListDataListener(ListDataListener l) {
      }
    });
    listColumns.setSelectionModel(colSelectionModel);
    listColumns.setCellRenderer(new ListCellRenderer() {
      /**
       * The element of columns list is formatted. The formatting uses list
       * index to
       * point to index-th column in the table object, and selection state to
       * render
       * background.
       */
      @Override
      public Component getListCellRendererComponent(
              JList list, Object value, int index,
              boolean isSelected, boolean cellHasFocus) {
        // create new label to represent list element
        JLabel element = new JLabel();
        element.setOpaque(true);
        // set label text with column name
        element.setText(table.getColumnName(index));
        // set icon based on column type
        String iconURL = new String();
        switch (table.getColumnType(index))
        {
          case Data.CHARACTER:
            iconURL = "/stats/gui/images/character.png";
            break;
          case Data.NUMERIC:
            iconURL = "/stats/gui/images/numeric.png";
            break;
          case Data.UNDEFINED:
          default:
        }
        element.setIconTextGap(7);
        element.setIcon(new ImageIcon(getClass().getResource(iconURL)));
        // set background based on selection
        element.setForeground(FORECOLOR_CELL);
        if (isSelected) element.setBackground(BACKCOLOR_SELECTED_CELL);
        else element.setBackground(list.getBackground());
        // return the newly created label
        return element;
      }
    });
    updateColumnList();

    /* initialize rows list */

    listRows.setModel(new ListModel() {
      @Override
      public int getSize() {
        return selectionGroups.size() + 1;
      }

      @Override
      public Object getElementAt(int index) {
        return null;
      }

      @Override
      public void addListDataListener(ListDataListener l) {
      }

      @Override
      public void removeListDataListener(ListDataListener l) {
      }
    });
    listRows.setCellRenderer(new ListCellRenderer() {
      @Override
      public Component getListCellRendererComponent(
              JList list, Object value, int index,
              boolean isSelected, boolean cellHasFocus) {
        // create new label to represent list element
        JLabel element = new JLabel();
        element.setOpaque(true);
        element.setForeground(FORECOLOR_CELL);
        // first element is for current selection
        if (index != 0)
        {
          // set label text with group name
          SelectionGroup group = selectionGroups.get(index - 1);
          element.setText(group.getName() + " (" + group.countElements() + ")");
          element.setIconTextGap(7);
          element.setIcon(group.getImageIcon());
          if (group.isGroupSelected(rowSelectionModel)
                  && group.countElements() != 0)
            element.setBackground(BACKCOLOR_SELECTED_CELL);
          else element.setBackground(list.getBackground());
        } else
        {
          int[] selectedRows = getSelectedRows();
          element.setText("Selection (" + selectedRows.length + ")");
          if (selectedRows.length == table.rows())
            element.setBackground(BACKCOLOR_SELECTED_CELL);
          else element.setBackground(BACKCOLOR_UNSELECTED_CELL);;
        }
        // return the newly created label
        return element;
      }
    });
    listRows.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        // get the index of clicked element
        int index = listRows.locationToIndex(e.getPoint());
        if (!listRows.getCellBounds(index, index).contains(e.getPoint()))
          index = -1;

        if (e.getButton() == MouseEvent.BUTTON1)
        {
          if (e.getClickCount() == 1)
          {

            if (index == 0)
            {
              int[] selection = getSelectedRows();
              if (selection.length == table.rows())
                rowSelectionModel.clearSelection();
              else rowSelectionModel.addSelectionInterval(0, table.rows() - 1);
            }
            if (index > 0)
            {
              SelectionGroup group = selectionGroups.get(index - 1);
              int[] elements = group.getElements();
              boolean isSelected = group.isGroupSelected(rowSelectionModel);
              if (isSelected)
                for (int i = 0; i < elements.length; i++)
                  rowSelectionModel.removeSelectionInterval(
                          elements[i], elements[i]);
              else
              {
                if (e.getModifiersEx() != MouseEvent.CTRL_DOWN_MASK)
                  rowSelectionModel.clearSelection();
                for (int i = 0; i < elements.length; i++)
                  rowSelectionModel.addSelectionInterval(
                          elements[i], elements[i]);
              }
              return;
            }
          }

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
    updateRowList();
  }

  /**
   * Initializes the color palette menu.
   */
  private void initColorPaletteMenu() {
    // initialize color palette menu
    menuColor.add(menuColorPalette);
    menuColor.add(menuMoreColors);
  }

  /**
   * Updates column list. The function changes border title and updates column
   * list. To be used whenever column structure or selection is modified.
   */
  private void updateColumnList() {
    // get column list border and reset the title
    TitledBorder border = (TitledBorder) scrollColumns.getBorder();
    border.setTitle("Columns (" + getSelectedColumns().length
            + "/" + table.columns() + ")");
    // update UI of scroll pane and column list
    scrollColumns.updateUI();
    listColumns.updateUI();
  }

  /**
   * Updates row list. The function changes border title and updates rows list.
   * To be used whenever rows structure or selection is modified.
   */
  private void updateRowList() {
    // get row list border and reset the title    
    TitledBorder border = (TitledBorder) scrollRows.getBorder();
    border.setTitle("Rows (" + getSelectedRows().length
            + "/" + table.rows() + ")");
    // update UI of scroll pane and row list
    scrollRows.updateUI();
    listRows.updateUI();
  }

  private int findRowSelectionGroup(int index) {

    for (int i = 0; i < selectionGroups.size(); i++)
      if (selectionGroups.get(i).hasIndex(index))
        return i;
    return -1;
  }

  private int findSelectionGroup(int marker, Color color, int size) {
    for (int i = 0; i < selectionGroups.size(); i++)
      if (selectionGroups.get(i).getMarker() == marker
              && selectionGroups.get(i).getColor() == color
              && selectionGroups.get(i).getSize() == size)
        return i;
    return -1;
  }

  private void changeRowSelectionGroup(int[] rows,
          int marker, Color color, int size) {

    int _marker = marker;
    Color _color = color;
    int _size = size;

    // change selection group for each selected row
    for (int i = 0; i < rows.length; i++)
    {
      // check if row is already in a selection group
      int oldGroup = findRowSelectionGroup(rows[i]);
      if (oldGroup != -1)
      {
        // remove row index from old selection group 
        selectionGroups.get(oldGroup).removeIndex(rows[i]);
        // and store marker and size
        if (marker == -1) _marker = selectionGroups.get(oldGroup).getMarker();
        if (color == null) _color = selectionGroups.get(oldGroup).getColor();
        if (size == -1) _size = selectionGroups.get(oldGroup).getSize();
      } else
      {
        if (marker == -1) _marker = SelectionGroup.DEFAULT_MARKER;
        if (color == null) _color = SelectionGroup.DEFAULT_COLOR;
        if (size == -1) _size = SelectionGroup.DEFAULT_SIZE;
      }

      // check if new group is already present
      int newGroup = findSelectionGroup(_marker, _color, _size);
      if (newGroup != -1)
        // add the row index to the existing group
        selectionGroups.get(newGroup).addIndex(rows[i]);
      else
      {
        // create new group and add row index
        SelectionGroup group = new SelectionGroup(
                "Untitled", _marker, _color, _size);
        group.addIndex(rows[i]);
        selectionGroups.add(group);
      }
    }
    // check for empty selection groups
    for (int i = selectionGroups.size() - 1; i >= 0; i--)
      if (selectionGroups.get(i).countElements() == 0)
        selectionGroups.remove(i);
    rowTable.updateUI();
    updateRowList();
  }

}
