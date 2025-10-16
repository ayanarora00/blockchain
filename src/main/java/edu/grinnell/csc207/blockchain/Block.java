package edu.grinnell.csc207.blockchain;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * A single block of a blockchain.
 */

public class Block {
    
    // Declaring the private variables
    private int num;
    private int amount;
    private long nonce;
    private Hash previous;
    private Hash hash;

    /** Constructor that creates a new block from the specified parameters, 
     *  performing the mining operation to discover the nonce and hash for this block given these parameters.
     * 
     * @param num the block number 
     * @param amount the amount to be put
     * @param prevHash the previous block's hash
     * @throws NoSuchAlgorithmException
     */

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

    /** Constructor that creates a new block from the specified parameters, using the provided nonce and additional parameters
     *  to generate the hash for the block. Because the nonce is provided, this constructor does not 
     *  need to perform the mining operation; it can compute the hash directly.
     * 
     * @param num the block number
     * @param amount the amount for the new block
     * @param prevHash the hash of the previous block
     * @param nonce the nonce for this block
     * @throws NoSuchAlgorithmException
     */

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

    /** Returns the number of this block.
     * 
     * @param takes nothing as parameter
     * @return int - num of the block
     */

    public int getNum(){
        return this.num;
    }

    /** Returns the amount of the block
     * 
     * @param takes nothing as parameter
     * @return int - amount of this block
     */
    public int getAmount(){
        return this.amount;
    }

    /** Returns the nonce of the block
     * 
     * @param takes nothing as parameter
     * @return long - nonce of this block
     */
    public long getNonce(){
        return this.nonce;
    }

    /** Returns the hash of the previous block
     * 
     * @param takes nothing as parameter
     * @return Hash - hash of the previous block
     */
    public Hash getPrevHash(){
        return this.previous;
    }

    /** Returns the hash of this block
     * 
     * @return Hash - hash of this block
     */
    public Hash getHash(){
        return this.hash;
    }

    /** returns a string representation of the Block
     *
     * 
     * @param takes nothing as parameter
     * @returns String - the string representation of the block
     */

    public String toString(){

        return ("Block" + this.getNum() + "(Amount:" + this.getAmount() + ", Nonce:"
                + this.getNonce() + ", prevHash:" + this.getPrevHash()  + "hash:" + 
                this.getHash() + ")");

    }





}
