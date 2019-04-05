package model;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.util.*;

/**
 * Class FileTransformer
 */
public class FileTransformer {

    private File textFile;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private SAXBuilder builder;
    private Document doc;
    private Element rootElement;
    private TreeMap<String, List<String>> passwordEntries;
    private List<String> passwordDates;

    /**
     * Creates a new object of the FileTransformer.
     */
    public FileTransformer(){}

    /**
     * Reads the information text about the application.
     *
     * @return Information text about the application.
     * @throws IOException
     */
    public String getInformationText() throws IOException {
        String output = "";
        String line = "";
        fileReader = new FileReader("./src/Info.txt");
        bufferedReader = new BufferedReader(fileReader);

        while((line = bufferedReader.readLine()) != null){
            output += line + "\n";
        }

        bufferedReader.close();
        fileReader.close();
        return output;
    }

    /**
     * Reads the information text about RSA encryption.
     *
     * @return Information text about RSA
     * @throws IOException
     */
    public String getRSAInfo() throws IOException {
        String output = "";
        String line = "";
        fileReader = new FileReader("./src/RSAInfo.txt");
        bufferedReader = new BufferedReader(fileReader);

        while((line = bufferedReader.readLine()) != null){
            output += line + "\n";
        }

        bufferedReader.close();
        fileReader.close();
        return output;
    }

    /**
     * Checks whether keys have already been created.
     *
     * @return true, if keys have been created.
     * @throws IOException
     */
    public boolean checkConfigKeyCreated() throws IOException {
        fileReader = new FileReader("./src/config.txt");
        bufferedReader = new BufferedReader(fileReader);
        if(bufferedReader.readLine().split("=")[1].equals("True")) {
            bufferedReader.close();
            fileReader.close();
            return true;
        } else {
            bufferedReader.close();
            fileReader.close();
            return false;
        }
    }

    /**
     * Checks whether entries are encrypted.
     *
     * @return true, if entries are encrypted.
     * @throws IOException
     */
    public boolean checkConfigEntriesLocked() throws IOException {
        fileReader = new FileReader("./src/config.txt");
        bufferedReader = new BufferedReader(fileReader);
        bufferedReader.readLine();

        if(bufferedReader.readLine().split("=")[1].equals("True")) {
            bufferedReader.close();
            fileReader.close();
            return true;
        } else {
            bufferedReader.close();
            fileReader.close();
            return false;
        }
    }

    /**
     * Updates the config file.
     *
     * @param s ~ Reading in a string that can only accept "true" or "false".
     * @param check ~ Reading in boolean value.
     * @throws IOException
     */
    public void updateConfigLocked(String s, boolean check) throws IOException {
        textFile = new File("./src/config.txt");
        fileWriter = new FileWriter(textFile);
        bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write("KeysCreated=" + (check ? "False": "True") + "\n");
        bufferedWriter.write("EntriesLocked=" + s);

        bufferedWriter.close();
        fileWriter.close();
    }

    /**
     * Creates a file in which the data required for encryption is stored.
     *
     * @param N ~ Reading in an integer value.
     * @param PHI ~ Reading in an integer value.
     * @param E ~ Reading in an integer value.
     * @throws IOException
     */
    public void enterDataInTextFileRSA(int N, int PHI, int E) throws IOException {
        textFile = new File("./src/RSAKeys.txt");
        fileWriter = new FileWriter(textFile);
        bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write("RSA-Modul N: " + N + "\n");
        bufferedWriter.write("PHI: " + PHI + "\n");
        bufferedWriter.write("Öffentlicher Schlüssel: " + E);

        bufferedWriter.close();
        fileWriter.close();
    }

