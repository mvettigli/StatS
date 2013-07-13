/* ----------------------------------------------------------------------------
 * File: ArraySelector.java
 * Date: July 5th, 2013
 * ----------------------------------------------------------------------------
 */
package stats.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListDataListener;
import stats.core.Array;
import stats.core.DataTypes;

/**
 * The {@code ArraySelector} class implements a {@code JList} object specialized
 * for handling {@code Array} objects. {@code ArraySelector} objects will
 * restrict {@code Array} selection given an allowed data type. If data type is
 * set to undefined, all {@code Array} can be handled.
 *
 * @author M. Vettigli
 * @version 1.0
 */
public class ArraySelector extends JPanel {

  //<editor-fold defaultstate="collapsed" desc="Static Members">
  /**
   * Foreground color of list elements.
   */
  private final static Color FORECOLOR =
          new Color(0, 0, 0);

  /**
   * Background color of selected cells.
   */
  private final static Color BACKCOLOR_SELECTED =
          new Color(0, 191, 255);

  /**
   * Background color of unselected cells.
   */
  private final static Color BACKCOLOR_UNSELECTED =
          new Color(255, 255, 255);
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Private Members">
  /**
   * Stores the allowed data type of {@code Array} objects that will be added to
   * the {@code ArraySelector} object.
   */
  private DataTypes type;

  /**
   * Stores the title of the selector.
   */
  private String title;

  /**
   * Stores the list of {@code Array} objects inserted in the list.
   */
  private ArrayList<Array> elements;

  /**
   * Reference to source {@code ArraySelector} for adding {@code Array} objects
   * in the selector.
   */
  private ArraySelector source;
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Default constructor for {@code ArraySelector} class. It will create new
   * {@code ArraySelector} object with undefined allowed data type and empty
   * title. The object will not be bound to any other {@code ArraySelector}
   * for array insertion through user interface.
   */
  public ArraySelector() {
    this(null, DataTypes.UNDEFINED, null);
  }

  /**
   * Constructor for {@code ArraySelector} class. It will create new
   * {@code ArraySelector} object given allowed data type and empty title. The
   * object will not be bound to any other {@code ArraySelector} for array
   * insertion through user interface.
   *
   * @param type allowed data type of {@code Array} elements.
   */
  public ArraySelector(DataTypes type) {
    this(null, type, null);
  }

