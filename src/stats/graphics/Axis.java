/* ============================================================================
 *                   StatS - STatistical Analysis ToolS
 * ============================================================================
 *
 *   File: Axis.java
 *   Date: Jun 6, 2013
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
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;

/**
 *
 * @author marco
 */
public class Axis extends Component {

  /**
   * Defines allow orientation for axis and labels, currently horizontal
   * and vertical.
   */
  public enum Orientations {

    HORIZONTAL,
    VERTICAL

  };

  /**
   * Defines format type for axis labels.
   */
  public enum Formats {

    BEST,
    FIXED

  };

  /**
   * Default axis title for object initialization.
   */
  private static final String DEFAULT_TITLE = "AXIS_TITLE";

  /**
   * Default orientation for object initialization.
   */
  private static final Orientations DEFAULT_ORIENTATION =
          Orientations.HORIZONTAL;

  /**
   * Default length for object initialization.
   */
  private static final int DEFAULT_LENGTH = 200;

  /**
   * Default minimum value for object initialization.
   */
  private static final double DEFAULT_MIN = 0;

  /**
   * Default maximum value for object initialization.
   */
  private static final double DEFAULT_MAX = 10;

  /**
   * Default increment for object initialization.
   */
  private static final double DEFAULT_INC = 1;

  /**
   * Default number of minor tick steps for object initialization.
   */
  private static final int DEFAULT_STEPS = 2;

  /**
   * Default tick length for object initialization.
   */
  private static final int DEFAULT_MAJOR_TICK_LENGTH = 5;

  /**
   * Default tick length for object initialization.
   */
  private static final int DEFAULT_MINOR_TICK_LENGTH = 2;

  /**
   * Default show label status for object initialization.
   */
  private static final boolean DEFAULT_SHOWLABEL = true;

  /**
   * Default label format for object initialization.
   */
  private static final Formats DEFAULT_FORMAT = Formats.BEST;

  /**
   * Default axis label font for object initialization.
   */
  private static final Font DEFAULT_TITLE_FONT =
          new Font("SansSerif", Font.BOLD, 12);

  /**
   * Default axis title font for object initialization.
   */
  private static final Font DEFAULT_LABEL_FONT =
          new Font("SansSerif", Font.PLAIN, 10);

  /**
   * Title of the axis.
   */
  private String title;

  /**
   * Orientation of the axis, it can be horizontal or vertical.
   */
  private Orientations axisOrientation;

  /**
   * Orientation of the axis, it can be horizontal or vertical.
   */
  private Orientations labelOrientation;

  /**
   * Length of the axis in pixels.
   */
  private int length;

  /**
   * Minimum value of the axis.
   */
  private double min;

  /**
   * Maximum value of the axis.
   */
  private double max;

  /**
   * Main tick increment.
   */
  private double inc;

  /**
   * Number of minor tick steps.
   */
  private int steps;

  /**
   * Length of main ticks in pixels.
   */
  private int mainTickLength;

  /**
   * Length of minor ticks in pixels.
   */
  private int minorTickLength;

  /**
   * Font of axis title.
   */
  private Font titleFont;

  /**
   * Font of axis labels.
   */
  private Font labelFont;

  /**
   * Show label status. If true labels are painted.
   */
  private boolean showLabels;

  /**
   * Distance of zero axis from upper-left corner of the component.
   */
  private int zeroOffset;

  private int overSize;

  private int labelOffset;

  private boolean reversed;

  private Formats format;

  private int digits;

  private int decimals;

  public Axis() {
    this(DEFAULT_TITLE, DEFAULT_ORIENTATION,
            DEFAULT_MIN, DEFAULT_MAX, DEFAULT_INC);
  }

  public Axis(String title, Orientations axisOrientation) {
    this(title, axisOrientation, DEFAULT_MIN, DEFAULT_MAX, DEFAULT_INC);
  }

