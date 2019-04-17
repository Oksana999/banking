package org.banking.banking.repositories;

import org.banking.banking.entities.Account;
import org.banking.banking.entities.Customer;
import org.banking.banking.entities.Transfer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TransferRepository extends CrudRepository<Transfer, Integer> {

    List<Transfer> findAllByAccountFrom_Customer(Customer account_customer);
    List<Transfer> findAllByAccountTo_Customer(Customer account_customer);
 //   List<Transfer> findAllByAccountTo(Customer account_customer);

   // List<Transfer> getAllTransfersByAccountFromAndAccountTo(Customer account_from, Customer account_to);
   // List<Transfer>getAllTransfersByAccount_Customer(Customer accountFrom, Customer accountTo, amount);

}
