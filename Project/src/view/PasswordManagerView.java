package view;

import controller.PasswordManagerController;
import model.PasswordManagerModel;
import org.jdom2.JDOMException;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * Class PasswordManagerView
 */
public class PasswordManagerView extends JFrame {

    /** DATFIELDS - ASSOSSIATION*/
    private PasswordManagerController passwordManagerController;
    private PasswordManagerModel passwordManagerModel;

    /** DATAFIELDS - JFRAME */
    private JPanel contentPane;
    private GroupLayout groupLayout;
    private String controllChangesView = "";

    /** DATFIELDS - VIEW START */
    private JTextField textfieldHeadingWelcome;
    private JButton btnStart;
    private JButton btnInfo;
    private JButton btnRSA;

    /** DATAFIELDS - VIEW INFO*/
    private JTextField textfieldHeadingInfo;
    private JTextPane panelInfo;
    private JButton btnBackInfo;
    private JScrollPane scrollPaneInfo;
    private JSeparator separatorView;

    /** DATAFIELDS - VIEW RSA*/
    private JTextPane panelRSA;
    private JTextField textfieldHeadingRSA;
    private JTextField fieldP;
    private JTextField fieldQ;
    private JTextField fieldN;
    private JTextField fieldE;
    private JTextField fieldPublicKey;
    private JTextField fieldPrivateKey;
    private JLabel labelFirstStep;
    private JLabel labelSecondStep;
    private JLabel labelThirdStep;
    private JLabel labelP;
    private JLabel labelQ;
    private JLabel labelN;
    private JLabel labelE;
    private JLabel labelPrivateKey;
    private JLabel labelPublicKey;
    private JButton btnBackRSA;
    private JButton btnDeleteKeys;
    private JButton btnDeleteEntry;
    private JScrollPane scrollPaneRSA;
    private JSeparator separatorRSA;

    /** DATAFIELDS - VIEW MANAGER*/
    private JTextField textfieldHeadingManager;
    private JButton btnBackManager;
    private JLabel labelUniqueName;
    private JLabel labelUserName;
    private JLabel labelPassword;
    private JLabel labelYourPassword;
    private JButton labelHide;
    private JLabel labelYourUserName;
    private JTextField fieldUniqueName;
    private JTextField fieldUsername;
    private JTextField fieldYourUserName;
    private JTextField fieldYourPassword;
    public JPasswordField passwordField;
    private JSeparator lineOneManager;
    private JSeparator lineTwoManager;
    private JScrollPane scrollPaneManager;
    private JButton btnCreateNewEntry ;
    private JTable tableManager;
    private final String[] roomTableColumnNames = {"Number", "Name", "Unlock", "Delete"};
    private DefaultTableModel defaultTableModel;