  public Axis(String title, Orientations axisOrientation,
          double min, double max, double inc) {

    this.title = title;
    this.axisOrientation = axisOrientation;
    this.min = min;
    this.max = max;
    this.inc = inc;


    /* default initialization of private variables */

    switch (axisOrientation)
    {
      case HORIZONTAL:
        this.labelOrientation = Orientations.HORIZONTAL;
        this.reversed = false;
        break;
      case VERTICAL:
        this.labelOrientation = Orientations.VERTICAL;
        this.reversed = true;
        break;
    }

    this.steps = DEFAULT_STEPS;
    this.length = DEFAULT_LENGTH;
    this.format = DEFAULT_FORMAT;
    this.digits = 10;
    this.decimals = 2;

    this.showLabels = DEFAULT_SHOWLABEL;
    this.labelFont = DEFAULT_LABEL_FONT;
    this.titleFont = DEFAULT_TITLE_FONT;
    this.mainTickLength = DEFAULT_MAJOR_TICK_LENGTH;
    this.minorTickLength = DEFAULT_MINOR_TICK_LENGTH;

    resize();
  }

  /**
   * Returns the title of the axis. The title axis is displayed below axis.
   *
   * @return the title of the axis.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Returns the orientation of the axis. It can either horizontal or vertical.
   *
   * @return the orientation of the axis.
   */
  public Orientations getAxisOrientation() {
    return axisOrientation;
  }

  /**
   * Returns the orientation of the labels. It can either horizontal or
   * vertical.
   *
   * @return the orientation of the labels.
   */
  public Orientations getLabelOrientation() {
    return this.labelOrientation;
  }

  /**
   * Returns the length of the axis in pixels.
   *
   * @return the length of the axis.
   */
  public int getLength() {
    return length;
  }

  /**
   * Returns the minimum value showed on the axis.
   *
   * @return the minimum value of the axis.
   */
  public double getMinimum() {
    return min;
  }

  /**
   * Returns the maximum value showed on the axis.
   *
   * @return the maximum value of the axis.
   */
  public double getMaximum() {
    return max;
  }

  /**
   * Return the increment for main ticks on the axis.
   *
   * @return the increment of main ticks.
   */
  public double getIncrement() {
    return inc;
  }

  /**
   * Returns the number of steps within two main ticks for minor tick drawing.
   *
   * @return number of steps within main ticks.
   */
  public int getSteps() {
    return steps;
  }

  /**
   * Returns the length of main ticks in pixels.
   *
   * @return the length of main ticks.
   */
  public int geMainTickLength() {
    return mainTickLength;
  }

  /**
   * Returns the length of minor ticks in pixels.
   *
   * @return the length of minor ticks.
   */
  public int geMinorTickLength() {
    return minorTickLength;
  }

  /**
   * Returns the font of axis title.
   *
   * @return the font of axis title.
   */
  public Font getTitleFont() {
    return titleFont;
  }

  /**
   * Returns the font of axis labels.
   *
   * @return the font of axis labels.
   */
  public Font getLabelFont() {
    return labelFont;
  }

  public boolean isReversed() {
    return reversed;
  }

  /**
   * Return the distance of zero point from upper-left corner.
   *
   * @return the zero offset from upper-left corner.
   */
  public int getZeroOffset() {
    return this.zeroOffset;
  }

  public void setTitle(String title) {
    if (this.title == title) return;
    this.title = title;
    repaint();
  }

  public void setAxisOrientation(Orientations orientation) {
    // check if orientation is already set
    if (axisOrientation == orientation) return;
    // set axis, default label orientation and reverse status
    axisOrientation = orientation;
    switch (axisOrientation)
    {
      case HORIZONTAL:
        labelOrientation = Orientations.HORIZONTAL;
        reversed = false;
        break;
      case VERTICAL:
        labelOrientation = Orientations.VERTICAL;
        reversed = true;
        break;
    }
    // resize and repaint the component
    resize();
    repaint();
  }

  public void setLabelOrientation(Orientations orientation) {
    // check if orientation is already set
    if (labelOrientation == orientation) return;
    // set new label orientation
    labelOrientation = orientation;
    // resize and repaint the component
    resize();
    repaint();
  }

