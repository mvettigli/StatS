/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stats.gui;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import static stats.core.DataType.CHARACTER;
import static stats.core.DataType.NUMERIC;

/**
 *
 * @author marco
 */
public class ColumnListRenderer extends DefaultListCellRenderer {

@Override
public Component getListCellRendererComponent(JList<?> list, Object value,
        int index, boolean isSelected, boolean cellHasFocus) {

    JLabel label = (JLabel) super.getListCellRendererComponent(
            list, value, index, isSelected, cellHasFocus);

    if (label.getText().startsWith("NUMERIC_"))
    {
        label.setIcon(new ImageIcon(getClass().getResource(
                "/stats/gui/images/numeric.png")));
        label.setText(label.getText().substring(8));
    } else if (label.getText().startsWith("CHARACTER_"))
    {
        label.setIcon(new ImageIcon(getClass().getResource(
                "/stats/gui/images/character.png")));
        label.setText(label.getText().substring(10));
    }
    return label;
}

}