    /**
     * Creats a new object of the PasswordManagerView.
     *
     * @param pmm ~ Reading in an object from PasswordManagerModel.
     */
    public PasswordManagerView(PasswordManagerModel pmm) {
        this.passwordManagerModel = pmm;
        passwordManagerController = new PasswordManagerController(this, pmm);

        getContentPane().setBackground(new Color(0, 0, 0));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1450, 800);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        groupLayout = new GroupLayout(getContentPane());
        setVisible(true);
    }

    /**
     * Generates the start view.
     */
    public void createStartView() {
        controllChangesView = "Start";

        textfieldHeadingWelcome = new JTextField();
        textfieldHeadingWelcome.setText("WELCOME TO THE PASSWORD APPLICATION");
        configuereTextFieldHeading(textfieldHeadingWelcome);

        btnStart = new JButton("Start");
        configuereButtons(btnStart);

        btnRSA = new JButton("Create Keys");
        configuereButtons(btnRSA);

        btnInfo = new JButton("Info");
        configuereButtons(btnInfo);

        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(textfieldHeadingWelcome, GroupLayout.DEFAULT_SIZE, 1414, Short.MAX_VALUE)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(btnStart, GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnRSA, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnInfo, GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(textfieldHeadingWelcome, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 520, Short.MAX_VALUE)
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                        .addComponent(btnRSA, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnStart, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                                        .addComponent(btnInfo, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                                .addContainerGap())
        );

        removeAll();
        getContentPane().setLayout(groupLayout);
    }

    /**
     * Generates the Information view.
     */
    public void createInfoView() throws IOException {
        controllChangesView = "Info";

        textfieldHeadingInfo = new JTextField();
        textfieldHeadingInfo.setText("INFORMATION GUIDE");
        configuereTextFieldHeading(textfieldHeadingInfo);

        btnBackInfo = new JButton("Back");
        configuereButtons(btnBackInfo);

        scrollPaneInfo = new JScrollPane();
        configuereScrollPane(scrollPaneInfo);

        panelInfo = new JTextPane();
        panelInfo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        panelInfo.setEditable(false);
        panelInfo.setForeground(Color.WHITE);
        panelInfo.setBackground(Color.BLACK);
        panelInfo.setText(passwordManagerModel.getFileTransformer().getInformationText());
        scrollPaneInfo.setViewportView(panelInfo);

        separatorView = new JSeparator();
        configuereSeparator(separatorView);

        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                                .addGap(7)
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(scrollPaneInfo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1385, Short.MAX_VALUE)
                                        .addComponent(textfieldHeadingInfo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1385, Short.MAX_VALUE)
                                        .addComponent(btnBackInfo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1385, Short.MAX_VALUE))
                                .addGap(7))
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(separatorView, GroupLayout.DEFAULT_SIZE, 1382, Short.MAX_VALUE)
                                .addContainerGap())
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(7)
                                .addComponent(textfieldHeadingInfo, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addGap(2)
                                .addComponent(separatorView, GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(scrollPaneInfo, GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                                .addGap(11)
                                .addComponent(btnBackInfo, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addGap(6))
        );

        removeAll();
        getContentPane().setLayout(groupLayout);
    }

    /**
     * Generates the rsa view
     */
    public void createRSAView() throws IOException {
        controllChangesView = "RSA";

        textfieldHeadingRSA = new JTextField();
        textfieldHeadingRSA.setText("CREATE THE PUBLIC AND PRIVATE KEYS");
        configuereTextFieldHeading(textfieldHeadingRSA);

        btnBackRSA = new JButton("Back");
        configuereButtons(btnBackRSA);

        btnDeleteKeys = new JButton("Delete Keys");
        configuereButtons(btnDeleteKeys);

        btnDeleteEntry = new JButton("Delete Entry");
        configuereButtons(btnDeleteEntry);

        scrollPaneRSA = new JScrollPane();
        configuereScrollPane(scrollPaneRSA);

        panelRSA = new JTextPane();
        panelRSA.setBackground(Color.BLACK);
        panelRSA.setForeground(Color.WHITE);
        panelRSA.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        panelRSA.setText(passwordManagerModel.getFileTransformer().getRSAInfo());
        scrollPaneRSA.setViewportView(panelRSA);

        labelFirstStep = new JLabel("FIRST STEP:");
        configuereLabelSize21(labelFirstStep);

        labelSecondStep = new JLabel("SECOND STEP:");
        configuereLabelSize21(labelSecondStep);

        labelThirdStep = new JLabel("THIRD STEP:");
        configuereLabelSize21(labelThirdStep);

        labelP = new JLabel("Entering the prime number P: ");
        configuereLabelSize17Right(labelP);

        labelQ = new JLabel("Entering the prime number Q: ");
        configuereLabelSize17Right(labelQ);

        labelN = new JLabel("Output PHI(N): ");
        configuereLabelSize17Right(labelN);

        labelE = new JLabel("Entering the number E: ");
        configuereLabelSize17Right(labelE);

        labelPublicKey = new JLabel("The Public Key: ");
        configuereLabelSize17Right(labelPublicKey);

        labelPrivateKey = new JLabel("The Private Key: ");
        configuereLabelSize17Right(labelPrivateKey);

        fieldP = new JTextField();
        fieldP.setName("fieldP");
        configuereTextFieldEditable(fieldP);

        fieldQ = new JTextField();
        fieldQ.setName("fieldQ");
        configuereTextFieldEditable(fieldQ);

        fieldN = new JTextField("0");
        configuereTextFieldNonEditable(fieldN);

        fieldE = new JTextField();
        fieldE.setName("fieldE");
        configuereTextFieldEditable(fieldE);

        fieldPublicKey = new JTextField("0");
        configuereTextFieldNonEditable(fieldPublicKey);

        fieldPrivateKey = new JTextField("0");
        configuereTextFieldNonEditable(fieldPrivateKey);

        separatorRSA = new JSeparator();
        configuereSeparator(separatorRSA);

        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(5)
                                .addComponent(textfieldHeadingRSA, GroupLayout.DEFAULT_SIZE, 1389, Short.MAX_VALUE)
                                .addGap(5))
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                                .addComponent(labelFirstStep, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)
                                                .addGroup(groupLayout.createSequentialGroup()
                                                        .addComponent(labelP, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(fieldP, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(groupLayout.createSequentialGroup()
                                                        .addComponent(labelQ, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(fieldQ, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(groupLayout.createSequentialGroup()
                                                        .addComponent(labelN, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(fieldN, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
                                                .addComponent(labelSecondStep, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)
                                                .addGroup(groupLayout.createSequentialGroup()
                                                        .addComponent(labelE, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(fieldE, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
                                                .addComponent(labelThirdStep, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)
                                                .addGroup(groupLayout.createSequentialGroup()
                                                        .addComponent(labelPublicKey, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(fieldPublicKey, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(groupLayout.createSequentialGroup()
                                                        .addComponent(labelPrivateKey, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addComponent(fieldPrivateKey, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
                                                .addComponent(btnDeleteKeys, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(btnDeleteEntry, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE))
                                .addGap(58)
                                .addComponent(scrollPaneRSA, GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(separatorRSA, GroupLayout.DEFAULT_SIZE, 1379, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(btnBackRSA, GroupLayout.DEFAULT_SIZE, 1379, Short.MAX_VALUE)
                                .addGap(20))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(textfieldHeadingRSA, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(separatorRSA, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(scrollPaneRSA, GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(labelFirstStep, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(fieldP)
                                                        .addComponent(labelP, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(labelQ, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(fieldQ, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(fieldN, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(labelN, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(labelSecondStep, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(fieldE, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(labelE, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(labelThirdStep, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(fieldPublicKey, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(labelPublicKey, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(fieldPrivateKey, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(labelPrivateKey, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                                                .addComponent(btnDeleteEntry, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnDeleteKeys, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(btnBackRSA, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addGap(14))
        );

        removeAll();
        getContentPane().setLayout(groupLayout);
    }

    /**
     * Generates the manager view.
     *
     * @throws JDOMException
     * @throws IOException
     */
    public void createViewManager() throws JDOMException, IOException {
        controllChangesView = "Manager";

        textfieldHeadingManager = new JTextField();
        textfieldHeadingManager.setText("MANAGE YOUR PASSWORDS");
        configuereTextFieldHeading(textfieldHeadingManager);

        lineOneManager = new JSeparator();
        configuereSeparator(lineOneManager);

        lineTwoManager = new JSeparator();
        configuereSeparator(lineTwoManager);

        btnCreateNewEntry = new JButton("Create new entry");
        configuereButtons(btnCreateNewEntry);

        btnBackManager = new JButton("Back");
        configuereButtons(btnBackManager);

        labelUniqueName = new JLabel("Entering a unique name ");
        configuereLabelSize17Center(labelUniqueName);

        labelUserName = new JLabel("Entering the user name");
        configuereLabelSize17Center(labelUserName);

        labelPassword = new JLabel("Entering the password");
        configuereLabelSize17Center(labelPassword);

        labelHide = new JButton("Hide");
        configuereButtons(labelHide);

        labelYourUserName = new JLabel("Your user name");
        configuereLabelSize17Center(labelYourUserName);

        labelYourPassword = new JLabel("Your Password");
        configuereLabelSize17Center(labelYourPassword);

        fieldUniqueName = new JTextField();
        fieldUniqueName.setName("fieldUniqueName");
        configuereTextFieldEditable(fieldUniqueName);

        fieldUsername = new JTextField();
        fieldUsername.setName("fieldUsername");
        configuereTextFieldEditable(fieldUsername);

        fieldYourUserName = new JTextField();
        configuereTextFieldNonEditable(fieldYourUserName);

        fieldYourPassword = new JTextField();
        configuereTextFieldNonEditable(fieldYourPassword);

        passwordField = new JPasswordField();
        passwordField.setName("passwordField");
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField.addActionListener(passwordManagerController);

        scrollPaneManager = new JScrollPane();
        configuereScrollPane(scrollPaneManager);

        defaultTableModel = new DefaultTableModel();
        defaultTableModel.setDataVector(null, roomTableColumnNames);

        tableManager = new JTable();
        updateTableManager(tableManager, defaultTableModel);

        scrollPaneManager.setViewportView(tableManager);

        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(5)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(textfieldHeadingManager, GroupLayout.DEFAULT_SIZE, 1389, Short.MAX_VALUE)
                                        .addComponent(lineOneManager, GroupLayout.DEFAULT_SIZE, 1389, Short.MAX_VALUE))
                                .addGap(5))
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(5)
                                .addComponent(lineTwoManager, GroupLayout.DEFAULT_SIZE, 1389, Short.MAX_VALUE)
                                .addGap(5))
                        .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(btnBackManager, GroupLayout.DEFAULT_SIZE, 1384, Short.MAX_VALUE)
                                        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                                        .addComponent(labelYourUserName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(fieldYourUserName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(labelHide, GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                                        .addComponent(labelYourPassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(fieldYourPassword, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE))))
                                .addGap(15))
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(5)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(btnCreateNewEntry, GroupLayout.DEFAULT_SIZE, 1389, Short.MAX_VALUE)
                                                .addGap(5))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(fieldUniqueName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                                                        .addComponent(labelUniqueName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(fieldUsername, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                                                        .addComponent(labelUserName, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(passwordField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                                                        .addComponent(labelPassword, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
                                                .addContainerGap())))
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(5)
                                .addComponent(scrollPaneManager, GroupLayout.DEFAULT_SIZE, 1389, Short.MAX_VALUE)
                                .addGap(5))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(6)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(textfieldHeadingManager, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(79)
                                                .addComponent(lineOneManager, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)))
                                .addGap(5)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(labelUniqueName, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelPassword, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelUserName, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addGap(11)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(fieldUniqueName, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(fieldUsername, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addGap(11)
                                .addComponent(btnCreateNewEntry, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addGap(11)
                                .addComponent(lineTwoManager, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
                                .addGap(11)
                                .addComponent(scrollPaneManager, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(labelYourUserName, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelYourPassword, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(fieldYourPassword, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(fieldYourUserName, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(labelHide, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(btnBackManager, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        removeAll();
        getContentPane().setLayout(groupLayout);
    }

    /**
     * Configures the button.
     *
     * @param btn ~ Reading in an object of the JButton.
     */
    public void configuereButtons(JButton btn){
        btn.addActionListener(passwordManagerController);
        btn.setForeground(new Color(255, 0, 0));
        btn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 27));
        btn.setContentAreaFilled(false);
        btn.setBackground(null);
        btn.setBorderPainted(false);
    }

    /**
     * Configures the textfield.
     *
     * @param textField ~ Reading in an object of the JTextField.
     */
    public void configuereTextFieldHeading(JTextField textField){
        textField.setToolTipText("");
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setForeground(Color.RED);
        textField.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 33));
        textField.setEditable(false);
        textField.setColumns(10);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textField.setBackground((Color) null);
    }

    /**
     * Configures the separator.
     *
     * @param separator ~ Reading in an object of the JSeparator.
     */
    public void configuereSeparator(JSeparator separator){
        separator.setForeground(Color.RED);
        separator.setBackground(Color.RED);
    }

    /**
     * Configures the label.
     *
     * @param label ~ Reading in an object of the JLabel.
     */
    public void configuereLabelSize17Center(JLabel label){
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.RED);
        label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
    }

    /**
     * Configures the label.
     *
     * @param label ~ Reading in an object of the JLabel.
     */
    public void configuereLabelSize17Right(JLabel label){
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setForeground(Color.RED);
        label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
    }

    /**
     * Configures the label.
     *
     * @param label ~ Reading in an object of the JLabel.
     */
    public void configuereLabelSize21(JLabel label){
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.RED);
        label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
    }

    /**
     * Configures the Scroll pane.
     *
     * @param scrollPane ~ Reading in an object of the JScrollPane.
     */
    public void configuereScrollPane(JScrollPane scrollPane){
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /**
     * Configures the Text Field.
     *
     * @param textField ~ Reading in an object of the JTextField
     */
    public void configuereTextFieldNonEditable(JTextField textField){
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setEditable(false);
        textField.setColumns(10);
    }

    /**
     * Configures the Text Field.
     *
     * @param textField ~ Reading in an object of the JTextField
     */
    public void configuereTextFieldEditable(JTextField textField){
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setEditable(true);
        textField.addKeyListener(passwordManagerController);
        textField.setColumns(10);
    }

    /**
     * The components are removed from the contentPane.
     */
    public void removeAll() {
        if(!controllChangesView.equals("Start")) {
            if(textfieldHeadingWelcome != null) getContentPane().remove(textfieldHeadingWelcome);
            if(btnRSA != null) getContentPane().remove(btnRSA);
            if(btnInfo != null) getContentPane().remove(btnInfo);
            if(btnStart != null) getContentPane().remove(btnStart);
        }

        if (!controllChangesView.equals("Info")) {
            if(textfieldHeadingInfo != null) getContentPane().remove(textfieldHeadingInfo);
            if(panelInfo != null)getContentPane().remove(panelInfo);
            if(btnBackInfo != null)getContentPane().remove(btnBackInfo);
            if(scrollPaneInfo != null)getContentPane().remove(scrollPaneInfo);
            if(separatorView != null)getContentPane().remove(separatorView);

        }

        if (!controllChangesView.equals("RSA")) {
            if(panelRSA != null)getContentPane().remove(panelRSA);
            if(textfieldHeadingRSA != null)getContentPane().remove(textfieldHeadingRSA);
            if(fieldP != null)getContentPane().remove(fieldP);
            if(fieldQ != null)getContentPane().remove(fieldQ);
            if(fieldN != null)getContentPane().remove(fieldN);
            if(fieldE != null)getContentPane().remove(fieldE);
            if(fieldPublicKey != null)getContentPane().remove(fieldPublicKey);
            if(fieldPrivateKey != null)getContentPane().remove(fieldPrivateKey);
            if(labelFirstStep != null)getContentPane().remove(labelFirstStep);
            if(labelSecondStep != null)getContentPane().remove(labelSecondStep);
            if(labelThirdStep != null)getContentPane().remove(labelThirdStep);
            if(labelP != null)getContentPane().remove(labelP);
            if(labelQ != null)getContentPane().remove(labelQ);
            if(labelN != null)getContentPane().remove(labelN);
            if(labelE != null)getContentPane().remove(labelE);
            if(labelPublicKey != null)getContentPane().remove(labelPublicKey);
            if(labelPrivateKey != null)getContentPane().remove(labelPrivateKey);
            if(btnBackRSA != null)getContentPane().remove(btnBackRSA);
            if(btnDeleteKeys != null)getContentPane().remove(btnDeleteKeys);
            if(btnDeleteEntry != null)getContentPane().remove(btnDeleteEntry);
            if(scrollPaneRSA != null)getContentPane().remove(scrollPaneRSA);
            if(separatorRSA != null)getContentPane().remove(separatorRSA);
        }

        if (!controllChangesView.equals("Manager")) {
            if(textfieldHeadingManager != null)getContentPane().remove(textfieldHeadingManager);
            if(btnBackManager != null)getContentPane().remove(btnBackManager);
            if(labelUniqueName != null)getContentPane().remove(labelUniqueName);
            if(labelUserName != null)getContentPane().remove(labelUserName);
            if(labelPassword != null)getContentPane().remove(labelPassword);
            if(labelYourPassword != null)getContentPane().remove(labelYourPassword);
            if(labelHide != null)getContentPane().remove(labelHide);
            if(labelYourUserName != null)getContentPane().remove(labelYourUserName);
            if(fieldUniqueName != null)getContentPane().remove(fieldUniqueName);
            if(fieldUsername != null)getContentPane().remove(fieldUsername);
            if(fieldYourUserName != null)getContentPane().remove(fieldYourUserName);
            if(fieldYourPassword != null)getContentPane().remove(fieldYourPassword);
            if(passwordField != null)getContentPane().remove(passwordField);
            if(lineOneManager != null)getContentPane().remove(lineOneManager);
            if(lineTwoManager != null)getContentPane().remove(lineTwoManager);
            if(tableManager != null)getContentPane().remove(tableManager);
            if(scrollPaneManager != null)getContentPane().remove(scrollPaneManager);
            if(btnCreateNewEntry!= null)getContentPane().remove(btnCreateNewEntry);
        }
    }

    /**
     * Updates the rsa view.
     */
    public void updateRSAView(){
        if(passwordManagerModel.getRSAEncrypter().getP() == -1) fieldP.setText("");
        if(passwordManagerModel.getRSAEncrypter().getQ() == -1) fieldQ.setText("");
        if(passwordManagerModel.getRSAEncrypter().getE() == -1) {
            fieldE.setText("");
            fieldN.setText("0");
            fieldPublicKey.setText("0");
            fieldPrivateKey.setText("0");
        }

        if(passwordManagerModel.getRSAEncrypter().getPHI() != 0)
           fieldN.setText(String.valueOf(passwordManagerModel.getRSAEncrypter().getPHI()));
        if(passwordManagerModel.getRSAEncrypter().getE() != 0 && passwordManagerModel.getRSAEncrypter().getE() != -1)
           fieldPublicKey.setText(String.valueOf(passwordManagerModel.getRSAEncrypter().getE()));
        if(passwordManagerModel.getRSAEncrypter().getD() != 0)
           fieldPrivateKey.setText(String.valueOf(passwordManagerModel.getRSAEncrypter().getD()));
    }

    /**
     * Updates the manager view.
     */
    public void updateManagerView(){
        fieldYourUserName.setText(passwordManagerModel.getPasswordAdministrator().getUsername());
        fieldYourPassword.setText(passwordManagerModel.getPasswordAdministrator().getPasssword());
    }

    /**
     * Updates the manager view.
     */
    public void updateManagerViewCreateEntry(){
        fieldUniqueName.setText("");
        fieldUsername.setText("");
        passwordField.setText("");
    }

    /**
     * Updates the password table.
     *
     * @throws JDOMException
     * @throws IOException
     */
    public void updateTableManager() throws JDOMException, IOException {
        updateTableManager(this.tableManager, this.defaultTableModel);
    }

    /**
     * Updates the password table.
     *
     * @param table ~ Reading in an object of the TableManager.
     * @param dtm ~ Reading in an object of the DefaultTableModel.
     * @throws JDOMException
     * @throws IOException
     */
    public void updateTableManager(JTable table, DefaultTableModel dtm) throws JDOMException, IOException {
        table.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));

        TreeMap passwordEntries = passwordManagerModel.getPasswordAdministrator().getPasswordDates();
        Iterator it = passwordEntries.keySet().iterator();
        String[][] tableData = new String[passwordEntries.size()][4];
        int counter = 0;

        table.setModel(dtm);
        table.getColumn("Unlock").setCellRenderer(new JButtonRenderer("Unlock"));
        table.getColumn("Delete").setCellRenderer(new JButtonRenderer("Delete"));

        while(it.hasNext()){
            String key = String.valueOf(it.next());
            tableData[counter][0] = String.valueOf(counter + 1);
            tableData[counter][1] = key;
            tableData[counter][2] = key + " Unlock";
            tableData[counter][3] = key + " Delete";
            counter++;
        }

        dtm.setDataVector(tableData, roomTableColumnNames);
        table.setModel(dtm);

        table.getColumn("Unlock").setCellRenderer(new JButtonRenderer("Unlock"));
        table.getColumn("Unlock").setCellEditor(new JButtonEditor(this.passwordManagerController, "Unlock"));

        table.getColumn("Delete").setCellRenderer(new JButtonRenderer("Delete"));
        table.getColumn("Delete").setCellEditor(new JButtonEditor(this.passwordManagerController, "Delete"));
    }
}