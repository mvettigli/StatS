/* ----------------------------------------------------------------------------
 * File: Axis.java
 * Date: June 6th, 2013
 * ----------------------------------------------------------------------------
 */
package stats.graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 *
 * @author marco
 */
public class Axis extends Component {

  /**
   * Horizontal orientation for axis object.
   */
  public static final int HORIZONTAL_ORIENTATION = 0;

  /**
   * Vertical orientation for axis object.
   */
  public static final int VERTICAL_ORIENTATION = 1;

  /**
   * Default axis title for object initialization.
   */
  private static final String DEFAULT_TITLE = "Axis title";

  /**
   * Default orientation for object initialization.
   */
  private static final int DEFAULT_ORIENTATION = HORIZONTAL_ORIENTATION;

  /**
   * Default length for object initialization.
   */
  private static final int DEFAULT_LENGTH = 200;

  /**
   * Default minimum value for object initialization.
   */
  private static final int DEFAULT_MIN = 0;

  /**
   * Default maximum value for object initialization.
   */
  private static final int DEFAULT_MAX = 10;

  /**
   * Default increment for object initialization.
   */
  private static final int DEFAULT_INC = 1;

  /**
   * Default number of minor tick steps for object initialization.
   */
  private static final int DEFAULT_STEPS = 2;

  /**
   * Default tick length for object initialization.
   */
  private static final int DEFAULT_MAINTICKLENGTH = 3;

  /**
   * Default tick length for object initialization.
   */
  private static final int DEFAULT_MINORTICKLENGTH = 1;

  /**
   * Default show label status for object initialization.
   */
  private static final boolean DEFAULT_SHOWLABEL = true;

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
  private int axisOrientation;

  /**
   * Orientation of the axis, it can be horizontal or vertical.
   */
  private int labelOrientation;

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

  public Axis() {
    this(DEFAULT_TITLE, DEFAULT_ORIENTATION, DEFAULT_MIN,
            DEFAULT_MAX, DEFAULT_INC);

  }

  public Axis(String title, int axisOrientation) {
    this(title, axisOrientation, DEFAULT_MIN, DEFAULT_MAX, DEFAULT_INC);
  }

  public Axis(String title, int axisOrientation,
          double min, double max, double inc) {
    this.title = title;
    this.axisOrientation = axisOrientation;
    this.min = min;
    this.max = max;
    this.inc = inc;

    /* default initialization of private variables */
    if (axisOrientation == HORIZONTAL_ORIENTATION)
      labelOrientation = HORIZONTAL_ORIENTATION;
    else labelOrientation = VERTICAL_ORIENTATION;
    this.mainTickLength = DEFAULT_MAINTICKLENGTH;
    this.minorTickLength = DEFAULT_MINORTICKLENGTH;
    this.steps = DEFAULT_STEPS;
    this.length = DEFAULT_LENGTH;
    this.showLabels = DEFAULT_SHOWLABEL;
    this.labelFont = DEFAULT_LABEL_FONT;
    this.titleFont = DEFAULT_TITLE_FONT;
    resize();
  }

  /**
   * Returns the title of the axis. The title axis is displayed below axis.
   *
   * @return the title of the axis.
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Returns the orientation of the axis. It can either horizontal or vertical.
   *
   * @return the orientation of the axis.
   */
  public int getAxisOrientation() {
    return this.axisOrientation;
  }

  /**
   * Returns the orientation of the labels. It can either horizontal or
   * vertical.
   *
   * @return the orientation of the labels.
   */
  public int getLabelOrientation() {
    return this.labelOrientation;
  }

  /**
   * Returns the length of the axis in pixels.
   *
   * @return the length of the axis.
   */
  public int getLength() {
    return this.length;
  }

  /**
   * Returns the minimum value showed on the axis.
   *
   * @return the minimum value of the axis.
   */
  public double getMinimum() {
    return this.min;
  }

  /**
   * Returns the maximum value showed on the axis.
   *
   * @return the maximum value of the axis.
   */
  public double getMaximum() {
    return this.max;
  }

  /**
   * Return the increment for main ticks on the axis.
   *
   * @return the increment of main ticks.
   */
  public double getIncrement() {
    return this.inc;
  }

  /**
   * Returns the number of steps within two main ticks for minor tick drawing.
   *
   * @return number of steps within main ticks.
   */
  public int getSteps() {
    return this.steps;
  }

  /**
   * Returns the length of main ticks in pixels.
   *
   * @return the length of main ticks.
   */
  public int geMainTickLength() {
    return this.mainTickLength;
  }

  /**
   * Returns the length of minor ticks in pixels.
   *
   * @return the length of minor ticks.
   */
  public int geMinorTickLength() {
    return this.minorTickLength;
  }

  /**
   * Returns the font of axis title.
   *
   * @return the font of axis title.
   */
  public Font getTitleFont() {
    return this.titleFont;
  }

  /**
   * Returns the font of axis labels.
   *
   * @return the font of axis labels.
   */
  public Font getLabelFont() {
    return this.labelFont;
  }

