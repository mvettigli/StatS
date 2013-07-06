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
 * @version 2.0
 */
public class Numeric extends Data {

  /**
   * The null value for {@code Numeric} class, the minimum of {@code
   * double} type.
   */
  private static final double NULL = Double.MIN_VALUE;

  /**
   * Variable used to store the content of the {@code Numeric} object.
   */
  private double data;

  /**
   * Default constructor for {@code Numeric} class. The content is
   * initialized with null value.
   */
  public Numeric() {
    this.data = NULL;
  }

  /**
   * Constructor for {@code Numeric} class from value. The Numeric instance
   * is initialized with given {@code data} value.
   *
   * @param data the initial value of object.
   */
  public Numeric(double data) {
    this.data = data;
  }

  /**
   * Constructor for {@code Numeric} class from string. The Numeric instance
   * is initialized trying to parsing the content of the string. If the content
   * is not a valid double number, an empty {@code Numeric} object will be
   * created.
   *
   * @param data the initial value of object as string.
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

  /**
   * The function returns the content of {@code Numeric} object.
   *
   * @return the double content of the object.
   */
  public double get() {
    return this.data;
  }

  /**
   * The function sets the content of the {@code Numeric} object given
   * {@code data} argument.
   *
   * @param data the new value the object will contain.
   */
  public void set(double data) {
    this.data = data;
  }

  /**
   * The function sets the content of the {@code Numeric} object parsing
   * the string representation. If empty string is given, null value is used
   * for the assignment.
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
    // define double variable
    double double_data;
    try
    {
      // try parsing the string representation
      double_data = Double.parseDouble(data);
    } catch (NumberFormatException e)
    {
      // if unsuccessful, return false
      return false;
    }
    // assign the parsed data
    this.data = double_data;
    return true;
  }

  /**
   * The function checks if the {@code Numeric} content is null value.
   *
   * @return true if null, else false.
   */
  @Override
  public boolean isEmpty() {
    return (data == NULL);
  }

  /**
   * The function sets the {@code Numeric} content to null value.
   */
  @Override
  public void empty() {
    this.data = NULL;
  }

  /**
   * The function returns the data type of the {@code Numeric} object.
   *
   * @return {@code Data.NUMERIC}.
   */
  @Override
  public int type() {
    return Data.NUMERIC;
  }

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
   * The function returns a deep copy of the {@code NUmeric} object.
   *
   * @return a deep copy of the object.
   */
  @Override
  public Data clone() {
    return new Numeric(this.data);
  }

}
