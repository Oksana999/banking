package org.banking.banking.repositories;

import org.banking.banking.entities.Customer;
import org.banking.banking.entities.Replenishment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReplenishmentRepository extends CrudRepository<Replenishment, Integer> {

    List<Replenishment> findAllByAccount_Customer(Customer account_customer);
}
