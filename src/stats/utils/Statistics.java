/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stats.utils;

import stats.core.Array;
import stats.core.Data;
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
