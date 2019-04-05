package model;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * Class RSAEncryptor
 */
public class RSAEncryptor {

    private FileTransformer fileTransformer;
    private PrimesVerifier primesVerifier;

    private int P;
    private int Q;
    private int N;
    private int PHI;
    private int E;
    private int D;

    /**
     * Creats a new object of the RSAEncryptor.
     */
    public RSAEncryptor(FileTransformer fileTransformer) throws IOException {
        this.fileTransformer = fileTransformer;
        this.primesVerifier = new PrimesVerifier();
        requestData();
    }

    /**
     * Returns the prime number P.
     * @return Prime number P.
     */
    public int getP(){
        return this.P;
    }

    /**
     * Returns the prime number Q.
     * @return Prime number Q.
     */
    public int getQ(){
        return this.Q;
    }

    /**
     * Returns the integer number PHI.
     * @return Integer number PHI.
     */
    public int getPHI(){
        return this.PHI;
    }

    /**
     * Returns the integer number E.
     * @return Integer number E.
     */

    public int getE(){
        return this.E;
    }
    /**
     * Returns the integer number D.
     * @return Integer number D.
     */
    public int getD(){
        return this.D;
    }

    /**
     * Sets a prime number.
     *
     * @param p ~ Reading in an integer value.
     * @throws Exception
     */
    public void setP(int p) throws Exception {
        if(fileTransformer.checkConfigKeyCreated()) throw new Exception("Keys already exist. Delete the keys using the delete-function.");
        if(p < 1) throw new Exception("Enter a positive number.");
        if(primesVerifier.isPrim(p)) {
            this.P = p;
            if (this.Q != 0 || this.Q != -1) calculateN();
        } else {
            throw new Exception("This number is not a prime number.");
        }
    }

    /**
     * Sets a prime number.
     *
     * @param q ~ Reading in an integer value.
     * @throws Exception
     */
    public void setQ(int q) throws Exception {
        if(fileTransformer.checkConfigKeyCreated()) throw new Exception("Keys already exist. Delete the keys using the delete-function.");
        if(q < 1) throw new Exception("Enter a positive number.");
        if(primesVerifier.isPrim(q)){
            this.Q = q;
            if(this.P != 0 || this.P != -1) calculateN();
        } else {
            throw new Exception("This number is not a prime number.");
        }
    }

    /**
     * Determines the number N.
     */
    public void calculateN(){
        this.N = this.P * this.Q;
        this.calculatePhi();
    }

    /**
     * Determines the number PHI.
     */
    public void calculatePhi(){
        this.PHI = (this.P - 1) * (this.Q - 1);
    }

    public boolean checkEntryE(int e) throws Exception {
        if(e > this.PHI || e < 2) {
            throw new Exception("Enter a number which is greater than 1 and less than PHI(N).");
        } else {
            return true;
        }
    }

    /**
     * Sets a integer number.
     *
     * @param e ~ Reading in an integer value.
     * @throws Exception
     */
     public void setE(int e) throws Exception {
        if(fileTransformer.checkConfigKeyCreated()) throw new Exception("Keys already exist. Delete the keys using the delete-function.");
        if(this.P == 0 || this.Q == 0) throw new Exception("Complete the first step successfully before moving on to the second step.");
        if(this.checkEntryE(e)) {
            this.E = e;
            this.calculateD();
        }
     }

    /**
     * Determines the number D.
     */
    public void calculateD() throws Exception {
        try {
            this.D = Integer.parseInt(BigInteger.valueOf(this.E).modInverse(BigInteger.valueOf(this.PHI)).toString());
        } catch (Exception e) {
            throw new Exception("Enter a number where ggt(e, PHI(N)) = 1 is valid.");
        }
    }

    /**
     * Transfers the entered parameters.
     * @throws IOException
     */
    public void enterData() throws IOException {
        fileTransformer.enterDataInTextFileRSA(this.N, this.PHI, this.E);
    }

    /**
     * Queries the data required for encryption or decryption.
     * @throws IOException
     */
    public void requestData() throws IOException {
        List<String> dates = fileTransformer.requestDataFromTextFileRSA();
        if(dates.size() != 0) {
            this.N = Integer.parseInt(dates.get(0));
            this.PHI = Integer.parseInt(dates.get(1));
            this.E = Integer.parseInt(dates.get(2));
        }
    }

    /**
     * Encrypts an input.
     *
     * @param input ~ Reading in a string.
     * @return The decrypted input.
     */
    public int[] encrypt(String input){
        char[] c = input.toCharArray();
        int[] result = new int[c.length];
        int number;
        BigInteger big;

        for(int i = 0; i < c.length; i++){
            number = c[i];
            big = new BigInteger(String.valueOf(number));
            big = big.pow(this.E);
            big = big.mod(BigInteger.valueOf(this.N));
            result[i] = Integer.parseInt(String.valueOf(big));
        }

        return result;
    }

    /**
     * Decrypts an input.
     *
     * @param inputs ~ Reading in an integer array.
     * @param key ~ Reading in an integer value.
     * @return The decrypted input.
     */
    public String decrypt(int[] inputs, int key){
        String output = "";
        int number;
        BigInteger big;

        for(int i = 0; i < inputs.length; i++){
            number = inputs[i];
            big = new BigInteger(String.valueOf(number));
            big = big.pow(key);
            big = big.mod(BigInteger.valueOf(this.N));
            output += (char) Integer.parseInt(String.valueOf(big));
        }

        return output;
    }

    /**
     * Verifies that the correct private key has been entered.
     *
     * @param privateKey ~ Reading in an integer value.
     * @return true, if the private key is correct.
     * @throws Exception
     */
    public boolean checkPrivateKey(int privateKey) throws Exception {
        if(!fileTransformer.checkConfigKeyCreated()) throw new Exception("No keys have been created.");
        else {
            requestData();

            BigInteger result;
            BigInteger bigD = BigInteger.valueOf(privateKey);
            BigInteger bigE = BigInteger.valueOf(this.E);
            BigInteger bigPHI = BigInteger.valueOf(this.PHI);

            result = bigD.multiply(bigE);
            result = result.mod(bigPHI);

            return 1 == Integer.parseInt(String.valueOf(result));
        }
    }

    /**
     * Resets all parameters.
     */
    public void resetAll(){
        this.P = -1;
        this.Q = -1;
        this.N = 0;
        this.PHI = 0;
        this.E = -1;
        this.D = 0;
    }

    /**
     * Resets input parameters.
     * @throws IOException
     */
    public void reset() throws IOException {
        this.P = 0;
        this.Q = 0;
        this.E = 0;
        requestData();
    }

}