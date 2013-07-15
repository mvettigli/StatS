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

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import stats.core.Array;
import stats.utils.Statistics;

/**
 *
 * @author marco
 */
public class ScatterPlot extends Plot {

  /**
   * Default axis label font for object initialization.
   */
  private static final Font DEFAULT_TITLE_FONT =
          new Font("SansSerif", Font.BOLD, 14);

  private Axis xAxis;

  private Axis yAxis;

  private Array xArray;

  private Array yArray;

  private String title;

  private Font titleFont;

  public ScatterPlot(Array x, Array y) {

    if (x == null || y == null) throw new IllegalArgumentException(
              "Cannot assign null arrays as X or Y variables.");
    if (x.size() != y.size()) throw new IllegalArgumentException(
              "X and Y arrays must have same size.");
    xArray = x;
    yArray = y;
    this.title = "Scatter Plot";
    this.titleFont = DEFAULT_TITLE_FONT;

    xAxis = new Axis(x.name(), Axis.Orientations.HORIZONTAL,
            Statistics.getMinimum(xArray),
            Statistics.getMaximum(xArray),
            Statistics.getRange(xArray) / 10);
    yAxis = new Axis(y.name(), Axis.Orientations.VERTICAL,
            Statistics.getMinimum(yArray),
            Statistics.getMaximum(yArray),
            Statistics.getRange(yArray) / 10);
  }

  @Override
  protected void paintComponent(Graphics g) {
    // convert Graphics to Graphics2D
    Graphics2D g2d = (Graphics2D) g;

    /* draw background, for debug */
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, this.getWidth(), this.getHeight());


    // draw plot title    
    g2d.setColor(Color.BLACK);
    Rectangle2D titleBounds = getTitleBounds();
    int x = (int) ((this.getWidth() - titleBounds.getWidth()) / 2);
    g2d.setFont(titleFont);
    g2d.drawString(title, x, (int) titleBounds.getHeight());

    g2d.translate(0, titleBounds.getHeight());
    yAxis.paint(g2d);
    g2d.translate(yAxis.getWidth() - xAxis.getZeroOffset(),
            yAxis.getZeroOffset());
    xAxis.paint(g2d);
  }

  @Override
  public void setBounds(int x, int y, int width, int height) {
    super.setBounds(x, y, width, height);

    xAxis.setBounds(0, 0, width - yAxis.getWidth(), height);
    yAxis.setBounds(0, 0, width,
            height - xAxis.getHeight() - (int) getTitleBounds().getHeight());

  }

  private Rectangle2D getTitleBounds() {
    FontMetrics titleMetrics = getFontMetrics(titleFont);
    return titleMetrics.getStringBounds(title, this.getGraphics());
  }

}
