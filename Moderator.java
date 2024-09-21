import NewCoinPackage.Members;
import NewCoinPackage.NewCoin_Honest;
import NewCoinPackage.NewCoin_Malicious;

public class Moderator
 {

  public NewCoin_Honest Newobj;
  // public NewCoin_Malicious Newobj;
  String[] issuedCoins; 

  public void initializeCoin(Members[] members, int tr_count) {
    // Initialize the coin issuing process
        int totalCoins = members.length * tr_count; // Total number of coins to issue
        issuedCoins = new String[totalCoins]; // Array to store the unique coins

        // Generate unique coins (6-digit unique numbers)
        for (int i = 0; i < totalCoins; i++) {
            issuedCoins[i] = String.format("%06d", 100000 + i); // Coins starting from 100000
        }

        // Initialize a transaction queue
        TransactionQueue transactionQueue = new TransactionQueue();

        // Distribute coins to each member
        int coinIndex = 0;
        for (Members member : members) {
            // Each member receives 'tr_count' number of coins
            for (int i = 0; i < tr_count; i++) {
                String coinID = issuedCoins[coinIndex];
                coinIndex++;

                // Create a transaction from the moderator to the member
                Transaction genesisTransaction = new Transaction(coinID, "Moderator", member.UID, null);

                // Add the transaction to the transaction queue
                transactionQueue.addTransaction(genesisTransaction);

                // Add the coin to the member's balance
                member.receiveCoin(coinID, genesisTransaction);
            }
        }

        // After distributing, the transactions can be added to the blockchain (mined by the miner)
        Newobj.processTransactions(transactionQueue);
    }

}
