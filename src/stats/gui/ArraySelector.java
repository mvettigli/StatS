/* ----------------------------------------------------------------------------
 * File: ArraySelector.java
 * Date: July 5th, 2013
 * ----------------------------------------------------------------------------
 */
package stats.gui;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import stats.core.Array;
import stats.core.DataTypes;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListDataListener;

/**
 * The {@code ArraySelector} class implements a {@code JList} object specialized
 * for handling {@code Array} objects. {@code ArraySelector} objects will
 * restrict {@code Array} selection given an allowed data type. If data type is
 * set to undefined, all {@code Array} can be handled.
 *
 * @author M. Vettigli
 * @version 1.0
 */
public class ArraySelector extends JScrollPane {

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

  // <editor-fold defaultstate="collapsed" desc="Private Members"> 
  /**
   * Stores the title of the {@code ArraySelector} object. It will used as label
   * for the scroll pane border.
   */
  private String title;

  /**
   * Stores the allowed data type of {@code Array} objects that will be added to
   * the {@code ArraySelector} object.
   */
  private DataTypes type;

  /**
   * The actual {@code JList} object the user will interact with.
   */
  private JList list;

  /**
   * Stores the list of {@code Array} objects inserted in the list.
   */
  private ArrayList<Array> elements;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructors"> 
  /**
   * Default constructor for {@code ArraySelector} class. It will create new
   * {@code ArraySelector} object with undefined allowed data type and empty
   * title.
   */
  public ArraySelector() {
    this(null, DataTypes.UNDEFINED);
  }

  /**
   * Constructor for for {@code ArraySelector} class. It will assign the title
   * of the {@code ArraySelector} and allowed data type for {@code Array}
   * objects to be stored.
   *
   * @param title title of the {@code Array} list.
   * @param type allowed data type of {@code Array} objects.
   */
  public ArraySelector(String title, DataTypes type) {

    // handle null titles
    if (title == null) title = new String();

    /* initialize private members */
    this.title = title;
    this.type = type;
    this.elements = new ArrayList<>();

    initComponents();

  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Getters"> 
  /**
   * Returns the title of the {@code ArraySelector} object. The title is visible
   * on object border.
   *
   * @return title of the list.
   */
  public String getTitle() {
    return title;
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
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Setters"> 
  /**
   * Changes the title of the {@code ArraySelector} object. The title is visible
   * on object border.
   *
   * @param title new title of the list.
   */
  public void setTitle(String title) {
    // check if given title is not null
    if (title == null) title = new String();
    // change border title property
    TitledBorder border = (TitledBorder) this.getBorder();
    border.setTitle(title);

    // assign new value
    this.title = title;
  }

  /**
   * Changes the allowed data type of the {@code ArraySelector} object. It
   * determines if {@code Array} objects can be stored in the list.
   * If set to undefined, all data type will be allowed for insertion.
   *
   * @param type new allowed data type of the list.
   */
  public void setDataType(DataTypes type) {
    if (this.type == type) return;
    this.type = type;

    // run consistency check for undesired array stored in the list
    consistencyCheck(type);
  }
  // </editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Methods">  
  public void addArray(Array array) {
    // ahandle 
    if (elements.contains(array)) return;
    if (type != DataTypes.UNDEFINED && array.type() != type) return;
    elements.add(array);

  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Private Methods">
  /**
   * Initializes object components.
   */
  private void initComponents() {

    /* initialize list model and renderer */
    list = new JList(new ListModel() {
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
    list.setCellRenderer(new ListCellRenderer() {
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

    /* initializes scroll pane properties */
    this.setViewportView(list);
    this.setBorder(new TitledBorder(this.title));

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
  //</editor-fold>

}
