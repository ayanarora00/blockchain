package edu.grinnell.csc207.blockchain;
import java.util.Arrays;

/**
 * A wrapper class over a hash value (a byte array).
 */
public class Hash {
    
    private byte[] data;

    public Hash(byte[] data){
        this.data = data;
    }

    public byte[] getData(){
        return this.data;
    }

    public boolean isValid(){
        return (this.data[0] == 0 && this.data[1] == 0 && this.data[2] == 0);
    }

    public String toString(){

        String str = "";
        for (int i = 0; i < this.data.length; i++){
            int value = Byte.toUnsignedInt(data[i]);
            str += String.format("%02x", value);
        }

        return str;
    }

    public boolean equals(Object other){

        if (!(other instanceof Hash)) {
            return false;
        } else {

            Hash o = (Hash) other;
            return Arrays.equals(this.data, o.data);

        }

    }

}
 