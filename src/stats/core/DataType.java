/* ----------------------------------------------------------------------------
 * File: DataType.java
 * Date: March 30th, 2013
 * ----------------------------------------------------------------------------
 */
package stats.core;

/**
 * The {@code DataType} enumeration classifies the different types of
 * {@code Data} subclasses. A special data type is UNDEFINED, used in
 * {@code Array} objects when the type is not defined yet.
 *
 * @author M. Vettigli
 * @version 1.0
 */
public enum DataType {

    /**
     * Data type returned by alphanumeric {@code Character} class.
     */
    CHARACTER,
    /**
     * Data type returned by numeric {@code Numeric} class.
     */
    NUMERIC,
    /**
     * Special data type returned by {@code Array}s when data type is not
     * defined yet.
     */
    UNDEFINED

}
