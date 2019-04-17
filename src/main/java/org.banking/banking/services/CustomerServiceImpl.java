package org.banking.banking.services;

import org.banking.banking.entities.Customer;
import org.banking.banking.repositories.CustomerRepository;
import org.banking.banking.utils.BankingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Optional<Customer> createCustomer(String firstName, String lastName, String phone, String password) {
        if (!BankingUtils.isValidPhone(phone)) {
            return Optional.empty();
        } else if (customerRepository.findByPhone(phone).isPresent() ) {

            return Optional.empty();
        }

            String md5pass = DigestUtils.md5DigestAsHex(password.getBytes());
            Customer customer = new Customer(firstName, lastName, phone, md5pass);
            customerRepository.save(customer);
            return Optional.of(customer);
        }


    @Override
    public Optional<Customer> login(String phone, String password) {
        if (!BankingUtils.isValidPhone(phone)) {
            return Optional.empty();
        }
        if(!customerRepository.findByPhone(phone).isPresent()){
            return Optional.empty();
        }
        Customer customer = customerRepository.findByPhone(phone).get();
        String md5pass = DigestUtils.md5DigestAsHex(password.getBytes());
        if(customer.getPhone().equals(phone)&& customer.getPassword().equals(md5pass)){
            return Optional.of(customer);

        }else{
            return Optional.empty();
        }



    }

    @Override
    public Optional<Customer> findById(int customerId) {
        return customerRepository.findById(customerId);

    }

    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(c->customers.add(c));
      return customers;

    }
}
