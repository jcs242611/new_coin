package NewCoinPackage;

import NewCoinPackage.TransactionBlock;
import NewCoinPackage.Members;

public class Transaction {

  public String coinID;
  public Members Source;
  public Members Destination;
  public TransactionBlock coinsrc_block;
  public Transaction next;
  public Transaction prev;

  public Transaction(){}   //default constructor

  public Transaction(Transaction tra){
    
    if(tra!=null){
      this.coinID = tra.coinID;
      this.Source = tra.Source;
      this.Destination  = tra.Destination;
      this.coinsrc_block = tra.coinsrc_block;
      this.next = tra.next;
      this.prev = tra.prev;

    }
  }

  @Override
public boolean equals(Object o) {
    // Check if the object is compared with itself
    if (this == o) {
        return true;
    }

    // Check if the object is null or of a different class
    if (o == null || getClass() != o.getClass()) {
        return false;
    }

    // Cast the object to a Transaction type
    Transaction c = (Transaction) o;

    // Check if coinID, Source UID, Destination UID, and coinsrc_block are all equal
    return this.coinID.equals(c.coinID) &&
           this.Source.UID.equals(c.Source.UID) &&
           this.Destination.UID.equals(c.Destination.UID) &&
           this.coinsrc_block == c.coinsrc_block;
}

}
