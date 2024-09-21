package NewCoinPackage;

import NewCoinPackage.Transaction;
import NewCoinPackage.EmptyQueueException;

public class TransactionQueue {

  public Transaction firstTransaction;
  public Transaction lastTransaction;
  public int numTransactions=0;

  public void AddTransactions (Transaction transaction) {
    if (numTransactions == 0) {
            firstTransaction = transaction;
            lastTransaction = transaction;
        } else {
            lastTransaction.next = transaction;  // Assuming there's a `next` field in Transaction
            lastTransaction = transaction;
        }
        numTransactions++;
  }
  
  public Transaction RemoveTransaction () throws EmptyQueueException {
    if (numTransactions == 0) {
            throw new EmptyQueueException();
        }
        Transaction removedTransaction = firstTransaction;
        firstTransaction = firstTransaction.next;
        numTransactions--;
        return removedTransaction;
  }

  public int size() {
    return numTransactions;
  }
}
