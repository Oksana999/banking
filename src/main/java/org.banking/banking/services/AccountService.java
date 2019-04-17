package org.banking.banking.services;

import org.banking.banking.entities.Account;
import org.banking.banking.entities.Transfer;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    public Optional<Account> createAccount(String name, String currency, String customerId);

    public List<Account> getAccounts();

    public Optional<Account> replenishment(String accountId, String amount);

    public Optional<Account> withdrawMoney(String accountId, String amount);

    public Optional<Account> transferringMoney(String accountIdFrom, String accountIdTo, String amount);

    public Optional<Account> changingCurrency(String accountIdToChange,
                                              String accountIdFromChange, String amount);


}
