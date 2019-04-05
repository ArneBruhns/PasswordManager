package model;

import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/**
 * Class PasswordAdministrator
 */
public class PasswordAdministrator {

    private FileTransformer fileTransformer;
    private RSAEncryptor rsaEncryptor;

    private TreeMap<String, List<String>> passwordEntries;
    private String uniquename;
    private String username;
    private String passsword;

    /**
     * Creats a new object of the PasswordAdministrator.
     */
    public PasswordAdministrator(FileTransformer fileTransformer, RSAEncryptor rsaEncryptor) throws JDOMException, IOException {
        this.rsaEncryptor = rsaEncryptor;
        this.fileTransformer = fileTransformer;
        this.getPasswordDates();
    }

    /**
     * Queries the data entries.
     *
     * @return A tree map that contains the data entries.
     * @throws JDOMException
     * @throws IOException
     */
    public TreeMap<String, List<String>> getPasswordDates() throws JDOMException, IOException {
        return this.passwordEntries = fileTransformer.requestPasswordDates();
    }

    /**
     * Returns the username.
     * @return Username.
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * Returns the password.
     * @return Password
     */
    public String getPasssword(){
        return this.passsword;
    }

    /**
     * Sets an unique name.
     * @param uniquename ~ Reading in a string.
     */
    public void setUniquename(String uniquename) {
        this.uniquename = uniquename;
    }

    /**
     * Sets an username.
     * @param username ~ Reading in an string.
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Sets a password.
     * @param passsword ~ Reading in a string.
     */
    public void setPasssword(String passsword){
        this.passsword = passsword;
    }

    /**
     * Checks whether a name has already been assigned.
     *
     * @return true, if the name has already been assigned.
     */
    public boolean containsKey(){
        return this.passwordEntries.containsKey(this.uniquename) && this.uniquename.length() != 0;
    }

    /**
     * Adds a data entry.
     *
     * @param encryptionNecessary ~ Reading in a boolean value.
     * @throws Exception
     */
    public void addEntry(boolean encryptionNecessary) throws Exception {
        if(this.username.length() == 0 || this.passsword.length() == 0) {
            throw new Exception("You have to enter a name and a password.");
        } else {
            this.fileTransformer.removeAllDatesInXMLFile(passwordEntries.keySet());
            this.passwordEntries.put(this.uniquename, List.of(this.username, this.passsword));
            if(encryptionNecessary){
                this.lockEntry(this.uniquename);
                this.passwordEntries.put(this.uniquename, List.of(this.username, this.passsword));
            }
            this.fileTransformer.addAllDatesInXMLFile(this.passwordEntries);
        }
    }

    /**
     * Unlocks a data entry.
     *
     * @param uniqueName ~ Reading in an unique name.
     * @param pw ~ Reading in a string.
     */
    public void unlockEntry(String uniqueName, String pw){
        List<String> unlockEntryDates = passwordEntries.get(uniqueName);

        String[] characters = unlockEntryDates.get(0).split( " ");
        int[] characterCodes = new int[characters.length];

        for(int i = 0; i < characters.length; i++) characterCodes[i] = Integer.parseInt(characters[i]);
        this.username = this.rsaEncryptor.decrypt(characterCodes, Integer.parseInt(pw));

        characters = unlockEntryDates.get(1).split(" ");
        characterCodes = new int[characters.length];

        for(int i = 0; i < characters.length; i++) characterCodes[i] = Integer.parseInt(characters[i]);
        this.passsword = this.rsaEncryptor.decrypt(characterCodes, Integer.parseInt(pw));
    }

    /**
     * Locks a data entry.
     *
     * @param uniqueName ~ Reading in a string.
     */
    public void lockEntry(String uniqueName){
        List<String> unlockEntryDates = passwordEntries.get(uniqueName);
        int[] characterCodes;
        this.username = "";
        this.passsword = "";

        characterCodes = this.rsaEncryptor.encrypt(unlockEntryDates.get(0));
        for(int i = 0; i < characterCodes.length; i++) this.username += characterCodes[i] + (i == characterCodes.length ? "" : " ");

        characterCodes = this.rsaEncryptor.encrypt(unlockEntryDates.get(1));
        for(int i = 0; i < characterCodes.length; i++) this.passsword += characterCodes[i] + (i == characterCodes.length ? "" : " ");
    }

    /**
     * Returns the entries of a data entry.
     * @param uniqueName ~ Reading in a string.
     */
    public void showEntry(String uniqueName){
        List<String> unlockEntryDates = passwordEntries.get(uniqueName);
        this.username = unlockEntryDates.get(0);
        this.passsword = unlockEntryDates.get(1);
    }

    /**
     * Hides a unlocked entry.
     */
    public void hideUnlockEntry(){
        this.username = "";
        this.passsword = "";
    }

    /**
     * Deletes a data enztry.
     *
     * @param key ~ Reading in a string.
     * @throws JDOMException
     * @throws IOException
     */
    public void deleteEntry(String key) throws JDOMException, IOException {
        this.passwordEntries.remove(key);
        this.fileTransformer.removeDatesInXMLFile(key);
    }

    /**
     * Unlocks all data entries.
     * @param pw
     * @throws JDOMException
     * @throws IOException
     */
    public void unlockAllEntries(String pw) throws JDOMException, IOException {
        this.fileTransformer.removeAllDatesInXMLFile(passwordEntries.keySet());

        Iterator it = this.passwordEntries.keySet().iterator();
        TreeMap<String, List<String>> map = new TreeMap<>();
        String key;

        while(it.hasNext()){
            key = String.valueOf(it.next());
            this.unlockEntry(key, pw);
            map.put(key, List.of(this.username, this.passsword));
        }

        this.passwordEntries = map;
        this.fileTransformer.addAllDatesInXMLFile(this.passwordEntries);
    }

    /**
     * Locks all data entries.
     * @throws JDOMException
     * @throws IOException
     */
    public void lockAllEntries() throws JDOMException, IOException {
        this.fileTransformer.removeAllDatesInXMLFile(passwordEntries.keySet());

        Iterator it = this.passwordEntries.keySet().iterator();
        TreeMap<String, List<String>> map = new TreeMap<>();
        String key;

        while(it.hasNext()){
            key = String.valueOf(it.next());
            this.lockEntry(key);
            map.put(key, List.of(this.username, this.passsword));
        }

        this.passwordEntries = map;
        this.fileTransformer.addAllDatesInXMLFile(this.passwordEntries);
    }

    /**
     * Checks whether the string meets the requirements.
     *
     * @return true, if th string meets the requirements.
     */
    public boolean checkNewEntry(){
        String name = this.uniquename.toLowerCase();
        char[] c = name.toCharArray();

        for(int i = 0; i < c.length; i++)
            if (c[i] < 97 || c[i] > 122) return false;

        return true;
    }
}
