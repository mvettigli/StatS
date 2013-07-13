/* ============================================================================
 *                   StatS - STatistical Analysis ToolS
 * ============================================================================
 *
 *   File: PlotHandler.java
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
package stats.gui;

import java.util.ArrayList;
import javax.swing.JPanel;
import stats.graphics.ScatterPlot;

/**
 *
 * @author marco
 */
public class PlotHandler extends JPanel {

  /**
   * Stores the generated plots
   */
  ArrayList<ScatterPlot> plots;

  /**
   * Creates new form PlotHandler
   */
  public PlotHandler() {

    /* initialize objects members */
    plots = new ArrayList<>();

    initComponents();
  }

  /**
   * This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    toolBar = new javax.swing.JToolBar();
    buttonSettings = new javax.swing.JButton();
    mainScrollPane = new javax.swing.JScrollPane();

    toolBar.setFloatable(false);
    toolBar.setRollover(true);

    buttonSettings.setText("Settings");
    buttonSettings.setFocusable(false);
    buttonSettings.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    buttonSettings.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    toolBar.add(buttonSettings);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
      .addComponent(mainScrollPane)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(mainScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonSettings;
  private javax.swing.JScrollPane mainScrollPane;
  private javax.swing.JToolBar toolBar;
  // End of variables declaration//GEN-END:variables
}
