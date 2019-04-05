package view;

import controller.PasswordManagerController;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class JButtonEditor
 */
public class JButtonEditor extends AbstractCellEditor implements TableCellEditor {

    private PasswordManagerController passwordManagerController;
    private JButton button;
    String txt;

    /**
     * Creates a new object of the JButtonEditor
     *
     * @param pmc ~ Reading in an object from PasswordManagerController.
     * @param name ~ Reading in a string consisting desired action.
     */
    public JButtonEditor(PasswordManagerController pmc, String name){
        super();
        passwordManagerController = pmc;
        button = new JButton(name);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    passwordManagerController.tableButtonEvent(button.getText());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        txt = (value == null) ? "" : value.toString();
        button.setText(txt);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }
}
