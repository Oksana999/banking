package org.banking.banking.repositories;

import org.banking.banking.entities.ChangingCurrency;
import org.banking.banking.entities.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChangeMoneyRepository extends CrudRepository<ChangingCurrency, Integer> {

    List<ChangingCurrency> findAllByAccountFromChange_Customer(Customer account_customer);
}
