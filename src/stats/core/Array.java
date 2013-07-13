/* ============================================================================
 *                   StatS - STatistical Analysis ToolS
 * ============================================================================
 *
 *   File: Array.java
 *   Date: Mar 30, 2013
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

import java.util.ArrayList;

/**
 * The {@code Array} class represents an array of {@code Data} elements. An
 * array must contain elements of the same data type but the common interface
 * can be used with all data types.
 *
 * @author M. Vettigli
 * @version 3.0
 */
public class Array {

  //<editor-fold defaultstate="collapsed" desc="Static Members">
  /**
   * Counter for unique untitled naming of {@code Array} objects.
   */
  private static int untitled_number = 1;
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Private Members">
  /**
   * Variable used to store the name of the {@code Array} object.
   */
  private String name;

  /**
   * Array of {@code Data} object to actually store the data.
   */
  private ArrayList<Data> array;
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Default constructor for {@code Array} class. The name of the object will
   * be generated by default as untitled name.
   */
  public Array() {
    this(getUntitledName());
  }

  /**
   * Constructor for {@code Array} class with name argument. The name of the
   * {@code Array} cannot be empty, it will be substitute by untitled name.
   *
   * @param name name of the array.
   */
  public Array(String name) {
    // check if argument is empty and use untitled name
    if (name == null || name.isEmpty()) name = getUntitledName();
    // assign the name to the Array
    this.name = name;
    // initialize the ArrayList
    array = new ArrayList<>();
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Getters">
  /**
   * Returns the name of the {@code Array} object.
   *
   * @return name of the object.
   */
  public String name() {
    return name;
  }

  /**
   * Returns the current size of the {@code Array} object.
   *
   * @return size of the array.
   */
  public int size() {
    return array.size();
  }

  /**
   * Returns the data type of the objects stored in the {@code Array} object.
   * If no element is stored, {@code Data.UNDEFINED} is returned.
   *
   * @return the data type of {@code Array} elements.
   */
  public DataTypes type() {
    if (array.isEmpty()) return DataTypes.UNDEFINED;
    return array.get(0).type();
  }

  /**
   * Checks if the {@code index} argument is a valid index for the {@code Array}
   * object.
   *
   * @param index integer number to be checked.
   * @return true if the index is valid, else false.
   */
  public boolean isIndex(int index) {
    return (index < 0 || index >= array.size());
  }

  /**
   * Returns the content of the {@code Array} at the specified position.
   *
   * @param index position inside the {@code Array}.
   * @return {@code Data} object stored in the {@code Array}.
   * @throws ArrayIndexOutOfBoundsException
   */
  public Data get(int index) {
    // check if index is valid
    if (this.isIndex(index)) throw new ArrayIndexOutOfBoundsException();
    return array.get(index);
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Setters">
  /**
   * Changes the name of the {@code Array} object. Empty names are not allowed.
   *
   * @param name new name of the {@code Array}.
   * @return true if successful, else false.
   */
  public boolean setName(String name) {
    // check if the name is not null
    if (name == null || name.isEmpty()) return false;
    // assign the name to the Array
    this.name = name;
    return true;
  }

  /**
   * Replaces the element of the {@code Array} object at the given position.
   *
   * @param index position of the element to be replaced.
   * @param data new data to substitute the element.
   * @throws ArrayIndexOutOfBoundsException
   */
  public boolean set(int index, Data data) {
    // check if the index is valid
    if (this.isIndex(index)) throw new ArrayIndexOutOfBoundsException();
    // check if data type is valid
    if (data.type() != this.type() && this.type() != DataTypes.UNDEFINED)
      return false;
    // assign the value
    array.set(index, data);
    return true;
  }

  /**
   * Replaces the element of the {@code Array} object at the given position
   * parsing the string representation.
   *
   * @param index position of the element to be replaced.
   * @param data a string representation of the new data.
   * @throws ArrayIndexOutOfBoundsException
   */
  public boolean set(int index, String data) {
    if (this.isIndex(index)) throw new ArrayIndexOutOfBoundsException();
    return array.get(index).set(data);
  }

  /**
   * Sets an element of the {@code Array} object specified by the index to
   * null value.
   *
   * @param index position inside the {@code Array}.
   * @throws ArrayIndexOutOfBoundsException
   */
  public void empty(int index) {
    // check if index is valid
    if (this.isIndex(index)) throw new ArrayIndexOutOfBoundsException();
    // empty the element
    array.get(index).empty();
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Adds a null element at the end of the {@code Array} object.
   */
  public void add() {
    this.add(nullElement(), 1);
  }

  /**
   * Adds a given number of null elements at the end of the {@code Array}
   * object.
   *
   * @param number number of null elements to be added.
   * @return true if successful, else false.
   */
  public boolean add(int number) {
    return this.add(nullElement(), number);
  }

  /**
   * Adds an element at the end of the {@code Array} object. The element must
   * be of the same type of those already stored.
   *
   * @param data element to be added.
   * @return true if successful, else false.
   */
  public boolean add(Data data) {
    return this.add(data, 1);
  }

  /**
   * Adds a given number of elements at the end of the {@code Array} object. The
   * element must be of the same type of those already stored.
   *
   * @param data element to be added.
   * @param number number of elements to be added.
   * @return true if successful, else false.
   */
  public boolean add(Data data, int number) {
    // check if DataType is valid
    DataTypes type = type();
    if (data.type() != type && type != DataTypes.UNDEFINED)
      return false;
    // check if number is valid
    if (number < 1) return false;
    // perform the insertion
    for (int i = 0; i < number; i++)
      array.add(data.clone());
    return true;
  }

  /**
   * Inserts a null element at the beginning of the {@code Array} object.
   *
   * @return true if successful, else false.
   */
  public boolean insert() {
    return this.insert(0, nullElement(), 1);
  }

  /**
   * The function inserts a null element to the {@code Array} object at a
   * specific position of the array.
   *
   * @param index position of the insertion.
   * @return true if successful, else false.
   * @throws ArrayIndexOutOfBoundsException
   */
  public boolean insert(int index) {
    return this.insert(index, nullElement(), 1);
  }

  /**
   * Inserts a given number of null elements to the {@code Array} object at a
   * specific position of the array.
   *
   * @param index position of the insertion.
   * @param number number of elements to be inserted.
   * @return true if successful, else false.
   * @throws ArrayIndexOutOfBoundsException
   */
  public boolean insert(int index, int number) {
    return this.insert(index, nullElement(), number);
  }

  /**
   * Inserts a given number of elements to the {@code Array} object at a
   * specific position of the array. The element must be of the same type of
   * those already stored.
   *
   * @param index position for insertion.
   * @param data element to be inserted.
   * @param number number of elements to be inserted.
   * @return true if the insertion was successful, else false.
   * @throws ArrayIndexOutOfBoundsException
   */
  public boolean insert(int index, Data data, int number) {
    // check if index is valid
    if (this.isIndex(index)) throw new ArrayIndexOutOfBoundsException();
    // check if DataType is valid
    DataTypes type = type();
    if (data.type() != type && type != DataTypes.UNDEFINED)
      return false;
    // check if number is valid
    if (number < 1) return false;
    // perform the insertion
    for (int i = 0; i < number; i++)
      array.add(index, data.clone());
    return true;
  }

  /**
   * Removes an element of the {@code Array} at a given position.
   *
   * @param index position for deletion.
   * @throws ArrayIndexOutOfBoundsException
   */
  public void remove(int index) {
    // check if index is valid
    if (this.isIndex(index)) throw new ArrayIndexOutOfBoundsException();
    // remove the element
    array.remove(index);
  }

  /**
   * Removes all elements inside the {@code Array} object.
   */
  public void clear() {
    array.clear();
  }

  /**
   * Swaps two elements inside the {@code Array} object.
   *
   * @param index1 first position of the element to be swapped.
   * @param index2 second position of the element to be swapped.
   * @throws ArrayIndexOutOfBoundsException
   */
  public void swap(int index1, int index2) {
    // check if indexes are valid
    if (!isIndex(index1) || isIndex(index2))
      throw new ArrayIndexOutOfBoundsException();
    // swap the elements
    Data data = array.get(index1);
    array.set(index1, array.get(index2));
    array.set(index2, data);
  }

  /**
   * Fills the {@code Array} object with given null elements until the final
   * size is achieved.
   *
   * @param size new size of the {@code Array}.
   * @return true if successful, else false.
   */
  public boolean fill(int size) {
    return this.fill(size, nullElement());
  }

  /**
   * Fills the {@code Array} object with given {@code Data} elements until the
   * final size is achieved.
   *
   * @param size new size of the {@code Array}.
   * @param data element used to fill the {@code Array}.
   * @return true if successful, else false.
   */
  public boolean fill(int size, Data data) {
    // check if new size is valid
    if (size <= array.size()) return false;
    // check if data type is valid
    if (data.type() != this.type() && this.type() != DataTypes.UNDEFINED)
      return false;
    // add the elements to the array
    int number = size - array.size();
    for (int i = 0; i < number; i++)
      array.add(data.clone());
    return true;
  }

  /**
   * Returns a string representation of the {@code Array} and its content.
   *
   * @return a string representation of the array.
   */
  @Override
  public String toString() {
    // initialize the string builder
    StringBuilder sb = new StringBuilder();
    // create the header
    sb.append("|").append(name).append(", ");
    if (!array.isEmpty()) sb.append(this.size()).append(" ");
    sb.append(this.type()).append("|> ( ");
    // add the content
    for (int i = 0; i < array.size(); i++)
    {
      if (array.get(i).isEmpty()) sb.append("_");
      else sb.append(array.get(i).toString());
      if (i + 1 != array.size()) sb.append(" , ");
    }
    sb.append(" )");
    return sb.toString();
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Private Methods">
  /**
   * Returns an untitled name for the {@code Array} object.
   *
   * @return a string containing the untitled array name.
   */
  private static String getUntitledName() {
    return "Array" + untitled_number++;
  }

  /**
   * Returns a null element based on current data type stored by the
   * {@code Array}.
   *
   * @return a null {@code Data} object.
   */
  private Data nullElement() {
    Data nullValue;
    switch (this.type())
    {
      case NUMERIC:
        nullValue = new Numeric();
        break;
      case CHARACTER:
      case UNDEFINED:
      default:
        nullValue = new Character();
    }
    return nullValue;
  }
  //</editor-fold>
}
