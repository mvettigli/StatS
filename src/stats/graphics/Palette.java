/* ============================================================================
 *                   StatS - STatistical Analysis ToolS
 * ============================================================================
 *
 *   File: Palette.java
 *   Date: Jun 9, 2013
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

package stats.graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * The {@code Palette} class defines a color palette to be used as menu item in
 * context menus. Number of rows, columns and size of the color tiles can be
 * chosen by the user during object initialization. When color is selected, the
 * object will fire an ActionPerformed event and selected color can be retrieved
 * through {@code getSelectedColor()}. The selection can be reset through
 * {@code resetSelection()}.
 *
 * @author M. Vettigli
 * @version 2.0
 */
public class Palette extends AbstractButton implements MenuElement {

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
  private Color color;

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
  private int tileSize;

  /**
   * Default constructor for {@code Palette} class. It simply initialize
   * components and set selected color to null.
   */
  public Palette() {
    this(DEFAULT_TILE_SIZE, DEFAULT_ROWS, DEFAULT_COLS);
  }

  /**
   * Constructor for {@code Palette} object. The user can specify the size of
   * colors tiles, column and row number of the palette. Each argument must be
   * positive else default settings will be used.
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
    this.color = null;
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
   * Returns the size of the color tiles.
   *
   * @return size of the tiles, in pixels.
   */
  public int getTileSize() {
    return this.tileSize;
  }

  /**
   * Returns the color selected by the user. If no color was selected, it
   * returns
   * null object.
   *
   * @return selected color or null.
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * Changes the selected color of the palette. If the given color is null
   * object,
   * the assignment will not take place.
   *
   * @param color the new selected color.
   */
  public void setColor(Color color) {
    if (color == null) return;
    this.color = color;
  }

  /**
   * Resets the color selection.
   */
  public void resetSelection() {
    this.color = null;
  }

  /**
   * Method overridden from MenuElement interface, not implemented.
   */
  @Override
  public void processMouseEvent(MouseEvent event, MenuElement[] path,
          MenuSelectionManager manager) {
  }

  /**
   * Method overridden from MenuElement interface, not implemented.
   */
  @Override
  public void processKeyEvent(KeyEvent event, MenuElement[] path,
          MenuSelectionManager manager) {
  }

  /**
   * Method overridden from MenuElement interface, not implemented.
   */
  @Override
  public void menuSelectionChanged(boolean isIncluded) {
  }

  /**
   * Returns an array of subelement of the menu element. Because no other
   * component can be nested in {@code Palette} object, it will return an empty
   * array. Method overridden from MenuElement interface.
   *
   * @return an empty array.
   */
  @Override
  public MenuElement[] getSubElements() {
    return new MenuElement[0];
  }

  /**
   * Returns the component to be painted. Method overridden from MenuElement
   * interface.
   *
   * @return the component to be painted.
   */
  @Override
  public Component getComponent() {
    return this;
  }

  /**
   * Initialize palette components. A color palette of JLabels with different
   * background color will be constructed.
   */
  private void initComponents() {
    // initialize layout manager
    GridLayout layout = new GridLayout(rows, cols, 1, 1);
    this.setLayout(layout);
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
        color = tile.getBackground();
        // fire action performed    
        fireActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                tile.getBackground().toString()));
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
      add(tile);
    }

    /* create next rows with color palette */
    for (float i = 0; i < rows - 1; i++)
    {
      for (float j = 0; j <= 0.8f; j += 0.8f / (cols - 1))
      {
        JLabel tile = new JLabel();
        tile.setOpaque(true);
        tile.setBorder(UNSELECTED_BORDER);
        Color tileColor = Color.getHSBColor(i / rows, 1.0f - j, 1);
        tile.setBackground(tileColor);
        tile.setToolTipText("R:" + tileColor.getRed() + ", G:"
                + tileColor.getGreen() + ", B:" + tileColor.getBlue());
        tile.addMouseListener(listener);
        add(tile);
      }
    }
    // resize the componente based on columns and rows
    setPreferredSize(new Dimension(tileSize * cols, tileSize * rows));
  }

}
