package org.banking.banking.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Getter
@Setter
@Entity
public class Withdrawal extends Transaction {
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
