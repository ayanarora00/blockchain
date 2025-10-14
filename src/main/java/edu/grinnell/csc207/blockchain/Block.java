package edu.grinnell.csc207.blockchain;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * A single block of a blockchain.
 */

public class Block {
    
    private int num;
    private int amount;
    private long nonce;
    private Hash previous;
    private Hash hash;


    public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {

        this.num = num;
        this.amount = amount;
        this.previous = prevHash;

        Long noncetest = (long) 0;
        
        while (true){

            MessageDigest md = MessageDigest.getInstance("SHA-256");
        
            md.update(ByteBuffer.allocate(4).putInt(num).array());
            md.update(ByteBuffer.allocate(4).putInt(amount).array());

            if (previous!=null){
                md.update(prevHash.getData());
            }

            md.update(ByteBuffer.allocate(8).putLong(noncetest).array());

            byte[] possible = md.digest();

            if (possible[0] == 0 && possible[1] == 0 && possible[2] == 0){

                this.nonce = noncetest;
                this.hash = new Hash(possible);
                break;

            }

            noncetest++;

        }
        
    }

    public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException{

        this.num = num;
        this.amount = amount;
        this.previous = prevHash;
        this.nonce = nonce;

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        
        md.update(ByteBuffer.allocate(4).putInt(num).array());
        md.update(ByteBuffer.allocate(4).putInt(amount).array());

        if (previous!=null){
            md.update(prevHash.getData());
        }

        md.update(ByteBuffer.allocate(8).putLong(nonce).array());
        this.hash = new Hash(md.digest());

    }

    public int getNum(){
        return this.num;
    }

    public int getAmount(){
        return this.amount;
    }

    public long getNonce(){
        return this.nonce;
    }

    public Hash getPrevHash(){
        return this.previous;
    }

    public Hash getHash(){
        return this.hash;
    }

    public String toString(){

        return ("Block" + this.getNum() + "(Amount:" + this.getAmount() + ", Nonce:"
                + this.getNonce() + ", prevHash:" + this.getPrevHash()  + "hash:" + 
                this.getHash() + ")");

    }





}
