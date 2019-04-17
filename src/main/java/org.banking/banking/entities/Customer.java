package org.banking.banking.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue
    private int id;
    @Column (name = "first_name")
    private String firstName;
    @Column (name = "last_name")
    private String lastName;
    @Column (name = "phone")
    private String phone;
    @OneToMany (mappedBy = "customer")
    private List<Account> accounts;
    @Column
    private String password;

    public Customer (){}

    public Customer(String firstName, String lastName, String phone, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.password = password;
    }
}
