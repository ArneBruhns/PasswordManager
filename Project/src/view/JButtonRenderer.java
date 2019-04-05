package view;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Class JButtonRenderer
 */
public class JButtonRenderer implements TableCellRenderer {

    JButton button;

    /**
     * Creates a new object of the JButtonRenderer.
     *
     * @param name ~ Reading in a string consisting desired action.
     */
    public JButtonRenderer(String name){
        button = new JButton(name);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        table.setShowGrid(false);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setFocusable(false);
        table.setRowSelectionAllowed(false);
        String txt = (value == null) ? "" : value.toString();
        button.setName(txt);
        return button;
    }
}
