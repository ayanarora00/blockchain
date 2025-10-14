package edu.grinnell.csc207.blockchain;

import java.security.NoSuchAlgorithmException;

/**
 * A linked list of hash-consistent blocks representing a ledger of
 * monetary transactions.
 */
public class BlockChain {

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

    public BlockChain(int initial) throws NoSuchAlgorithmException {

        Block firstblock = new Block(0, initial, null);
        Node node = new Node (firstblock, null);
        this.first = node;
        this.last = node;
        this.size = 1;
        
    }

    public Block mine(int amount) throws NoSuchAlgorithmException{

        Hash prevHash = last.block.getHash();
        int num = size;
        return new Block(num, amount, prevHash);

    }

    public int getSize(){
        return this.size;
    }

    public void append(Block blk) throws IllegalArgumentException{

        Node node = new Node(blk, null);
        this.last.next = node;
        this.last = node;
        this.size++;

    }

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

    public Hash getHash(){
        return this.last.block.getHash();
    }

    public boolean isValidBlockChain(){

        if (first == null){
            return true;
        }

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

            if (anna < 0 || bob < 0){
                return false;
            }

            if (!curr.block.getHash().isValid()){
                return false;
            }

            if (!curr.block.getHash().equals(curr.next.block.getPrevHash())){
                return false;
            }

        }

        return true;
        
    }

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