  /**
   * Return the distance of zero point from upper-left corner.
   *
   * @return the zero offset from upper-left corner.
   */
  public int getZeroOffset() {
    return this.zeroOffset;
  }

  @Override
  public void setBounds(int x, int y, int width, int height) {
    // update axis lenght based on zero offset and oversize
    this.length = width - this.zeroOffset - calculateOverSize();
    height = calculateHeight();
    super.setBounds(x, y, width, height);
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);

    double range = max - min;
    double step = length / range;
    int offset = zeroOffset + (int) (Math.abs((min - (int) (min / inc) * inc) * step));
    double firstValue = (int) (min / inc) * inc;
    FontMetrics labelMetric = g.getFontMetrics(labelFont);
    FontMetrics titleMetric = g.getFontMetrics(titleFont);

    // draw background, for debug
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, this.getWidth(), this.getHeight());
    // draw the axis line
    g.setColor(Color.BLACK);
    g.drawLine(zeroOffset, 0, zeroOffset + length, 0);
    // draw main ticks
    for (int i = 0; i <= range / inc; i++)
      g.drawLine(offset + (int) (i * step * inc), 0,
              offset + (int) (i * step * inc), mainTickLength);
    // draw minor ticks
    double minorStep = step * inc / steps;
    for (int i = 0; i < Math.abs(range / inc); i++)
      for (int j = 1; j < steps; j++)
        g.drawLine(offset + (int) (i * step * inc + j * minorStep), 0,
                offset + (int) (i * step * inc + j * minorStep),
                minorTickLength);
    // draw main thicks labels
    if (showLabels)
    {
      g.setFont(labelFont);
      for (int i = 0; i <= Math.abs(range / inc); i++)
      {
        String label = Double.toString(firstValue + i * inc);
        int labelWidth = (int) (labelMetric.getStringBounds(label, g).getWidth());
        g.drawString(label, offset + (int) (i * step * inc) - labelWidth / 2,
                labelMetric.getHeight() + mainTickLength);
      }
    }

    /* draw axis title */
    double titleWidth = titleMetric.getStringBounds(title, g).getWidth();
    int xTitle = (int) (zeroOffset + (length - titleWidth) / 2);
    int yTitle = titleMetric.getHeight() + mainTickLength
            + ((showLabels) ? labelMetric.getHeight() : 0);
    g.setFont(titleFont);
    g.drawString(title, xTitle, yTitle);

  }

  private void resize() {

    /* update zero oversize and redefine component size */
    this.zeroOffset = calculateZeroOffset();
    int componentWidth = this.length + this.zeroOffset + calculateOverSize();
    int componentHeight = calculateHeight();
    setSize(componentWidth, componentHeight);

  }

  private int calculateOverSize() {

    double oversize = 0;
    // if labels are not shown, return null oversize
    if (!showLabels) return 0;
    // calculate oversize based on axis orientation
    switch (axisOrientation)
    {
      case HORIZONTAL_ORIENTATION:
        /* define variables for convenience */
        double range = max - min;
        double step = length / range;
        double lastValue = (int) (max / inc) * inc;
        FontMetrics labelMetric = getFontMetrics(labelFont);

        /* calculate oversize due to overflow of last label */
        String lastLabel = Double.toString(lastValue);
        double offset = Math.abs((lastValue - min) * step);
        double lastLabelLenght = labelMetric.getStringBounds(
                lastLabel, this.getGraphics()).getWidth();
        oversize = -(length - offset - lastLabelLenght / 2);
        break;
      case VERTICAL_ORIENTATION:
      default:
        break;
    }
    // return calculated oversize if positive
    if (oversize > 0)
      return (int) oversize;
    else return 0;

  }

  private int calculateZeroOffset() {

    double zero = 0;
    // if labels are not shown, return null offset
    if (!showLabels) return 0;
    switch (axisOrientation)
    {
      case HORIZONTAL_ORIENTATION:
        /* define variables for convenience */
        double range = max - min;
        double step = length / range;
        double firstValue = (int) (min / inc) * inc;
        FontMetrics labelMetric = getFontMetrics(labelFont);

        /* calculate offset due to overflow of first label */
        String firstLabel = Double.toString(firstValue);
        double offset = Math.abs((firstValue - min) * step);
        double firstLabelLenght = labelMetric.getStringBounds(
                firstLabel, this.getGraphics()).getWidth();
        zero = -(offset - firstLabelLenght / 2);
        break;
      case VERTICAL_ORIENTATION:
      default:
        break;
    }
    // return calculated offset if positive
    if (zero > 0)
      return (int) zero;
    else return 0;

  }

  private int calculateHeight() {

    /* define variables for convenience */
    FontMetrics labelMetric = getFontMetrics(labelFont);
    FontMetrics titleMetric = getFontMetrics(titleFont);

    /* calculate component height as sum of main ticks length, 
     * label heigth (if shown) and title heigth */
    return titleMetric.getHeight() + mainTickLength
            + ((showLabels) ? labelMetric.getHeight() : 0);

  }

}
