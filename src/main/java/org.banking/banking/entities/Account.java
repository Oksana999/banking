package org.banking.banking.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table (name = "accounts")
public class Account {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String name;
    @ManyToOne
    @JoinColumn (name = "customer_id")
    private Customer customer;
    @Column
    @Enumerated(value = EnumType.STRING)
    private Currency currency;
    @Column
    private double amount;
}
