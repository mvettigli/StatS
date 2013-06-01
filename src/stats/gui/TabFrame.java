/* ----------------------------------------------------------------------------
 * File: TabFrame.java
 * Date: June 1st, 2013
 * ----------------------------------------------------------------------------
 */
package stats.gui;

import javax.swing.Icon;
import javax.swing.JPanel;

/**
 * The {@code TabFrame} class is a wrapper for any tabbed frame to be displayed
 * in StatS application.
 * The StatS main application handles internal frames as a list of tabbed
 * panes. Each internal frame must be a subclass of TabFrame for correctly
 * handling frame status and behavior.
 *
 * @author M. Vettigli
 * @version 1.0
 */
public class TabFrame {
  
  private JPanel panel;
  
  private Icon icon;
    
  public TabFrame() {
    
  }
  
  public TabFrame(JPanel panel, Icon icon) {
    this.panel = panel;
    this.icon = icon;
  }
  
  public JPanel getPanel() {
    return panel;
  }
  
  public Icon getIcon() {
    return icon;
  }
  
  public void setIcon(Icon icon) {
    this.icon = icon;
  }
}
