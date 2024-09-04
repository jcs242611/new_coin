package NewCoinPackage;

import NewCoinPackage.TransactionQueue;
import NewCoinPackage.BlockChain_Malicious;
import NewCoinPackage.Members;

public class NewCoin_Malicious {

  public TransactionQueue pendingTransactions;
  public BlockChain_Malicious bChain;
  public Members[] memberlist;
  public String latestCoinID;
}