  public void setMinimum(double min) {
    // check if minimum value is already set
    if (this.min == min) return;
    // check if new minimum value is greater than maximum and swap them
    if (min > this.max)
    {
      this.min = this.max;
      this.max = min;
    } else
    {
      this.min = min;
    }
    // resize and repaint component    
    resize();
    repaint();
  }

  public void setMaximum(double max) {
    // check if maximum value is already set
    if (this.max == max) return;
    // check if new maximum value is less than minimum and swap them
    if (max < this.min)
    {
      this.max = this.min;
      this.min = max;
    } else
    {
      this.max = max;
    }

    // resize and repaint component    
    resize();
    repaint();
  }

  public void setIncrement(double inc) {
    // check if increment is already set
    if (this.inc == inc) return;
    if (inc <= 0) return;
    this.inc = inc;
    // resize and repaint component    
    resize();
    repaint();
  }

  public void setSteps(int steps) {
    // check if step number is already set
    if (this.steps == steps) return;
    // step number can't be less than one
    // when set to one, minor ticks are not shown
    if (steps < 1) return;
    // set step number and repaint component
    this.steps = steps;
    repaint();
  }

  public void setMainTickLenght(int lenght) {
    // check if main tick lenght is already set
    if (mainTickLength == lenght) return;
    // main tick lenght must be positive or zero
    if (lenght < 0) return;
    // set main tick length, resize and repaint the component
    mainTickLength = lenght;
    resize();
    repaint();
  }

  public void setMinorTickLenght(int lenght) {
    // check if minor tick lenght is already set
    if (minorTickLength == lenght) return;
    // minor tick lenght must be positive or zero
    if (lenght < 0) return;
    // set minor tick length, resize and repaint the component
    minorTickLength = lenght;
    resize();
    repaint();
  }

  public void setTitleFont(Font font) {
    this.titleFont = font;
    resize();
    repaint();
  }

  public void setLabelFont(Font font) {
    this.labelFont = font;
    resize();
    repaint();
  }

  public void setReversed(boolean reversed) {
    if (this.reversed == reversed) return;
    this.reversed = reversed;
    repaint();
  }

