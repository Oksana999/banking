package org.banking.banking.services;

import org.banking.banking.entities.*;

import java.util.List;
import java.util.Map;

public interface TransactionService {
    public void saveReplenishment(Replenishment replenishment);
    public  void saveWithdrawal(Withdrawal withdrawal);
    public void saveTransferring(Transfer transfer);
    public void saveChangingCurrency(ChangingCurrency changingCurrency);
    List<Transaction> getAllTransactions(Customer customer);

    Map<Customer, List<Transaction>> getAllTransactionsMap (Customer customer);

}
