package NewCoinPackage;

import NewCoinPackage.Transaction;
import HelperClasses.MerkleTree;

public class TransactionBlock {

  public Transaction[] trarray;
  public TransactionBlock previous;
  public MerkleTree Tree;
  public String trsummary;
  public String nonce;
  public String dgst;

  TransactionBlock(Transaction[] t) {
    this.trarray = t;
    this.previous = null;
    this.Tree = new MerkleTree(t);  // Assuming a constructor for MerkleTree that takes transactions
    this.trsummary = this.Tree.getRoot();  // Assuming MerkleTree has a method to get the root hash
    this.nonce = generateNonce();  // Assuming the nonce is generated here
    this.dgst = computeDigest();  // Assuming digest is computed from the block details
    
  }

  public boolean checkTransaction (Transaction[] t) {
    // You can add custom logic to check transactions, such as ensuring no double-spending
        for (Transaction tx : t) {
            if (tx == null || !verifyTransaction(tx)) {
                return false;
            }
        }
        return true;
  }
    private String generateNonce() {
       String tempNonce = "";
       String hash = "";
       do {
        tempNonce = String.valueOf(new Random().nextInt());
        String dataToHash = previous != null ? previous.dgst + trsummary + tempNonce : trsummary + tempNonce;
        hash = HelperClasses.sha256.encrypt(dataToHash);
        } while (!hash.startsWith("0000")); 

        return tempNonce;
    
    }

    private String computeDigest() {
        String dataToHash = previous != null ? previous.dgst + trsummary + nonce : trsummary + nonce;
        return HelperClasses.sha256.encrypt(dataToHash);
    }

    private boolean verifyTransaction(Transaction tx) {
        if(tx = null && tx.coinID = null && tx.Source = null && tx.Destination = null) {
            return false;
        }
        

    TransactionBlock srcBlock = t.coinsrc_block;
    boolean isValidSource = false;

    // Check if the coin's source block is valid by finding the source and destination relationship
    if (srcBlock != null) {
        for (Transaction tx : srcBlock.trarray) {
            if (tx != null && tx.coinID.equals(t.coinID)) {
                if (tx.Destination == t.Source) {
                    isValidSource = true;
                    break;
                }
            }
        }
        if (!isValidSource) {
            return false;  // Invalid source for the transaction
        }
    }

    // Step 2: Check for double-spending in the current block
    int coinIDCount = 0;
    for (Transaction tx : this.trarray) {
        if (tx != null && tx.coinID.equals(t.coinID)) {
            coinIDCount++;
            if (coinIDCount > 1) {
                return false;  // Double-spending detected within the current block
            }
        }
    }

    // Step 3: Check for double-spending in previous blocks
    TransactionBlock currentBlock = this.previous;

    while (currentBlock != null && currentBlock != srcBlock) {
        for (Transaction tx : currentBlock.trarray) {
            if (tx != null && tx.coinID.equals(t.coinID)) {
                return false;  // Double-spending detected in previous blocks
            }
        }
        currentBlock = currentBlock.previous;
    }


        return true;
    }

}
