/* ============================================================================
 *                   StatS - STatistical Analysis ToolS
 * ============================================================================
 *
 *   File: SelectionGroup.java
 *   Date: Jun 12, 2013
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
package stats.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import java.util.ArrayList;

/**
 * The class {@code SelectionGroup} is used to store indexes of a {@code Table}
 * row selection. A group selection is usually defined by name and marker, which
 * is used to draw data in analysis. The marker can be customized by choosing
 * type, color and size. An image icon will be generated to be used for data
 * display.
 *
 * @author M. Vettigli
 * @version 1.0
 */
public class SelectionGroup {

  /**
   * Represents tiny size.
   */
  public final static int SIZE_TINY = 1;

  /**
   * Represents small size.
   */
  public final static int SIZE_SMALL = 2;

  /**
   * Represents medium size.
   */
  public final static int SIZE_MEDIUM = 3;

  /**
   * Represents large size.
   */
  public final static int SIZE_LARGE = 4;

  /**
   * Represents huge size.
   */
  public final static int SIZE_HUGE = 5;

  /**
   * Represents empty marker. All data grouped with this marker is hidden.
   */
  public final static int MARKER_HIDE = 0;

  /**
   * Represents a full circle marker.
   */
  public final static int MARKER_FULL_CIRCLE = 1;

  /**
   * Represents an hollow circle marker.
   */
  public final static int MARKER_HOLLOW_CIRCLE = 2;

  /**
   * Represents a full square marker.
   */
  public final static int MARKER_FULL_SQUARE = 3;

  /**
   * Represents an hollow square marker.
   */
  public final static int MARKER_HOLLOW_SQUARE = 4;

  /**
   * Defines the default marker used for class initialization, by default
   * a full circle marker.
   */
  public final static int DEFAULT_MARKER = MARKER_FULL_CIRCLE;

  /**
   * Defines the default size used for class initialization, by default
   * medium size.
   */
  public final static int DEFAULT_SIZE = SIZE_MEDIUM;

  /**
   * Defines the default color used for class initialization, by default
   * black.
   */
  public final static Color DEFAULT_COLOR = Color.BLACK;

  /**
   * Size of the image icon to be generated.
   */
  private final static int ICON_SIZE = 11;

  /**
   * Center of the image icon.
   */
  private final static int ICON_CENTER = (ICON_SIZE - 1) / 2;

  /**
   * Counter used to generate untitled group names.
   */
  private static int untitledNumber = 1;

  /**
   * Array list storing element of the selection group.
   */
  private ArrayList<Integer> elements;

  /**
   * Name of the selection group.
   */
  private String name;

  /**
   * Marker type, used to draw data belonging to the selection group.
   */
  private int marker;

  /**
   * Color of the marker, used to draw data belonging to the selection group.
   */
  private Color color;

  /**
   * Size of the marker, used to draw data belonging to the selection group.
   */
  private int size;

  /**
   * Generated icon image, used to draw data belonging to the selection group.
   */
  private ImageIcon icon;

  /**
   * Constructor for {@code SelectionGroup} class given group name. The name of
   * the object cannot be empty. If so, a default name is generated. By default,
   * marker type is a full circle, color is black and size is small.
   *
   * @param name name of the selection group.
   */
  public SelectionGroup(String name) {
    this(name, DEFAULT_MARKER, DEFAULT_COLOR, DEFAULT_SIZE);
  }

  /**
   * Constructor for {@code SelectionGroup} class given group name, marker type,
   * color and size. All properties of the object are directly initialized. If
   * any argument is not valid, default initialization is used.
   *
   * @param name name of the selection group.
   * @param marker type of marker.
   * @param color color of the marker.
   * @param size size of the marker.
   */
  public SelectionGroup(String name, int marker, Color color, int size) {
    // check if arguments are valid
    if (name == null || name.isEmpty()) name = getUntitledName();
    if (color == null) color = Color.BLACK;
    if (marker != MARKER_HIDE
            && marker != MARKER_FULL_CIRCLE
            && marker != MARKER_FULL_SQUARE
            && marker != MARKER_HOLLOW_CIRCLE
            && marker != MARKER_HOLLOW_CIRCLE)
      marker = MARKER_FULL_CIRCLE;
    if (size != SIZE_TINY
            && size != SIZE_SMALL
            && size != SIZE_MEDIUM
            && size != SIZE_LARGE
            && size != SIZE_HUGE)
      size = SIZE_TINY;
    /* initialize object variables */
    this.elements = new ArrayList<>();
    this.name = name;
    this.color = color;
    this.marker = marker;
    this.size = size;
    // generate icon
    this.icon = generateImageIcon();
  }

  /**
   * Returns the name of the selection group.
   *
   * @return name of the selection group.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the type of marker of the selection group.
   *
   * @return type of marker.
   */
  public int getMarker() {
    return marker;
  }

  /**
   * Returns the color of the marker of the selection group.
   *
   * @return color of the marker.
   */
  public Color getColor() {
    return color;
  }

  /**
   * Returns the size of the marker of the selection group.
   *
   * @return size of the marker.
   */
  public int getSize() {
    return size;
  }

  /**
   * Returns the number of elements currently belonging to the selection group.
   *
   * @return number of elements of the group.
   */
  public int countElements() {
    return elements.size();
  }

  /**
   * Returns an array of integers containing all elements stored in the
   * selection group.
   *
   * @return the elements of group, as array of integers.
   */
  public int[] getElements() {
    int[] elementList = new int[elements.size()];
    for (int i = 0; i < elements.size(); i++)
      elementList[i] = elements.get(i);
    return elementList;
  }

  /**
   * Returns the image icon of the marker, generated from object current
   * properties. The size of the icon is 11x11 pixels.
   *
   * @return the image icon of the marker
   */
  public ImageIcon getImageIcon() {
    return this.icon;
  }

