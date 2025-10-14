package edu.grinnell.csc207.blockchain;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * The main driver for the block chain program.
 */
public class BlockChainDriver {
   
    /**
     * The main entry point for the program.
     * @param args the command-line arguments
     * @throws NoSuchAlgorithmException 
     *
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        
        if (args.length != 1){
            System.out.println("Usage java BlockChainDriver <Initial Amount>");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        
        String arg = args[0];
        int initial = Integer.parseInt(arg);
        BlockChain blockchain = new BlockChain(initial);

        
        
        while (true){

            System.out.print(blockchain.toString());
            System.out.print("Command? ");

            if (!scanner.hasNext()){
                break;
            }

            String command = scanner.next();

            if (command.equals("mine")){

                

                System.out.print("Amount transferred? ");
                String command2 = scanner.next();
                int amt = Integer.parseInt(command2);
                Block block = blockchain.mine(amt);
                System.out.println("amount = " + block.getAmount() + ", nonce = " + block.getNonce());
                System.out.println();

            } else if (command.equals("append")) {

    

                System.out.print("Amount transferred? ");
                String command2 = scanner.next();
                int amt = Integer.parseInt(command2);
                
                System.out.print("Amount transferred? ");
                String command3 = scanner.next();
                Long nonce = Long.parseLong(command3);

                System.out.println();

                Block block = new Block(blockchain.getSize(), amt, blockchain.getHash(), nonce);

                blockchain.append(block);


            } else if (command.equals("remove")) {

            } else if (command.equals("check")) {

                if (blockchain.isValidBlockChain()){
                    System.out.println("Chain is valid!");
                } else {
                    System.out.println("Chain is invalid!");
                }

                System.out.println();

            } else if (command.equals("report")){

                blockchain.printBalances();
                System.out.println();

            } else if (command.equals("help")){

                System.out.print("Valid commands:\r\n" + //
                                        "    mine: discovers the nonce for a given transaction\r\n" + 
                                        "    append: appends a new block onto the end of the chain\r\n" + 
                                        "    remove: removes the last block from the end of the chain\r\n" + 
                                        "    check: checks that the block chain is valid\r\n" + 
                                        "    report: reports the balances of Alice and Bob\r\n" +
                                        "    help: prints this list of commands\r\n" +
                                        "    quit: quits the program\n");

                System.out.println();


            } else if (command.equals("quit")){

                break;

            }




        }






    }  
}