  @Override
  public void paint(Graphics g) {

    // cast to Graphics2D objct to allow rotation
    Graphics2D g2D = (Graphics2D) g;

    /* define variables for convenience */
    double range = max - min;
    double scale = length / range;
    double step1 = scale * inc;
    double step2 = scale * (inc / steps);
    // define major ticks variable for convenience
    double first1 = Math.round(min / inc) * inc;
    if (first1 < min) first1 += inc;
    int offset1 = zeroOffset + (int) (Math.abs(min - first1) * scale);
    int number1 = (int) Math.abs(range / inc);
    // define minor ticks variable for convenience
    double first2 = Math.round(min / inc * steps) * inc / steps;
    if (first2 < min) first2 += (inc / steps);
    int offset2 = zeroOffset + (int) (Math.abs(min - first2) * scale);
    int number2 = (int) Math.abs(range / inc * steps);
    // define number of minor ticks in the first major offset
    int number3 = (int) Math.round(Math.abs(first1 - min) / (inc / steps));
    // define font metrics
    FontMetrics labelMetric = g2D.getFontMetrics(labelFont);
    FontMetrics titleMetric = g2D.getFontMetrics(titleFont);

    System.out.println(first1);
    System.out.println(first2);
    System.out.println(number3);
    System.out.println(inc / steps);

    /* draw background, for debug */
    g2D.setColor(Color.WHITE);
    g2D.fillRect(0, 0, this.getWidth(), this.getHeight());

    // rotate the Graphics object when orientation is vertical
    switch (axisOrientation)
    {
      case VERTICAL:
        g2D.rotate(Math.toRadians(90));
        g2D.translate(0, -this.getWidth() + 1);
        break;
      case HORIZONTAL:
        break;
    }

    /* draw axis line */
    g2D.setColor(Color.BLACK);
    g2D.drawLine((reversed) ? overSize : zeroOffset, 0,
            ((reversed) ? overSize : zeroOffset) + length, 0);

    /* draw major ticks */
    for (int i = 0; i <= number1; i++)
    {
      int x = offset1 + (int) Math.round(i * step1);
      if (reversed) x = length + overSize + zeroOffset - x;
      g2D.drawLine(x, 0, x, mainTickLength);
    }

    /* draw minor ticks */
    for (int i = 0; i <= number2; i++)
    {
      int x = offset2 + (int) Math.round(i * step2);
      if (reversed) x = length + overSize + zeroOffset - x;
      if ((i - number3) % steps != 0)
        g2D.drawLine(x, 0, x, minorTickLength);
    }

    /* draw main thicks labels */
    if (showLabels)
    {
      g2D.setFont(labelFont);

      // rotate Graphics object when label orientation is vertical
      switch (labelOrientation)
      {
        case VERTICAL:
          g2D.rotate(Math.toRadians(-90));
          break;
        case HORIZONTAL:
          break;
      }

      for (int i = 0; i <= number1; i++)
      {
        String label = format(first1 + i * inc);
        int width = (int) labelMetric.getStringBounds(label, g2D).getWidth();
        int height = (int) labelMetric.getStringBounds(label, g2D).getHeight();
        int x = 0;
        int y = 0;

        switch (labelOrientation)
        {
          case VERTICAL:
            x = -(width + mainTickLength + 2);
            y = offset1 + (int) (i * step1) + height / 2;
            if (reversed) y = length + overSize + zeroOffset - y + height;
            break;
          case HORIZONTAL:
            x = offset1 + (int) (i * step1) - width / 2;
            y = height + mainTickLength + 2;
            if (reversed) x = length + overSize + zeroOffset + -x - width;
            break;
        }

        // draw the label
        g2D.drawString(label, x, y);

      }
      // rotate back Graphics object when label orientation is vertical
      switch (labelOrientation)
      {
        case VERTICAL:
          g2D.rotate(Math.toRadians(90));
          break;
        case HORIZONTAL:
          break;
      }
    }

    /* draw axis title */
    double width = titleMetric.getStringBounds(title, g2D).getWidth();
    int x = (int) (zeroOffset + (length - width) / 2);
    int y = titleMetric.getHeight() + mainTickLength + 2
            + ((showLabels) ? labelOffset : 0);
    g2D.setFont(titleFont);
    g2D.drawString(title, x, y);

  }

  private void resize() {

    /* update zero oversize and redefine component size */
    this.zeroOffset = calculateZeroOffset();
    this.overSize = calculateOverSize();
    this.labelOffset = calculateLabelOffset();

    int componentWidth = calculateWidth();
    int componentHeight = calculateHeight();

    switch (axisOrientation)
    {
      case VERTICAL:
        int temp = componentWidth;
        componentHeight = componentWidth;
        componentWidth = temp;
        break;
      case HORIZONTAL:
      default:
    }

    setSize(componentWidth, componentHeight);
  }

  private int calculateOverSize() {

    double oversize = 0;
    // if labels are not shown, return null oversize
    if (!showLabels) return 0;
    // calculate oversize based on axis orientation

    /* define variables for convenience */
    double range = max - min;
    double step = length / range;
    double lastValue = Math.round(max / inc) * inc;
    if (lastValue > max) lastValue -= inc;
    FontMetrics labelMetric = getFontMetrics(labelFont);

    /* calculate oversize due to overflow of last label */
    String lastLabel = format(lastValue);
    double offset = Math.abs((lastValue - min) * step);
    double lastLabelLenght = 0;

    switch (labelOrientation)
    {
      case HORIZONTAL:
        lastLabelLenght = labelMetric.getStringBounds(
                lastLabel, this.getGraphics()).getWidth();
        break;
      case VERTICAL:
        lastLabelLenght = labelMetric.getStringBounds(
                lastLabel, this.getGraphics()).getHeight();
        break;

      default:
        break;
    }
    oversize = -(length - offset - lastLabelLenght / 2);
    // return calculated oversize if positive
    if (oversize > 0)
      return (int) Math.round(oversize);
    else return 0;

  }

