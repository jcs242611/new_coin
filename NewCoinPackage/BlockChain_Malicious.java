package NewCoinPackage;

import NewCoinPackage.TransactionBlock;

public class BlockChain_Malicious {

  public int tr_count;
  public static final String start_string = "NewCoin";
  public TransactionBlock[] lastBlocksList;

  public static boolean checkTransactionBlock (TransactionBlock tB) {
    return tB.checkTransaction(tB.trarray);

  }

  public TransactionBlock FindLongestValidChain () {
    TransactionBlock longestChain = null;
        int maxLength = 0;

        for (TransactionBlock block : lastBlocksList) {
            int length = 0;
            TransactionBlock current = block;
            while (current != null) {
                if (!checkTransactionBlock(current)) {
                    break;
                }
                length++;
                current = current.previous;
            }

            if (length > maxLength) {
                maxLength = length;
                longestChain = block;
            }
        }

     return longestChain;

  }

  public void InsertBlock_Malicious (TransactionBlock newBlock) {
    for (int i = 0; i < lastBlocksList.length; i++) {
            if (lastBlocksList[i] == null || !checkTransactionBlock(lastBlocksList[i])) {
                newBlock.previous = lastBlocksList[i];
                lastBlocksList[i] = newBlock;
                break;
            }
        }

  }
}
