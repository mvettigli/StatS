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
 * @version 3.0
 */
public class Character extends Data {

  //<editor-fold defaultstate="collapsed" desc="Static Members">
  /**
   * The null value for {@code Character} class, an empty string.
   */
  public static final String NULL = "";
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Private Members">
  /**
   * Stores the content of {@code Character} object.
   */
  private String data;
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Default constructor for {@code Character} class. The content is initialized
   * with null value.
   */
  public Character() {
    this(NULL);
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
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Getters">
  /**
   * Returns the content of the {@code Character} object. Because the data is
   * stored as {@code String}, the value returned is not a representation of the
   * {@code Data} object.
   *
   * @return the content of the object.
   */
  public String get() {
    return data;
  }

  /**
   * Returns the data type of the {@code Character} object.
   *
   * @return {@code DataTypes.CHARACTER}.
   */
  @Override
  public DataTypes type() {
    return DataTypes.CHARACTER;
  }

  /**
   * Checks if the content of the object is set to null value.
   *
   * @return true if null, else false.
   */
  @Override
  public boolean isEmpty() {
    return (data.equals(NULL));
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Setters">
  /**
   * Changes the content of the {@code Character} object with given
   * {@code String} argument.
   *
   * @param data new content of the object.
   * @return true if successful, else false.
   */
  @Override
  public boolean set(String data) {
    // handle null string argument
    if (data == null) data = new String();
    // assign new data value
    this.data = data;
    return true;
  }

  /**
   * Sets the content of the {@code Character} object to null value.
   */
  @Override
  public void empty() {
    data = NULL;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Returns a string representation of the object. Because the
   * {@code Character} data is internally stored as a {@code String} object,
   * it actually returns the content of the object.
   *
   * @return a string representation of the object.
   */
  @Override
  public String toString() {
    return data;
  }

  /**
   * Returns a deep copy of the {@code Character} object.
   *
   * @return a deep copy of the object.
   */
  @Override
  public Data clone() {
    return new Character(data);
  }
  //</editor-fold>

}