  private int calculateZeroOffset() {

    // if labels are not shown, return null offset
    if (!showLabels) return 0;

    double range = max - min;
    double step = length / range;
    double firstValue = Math.round(min / inc) * inc;
    if (firstValue < min) firstValue += inc;

    FontMetrics labelMetric = getFontMetrics(labelFont);

    /* calculate offset due to overflow of first label */
    String firstLabel = format(firstValue);
    double offset = Math.abs((firstValue - min) * step);
    double firstLabelLenght = 0;

    switch (labelOrientation)
    {
      case HORIZONTAL:
        /* define variables for convenience */
        firstLabelLenght = labelMetric.getStringBounds(firstLabel,
                this.getGraphics()).getWidth();
        break;
      case VERTICAL:
        firstLabelLenght = labelMetric.getStringBounds(firstLabel,
                this.getGraphics()).getHeight();
        break;
      default:
        break;
    }
    // return calculated offset if positive
    double zero = -(offset - firstLabelLenght / 2);
    if (zero > 0) return (int) Math.round(zero);
    else return 0;

  }

  private int calculateWidth() {
    return length + zeroOffset + overSize + 1;
  }

  private int calculateHeight() {

    /* define variables for convenience */
    FontMetrics labelMetric = getFontMetrics(labelFont);
    FontMetrics titleMetric = getFontMetrics(titleFont);
    int heigth = 0;
    switch (labelOrientation)
    {
      case HORIZONTAL:
        heigth = titleMetric.getHeight() + titleMetric.getMaxDescent() + mainTickLength + 2
                + ((showLabels) ? labelMetric.getHeight() : 0);
        break;
      case VERTICAL:
        heigth = titleMetric.getHeight() + titleMetric.getMaxDescent() + mainTickLength + 2
                + ((showLabels) ? labelOffset : 0);
        break;

      default:
    }
    /* calculate component height as sum of main ticks length, 
     * label heigth (if shown) and title heigth */
    return heigth + 1;

  }

  private int calculateLabelOffset() {

    int offset = 0;
    double range = max - min;
    double firstValue = (int) (min / inc) * inc;
    FontMetrics labelMetric = getFontMetrics(labelFont);
    switch (labelOrientation)
    {
      case HORIZONTAL:
        offset = labelMetric.getHeight();
        break;
      case VERTICAL:
        for (int i = 0; i <= Math.abs(range / inc); i++)
        {
          String label = format(firstValue + i * inc);
          Rectangle2D bound = labelMetric.getStringBounds(label, this.getGraphics());
          if (bound.getWidth() > offset)
            offset = (int) bound.getWidth();
        }
        break;
      default:
    }
    return offset;
  }

  private int calculateLenght(int width) {
    return width - zeroOffset - overSize - 1;
  }

  @Override
  public void setBounds(int x, int y, int width, int height) {
    // update axis lenght based on zero offset and oversize    
    switch (axisOrientation)
    {
      case HORIZONTAL:
        length = calculateLenght(width);
        height = calculateHeight();
        break;
      case VERTICAL:
        length = calculateLenght(height);
        width = calculateHeight();
        break;
    }
    super.setBounds(x, y, width, height);
  }

  private String format(double value) {

    StringBuilder sb = new StringBuilder();
    /*   
     double digits = formatLenght;
     if (digits == 0 && value != 0) 
     digits = (int) Math.ceil(Math.log10(value < 0 ? -value : value));    
    
     if (digits < 0)
     {
     sb.append("0.");
     for (int i = 0; i < -digits; i++)
     sb.append("0");
     }
     if (digits > 0) sb.append("#");
     */
    sb.append("##0.000");
    DecimalFormat df = new DecimalFormat(sb.toString());
    return df.format(value);
  }

}
