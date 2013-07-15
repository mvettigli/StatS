/* ============================================================================
 *                   StatS - STatistical Analysis ToolS
 * ============================================================================
 *
 *   File: Statistics.java
 *   Date: Jul 2, 2013
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
package stats.utils;

import stats.core.Array;
import stats.core.DataTypes;
import stats.core.Numeric;

/**
 *
 * @author marco
 */
public class Statistics {
  
  public static double getMinimum(Array array) {
    if (array.type() != DataTypes.NUMERIC) throw new IllegalArgumentException(
              "Cannot compute minimum value of CHARACTER array.");
    if (array.size() == 0) return Numeric.NULL;
    double minimum = Double.MAX_VALUE;
    for (int i = 0; i < array.size(); i++)
    {
      Numeric element = (Numeric) array.get(i);
      if (element.get() < minimum) minimum = element.get();
    }
    return minimum;
  }
  
  public static double getMaximum(Array array) {
    if (array.type() != DataTypes.NUMERIC) throw new IllegalArgumentException(
              "Cannot compute minimum value of CHARACTER array.");
    if (array.size() == 0) return Numeric.NULL;
    double maximum = -Double.MAX_VALUE;
    for (int i = 0; i < array.size(); i++)
    {
      Numeric element = (Numeric) array.get(i);
      if (element.get() > maximum) maximum = element.get();
    }
    return maximum;
  }
  
  public static double getRange(Array array) {
    return getMaximum(array) - getMinimum(array);
  }
  
}
