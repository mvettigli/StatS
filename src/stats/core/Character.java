/* ----------------------------------------------------------------------------
 * File: Character.java
 * Date: March 30th, 2013
 * ----------------------------------------------------------------------------
 */
package stats.core;

/**
 * The {@code Character} class is used to store an alphanumeric data. It
 * is a subclass of {@code Data} and implements all abstract methods. Because
 * the data is stored as a String object, the interface is very similar to the
 * superclass it is inhered from.
 *
 * @author M. Vettigli
 * @version 2.0
 */
public class Character extends Data {

  /**
   * The null value for {@code Character} class, an empty string.
   */
  private static final String NULL = "";

  /**
   * The variable used to store content of {@code Character} object.
   */
  private String data;

  /**
   * Default constructor for {@code Character} class. The content is
   * initialized with null value.
   */
  public Character() {
    this.data = NULL;
  }

  /**
   * Constructor with argument for {@code Character} class. The content is
   * initialized with given argument.
   *
   * @param data the alphanumeric content of {@code Character} object.
   */
  public Character(String data) {
    this.data = data;
  }

  /**
   * The function sets the content of the {@code Character} object with the
   * {@code data} argument.
   *
   * @param data the new content of the object.
   * @return true if successful, else false.
   */
  @Override
  public boolean set(String data) {
    this.data = data;
    return true;
  }

  /**
   * The function returns the content of the {@code Character} object.
   * Because the data is stored as {@code String}, the value returned is not a
   * representation of the {@code Data} object.
   *
   * @return the content of the object.
   */
  public String get() {
    return this.data;
  }

  /**
   * The function returns the data type of the object.
   *
   * @return {@code Data.CHARACTER}.
   */
  @Override
  public int type() {
    return Data.CHARACTER;
  }

  /**
   * The function tells if the content of the object is set to null value.
   *
   * @return true if null, else false.
   */
  @Override
  public boolean isEmpty() {
    return (data == NULL);
  }

  /**
   * The function sets the content of the {@code Character} object to null
   * value.
   */
  @Override
  public void empty() {
    this.data = NULL;
  }

  /**
   * The function returns a string representation of the object. Because the
   * {@code Character} data is internally stored as a {@code String} object,
   * it actually returns the content of the object as with get() function.
   *
   * @return a string representation of the object.
   */
  @Override
  public String toString() {
    return this.data;
  }

  /**
   * The function returns a deep copy of the {@code Character} object.
   *
   * @return a deep copy of the object.
   */
  @Override
  public Data clone() {
    return new Character(this.data);
  }

}
