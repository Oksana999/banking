package org.banking.banking.services;

import org.banking.banking.entities.*;
import org.banking.banking.repositories.ChangeMoneyRepository;
import org.banking.banking.repositories.ReplenishmentRepository;
import org.banking.banking.repositories.TransferRepository;
import org.banking.banking.repositories.WithdrawlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private ReplenishmentRepository replenishmentRepository;
    @Autowired
    private WithdrawlRepository withdrawlRepository;
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private ChangeMoneyRepository changeMoneyRepository;


    @Override
    public void saveReplenishment(Replenishment replenishment) {
        replenishmentRepository.save(replenishment);

    }

    @Override
    public void saveWithdrawal(Withdrawal withdrawal) {
        withdrawlRepository.save(withdrawal);

    }
    @Override
    public void saveTransferring(Transfer transfer) {
        transferRepository.save(transfer);

    }

    @Override
    public void saveChangingCurrency(ChangingCurrency changingCurrency) {
        changeMoneyRepository.save(changingCurrency);

    }


    @Override
    public List<Transaction> getAllTransactions(Customer customer) {
        return  getAllTransactionsByCustomer(customer);
    }

    @Override
    public Map<Customer, List<Transaction>> getAllTransactionsMap(Customer customer) {
        Map<Customer, List<Transaction>> customerListMap = new HashMap<>();
        customerListMap.put(customer,  getAllTransactionsByCustomer(customer) );
        return customerListMap;
    }

    private List<Transaction> getAllTransactionsByCustomer (Customer customer){
        List<Transaction> transactions = new ArrayList<>();
        transactions.addAll(replenishmentRepository.findAllByAccount_Customer(customer));
        transactions.sort(Comparator.comparing(Transaction::getDateTime).reversed());

        transactions.addAll(withdrawlRepository.getAllByAccount_Customer(customer));
        transactions.sort(Comparator.comparing(Transaction::getDateTime).reversed());

        transactions.addAll(transferRepository.findAllByAccountFrom_Customer(customer));
        transactions.sort(Comparator.comparing(Transaction::getDateTime).reversed());

        transactions.addAll(transferRepository.findAllByAccountTo_Customer(customer));
        transactions.sort(Comparator.comparing(Transaction::getDateTime).reversed());

        transactions.addAll(changeMoneyRepository.findAllByAccountFromChange_Customer(customer));
        transactions.sort(Comparator.comparing(Transaction::getDateTime).reversed());
        return transactions;

    }



}