  /**
   * Checks if all elements of the selection group are selected in the given
   * {@code ListSelectionModel} object.
   *
   * @param model list selection model to be checked.
   * @return true if all elements of the group are selected, else false.
   */
  public boolean isGroupSelected(ListSelectionModel model) {
    // check if all elements of the group are selected
    for (int i = 0; i < elements.size(); i++)
      if (!model.isSelectedIndex(elements.get(i))) return false;
    return true;
  }

  /**
   * Checks if the given index is present in the selection group.
   *
   * @param index index of the element to be checked.
   * @return true if present in the group, else false.
   */
  public boolean hasIndex(int index) {
    // check if the given index is in the selection group
    for (int i = 0; i < elements.size(); i++)
      if (elements.get(i) == index) return true;
    return false;
  }

  /**
   * Changes the name of the selection group. The new name cannot be empty.
   *
   * @param name new name of the group.
   */
  public void setName(String name) {
    // check if new name is valid
    if (name == null || name.isEmpty()) return;
    if (name.equals(this.name)) return;
    // change the name of the object
    this.name = name;
  }

  /**
   * Changes the type of the marker used to render the data belonging to the
   * selection group. The marker type argument must be defined as static member
   * of
   * {@code SelectionGroup} class.
   *
   * @param marker new marker of the group.
   */
  public void setMarker(int marker) {
    // check if new marker is valid
    if (marker != MARKER_HIDE
            && marker != MARKER_FULL_CIRCLE
            && marker != MARKER_HOLLOW_CIRCLE
            && marker != MARKER_FULL_SQUARE
            && marker != MARKER_HOLLOW_SQUARE)
      return;
    if (marker == this.marker) return;
    // reassign marker and regenerate icon
    this.marker = marker;
    this.icon = generateImageIcon();
  }

  /**
   * Changes the color of the marker associated to the selection group.
   *
   * @param color new color of the marker.
   */
  public void setColor(Color color) {
    // check if new color is different from current one
    if (this.color == color) return;
    // reassign color and regenerate icon
    this.color = color;
    this.icon = generateImageIcon();
  }

  /**
   * Changes the size of the marker of the selection group. The marker size
   * argument must be defined as static member of {@code SelectionGroup} class.
   *
   * @param size new size of the marker.
   */
  public void setSize(int size) {
    // check if new size is valid
    if (size != SIZE_TINY
            && size != SIZE_SMALL
            && size != SIZE_MEDIUM
            && size != SIZE_LARGE
            && size != SIZE_HUGE)
      return;
    if (size == this.size) return;
    // reassign size and regenerate icon
    this.size = size;
    this.icon = generateImageIcon();
  }

  /**
   * Adds a new element to the selection group. The element is commonly the row
   * index of a {@code Table} object. It must be a positive integer number. If
   * the element is already present in the selection group, the function will
   * return false.
   *
   * @param element the element to be inserted in the group.
   * @return true if the addition was successful, else false.
   */
  public boolean addIndex(int element) {
    // check if element is valid and already in the group
    if (element < 0) return false;
    if (this.hasIndex(element)) return false;
    // add the new index to the selection group
    elements.add(element);
    return true;
  }

  /**
   * Removes an element from the selection group. The element is commonly the
   * row
   * index of a {@code Table} object. It must be a positive integer number.
   *
   * @param element element to be removed from the group.
   * @return true if removal was successful, else false.
   */
  public boolean removeIndex(int element) {
    // check if index is in the group
    if (!this.hasIndex(element)) return false;
    elements.remove(this.findElement(element));
    return true;
  }

  /**
   * Finds the index of an element stored in the {@code elements} array list. If
   * the element is not found, -1 is returned
   *
   * @param element desired element in the array list.
   * @return index of the given element, -1 if not found.
   */
  private int findElement(int element) {
    // find the index of the element
    for (int i = 0; i < elements.size(); i++)
      if (elements.get(i) == element) return i;
    // if not found return -1
    return -1;
  }

  /**
   * Generates the image of the marker to be used for data drawing. The image is
   * created starting from the current properties of the selection group object.
   * The size of the image is 11x11 pixels by default.
   *
   * @return an image icon of the marker.
   */
  private ImageIcon generateImageIcon() {
    // initialize variables for drawing
    BufferedImage image = new BufferedImage(ICON_SIZE, ICON_SIZE,
            BufferedImage.TYPE_INT_ARGB);
    Graphics g = image.getGraphics();
    /* used in debug mode to paint a black background
     g.setColor(Color.BLACK);
     g.fillRect(0, 0, 11, 11);
     */
    // set color of the drawing
    g.setColor(color);
    // draw the marker based on its type
    switch (this.marker)
    {
      case MARKER_FULL_CIRCLE:
        g.fillOval(ICON_CENTER - size, ICON_CENTER - size, size * 2, size * 2);
      case MARKER_HOLLOW_CIRCLE:
        g.drawOval(ICON_CENTER - size, ICON_CENTER - size, size * 2, size * 2);
        break;
      case MARKER_FULL_SQUARE:
        g.fillRect(ICON_CENTER - size, ICON_CENTER - size, 2 * size, 2 * size);
      case MARKER_HOLLOW_SQUARE:
        g.drawRect(ICON_CENTER - size, ICON_CENTER - size, 2 * size, 2 * size);
        break;
      case MARKER_HIDE:
      default:
    }
    // return an ImageIcon
    return new ImageIcon(image);
  }

  /**
   * Generates an untitled name for new selection group instancing. The name is
   * created using a static integer counter.
   *
   * @return a new untitled name.
   */
  private String getUntitledName() {
    return "Group" + untitledNumber++;
  }

}
