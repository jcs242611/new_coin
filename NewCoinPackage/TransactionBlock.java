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
// A method to generate a nonce
    private String generateNonce() {
        // Placeholder for nonce generation (it could involve proof of work)
       // return "12345";  // Replace with actual nonce generation logic
       String tempNonce = "";
    String hash = "";
    do {
        tempNonce = String.valueOf(new Random().nextInt());  // Random nonce
        String dataToHash = previous != null ? previous.dgst + trsummary + tempNonce : trsummary + tempNonce;
        hash = HelperClasses.sha256.encrypt(dataToHash);
    } while (!hash.startsWith("0000"));  // Example of a difficulty check, adjust as necessary
    return tempNonce;
    
    }

    // Compute the digest for the block (e.g., a hash of the block data)
    private String computeDigest() {
        String dataToHash = previous != null ? previous.dgst + trsummary + nonce : trsummary + nonce;
        return HelperClasses.sha256.encrypt(dataToHash);  // Assuming the sha256 class has an encrypt method
    }

    // Verify individual transaction (can be extended to check signatures, coin sources, etc.)
    private boolean verifyTransaction(Transaction tx) {
        // Placeholder for custom transaction verification logic
        return true;
    }

}
