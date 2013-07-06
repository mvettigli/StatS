/* ----------------------------------------------------------------------------
 * File: Data.java
 * Date: March 30th, 2013
 * ----------------------------------------------------------------------------
 */
package stats.core;

/**
 * The {@code Data} class is the common interface for every data type
 * used for data storage in StatS. It is an abstract class and each subclass
 * must implements its methods.
 *
 * @author M. Vettigli
 * @version 2.0
 */
public abstract class Data {

  /**
   * Defines character data type.
   */
  public final static int CHARACTER = 0;

  /**
   * Defines numeric data type.
   */
  public final static int NUMERIC = 1;
  
  /**
   * Defines the undefined data type.
   */
  public final static int UNDEFINED = -1;

  /**
   * The function returns the type of the {@code Data} object. Because it is
   * abstract, each subclass of {@code Data} must implement this method.
   *
   * @return the data type of the object.
   */
  public abstract int type();

  /**
   * The function tells if the content of the {@code Data} object is empty.
   * Because it is abstract, each subclass of {@code Data} must implement this
   * method.
   *
   * @return true if null, else false.
   */
  public abstract boolean isEmpty();

  /**
   * The function sets the content of the {@code Data} object to null
   * value. Because it is abstract, each subclass of {@code Data} must
   * implement this method.
   */
  public abstract void empty();

  /**
   * The function sets the content of the {@code Data} object parsing the
   * string argument. Because it is abstract, each subclass of {@code Data}
   * must implement this method.
   *
   * @param data a string representation of the content.
   * @return true if assignment was successful, else false.
   */
  public abstract boolean set(String data);

  /**
   * The method returns a deep copy of the {@code Data} object. Because it
   * is abstract, each subclass of {@code Data} must implement this method.
   *
   * @return a deep copy of the object.
   */
  @Override
  public abstract Data clone();

}