  /**
   * Constructor for for {@code ArraySelector} class. It will assign the title
   * of the {@code ArraySelector}, allowed data type for {@code Array}
   * objects to be stored and source {@code ArraySelector} for insertion through
   * user interface.
   *
   * @param title title of the selector.
   * @param type allowed data type of {@code Array} elements.
   * @param source source of {@code Array} elements for insertion.
   */
  public ArraySelector(String title, DataTypes type, ArraySelector source) {

    // handle null titles
    if (title == null) title = new String();

    this.type = type;
    this.source = source;
    this.title = title;
    this.elements = new ArrayList<>();

    initComponents();
    updateSelector();

    /* initialize list model and renderer */
    listElements.setModel(new ListModel() {
      @Override
      public int getSize() {
        return elements.size();
      }

      @Override
      public Object getElementAt(int index) {
        return elements.get(index);
      }

      @Override
      public void addListDataListener(ListDataListener l) {
      }

      @Override
      public void removeListDataListener(ListDataListener l) {
      }
    });
    listElements.setCellRenderer(new ListCellRenderer() {
      @Override
      public Component getListCellRendererComponent(
              JList list, Object value, int index,
              boolean isSelected, boolean cellHasFocus) {
        // create new label to represent list element
        Array element = (Array) value;
        JLabel label = new JLabel();
        label.setOpaque(true);
        // set label text with column name
        label.setText(element.name());
        // set icon based on column type
        label.setIconTextGap(7);
        label.setIcon(element.type().getIcon());
        // set background based on selection
        label.setForeground(FORECOLOR);
        if (isSelected) label.setBackground(BACKCOLOR_SELECTED);
        else label.setBackground(BACKCOLOR_UNSELECTED);
        // return the newly created label
        return label;
      }
    });
    listElements.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {

        // manage popup call
        if (isEditable() && e.getButton() == MouseEvent.BUTTON3
                && e.getClickCount() == 1)
        {
          menuAdd.setEnabled(numberSelectedSourceArrays() > 0);
          menuAddAll.setEnabled(numberSourceArrays() > 0);
          menuRemove.setEnabled(numberSelectedArrays() > 0);
          menuRemoveAll.setEnabled(numberArrays() > 0);
          // show the row popup
          popupMenu.show(listElements, e.getX(), e.getY());
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
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Getters">
  /**
   * Returns the title of the {@code ArraySelector} object. The title is visible
   * on object border.
   *
   * @return title of the list.
   */
  public String getTitle() {
    return ((TitledBorder) scrollPane.getBorder()).getTitle();
  }

  /**
   * Returns the allowed data type of {@code Array} objects that will be stored
   * in the {@code ArraySelector}.
   *
   * @return allowed data type of the list.
   */
  public DataTypes getDataType() {
    return type;
  }

  /**
   * Returns the source {@code ArraySelector} used for insertion through user
   * interface. If it is not set, {@code null} object will be returned.
   *
   * @return the source {@code ArraySelector}.
   */
  public ArraySelector getSourceSelector() {
    return source;
  }

  /**
   * Checks if the {@code ArraySelector} object is editable through user
   * interface. The object will be editable only if the source
   * {@code ArraySelector} is defined.
   *
   * @return true if editable through user interface, else false.
   */
  public boolean isEditable() {
    return (source != null);
  }

  /**
   * Returns a list of all {@code Array} objects contained in the {@code
   * ArraySelector}.
   *
   * @return list of {@code Array} object in the selector.
   */
  public ArrayList<Array> getArrays() {
    return elements;
  }

  /**
   * Returns a list of all {@code Array} objects currently selected in the
   * {@code ArraySelector}.
   *
   * @return list of selected {@code Array} objects.
   */
  public ArrayList<Array> getSelectedArrays() {

    ArrayList<Array> selection = new ArrayList<>();
    int[] selectedIndexes = listElements.getSelectedIndices();
    for (int i = 0; i < selectedIndexes.length; i++)
      selection.add(elements.get(selectedIndexes[i]));
    return selection;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Setters">
  /**
   * Changes the title of the {@code ArraySelector} object.
   *
   * @param title new title of the list.
   */
  public void setTitle(String title) {
    // set new title
    if (title == null) return;
    this.title = title;
    updateSelector();
  }

  /**
   * Changes the allowed data type of the {@code ArraySelector} object. It
   * determines if {@code Array} objects can be stored in the list.
   * If set to undefined, all data type will be allowed for insertion.
   *
   * @param type new allowed data type of the list.
   */
  public void setDataType(DataTypes type) {
    // set new data type
    if (this.type == type) return;
    this.type = type;
    // run consistency check for undesired array stored in the list
    consistencyCheck(type);
  }

  /**
   * Changes the source {@code ArraySelector} for array insertion through user
   * interface. If set to null, the {@code ArraySelector} cannot be edited
   * through user interface.
   *
   * @param source new source {@code ArraySelector} for array insertion.
   */
  public void setSourceSelector(ArraySelector source) {
    // set new source and remove all elements
    this.source = source;
    this.clear();
    updateSelector();
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Inserts an {@code Array} object in the {@code ArraySelector} object. The
   * array type must match selector's type to be successfully inserted. If the
   * array is already present in the selector, it will not be duplicated.
   *
   * @param array array to be inserted.
   */
  public void insertArray(Array array) {
    // check if the array is already contained in the selector
    // and if the data type is allowed for inserion
    if (elements.contains(array)) return;
    if (type != DataTypes.UNDEFINED && array.type() != type) return;
    elements.add(array);
    // update the element list
    listElements.clearSelection();
    listElements.updateUI();
  }

  /**
   * Inserts a list of {@code Array} object in the {@code ArraySelector}. Each
   * array type must match selector's type to be successfully inserted. If
   * already present in the selected, array will not be duplicated.
   *
   * @param arrays list of {@code Array} objects to be inserted.
   */
  public void insertArrays(ArrayList<Array> arrays) {
    // add each array contained in the arraylist
    for (Array array : arrays)
    {
      // check if the array is already contained in the selector
      // and if the data type is allowed for inserion
      if (elements.contains(array)) continue;
      if (type != DataTypes.UNDEFINED && array.type() != type) continue;
      elements.add(array);
    }
    // update the element list
    listElements.clearSelection();
    listElements.updateUI();
  }

  /**
   * Removes an {@code Array} object from the {@code ArraySelector}.
   *
   * @param array array to be removed.
   */
  public void removeArray(Array array) {
    elements.remove(array);
    listElements.clearSelection();
    listElements.updateUI();
  }

  /**
   * Removes a list of {@code Array} objects from the {@code ArraySelector}.
   *
   * @param arrays list of arrays to be removed.
   */
  public void removeArrays(ArrayList<Array> arrays) {
    for (Array array : arrays)
      elements.remove(array);
    listElements.clearSelection();
    listElements.updateUI();
  }

  /**
   * Removes all {@code Array} objects from the {@code ArraySelector}.
   */
  public void clear() {
    elements.clear();
    listElements.clearSelection();
    listElements.updateUI();
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Private Methods">
  /**
   * Update the user interface of the {@code ArraySelector} object based on
   * current status variables.
   */
  private void updateSelector() {
    // reset titled border with selector title
    TitledBorder border = (TitledBorder) scrollPane.getBorder();
    border.setTitle(title);
    scrollPane.updateUI();
    listElements.updateUI();
  }

  /**
   * Checks if list content is coherent with allowed data type. The function
   * scrolls the content of {@code elements} array and removes any {@code Array}
   * object with forbidden data type.
   *
   * @param type data type to be checked for coherence.
   */
  private void consistencyCheck(DataTypes type) {

    // create new array list and fill with allowed Array objects
    ArrayList<Array> allowedElements = new ArrayList<>();
    for (Array element : elements)
      if (element.type() == type) allowedElements.add(element);

    // assign new array list
    elements = allowedElements;

  }

  /**
   * Returns the number of arrays in the {@code ArraySelector}.
   *
   * @return number of arrays.
   */
  private int numberArrays() {
    return getArrays().size();
  }

  /**
   * Returns the number of arrays in the source {@code ArraySelector}.
   *
   * @return number of arrays in the source selector.
   */
  private int numberSourceArrays() {
    if (source != null) return source.getArrays().size();
    else return -1;
  }

  /**
   * Returns the number of selected arrays in the {@code ArraySelector}.
   *
   * @return number of selected arrays.
   */
  private int numberSelectedArrays() {
    return getSelectedArrays().size();
  }

  /**
   * Returns the number of selected arrays in the source {@code ArraySelector}.
   *
   * @return number of selected arrays in source selector.
   */
  private int numberSelectedSourceArrays() {
    if (source != null) return source.getSelectedArrays().size();
    else return -1;
  }

  //</editor-fold>
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    popupMenu = new javax.swing.JPopupMenu();
    menuAdd = new javax.swing.JMenuItem();
    menuAddAll = new javax.swing.JMenuItem();
    menuRemove = new javax.swing.JMenuItem();
    menuRemoveAll = new javax.swing.JMenuItem();
    scrollPane = new javax.swing.JScrollPane();
    listElements = new javax.swing.JList();

    menuAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stats/gui/images/bullet_arrow_right.png"))); // NOI18N
    menuAdd.setText("Insert");
    menuAdd.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuAddActionPerformed(evt);
      }
    });
    popupMenu.add(menuAdd);

    menuAddAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stats/gui/images/bullet_arrow_right_2.png"))); // NOI18N
    menuAddAll.setText("Insert all");
    menuAddAll.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuAddAllActionPerformed(evt);
      }
    });
    popupMenu.add(menuAddAll);

    menuRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stats/gui/images/bullet_arrow_left.png"))); // NOI18N
    menuRemove.setText("Remove");
    menuRemove.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuRemoveActionPerformed(evt);
      }
    });
    popupMenu.add(menuRemove);

    menuRemoveAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stats/gui/images/bullet_arrow_right_2.png"))); // NOI18N
    menuRemoveAll.setText("Remove all");
    menuRemoveAll.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuRemoveAllActionPerformed(evt);
      }
    });
    popupMenu.add(menuRemoveAll);

    setMinimumSize(new java.awt.Dimension(14, 102));
    setPreferredSize(new java.awt.Dimension(350, 102));

    scrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Title"));
    scrollPane.setViewportView(listElements);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
    );
  }// </editor-fold>//GEN-END:initComponents

  /**
   * Handles click on "Insert" menu item. The function will insert all selected
   * arrays in the source {@code ArraySelector} in this selector. Inserted
   * arrays will not be duplicated. If the source selector is not defined,
   * this function should not be called.
   *
   * @param evt the action event.
   */
  private void menuAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAddActionPerformed
    if (source != null) insertArrays(source.getSelectedArrays());
  }//GEN-LAST:event_menuAddActionPerformed

  /**
   * Handles click on "Insert all" menu item. The function will insert all
   * arrays of the source {@code ArraySelector} in this selector. Inserted
   * arrays will not be duplicated. If the source selector is not defined, this
   * function should not be called.
   *
   * @param evt the action event.
   */
  private void menuAddAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAddAllActionPerformed
    if (source != null) insertArrays(source.getArrays());
  }//GEN-LAST:event_menuAddAllActionPerformed

  /**
   * Handles click on "Remove" menu item. The function will remove all selected
   * arrays in this selector.
   *
   * @param evt the action event.
   */
  private void menuRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRemoveActionPerformed
    removeArrays(getSelectedArrays());
  }//GEN-LAST:event_menuRemoveActionPerformed

  /**
   * Handles click on "Remove all" menu item. The function will remove all
   * arrays in this selector.
   *
   * @param evt the action event.
   */
  private void menuRemoveAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRemoveAllActionPerformed
    clear();
  }//GEN-LAST:event_menuRemoveAllActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JList listElements;
  private javax.swing.JMenuItem menuAdd;
  private javax.swing.JMenuItem menuAddAll;
  private javax.swing.JMenuItem menuRemove;
  private javax.swing.JMenuItem menuRemoveAll;
  private javax.swing.JPopupMenu popupMenu;
  private javax.swing.JScrollPane scrollPane;
  // End of variables declaration//GEN-END:variables

}
