package NewCoinPackage;

import NewCoinPackage.TransactionBlock;

public class BlockChain_Honest {

  public int tr_count;
  public static final String start_string = "NewCoin";
  public TransactionBlock lastBlock;

  public void InsertBlock_Honest (TransactionBlock newBlock) {
    if (newBlock.checkTransaction(newBlock.trarray)) {
            newBlock.previous = lastBlock;
            lastBlock = newBlock;
        } else {
            System.out.println("Block contains invalid transactions.");
        }

  }
}
