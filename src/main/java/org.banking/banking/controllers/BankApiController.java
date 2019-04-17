package org.banking.banking.controllers;

import org.banking.banking.entities.Account;
import org.banking.banking.entities.Customer;
import org.banking.banking.entities.Transaction;
import org.banking.banking.entities.Transfer;
import org.banking.banking.repositories.AccountRepository;
import org.banking.banking.services.AccountService;
import org.banking.banking.services.CustomerService;
import org.banking.banking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class BankApiController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/")
    //  @ResponseBody
    public String index() {
        return "index";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/completeRegistration")
    public String completeRegistration(Model model,
                                       @RequestParam String firstName,
                                       @RequestParam String lastName,
                                       @RequestParam String phone,
                                       @RequestParam String password) {
        Optional<Customer> customer = customerService.createCustomer(firstName, lastName, phone, password);

        if (customer.isPresent()) {
            return "successRegistration";
        } else {
            model.addAttribute("message", "Customer already exists or " +
                    "phone not valid");
            return "error";
        }
    }

    @PostMapping("/checkLogin")
    public String checkLogin(Model model,
                             @RequestParam String phone,
                             @RequestParam String password) {
        Optional<Customer> customerOpt = customerService.login(phone, password);

        if (customerOpt.isPresent()) {
            model.addAttribute("customer", customerOpt.get());

            List<Transaction> transactions = transactionService.getAllTransactions(customerOpt.get());

            model.addAttribute("transactions", transactions);

            List<Customer> customers = customerService.findAllCustomers();
            model.addAttribute("customers", customers);
            return "personalService";
        } else {
            model.addAttribute("message", "Login Failed");
            return "error";

        }
    }

    @PostMapping("/createAccount")
    public String createAccount(@RequestParam String name,
                                @RequestParam String currency,
                                @RequestParam String customerId) {
        Optional<Account> account = accountService.createAccount(name, currency, customerId);
        return "";
    }


    @PostMapping("/replenishment")
    public String replenishment(@RequestParam String accountId,
                                @RequestParam String amount) {

        Optional<Account> account = accountService.replenishment(accountId, amount);
        return "";
    }

    @PostMapping("/withdrawMoney")
    @ResponseBody
    public String withdrawMoney(@RequestParam String accountId,
                                @RequestParam String amount) {
        Optional<Account> account = accountService.withdrawMoney(accountId, amount);
        if(!account.isPresent()){
            return "Not enough money on your account !";
        }
        return "Success !";
    }


     @PostMapping("/transferringMoney")
     @ResponseBody
     public String transferMoney (@RequestParam String accountIdFrom,
                                  @RequestParam String accountIdTo,
                                  @RequestParam String amount) {
          Optional<Account> accountFrom = accountRepository.findById(Integer.valueOf(accountIdFrom));
          Optional<Account> accountTo = accountRepository.findById(Integer.valueOf(accountIdTo));
//         Optional<Account> accountFrom = accountService.transferringMoney(accountIdFrom, amount);
//         Optional<Account> accountTo = accountService.transferringMoney(accountIdTo, amount);

         if (!accountFrom.isPresent() && !accountTo.isPresent()) {
             return "User not found";
         }
         if (accountFrom.get().getAmount() < Integer.valueOf(amount)) {
             return "Not enough money on your account !";
         }

         accountService.transferringMoney(accountIdFrom, accountIdTo, amount);
         return "Success !";
     }

    @PostMapping("/changingCurrency")
    @ResponseBody
    public String changingCurrency(@RequestParam String accountIdToChange,
                                   @RequestParam String accountIdFromChange,
                                   @RequestParam String amount){
        Optional<Account> accountToChange = accountService.changingCurrency(accountIdToChange, accountIdFromChange, amount);
       // Optional<Account> accountFromChange = accountService.changingCurrency(accountIdToChange, accountIdFromChange, amount));

      //  while (accountToChange.isPresent() && accountFromChange.isPresent()){

        if (!accountToChange.isPresent() ){
            return "Account not found";
        }
        if (accountToChange.get().getAmount() < Integer.valueOf(amount)) {
            return "Not enough money on your account !";
        }


        return "Success !";
    }
}


        // return "";






