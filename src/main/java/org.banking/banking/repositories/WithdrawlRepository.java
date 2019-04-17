package org.banking.banking.repositories;

import org.banking.banking.entities.Customer;
import org.banking.banking.entities.Withdrawal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WithdrawlRepository extends CrudRepository<Withdrawal, Integer > {
    List<Withdrawal> getAllByAccount_Customer(Customer account_customer);
}

