package model;

import org.jdom2.JDOMException;

import java.io.IOException;

/**
 * Class PasswordManagerModel
 */
public class PasswordManagerModel {

    FileTransformer fileTransformer;
    RSAEncryptor rsa;
    PasswordAdministrator passwordAdministrator;

    /**
     * Creates a new object of the PasswordManagerModel.
     */
    public PasswordManagerModel() throws IOException, JDOMException {
        this.fileTransformer = new FileTransformer();
        this.rsa = new RSAEncryptor(fileTransformer);
        this.passwordAdministrator = new PasswordAdministrator(fileTransformer, rsa);
    }

    /**
     * Returns the FileTransformer.
     * @return The object of the FileTransformer.
     */
    public FileTransformer getFileTransformer(){
        return this.fileTransformer;
    }

    /**
     * Returns the RSAEncrypter.
     * @return The object of the RSAEncrypter.
     */
    public RSAEncryptor getRSAEncrypter(){
        return this.rsa;
    }

    /**
     * Returns the PasswordAdministrator.
     * @return The object of the PasswordAdministrator.
     */
    public PasswordAdministrator getPasswordAdministrator(){
        return this.passwordAdministrator;
    }

}
