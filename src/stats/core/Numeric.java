/* ----------------------------------------------------------------------------
 * File: Numeric.java
 * Date: March 30th, 2013
 * ----------------------------------------------------------------------------
 */
package stats.core;

/**
 * The {@code Numeric} class is used to store numeric data. The data can
 * be decimal and are stored with double precision. Like any other {@code Data}
 * subclass, the object can be set to null value.
 *
 * @author M. Vettigli
 * @version 3.0
 */
public class Numeric extends Data {

  //<editor-fold defaultstate="collapsed" desc="Static Members">
  /**
   * The null value for {@code Numeric} class, the minimum of {@code
   * double} type.
   */
  public static final double NULL = Double.MIN_VALUE;
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Private Members">
  /**
   * Stores the content of the {@code Numeric} object.
   */
  private double data;
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Default constructor for {@code Numeric} class. The content is
   * initialized with null value.
   */
  public Numeric() {
    this(NULL);
  }

  /**
   * Constructor for {@code Numeric} class from value. The {@code Numeric}
   * instance is initialized with given {@code double} value.
   *
   * @param data initial value of object.
   */
  public Numeric(double data) {
    this.data = data;
  }

  /**
   * Constructor for {@code Numeric} class from string. The {@code Numeric}
   * instance is initialized trying to parsing the content of the string. If the
   * content is not a valid double number, an empty {@code Numeric} object will
   * be created.
   *
   * @param data initial value of object as string.
   */
  public Numeric(String data) {
    try
    {
      double value = Double.parseDouble(data);
      this.data = value;
    } catch (NumberFormatException e)
    {
      this.data = NULL;
    }
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Getters">
  /**
   * Returns the content of {@code Numeric} object.
   *
   * @return {@code double} content of the object.
   */
  public double get() {
    return data;
  }

  /**
   * Returns the data type of the {@code Numeric} object.
   *
   * @return {@code DataTypes.NUMERIC}.
   */
  @Override
  public DataTypes type() {
    return DataTypes.NUMERIC;
  }

  /**
   * Checks if the {@code Numeric} content is null value.
   *
   * @return true if null, else false.
   */
  @Override
  public boolean isEmpty() {
    return (data == NULL);
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Setters">
  /**
   * Changes the content of the {@code Numeric} object given {@code double}
   * argument.
   *
   * @param data new value as {@code double}.
   */
  public void set(double data) {

    this.data = data;

  }

  /**
   * Changes the content of the {@code Numeric} object parsing the string
   * representation. If empty string is given, null value is used for the
   * assignment.
   *
   * @param data a string representation of numeric data.
   * @return true if assignment and parsing was successful, else false.
   */
  @Override
  public boolean set(String data) {
    // check if the data is empty and assign null value
    if (data.isEmpty())
    {
      this.data = NULL;
      return true;
    }

    // try parsing the string representation
    double parsedData;
    try
    {
      parsedData = Double.parseDouble(data);
    } catch (NumberFormatException e)
    {
      // if unsuccessful, return false
      return false;
    }

    // assign the parsed data
    this.data = parsedData;
    return true;
  }

  /**
   * Sets the {@code Numeric} content to null value.
   */
  @Override
  public void empty() {
    data = NULL;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Returns a string representation of the {@code Numeric} object.
   *
   * @return a string representation of the content.
   */
  @Override
  public String toString() {
    if (data == NULL) return new String();
    else return String.valueOf(data);
  }

  /**
   * Returns a deep copy of the {@code NUmeric} object.
   *
   * @return a deep copy of the object.
   */
  @Override
  public Data clone() {
    return new Numeric(data);
  }
  //</editor-fold>

}
