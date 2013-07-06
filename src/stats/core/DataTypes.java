/* ----------------------------------------------------------------------------
 * File: DataTypes.java
 * Date: July 6th, 2013
 * ----------------------------------------------------------------------------
 */
package stats.core;

import javax.swing.ImageIcon;

/**
 * The {@code DataTypes} enumeration defines currently implemented data types.
 * Each data type is implemented through a subclass of {@code Data}. The
 * {@code UNDEFINED} type is used when data type is not defined, such as in
 * empty {@code Array} objects. The enumeration given some basic help functions
 * related to data type, such as the related icon.
 *
 * @author M. Vettigli
 * @version 1.0
 */
public enum DataTypes {

  CHARACTER,
  NUMERIC,
  UNDEFINED;

  /**
   * Returns the icon associated to the data type, to be used in GUI
   * applications.
   *
   * @return icon for data type.
   */
  public ImageIcon getIcon() {
    
    switch (this)
    {
      case CHARACTER:
        return new ImageIcon(getClass().getResource(
                "/stats/gui/images/character.png"));
      case NUMERIC:
        return new ImageIcon(getClass().getResource(
                "/stats/gui/images/numeric.png"));
      case UNDEFINED:
      default:
        return null;
    }
    
  }

}
