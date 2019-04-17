package org.banking.banking.repositories;

import org.banking.banking.entities.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {


}
