/* ============================================================================
 *                   StatS - STatistical Analysis ToolS
 * ============================================================================
 *
 *   File: DialogInsertCols.java
 *   Date: May 26, 2013
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

package stats.gui.dialogs;

import java.awt.Frame;
import stats.core.DataTypes;

/**
 * The {@code DialogInsertCols} class is a dialog for getting all the
 * informations needed for insertion of columns in a {@code Table} object
 * upon selection. The information are:
 * <ul>
 * <li>column root name,</li>
 * <li>data type of the new columns,</li>
 * <li>number of columns to be inserted</li>
 * <li>position of the insertion.
 * </ul>
 * After initialization, call {@code showDialog()} to show the dialog and
 * get dialog result.
 *
 * @author M. Vettigli
 * @version 1.0
 */
public class DialogInsertCols extends javax.swing.JDialog {

  /**
   * Define the insertion position after current selection.
   */
  public static final int POSITION_AFTER = 0;

  /**
   * Define the insertion position before current selection.
   */
  public static final int POSITION_BEFORE = 1;

  /**
   * Define the insertion position at the beginning of the table.
   */
  public static final int POSITION_BEGIN = 2;

  /**
   * Define the insertion position at the end of the table.
   */
  public static final int POSITION_END = 3;

  /**
   * Stores the result of the dialog. If true, the dialog was successfully
   * closed and insertion can take place. If false, the dialog was closed or
   * the operation was not confirmed.
   */
  private boolean dialogResult;

  /**
   * Creates new form DialogInsertCols
   */
  public DialogInsertCols(Frame parent, boolean modal) {

    /* initialize form components */
    super(parent, modal);
    initComponents();
    this.getRootPane().setDefaultButton(buttonOk);

    /* initialize private variable */
    dialogResult = false;
  }

  /**
   * Returns the root name of the new columns to be inserted. The value is
   * extracted from the content of text field inserted by the user.
   *
   * @return name for new columns.
   */
  public String getColumnName() {
    return textName.getText();
  }

  /**
   * Returns the type of new columns to be inserted. The return type is
   * related to the selection of combo-box.
   *
   * @return the type of new columns.
   */
  public DataTypes getColumnType() {
    //get the label of the selected item..
    String selection = comboDataType.getSelectedItem().toString();
    // and return data type based on its content 
    if (selection.equals("Character")) return DataTypes.CHARACTER;
    else if (selection.equals("Numeric")) return DataTypes.NUMERIC;
    return DataTypes.UNDEFINED;
  }

  /**
   * Returns the number of new columns to be inserted. The value is extracted
   * from the spinner-box of the dialog.
   *
   * @return number of new columns.
   */
  public int getColumnNumber() {
    int columnNumber = 0;
    try
    {
      columnNumber = Integer.parseInt(spinnerNumber.getValue().toString());
    } catch (NumberFormatException e)
    {
      return 0;
    }
    return columnNumber;
  }

  /**
   * Returns the position of the new columns to be inserted. It depends on
   * selection of radio-boxes in the dialog window by the user. The possible
   * results are:
   * <ul> <li>{@code POSITION_AFTER},</li>
   * <li>{@code POSITION_BEFORE},</li>
   * <li>{@code POSITION_BEGIN},</li>
   * <li>{@code POSITION_END}</li></ul>
   *
   * @return position of the insertion.
   */
  public int getColumnPosition() {
    // check which radio button is selected and returns the proper value
    if (radioAfter.isSelected()) return POSITION_AFTER;
    else if (radioBefore.isSelected()) return POSITION_BEFORE;
    else if (radioBegin.isSelected()) return POSITION_BEGIN;
    else return POSITION_END;
  }

  /**
   * The function shows the {@code DialogInsertCols} object in modal state and
   * returns true if the insertion is confirmed, else false.
   *
   * @return true if insertion is confirmed, else false.
   */
  public boolean showDialog() {
    setVisible(true);
    return dialogResult;
  }

  /**
   * This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    radio_group = new javax.swing.ButtonGroup();
    textName = new javax.swing.JTextField();
    radioBefore = new javax.swing.JRadioButton();
    radioAfter = new javax.swing.JRadioButton();
    radioBegin = new javax.swing.JRadioButton();
    radioEnd = new javax.swing.JRadioButton();
    comboDataType = new javax.swing.JComboBox();
    spinnerNumber = new javax.swing.JSpinner();
    javax.swing.JLabel labelHeader = new javax.swing.JLabel();
    javax.swing.JLabel labelDataType = new javax.swing.JLabel();
    javax.swing.JLabel labelNumber = new javax.swing.JLabel();
    javax.swing.JLabel labelPosition = new javax.swing.JLabel();
    separator = new javax.swing.JSeparator();
    buttonOk = new javax.swing.JButton();
    buttonCancel = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Insert column(s)...");

    radio_group.add(radioBefore);
    radioBefore.setText("before");

    radio_group.add(radioAfter);
    radioAfter.setSelected(true);
    radioAfter.setText("after");

    radio_group.add(radioBegin);
    radioBegin.setText("begin");

    radio_group.add(radioEnd);
    radioEnd.setText("end");

    comboDataType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Character", "Numeric" }));

    spinnerNumber.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));

    labelHeader.setText("Insert name of new column(s)");

    labelDataType.setText("Type :");

    labelNumber.setText("Number :");

    labelPosition.setText("Position :");

    buttonOk.setText("OK");
    buttonOk.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonOkActionPerformed(evt);
      }
    });

    buttonCancel.setText("Cancel");
    buttonCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonCancelActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(labelDataType)
              .addComponent(labelPosition))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addComponent(comboDataType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelNumber)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGroup(layout.createSequentialGroup()
                .addComponent(radioAfter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioBefore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioBegin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioEnd))))
          .addComponent(textName)
          .addGroup(layout.createSequentialGroup()
            .addComponent(labelHeader)
            .addGap(0, 0, Short.MAX_VALUE))
          .addComponent(separator)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(buttonOk)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonCancel)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(labelHeader)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(radioBefore)
          .addComponent(radioAfter)
          .addComponent(radioBegin)
          .addComponent(radioEnd)
          .addComponent(labelPosition))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(comboDataType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(spinnerNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(labelDataType)
          .addComponent(labelNumber))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(5, 5, 5)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(buttonCancel)
          .addComponent(buttonOk))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  /**
   * Handles the click on "OK" button. The function will set the dialog result
   * flag on true and it will dispose the dialog.
   *
   * @param evt the action event.
   */
  private void buttonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOkActionPerformed
    dialogResult = true;
    this.dispose();
  }//GEN-LAST:event_buttonOkActionPerformed

  /**
   * Handles the click on "Cancel" button. The function will set the dialog
   * result flag on false and it will dispose the dialog.
   *
   * @param evt the action event.
   */
  private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
    dialogResult = false;
    this.dispose();
  }//GEN-LAST:event_buttonCancelActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonCancel;
  private javax.swing.JButton buttonOk;
  private javax.swing.JComboBox comboDataType;
  private javax.swing.JRadioButton radioAfter;
  private javax.swing.JRadioButton radioBefore;
  private javax.swing.JRadioButton radioBegin;
  private javax.swing.JRadioButton radioEnd;
  private javax.swing.ButtonGroup radio_group;
  private javax.swing.JSeparator separator;
  private javax.swing.JSpinner spinnerNumber;
  private javax.swing.JTextField textName;
  // End of variables declaration//GEN-END:variables

}
