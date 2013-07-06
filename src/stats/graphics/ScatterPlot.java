/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
