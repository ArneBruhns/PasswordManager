package controller;

import model.PasswordManagerModel;
import org.jdom2.JDOMException;
import view.ErrorPopUp;
import view.PasswordManagerView;
import view.PrivateKeyPopUp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * Class PasswordManagerController
 */
public class PasswordManagerController implements KeyListener, ActionListener {

    private PasswordManagerView passwordManagerView;
    private PasswordManagerModel passwordManagerModel;
    private ErrorPopUp errorPopUp;
    private PrivateKeyPopUp privateKeyPopUp;
    private String name;

    /**
     * Creates a new object of the PasswordManagerController.
     *
     * @param pmv ~ Reading in an object from PasswordManagerView
     * @param pmm ~ Reading in an object from PasswordManagerModel
     */
    public PasswordManagerController(PasswordManagerView pmv, PasswordManagerModel pmm){
        this.passwordManagerView = pmv;
        this.passwordManagerModel = pmm;
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB){
            if(e.getSource() instanceof JTextField) {

                JTextField textField = (JTextField) e.getSource();

                if (textField.getName().equals("fieldP")) {
                    try {
                        this.passwordManagerModel.getRSAEncrypter().setP(Integer.parseInt(textField.getText()));
                    } catch (Exception ex) {
                        errorPopUp = new ErrorPopUp(this, ex.getMessage());
                    }
                    passwordManagerView.updateRSAView();
                    return;
                }

                if (textField.getName().equals("fieldQ")) {
                    try {
                        this.passwordManagerModel.getRSAEncrypter().setQ(Integer.parseInt(textField.getText()));
                    } catch (Exception ex) {
                        errorPopUp = new ErrorPopUp(this, ex.getMessage());
                    }
                    passwordManagerView.updateRSAView();
                    return;
                }

                if (textField.getName().equals("fieldE")) {
                    try {
                        this.passwordManagerModel.getRSAEncrypter().setE(Integer.parseInt(textField.getText()));
                        this.passwordManagerModel.getRSAEncrypter().enterData();
                        if (!this.passwordManagerModel.getFileTransformer().checkConfigEntriesLocked()) {
                            this.passwordManagerModel.getPasswordAdministrator().lockAllEntries();
                            this.passwordManagerModel.getFileTransformer().updateConfigLocked("True", this.passwordManagerModel.getFileTransformer().checkConfigKeyCreated());
                        }
                    } catch (Exception ex) {
                        errorPopUp = new ErrorPopUp(this, ex.getMessage());
                    }
                    passwordManagerView.updateRSAView();
                    return;
                }

                if (textField.getName().equals("fieldUniqueName")) {
                    this.passwordManagerModel.getPasswordAdministrator().setUniquename(textField.getText());
                    return;
                }

                if (textField.getName().equals("fieldUsername")) {
                    this.passwordManagerModel.getPasswordAdministrator().setUsername(textField.getText());
                    return;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton){

            JButton pressed = (JButton) e.getSource();

            if(pressed.getText().equals("Start")) {
                try {
                    this.passwordManagerView.createViewManager();
                    this.passwordManagerModel.getRSAEncrypter().requestData();
                } catch (Exception ex) {
                    this.errorPopUp = new ErrorPopUp(this, "File for access data not found.");
                }
                return;
            }

            if(pressed.getText().equals("Create Keys")) {
                try {
                    this.passwordManagerView.createRSAView();
                } catch (IOException ex) {
                    this.errorPopUp = new ErrorPopUp(this, "File not found.");
                }
                return;
            }

            if(pressed.getText().equals("Info")) {
                try {
                    this.passwordManagerView.createInfoView();
                } catch (IOException ex) {
                    this.errorPopUp = new ErrorPopUp(this, "File not found.");
                }
                return;
            }

            if(pressed.getText().equals("Back")) {
                this.passwordManagerView.createStartView();
                this.passwordManagerModel.getRSAEncrypter().resetAll();
                this.passwordManagerModel.getPasswordAdministrator().hideUnlockEntry();
                return;
            }

            if(pressed.getText().equals("Delete Entry")){
                this.passwordManagerModel.getRSAEncrypter().resetAll();
                this.passwordManagerView.updateRSAView();
                try {
                    this.passwordManagerModel.getRSAEncrypter().reset();
                } catch (IOException ex) {
                    this.errorPopUp = new ErrorPopUp(this, ex.getMessage());
                }
                return;
            }

            if(pressed.getText().equals("Delete Keys")){
                this.passwordManagerModel.getRSAEncrypter().resetAll();
                this.passwordManagerView.updateRSAView();
                try {
                    this.passwordManagerModel.getRSAEncrypter().reset();
                } catch (IOException ex) {
                    this.errorPopUp = new ErrorPopUp(this, ex.getMessage());
                }
                try {
                    if(this.passwordManagerModel.getFileTransformer().checkConfigKeyCreated())
                        this.privateKeyPopUp = new PrivateKeyPopUp(this, "DeleteKeys");
                    else
                        this.errorPopUp = new ErrorPopUp(this, "No keys created.");
                } catch (IOException ex) {
                    this.errorPopUp = new ErrorPopUp(this, ex.getMessage());
                }
                return;
            }

            if(pressed.getText().equals("Okay")) {
                this.errorPopUp.dispose();
                this.errorPopUp = null;
                return;
            }

            if(pressed.getText().equals("Confirm") && pressed.getName().equals("DeleteKeys")){
                try {
                    if(this.passwordManagerModel.getFileTransformer().checkConfigKeyCreated()) {
                        if (this.passwordManagerModel.getRSAEncrypter().checkPrivateKey(Integer.parseInt(this.privateKeyPopUp.getPassword()))) {
                            this.passwordManagerModel.getPasswordAdministrator().unlockAllEntries(this.privateKeyPopUp.getPassword());
                            this.passwordManagerModel.getFileTransformer().updateConfigLocked("False", this.passwordManagerModel.getFileTransformer().checkConfigKeyCreated());
                            this.passwordManagerModel.getFileTransformer().createEmptyTextFileRSA();
                            this.privateKeyPopUp.dispose();
                        } else {
                            this.errorPopUp = new ErrorPopUp(this, "Wrong Key.");
                        }
                    }
                } catch (Exception ex) {
                    this.errorPopUp = new ErrorPopUp(this, ex.getMessage());
                }
                return;
            }

            if(pressed.getText().equals("Confirm") && pressed.getName().equals("Unlock")){
                try {
                    if(this.passwordManagerModel.getRSAEncrypter().checkPrivateKey(Integer.parseInt(this.privateKeyPopUp.getPassword()))){
                        this.passwordManagerModel.getPasswordAdministrator().unlockEntry(this.name, this.privateKeyPopUp.getPassword());
                        this.privateKeyPopUp.dispose();
                    } else {
                        this.errorPopUp = new ErrorPopUp(this, "Wrong Key.");
                    }
                    this.passwordManagerView.updateManagerView();
                } catch (Exception ex) {
                    this.errorPopUp = new ErrorPopUp(this, ex.getMessage());
                }
                return;
            }

            if(pressed.getText().equals("Confirm") && pressed.getName().equals("Delete")){
                try {
                    if(this.passwordManagerModel.getRSAEncrypter().checkPrivateKey(Integer.parseInt(this.privateKeyPopUp.getPassword()))){
                        this.passwordManagerModel.getPasswordAdministrator().deleteEntry(this.name);
                        this.privateKeyPopUp.dispose();
                    } else {
                        this.errorPopUp = new ErrorPopUp(this, "Wrong Key.");
                    }
                    this.passwordManagerView.updateTableManager();
                } catch (Exception ex) {
                    this.errorPopUp = new ErrorPopUp(this, "Wrong Key.");
                }
                return;
            }

            if(pressed.getText().equals("Hide")){
                this.passwordManagerModel.getPasswordAdministrator().hideUnlockEntry();
                this.passwordManagerView.updateManagerView();
                return;
            }

            if(pressed.getText().equals("Create new entry")){
                try {
                    this.passwordManagerModel.getPasswordAdministrator().setPasssword(String.valueOf(this.passwordManagerView.passwordField.getPassword()));
                    if (!this.passwordManagerModel.getPasswordAdministrator().checkNewEntry() || this.passwordManagerModel.getPasswordAdministrator().containsKey()) {
                        this.errorPopUp = new ErrorPopUp(this, "You have to enter a unique name, username and password. Only alphabetical characters are accepted.");
                        this.passwordManagerView.updateManagerViewCreateEntry();
                        return;
                    }
                } catch (Exception ex) {
                    this.errorPopUp = new ErrorPopUp(this, "You have to enter a unique name, username and password. Only alphabetical characters are accepted.");
                    return;
                }
                try {
                    if(this.passwordManagerModel.getFileTransformer().checkConfigKeyCreated()){
                        this.passwordManagerModel.getPasswordAdministrator().addEntry(true);
                    } else {
                        this.passwordManagerModel.getPasswordAdministrator().addEntry(false);
                    }
                    this.passwordManagerView.updateManagerViewCreateEntry();
                    this.passwordManagerView.updateTableManager();
                } catch (Exception ex) {
                    this.errorPopUp = new ErrorPopUp(this, ex.getMessage());
                }
                return;
            }
        }
    }

    /**
     * Processes events from the table.
     *
     * @param text ~ Reading in a string consisting of an entry name and an action.
     * @throws JDOMException
     * @throws IOException
     */
    public void tableButtonEvent(String text) throws JDOMException, IOException {
        this.name = text.split(" ")[0];
        if(this.passwordManagerModel.getFileTransformer().checkConfigKeyCreated()) {
            if (text.split(" ")[1].equals("Unlock")) {
                this.privateKeyPopUp = new PrivateKeyPopUp(this, "Unlock");
            }
            if (text.split(" ")[1].equals("Delete")) {
                this.privateKeyPopUp = new PrivateKeyPopUp(this, "Delete");
            }
        } else {
            if (text.split(" ")[1].equals("Unlock")) {
                this.passwordManagerModel.getPasswordAdministrator().showEntry(this.name);
                this.passwordManagerView.updateManagerView();
            }
            if (text.split(" ")[1].equals("Delete")) {
                this.passwordManagerModel.getPasswordAdministrator().deleteEntry(this.name);
            }
        }
        this.passwordManagerView.updateTableManager();
    }
}