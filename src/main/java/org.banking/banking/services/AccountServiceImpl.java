package org.banking.banking.services;

import org.banking.banking.entities.*;
import org.banking.banking.repositories.AccountRepository;
import org.banking.banking.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransferRepository transferRepository;

    @Value("${exchange.RUR.to.USD}")
    private String exchangeRateRURToUSD;
    @Value("${exchange.RUR.to.EUR}")
    private String exchangeRateRURToEUR;
    @Value("${exchange.USD.to.EUR}")
    private String exchangeRateUSDToEUR;


    @Override
    public Optional<Account> createAccount(String name, String currency, String customerId) {
        // left side should be Optional
        // check if customer present
        Optional<Customer> customer = customerService.findById(Integer.valueOf(customerId));
        //  Customer customer = customerService.findById(Integer.valueOf(customerId)).get();
        if (!customer.isPresent()) {
            return Optional.empty();
        } else {
            Account account = new Account();
            account.setName(name);
            account.setCurrency(Currency.valueOf(currency));
            account.setCustomer(customer.get());
            accountRepository.save(account);
            return Optional.of(account);

        }
    }

    @Override
    public List<Account> getAccounts() {
        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);
        return accounts;
    }

    @Override
    public Optional<Account> replenishment(String accountId, String amount) {

        Optional<Account> accountOpt = accountRepository.findById(Integer.valueOf(accountId));
        if(!accountOpt.isPresent()){
           return Optional.empty();
        }
       Account account = accountOpt.get();
        double addAmount = Double.valueOf(amount);
        account.setAmount(account.getAmount() + addAmount);
        accountRepository.save(account);
        Replenishment replenishment = new Replenishment();
        replenishment.setAccount(account);
        replenishment.setDateTime(LocalDateTime.now());
        replenishment.setValue(addAmount);
        transactionService.saveReplenishment(replenishment);
        return accountOpt;
    }

    @Override
    public Optional<Account> withdrawMoney(String accountId, String amount) {

        Optional<Account> accountOpt1 = accountRepository.findById(Integer.valueOf(accountId));
        if(!accountOpt1.isPresent()) {
            return Optional.empty();
        }
        Account account = accountOpt1.get();
        double withdrawAmount = Double.valueOf(amount);
        if(account.getAmount() >= Double.valueOf(amount)){
            account.setAmount((account.getAmount() - withdrawAmount));
            accountRepository.save(account);

            Withdrawal withdrawal =  new Withdrawal();
            withdrawal.setAccount(account);
            withdrawal.setDateTime(LocalDateTime.now());
            withdrawal.setValue(withdrawAmount);
            transactionService.saveWithdrawal(withdrawal);
        }
        return accountOpt1;
    }



    @Override
    public Optional<Account> transferringMoney(String accountIdFrom ,String accountIdTo, String amount) {
        Optional<Account> accountFromOptional = accountRepository.findById(Integer.valueOf(accountIdFrom));
        Optional<Account> accountToOptional = accountRepository.findById(Integer.valueOf(accountIdTo));
        if (!accountFromOptional.isPresent() || !accountToOptional.isPresent()) {
            return Optional.empty();
        }

            Account accountFrom = accountFromOptional.get();
            Account accountTo = accountToOptional.get();

            accountFrom.setAmount(accountFrom.getAmount() - Double.valueOf(amount));
            accountRepository.save(accountFrom);

            accountTo.setAmount(accountTo.getAmount() + Double.valueOf(amount));
            accountRepository.save(accountTo);

            Transfer transfer = new Transfer();
            transfer.setAccountTo(accountTo);
            transfer.setAccountFrom(accountFrom);
            transfer.setDateTime(LocalDateTime.now());
            transfer.setValue(Double.valueOf(amount));

            transactionService.saveTransferring(transfer);

        return accountFromOptional;
    }

    @Override
    public Optional<Account> changingCurrency(String accountIdToChange,
                                              String accountIdFromChange, String amount) {
     //   double exchangeRateRURToUSD = 50.45;

        Optional<Account> accountOptToChange = accountRepository.findById(Integer.valueOf(accountIdToChange));
        Optional<Account> accountOptAfter = accountRepository.findById(Integer.valueOf(accountIdFromChange));

       if(!accountOptToChange.isPresent() || !accountOptAfter.isPresent()) {
           return Optional.empty();
       }
            Account accountToChange = accountOptToChange.get();
        Account accountAfterChange = accountOptAfter.get();
         if(accountToChange.getCurrency().equals(Currency.RUR) &&
             accountAfterChange.getCurrency().equals(Currency.USD)) {
             accountToChange.setAmount(accountOptToChange.get().getAmount() - Double.valueOf(amount));
             accountAfterChange.setAmount(accountOptAfter.get().getAmount() + Double.valueOf(amount) /Double.valueOf(exchangeRateRURToUSD));
        }else if( accountToChange.getCurrency().equals(Currency.USD) &&
                   accountAfterChange.getCurrency().equals(Currency.RUR)) {
             accountToChange.setAmount(accountOptToChange.get().getAmount() - Double.valueOf(amount));
             accountAfterChange.setAmount(accountOptAfter.get().getAmount() + Double.valueOf(amount) * Double.valueOf(exchangeRateRURToUSD));

         }else if (accountToChange.getCurrency().equals(Currency.RUR)&&
                 accountAfterChange.getCurrency().equals(Currency.EUR)){
             accountToChange.setAmount(accountOptToChange.get().getAmount() - Double.valueOf(amount));
             accountAfterChange.setAmount(accountOptAfter.get().getAmount() + Double.valueOf(amount) / Double.valueOf(exchangeRateRURToEUR));
         }else if( accountToChange.getCurrency().equals(Currency.EUR) &&
            accountAfterChange.getCurrency().equals(Currency.RUR)) {
        accountToChange.setAmount(accountOptToChange.get().getAmount() - Double.valueOf(amount));
        accountAfterChange.setAmount(accountOptAfter.get().getAmount() + Double.valueOf(amount) * Double.valueOf(exchangeRateRURToEUR));

    }else if (accountToChange.getCurrency().equals(Currency.USD)&&
                 accountAfterChange.getCurrency().equals(Currency.EUR)){
             accountToChange.setAmount(accountOptToChange.get().getAmount() - Double.valueOf(amount));
             accountAfterChange.setAmount(accountOptAfter.get().getAmount() + Double.valueOf(amount) * Double.valueOf(exchangeRateUSDToEUR));
         }else if( accountToChange.getCurrency().equals(Currency.EUR) &&
                 accountAfterChange.getCurrency().equals(Currency.USD)) {
             accountToChange.setAmount(accountOptToChange.get().getAmount() - Double.valueOf(amount));
             accountAfterChange.setAmount(accountOptAfter.get().getAmount() + Double.valueOf(amount) / Double.valueOf(exchangeRateUSDToEUR));

         }

        accountRepository.save(accountToChange);
        accountRepository.save(accountAfterChange);

        ChangingCurrency changingCurrency = new ChangingCurrency();
        changingCurrency.setAccountToChange(accountToChange);
        changingCurrency.setAccountFromChange(accountAfterChange);
        changingCurrency.setDateTime(LocalDateTime.now());
        changingCurrency.setValue(Double.valueOf(amount));

        transactionService.saveChangingCurrency(changingCurrency);


        return accountOptToChange;
    }
}
