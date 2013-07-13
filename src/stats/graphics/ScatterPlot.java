/* ============================================================================
 *                   StatS - STatistical Analysis ToolS
 * ============================================================================
 *
 *   File: ScatterPlot.java
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

package stats.graphics;

import java.awt.Component;
import java.awt.Graphics;
import stats.core.Array;
import stats.utils.Statistics;

/**
 *
 * @author marco
 */
public class ScatterPlot extends Component {

  private Axis xAxis;

  private Axis yAxis;

  private Array xArray;

  private Array yArray;

  public ScatterPlot(Array x, Array y) {

    if (x == null || y == null) throw new IllegalArgumentException(
              "Cannot assign null arrays as X or Y variables.");
    if (x.size() != y.size()) throw new IllegalArgumentException(
              "X and Y arrays must have same size.");
    xArray = x;
    yArray = y;

    xAxis = new Axis(x.name(), Axis.Orientations.HORIZONTAL,
            Statistics.getMinimum(xArray),
            Statistics.getMaximum(xArray),
            Statistics.getRange(xArray) / 10);
    xAxis = new Axis(y.name(), Axis.Orientations.VERTICAL,
            Statistics.getMinimum(yArray),
            Statistics.getMaximum(yArray),
            Statistics.getRange(yArray) / 10);
    resize();
  }

  @Override
  public void paint(Graphics g) {
    xAxis.paint(g);
    yAxis.paint(g);
  }

  private void resize() {
    this.setSize(xAxis.getWidth(), yAxis.getHeight());
  }

}
