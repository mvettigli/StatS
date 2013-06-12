/* ----------------------------------------------------------------------------
 * File: MainFrame.java
 * Date: March 31th, 2013
 * ----------------------------------------------------------------------------
 */
package stats.gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import stats.utils.FileUtils;
import stats.core.Table;
import stats.utils.CSVParser;

/**
 *
 * @author marco
 */
public class MainFrame extends javax.swing.JFrame {

  /**
   * Creates new form MainFrame
   */
  public MainFrame() {
    initComponents();

    frames = new ArrayList<>();

    setIconImage(new ImageIcon(getClass().getResource(
            "/stats/gui/images/main.png")).getImage());
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    toolPane = new javax.swing.JToolBar();
    splitPane = new javax.swing.JSplitPane();
    lateralPane = new javax.swing.JPanel();
    tabbedPane = new javax.swing.JTabbedPane();
    statusBar = new javax.swing.JPanel();
    menuBar = new javax.swing.JMenuBar();
    jMenu1 = new javax.swing.JMenu();
    menu_new = new javax.swing.JMenu();
    menu_newTable = new javax.swing.JMenuItem();
    menu_debug = new javax.swing.JMenuItem();
    menu_open = new javax.swing.JMenuItem();
    jMenu2 = new javax.swing.JMenu();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("StatS - STatistical Analysis ToolS");
    setFocusable(false);

    toolPane.setFloatable(false);
    toolPane.setRollover(true);

    splitPane.setDividerLocation(150);
    splitPane.setDividerSize(2);

    javax.swing.GroupLayout lateralPaneLayout = new javax.swing.GroupLayout(lateralPane);
    lateralPane.setLayout(lateralPaneLayout);
    lateralPaneLayout.setHorizontalGroup(
      lateralPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 149, Short.MAX_VALUE)
    );
    lateralPaneLayout.setVerticalGroup(
      lateralPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 530, Short.MAX_VALUE)
    );

    splitPane.setLeftComponent(lateralPane);
    splitPane.setRightComponent(tabbedPane);

    javax.swing.GroupLayout statusBarLayout = new javax.swing.GroupLayout(statusBar);
    statusBar.setLayout(statusBarLayout);
    statusBarLayout.setHorizontalGroup(
      statusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 0, Short.MAX_VALUE)
    );
    statusBarLayout.setVerticalGroup(
      statusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 22, Short.MAX_VALUE)
    );

    jMenu1.setText("File");

    menu_new.setText("New");

    menu_newTable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stats/gui/images/table.png"))); // NOI18N
    menu_newTable.setText("Table");
    menu_newTable.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menu_newTableActionPerformed(evt);
      }
    });
    menu_new.add(menu_newTable);

    menu_debug.setText("debug");
    menu_debug.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menu_debugActionPerformed(evt);
      }
    });
    menu_new.add(menu_debug);

    jMenu1.add(menu_new);

    menu_open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stats/gui/images/folder_table.png"))); // NOI18N
    menu_open.setText("Open...");
    menu_open.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menu_openActionPerformed(evt);
      }
    });
    jMenu1.add(menu_open);

    menuBar.add(jMenu1);

    jMenu2.setText("Edit");
    menuBar.add(jMenu2);

    setJMenuBar(menuBar);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(statusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(toolPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(toolPane, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(splitPane)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void menu_newTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_newTableActionPerformed
    // TODO add your handling code here:    
    Table table = new Table();
    TableHandler tablehandler = new TableHandler(table, true);
    TabFrame tabframe = new TabFrame(tablehandler, new ImageIcon(
            getClass().getResource("/stats/gui/images/table.png")));
    frames.add(tabframe);
    tabbedPane.addTab(table.name(), tabframe.getIcon(), tabframe.getPanel());
  }//GEN-LAST:event_menu_newTableActionPerformed

  private void menu_openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_openActionPerformed

    // TODO: customize accessory object to get separator and quote chars
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.addChoosableFileFilter(new FileFilter() {
      /**
       * Defines which file extension is valid.
       * Up to v1.0, only CSV and TXT files are allowed.
       */
      @Override
      public boolean accept(File file) {
        // this statement allow navigating directories
        if (file.isDirectory()) return true;
        // get the extension of the file
        String extension = FileUtils.getExtension(file);
        // check if the extension is CSV or TXT
        if (extension != null)
        {
          if (extension.equals(FileUtils.CSV)
                  || extension.equals(FileUtils.TXT))
            return true;
          else return false;
        }
        return false;
      }

      /**
       * Returns the filter description used in the dialog.
       */
      @Override
      public String getDescription() {
        return "Comma separated value files (.csv, .txt)";
      }
    });
    if (fileChooser.showOpenDialog(this) == JFileChooser.CANCEL_OPTION) return;
    // create a CSV parser and set its parameters
    File selectedFile = fileChooser.getSelectedFile();
    CSVParser parser = new CSVParser();
    parser.setFile(selectedFile);
    try
    {
      // parse the file and get the resulting table
      Table table = parser.parseFile();
      table.setName(FileUtils.getFileName(selectedFile));
      // create new TabFrame
      TableHandler tablehandler = new TableHandler(table, true);
      TabFrame tabframe = new TabFrame(tablehandler, new ImageIcon(
              getClass().getResource("/stats/gui/images/table.png")));
      frames.add(tabframe);
      tabbedPane.addTab(table.name(), tabframe.getIcon(), tabframe.getPanel());
    } catch (IOException e)
    {
    }
  }//GEN-LAST:event_menu_openActionPerformed

  private void menu_debugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_debugActionPerformed
    // create new TabFrame

    PlotHandler plotHandler = new PlotHandler();
    TabFrame tabframe = new TabFrame(plotHandler, null);
    frames.add(tabframe);
    tabbedPane.addTab("debug", tabframe.getIcon(), tabframe.getPanel());
  }//GEN-LAST:event_menu_debugActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try
    {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
      {
        if ("GTK+".equals(info.getName()))
        {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex)
    {
      java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex)
    {
      java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex)
    {
      java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex)
    {
      java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new MainFrame().setVisible(true);
      }
    });
  }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenu jMenu1;
  private javax.swing.JMenu jMenu2;
  private javax.swing.JPanel lateralPane;
  private javax.swing.JMenuBar menuBar;
  private javax.swing.JMenuItem menu_debug;
  private javax.swing.JMenu menu_new;
  private javax.swing.JMenuItem menu_newTable;
  private javax.swing.JMenuItem menu_open;
  private javax.swing.JSplitPane splitPane;
  private javax.swing.JPanel statusBar;
  private javax.swing.JTabbedPane tabbedPane;
  private javax.swing.JToolBar toolPane;
  // End of variables declaration//GEN-END:variables

  private ArrayList<TabFrame> frames;

}
