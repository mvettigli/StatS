/* ============================================================================
 *                   StatS - STatistical Analysis ToolS
 * ============================================================================
 *
 *   File: Data.java
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

/**
 * The {@code Data} class is the common interface for every data type
 * used for data storage in StatS. It is an abstract class and each subclass
 * must implements its methods.
 *
 * @author M. Vettigli
 * @version 3.0
 */
public abstract class Data {

  //<editor-fold defaultstate="collapsed" desc="Getters">
  /**
   * Returns the type of the {@code Data} object. Because it is abstract, each
   * subclass of {@code Data} must implement this method.
   *
   * @return the data type of the object.
   */
  public abstract DataTypes type();

  /**
   * The function tells if the content of the {@code Data} object is empty.
   * Because it is abstract, each subclass of {@code Data} must implement this
   * method.
   *
   * @return true if null, else false.
   */
  public abstract boolean isEmpty();
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Setters">
  /**
   * Changes the content of the {@code Data} object parsing the string argument.
   * Because it is abstract, each subclass of {@code Data} must implement this
   * method.
   *
   * @param data a string representation of the content.
   * @return true if assignment was successful, else false.
   */
  public abstract boolean set(String data);

  /**
   * Changes the content of the {@code Data} object to null value. Because it
   * is abstract, each subclass of {@code Data} must implement this method.
   */
  public abstract void empty();
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * The method returns a deep copy of the {@code Data} object. Because it
   * is abstract, each subclass of {@code Data} must implement this method.
   *
   * @return a deep copy of the object.
   */
  @Override
  public abstract Data clone();
  //</editor-fold>

}
