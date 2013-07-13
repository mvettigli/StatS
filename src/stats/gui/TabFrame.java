/* ============================================================================
 *                   StatS - STatistical Analysis ToolS
 * ============================================================================
 *
 *   File: TabFrame.java
 *   Date: Jun 1, 2013
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
