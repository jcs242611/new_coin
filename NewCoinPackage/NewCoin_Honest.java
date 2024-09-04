package NewCoinPackage;

import NewCoinPackage.TransactionQueue;
import NewCoinPackage.BlockChain_Honest;
import NewCoinPackage.Members;

public class NewCoin_Honest {

  public TransactionQueue pendingTransactions;
  public BlockChain_Honest bChain;
  public Members[] memberlist;
  public String latestCoinID;
}
