package NewCoinPackage;

import NewCoinPackage.TransactionBlock;
import NewCoinPackage.Members;

public class Transaction {

  public String coinID;
  public Members Source;
  public Members Destination;
  public TransactionBlock coinsrc_block;
  public Transaction next;

}
