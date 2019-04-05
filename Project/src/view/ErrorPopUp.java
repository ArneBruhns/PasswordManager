package view;

import controller.PasswordManagerController;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Class ErrorPopUp
 */
public class ErrorPopUp extends JFrame {

    private JPanel contentPane;
    private GroupLayout groupLayout;
    private PasswordManagerController passwordManagerController;
    private JButton btnOkay;
    private JTextPane errorTextField;

    /**
     * Creates a new object of the ErrorPopUp.
     *
     * @param pmc ~ Reading in an object from PasswordManagerController
     * @param errormessage ~ Reading in a error message.
     */
    public ErrorPopUp(PasswordManagerController pmc, String errormessage){
        this.passwordManagerController = pmc;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(Color.RED);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        btnOkay = new JButton("Okay");
        btnOkay.addActionListener(this.passwordManagerController);
        btnOkay.setForeground(Color.BLACK);
        btnOkay.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        btnOkay.setContentAreaFilled(false);
        btnOkay.setBorderPainted(false);
        btnOkay.setBackground((Color) null);

        errorTextField = new JTextPane();
        errorTextField.setEditable(false);
        errorTextField.setForeground(Color.BLACK);
        errorTextField.setText("ERROR MESSAGE: \n" + errormessage);
        errorTextField.setBackground(Color.RED);
        errorTextField.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));

        groupLayout = new GroupLayout(contentPane);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(btnOkay, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(errorTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
                                .addGap(2))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(errorTextField, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnOkay, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        contentPane.setLayout(groupLayout);
        setVisible(true);
    }
}
