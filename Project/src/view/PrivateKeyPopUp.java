package view;

import controller.PasswordManagerController;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Class PrivateKeyPopUp
 */
public class PrivateKeyPopUp extends JFrame {

    private JPanel contentPane;
    private GroupLayout groupLayout;
    private PasswordManagerController passwordManagerController;
    private JTextField textfieldHeading;
    private JPasswordField passwordField;
    private JButton btnConfirm;

    /**
     * Creates a new object of the PrivateKeyPopUp.
     *
     * @param pmc ~ Reading in an object from PasswordManagerController
     * @param btnName ~ Reading in a string consisting of the action performed.
     */
    public PrivateKeyPopUp(PasswordManagerController pmc, String btnName) {
        this.passwordManagerController = pmc;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        textfieldHeading = new JTextField();
        textfieldHeading.setEditable(false);
        textfieldHeading.setBackground(Color.BLACK);
        textfieldHeading.setHorizontalAlignment(SwingConstants.CENTER);
        textfieldHeading.setText("Enter the Private Key");
        textfieldHeading.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
        textfieldHeading.setForeground(Color.RED);
        textfieldHeading.setColumns(10);
        textfieldHeading.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));

        btnConfirm = new JButton("Confirm");
        btnConfirm.setName(btnName);
        btnConfirm.addActionListener(this.passwordManagerController);
        btnConfirm.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
        btnConfirm.setBackground(Color.BLACK);
        btnConfirm.setForeground(Color.RED);
        btnConfirm.setContentAreaFilled(false);
        btnConfirm.setBorderPainted(false);

        groupLayout = new GroupLayout(contentPane);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                        .addComponent(btnConfirm, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(textfieldHeading, Alignment.LEADING)
                                        .addComponent(passwordField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
                                .addContainerGap())
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(textfieldHeading, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnConfirm, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                .addContainerGap())
        );

        contentPane.setLayout(groupLayout);
        setVisible(true);
    }

    /**
     * Return the password.
     *
     * @return password
     */
    public String getPassword(){
        return new String(passwordField.getPassword());
    }

}