    /**
     * Creates an empty file in which the data required for encryption is stored.
     * @throws IOException
     */
    public void createEmptyTextFileRSA() throws IOException {
        textFile = new File("./src/RSAKeys.txt");
        fileWriter = new FileWriter(textFile);
        bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write("RSA-Modul N: 0\n");
        bufferedWriter.write("PHI: 0\n");
        bufferedWriter.write("Öffentlicher Schlüssel: 0");

        bufferedWriter.close();
        fileWriter.close();
    }

    /**
     * Queries the file in which the data required for encryption is stored.
     *
     * @return A list in which the data required for encryption is stored.
     * @throws IOException
     */
    public List<String> requestDataFromTextFileRSA() throws IOException {
        List<String> dates = new LinkedList<>();
        fileReader = new FileReader("./src/RSAKeys.txt");
        bufferedReader = new BufferedReader(fileReader);

        String input;

        while((input = bufferedReader.readLine()) != null){
            dates.add(input.split(":")[1].trim());
        }

        bufferedReader.close();
        fileReader.close();

        return dates;
    }

    /**
     * Queries the file in which the entries are stored.
     *
     * @return A Map containing the data of the entries.
     * @throws JDOMException
     * @throws IOException
     */
    public TreeMap<String, List<String>> requestPasswordDates() throws JDOMException, IOException {
        builder = new SAXBuilder();
        doc = builder.build("./src/Passwords.xml");
        rootElement = doc.getRootElement();

        passwordEntries = new TreeMap<>();

        List<Element> rootElementChildren = rootElement.getChildren();
        List<Element> childChildren;
        Element child;

        for(int i = 0; i < rootElementChildren.size(); i++){
            passwordDates = new LinkedList<>();
            child = rootElementChildren.get(i);
            childChildren = child.getChildren();
            passwordDates.add(childChildren.get(0).getText());
            passwordDates.add(childChildren.get(1).getText());
            passwordEntries.put(child.getName(), passwordDates);
        }

        return passwordEntries;
    }

    /**
     * Deletes an entry.
     *
     * @param key ~ Reading in a string that represents the entry name.
     * @throws JDOMException
     * @throws IOException
     */
    public void removeDatesInXMLFile(String key) throws JDOMException, IOException {
        builder = new SAXBuilder();
        doc = builder.build("./src/Passwords.xml");
        rootElement = doc.getRootElement();

        rootElement.removeChild(key);
        printXMLFile();
    }

    /**
     * Deletes a set of entries.
     *
     * @param keys ~ Reading in a set containing a set of entry names.
     * @throws JDOMException
     * @throws IOException
     */
    public void removeAllDatesInXMLFile(Set<String> keys) throws JDOMException, IOException {
        builder = new SAXBuilder();
        doc = builder.build("./src/Passwords.xml");
        rootElement = doc.getRootElement();

        Iterator it = keys.iterator();

        while(it.hasNext())rootElement.removeChild(String.valueOf(it.next()));
        printXMLFile();
    }

    /**
     * Adds a quantity of entries to the file containing the password entries.
     *
     * @param keys ~ Reading in a quantity of entries.
     * @throws JDOMException
     * @throws IOException
     */
    public void addAllDatesInXMLFile(TreeMap<String, List<String>> keys) throws JDOMException, IOException {
        builder = new SAXBuilder();
        doc = builder.build("./src/Passwords.xml");
        rootElement = doc.getRootElement();

        Iterator it = keys.keySet().iterator();
        Element entry;
        Element username;
        Element password;
        String key;

        while(it.hasNext()){
            key = String.valueOf(it.next());
            entry = new Element(key);
            username = new Element("username").setText(keys.get(key).get(0));
            password = new Element("password").setText(keys.get(key).get(1));
            entry.addContent(username).addContent(password);
            rootElement.addContent(entry);
        }

        printXMLFile();
    }

    /**
     * Creates a new File in which the password entries are stored.
     * @throws IOException
     */
    public void printXMLFile() throws IOException {
        File xmlFile = new File("./src/Passwords.xml");
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(doc, new FileWriter(xmlFile));
        xmlOutputter.clone();
    }

}
