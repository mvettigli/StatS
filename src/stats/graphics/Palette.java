/* ----------------------------------------------------------------------------
 * File: Palette.java
 * Date: June 9th, 2013
 * ----------------------------------------------------------------------------
 */
package stats.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * The {@code Palette} class defines a color palette to be used as
 * {@link JMenuItem} in context menus. Number of rows, columns and size
 * of the color tiles can be chosen by the user during object initialization.
 * When color is selected, the object will fire an ActionEvent and selected
 * color can be retrieved through {@code getSelectedColor()}. Before the menu
 * is showed, the selection must be reset through {@code resetSelection()}.
 *
 * @author M. Vettigli
 * @version 1.0
 */
public class Palette extends JMenuItem {

  /**
   * Default number of tile rows.
   */
  private static final int DEFAULT_ROWS = 15;

  /**
   * Default number of tile columns.
   */
  private static final int DEFAULT_COLS = 7;

  /**
   * Default size of color tiles.
   */
  private static final int DEFAULT_TILE_SIZE = 15;

  /**
   * Default unselected tile border.
   */
  private final static Border UNSELECTED_BORDER =
          new LineBorder(Color.BLACK);

  /**
   * Default selected tile border.
   */
  private final static Border SELECTED_BORDER =
          new LineBorder(Color.BLACK, 2);

  /**
   * The color selected by the user. If no selection takes place it is null.
   */
  private Color selectedColor;

  /**
   * Number of row tiles in the palette.
   */
  private int rows;

  /**
   * Number of column tiles in the palette.
   */
  private int cols;

  /**
   * The size of each color tile.
   */
  private static int tileSize = 15;

  /**
   * Default constructor for {@code Palette} class. It simply initialize
   * components and set selected color to null.
   */
  public Palette() {
    this(DEFAULT_TILE_SIZE, DEFAULT_ROWS, DEFAULT_COLS);
  }

  /**
   * Constructor for {@code Palette} object. The user can specify the size of
   * colors tiles, column and row number of the palette. Each argument must
   * be positive else default settings will be used.
   *
   * @param tileSize size of the tiles, in pixels.
   * @param cols number of tile columns.
   * @param rows number of tile rows.
   */
  public Palette(int tileSize, int rows, int cols) {
    // if valid change tile size and column, rows number
    if (tileSize > 0 && cols > 0 && rows > 0)
    {
      this.tileSize = tileSize;
      this.cols = cols;
      this.rows = rows;
    }
    // initialize the Palette object
    initComponents();
    this.selectedColor = null;
  }

  /**
   * Returns the size of the color tiles.
   *
   * @return size of the tiles, in pixels.
   */
  public int getTileSize() {
    return this.tileSize;
  }

  /**
   * Returns the number of tile rows in the palette.
   *
   * @return number of tile rows.
   */
  public int getTileRows() {
    return this.rows;
  }

  /**
   * Returns the number of tile columns in the palette.
   *
   * @return number of tile columns.
   */
  public int getTileColumns() {
    return this.cols;
  }

  /**
   * Returns the color selected by the user. If no color was selected,
   * it returns null object.
   *
   * @return selected color or null.
   */
  public Color getSelectedColor() {
    return this.selectedColor;
  }

  /**
   * Resets the color selection.
   */
  public void resetSelection() {
    this.selectedColor = null;
  }

  /**
   * Initialize palette components. A color palette of JLabels will be
   * constructed.
   */
  private void initComponents() {
    // initialize layout manager
    GridLayout layout = new GridLayout(rows, cols, 1, 1);
    JPanel palette = new JPanel(layout);
    palette.setOpaque(true);
    add(palette);
    // define common MouseListerer
    MouseListener listener = new MouseListener() {
      /**
       * Reset the border to unselected and fire an ActionPerformed event.
       */
      @Override
      public void mouseClicked(MouseEvent e) {
        // get the clicked tile and reset the border
        JLabel tile = (JLabel) e.getSource();
        tile.setBorder(UNSELECTED_BORDER);
        // change color selection and fire ActionPerformed event
        selectedColor = tile.getBackground();
        fireActionPerformed(new ActionEvent(this,
                ActionEvent.ACTION_PERFORMED, null));
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
       * Changes the border of the color tile to selected.
       */
      @Override
      public void mouseEntered(MouseEvent e) {
        JLabel tile = (JLabel) e.getSource();
        tile.setBorder(SELECTED_BORDER);
      }

      /**
       * Changes the border of the color tile to unselected.
       */
      @Override
      public void mouseExited(MouseEvent e) {
        JLabel tile = (JLabel) e.getSource();
        tile.setBorder(UNSELECTED_BORDER);
      }
    };

    /* create first row with gray scale */
    for (int i = 0; i < cols; i++)
    {
      JLabel tile = new JLabel();
      tile.setOpaque(true);
      tile.setBorder(UNSELECTED_BORDER);
      // calculate the gray tone
      int rgb = (int) (i * 255 / (cols - 1));
      tile.setBackground(new Color(rgb, rgb, rgb));
      tile.setToolTipText("R:" + rgb + ", G:" + rgb + ", B:" + rgb);
      tile.addMouseListener(listener);
      palette.add(tile);
    }

    /* create next rows with color palette */
    for (float i = 0; i < rows - 1; i++)
    {
      for (float j = 0; j <= 0.8f; j += 0.8f / (cols - 1))
      {
        JLabel tile = new JLabel();
        tile.setOpaque(true);
        tile.setBorder(UNSELECTED_BORDER);
        Color color = Color.getHSBColor(i / rows, 1.0f - j, 1);
        tile.setBackground(color);
        tile.setToolTipText("R:" + color.getRed() + ", G:"
                + color.getGreen() + ", B:" + color.getBlue());
        tile.addMouseListener(listener);
        palette.add(tile);
      }
    }
    // resize the componente based on columns and rows
    this.setPreferredSize(new Dimension(tileSize * cols,
            tileSize * rows));
  }

}
