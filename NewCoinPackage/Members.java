package NewCoinPackage;

import java.util.*;
import HelperClasses.Pair;
import NewCoinPackage.Transaction;
import NewCoinPackage.TransactionBlock;
import NewCoinPackage.NewCoin_Honest;
import NewCoinPackage.NewCoin_Malicious;
import NewCoinPackage.MissingTransactionException;

public class Members
 {

  public String UID;
  public List<Pair<String, TransactionBlock>> mycoins;
  public Transaction[] in_process_trans;

  public void initiateCoinsend(String destUID, NewCoin_Honest Newobj) {
     Transaction transaction = new Transaction("coinID", this, getMemberByUID(destUID, Newobj.memberlist), null);
        Newobj.pendingTransactions.AddTransactions(transaction);

  }

  public void initiateCoinsend(String destUID, NewCoin_Malicious Newobj) {
    Transaction transaction = new Transaction("coinID", this, getMemberByUID(destUID, Newobj.memberlist), null);
        Newobj.pendingTransactions.AddTransactions(transaction);

  }

  public Pair<List<Pair<String, String>>, List<Pair<String, String>>> finalizeCoinsend (Transaction tobj, NewCoin_Honest NewObj) throws MissingTransactionException {
    List<Pair<String, String>> inBlock = new ArrayList<>();
    List<Pair<String, String>> notInBlock = new ArrayList<>();

    boolean found = false;
        for (TransactionBlock block : NewObj.bChain.getBlocks()) {  // Assuming bChain has a method to return blocks
            for (Transaction tx : block.trarray) {
                if (tx.coinID.equals(tobj.coinID)) {
                    found = true;
                    inBlock.add(new Pair<>(tx.coinID, block.dgst));
                    break;
                }
            }
            if (!found) {
                notInBlock.add(new Pair<>(tobj.coinID, block.dgst));
            }
        }

        if (!found) {
            throw new MissingTransactionException();
        }

        return new Pair<>(inBlock, notInBlock);

  }

  public void MineCoin(NewCoin_Honest NewObj) {
    Transaction[] transactions = new Transaction[NewObj.bChain.tr_count];
    for (int i = 0; i < NewObj.bChain.tr_count; i++) {
      transactions[i] = NewObj.pendingTransactions.RemoveTransaction();
       }

    TransactionBlock newBlock = new TransactionBlock(transactions);
    NewObj.bChain.InsertBlock_Honest(newBlock);

  }  

  public void MineCoin(NewCoin_Malicious NewObj) {
    Transaction[] transactions = new Transaction[NewObj.bChain.tr_count];
    for (int i = 0; i < NewObj.bChain.tr_count; i++) {
       transactions[i] = NewObj.pendingTransactions.RemoveTransaction();
        }

    TransactionBlock newBlock = new TransactionBlock(transactions);
    NewObj.bChain.InsertBlock_Malicious(newBlock);

  }  

// Helper method to get member by UID
    private Members getMemberByUID(String UID, Members[] memberList) {
        for (Members member : memberList) {
            if (member.UID.equals(UID)) {
                return member;
            }
        }
        return null;
    }


}
