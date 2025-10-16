package edu.grinnell.csc207.blockchain;

import java.security.NoSuchAlgorithmException;

/**
 * A linked list of hash-consistent blocks representing a ledger of
 * monetary transactions.
 */
public class BlockChain {

    /**Creating a private class called Node 
     * This implementation comes from the list implementation lab we worked upon for LAB 08, which I worked on
     * with Rayanne
    */
    private static class Node{

        public Block block;
        public Node next;
        public Node(Block block, Node next){
            this.block = block;
            this.next = next;
        }

    }
    
    private Node first; 
    private Node last;
    private int size;

    /** Constructor used for creating blockchain
     * 
     * @param initial - the initial amount the user will eventually enter as a command line argument
     * @throws NoSuchAlgorithmException
     */
    public BlockChain(int initial) throws NoSuchAlgorithmException {

        Block firstblock = new Block(0, initial, null);
        Node node = new Node (firstblock, null);

        // We use trackers for the first and last node and size
        this.first = node;
        this.last = node;
        this.size = 1;
        
    }

    /** mines a new candidate block to be added to the list using the block constructor.
     * 
     * @param amount the amount of the transaction to be added to the blockchain 
     * @return Block - the new block that we mine
     * @throws NoSuchAlgorithmException
     */
    public Block mine(int amount) throws NoSuchAlgorithmException{

        Hash prevHash = last.block.getHash();
        int num = size;
        return new Block(num, amount, prevHash);

    }

    /** Function returns blockchain size
     * 
     * @param takes nothing as parameter
     * @return int - the size of the blockchain
     */
    public int getSize(){
        return this.size;
    }

    /** adds the block taken as parameter to the list, 
     * throwing an IllegalArgumentException if this block cannot be added to the list 
     * (because it is invalid wrt the rest of the blocks).
     * 
     * @param Block blk that needs to be added to the blockchain
     * @throws IllegalArgumentException
     */
    public void append(Block blk) throws IllegalArgumentException{

        Node node = new Node(blk, null);
        this.last.next = node;
        this.last = node;
        this.size++;

    }

    /** Removes the last block from the chain, returning true.
     * If the chain only contains a single block, then removeLast does nothing and returns false.
     * 
     * @param takes nothing as parameter
     * @return boolean - true or false depending on how many blocks there are in the chain
     */
    public boolean removeLast(){
        
        Node curr = first; 

        if (curr.next == null){
            return false;
        } else {

            while (curr.next!=last){
                curr = curr.next;
            }

            curr.next = last.next;
            this.last = curr;
            this.size--;
            return true;

        }
        
    }

    /** returns the hash of the last block
     * 
     * @param takes nothing as parameter
     * @return Hash 
     */
    public Hash getHash(){
        return this.last.block.getHash();
    }

    /** walks the blockchain and ensures that its blocks are consistent and valid.
     * 
     * @return true or false depending on whether blocks are valid
     */
    public boolean isValidBlockChain(){

        // If no blocks, then the chain is valid
        if (first == null){
            return true;
        }

        Node curr = first;
        int anna = first.block.getAmount();
        int bob = 0;

        // Traversing throught the chain
        while (curr.next != null){

            int amt = curr.block.getAmount();

            if (amt < 0){
                anna -= amt;
                bob += amt;
            } else {
                anna += amt;
                bob -= amt;
            }

            // Checking if amounts are valid 
            if (anna < 0 || bob < 0){
                return false;
            }

            // Checking if the current block's hash is valid
            if (!curr.block.getHash().isValid()){
                return false;
            }

            // Checking if the current block's hash equals the value of the next block's previous block field
            if (!curr.block.getHash().equals(curr.next.block.getPrevHash())){
                return false;
            }

            // All these conditions are checked to ensure complete validity of the chain

        }

        return true;
        
    }

    /**Prints Bob and anna's balances
     * 
     * @param none
     * @returns void returns nothing
     */
    public void printBalances(){

        Node curr = first;
        int anna = first.block.getAmount();
        int bob = 0;

        while (curr.next != null){

            int amt = curr.block.getAmount();

            if (amt < 0){
                anna -= amt;
                bob += amt;
            } else {
                anna += amt;
                bob -= amt;
            }

        }

        System.out.println("Anna:" + anna + ", Bob:" + bob);

    }

    /** returns a string representation of the BlockChain
     *  which is simply the string representation of each of its blocks, earliest to latest, one per line
     * 
     * @param takes nothing as parameter
     * @returns String - the string representation of the blockcgain
     */
    public String toString(){

        String str = "";
        Node curr = first;

        while (curr!=null){

            Block b = curr.block;
            str += "Block " + b.getNum() + " (Amount: " + b.getAmount() + ", Nonce: " + b.getNonce() + ", prevHash: "
             + b.getPrevHash() + ", hash: " + b.getHash() + ")\n"; 

            curr = curr.next;
                    
        }

        return str;
        
    }

}
