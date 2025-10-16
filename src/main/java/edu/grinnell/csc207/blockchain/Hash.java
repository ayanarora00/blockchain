// Importing the packages required

package edu.grinnell.csc207.blockchain;
import java.util.Arrays;

/**
 * A wrapper class over a hash value (a byte array).
 */
public class Hash {
    
    // Declaring the byte array
    private byte[] data;

    /** Constructor method to create a hash
     * 
     * @param byte[] data - a byte array
     */
    public Hash(byte[] data){
        this.data = data;
    }

    /** Returns the data of the Hash, the byte array that makes up the Hash
     * 
     * @param none
     * @return returns the data of the Hash, the byte array that makes up the Hash
     */
    public byte[] getData(){
        return this.data;
    }

    /**  returns true if this hash meets the criteria for validity, i.e., its first three indices contain zeroes.
     * 
     * @param none
     * @return boolean depending on the validity of the array
     */
    public boolean isValid(){
        return (this.data[0] == 0 && this.data[1] == 0 && this.data[2] == 0);
    }

    /**Returns the string representation of the hash as a string of hexadecimal digits, 2 digits per byte.
     * 
     * @param none
     * @return String - the hash as a string
     */
    public String toString(){

        String str = "";
        for (int i = 0; i < this.data.length; i++){
            int value = Byte.toUnsignedInt(data[i]);
            str += String.format("%02x", value);
        }

        return str;
    }

    /**Returns true if this hash is structurally equal to the argument.
     * 
     * @param Object that we type cast and compare to our hash
     * @return boolean depending on the equality
     */
    public boolean equals(Object other){

        if (!(other instanceof Hash)) {
            return false;
        } else {

            Hash o = (Hash) other;
            return Arrays.equals(this.data, o.data);

        }

    }

}
 