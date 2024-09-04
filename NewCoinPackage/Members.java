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

  }

  public void initiateCoinsend(String destUID, NewCoin_Malicious Newobj) {

  }

  public Pair<List<Pair<String, String>>, List<Pair<String, String>>> finalizeCoinsend (Transaction tobj, NewCoin_Honest NewObj) throws MissingTransactionException {

  }

  public void MineCoin(NewCoin_Honest NewObj) {

  }  

  public void MineCoin(NewCoin_Malicious NewObj) {

  }  
}
