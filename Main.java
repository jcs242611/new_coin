import NewCoinPackage.*;
import HelperClasses.*;

public class Main {

    public static void main(String[] args) {
        // Initialize members
        Members member1 = new Members();
        member1.UID = "Buyer1";

        Members member2 = new Members();
        member2.UID = "Seller1";

        Members miner = new Members();
        miner.UID = "Miner1";

        // Initialize NewCoin_Honest system
        NewCoin_Honest newCoinSystem = new NewCoin_Honest();
        newCoinSystem.bChain = new BlockChain_Honest();
        newCoinSystem.pendingTransactions = new TransactionQueue();

        // Moderator initializes the coins
        Moderator mod = new Moderator();
        Members[] members = { member1, member2, miner };
        mod.initializeCoin(members, 4);  // Give 4 coins to each member

        // Buyer sends a coin to the Seller
        System.out.println("Buyer1 sending coin to Seller1...");
        member1.initiateCoinsend(member2.UID, newCoinSystem);

        // Miner mines the transaction and adds it to the blockchain
        System.out.println("Miner1 mining...");
        miner.MineCoin(newCoinSystem);

        // Finalize the transaction, buyer proves to seller
        try {
            System.out.println("Finalizing transaction...");
            Transaction tobj = newCoinSystem.pendingTransactions.firstTransaction;
            Pair<List<Pair<String, String>>, List<Pair<String, String>>> proof = member1.finalizeCoinsend(tobj, newCoinSystem);

            // Proof of membership is verified by Seller1
            System.out.println("Seller1 verifies transaction...");
            System.out.println("Proof received: " + proof);
        } catch (MissingTransactionException e) {
            System.out.println("Transaction missing!");
        }

        // Print the blockchain details
        System.out.println("Blockchain after transactions:");
        TransactionBlock currentBlock = newCoinSystem.bChain.lastBlock;
        while (currentBlock != null) {
            System.out.println("Block: " + currentBlock.dgst + " with transactions:");
            for (Transaction t : currentBlock.trarray) {
                System.out.println("Transaction: " + t.coinID + " from " + t.Source.UID + " to " + t.Destination.UID);
            }
            currentBlock = currentBlock.previous;
        }
    }
}
