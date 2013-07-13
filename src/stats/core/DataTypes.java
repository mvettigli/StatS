/* ============================================================================
 *                   StatS - STatistical Analysis ToolS
 * ============================================================================
 *
 *   File: DataTypes.java
 *   Date: Jul 6, 2013
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
