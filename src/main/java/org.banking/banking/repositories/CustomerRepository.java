package org.banking.banking.repositories;

import org.banking.banking.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    //Customer findByPhone(String phone);


    Optional<Customer> findByPhone(String phone);
}
