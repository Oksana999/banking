package org.banking.banking.services;

import org.banking.banking.entities.Customer;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    public Optional<Customer> createCustomer(String firstName,
                                             String lastName,
                                             String phone,
                                             String password);

    public Optional<Customer> login(String phone, String password);

    public Optional<Customer> findById (int customerId);

    public List<Customer> findAllCustomers ();
}
