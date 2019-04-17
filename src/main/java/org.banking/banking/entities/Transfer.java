package org.banking.banking.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="transfers")
public class Transfer extends Transaction{
    @ManyToOne
    @JoinColumn(name="account_from")
    private Account accountFrom;
    @ManyToOne
    @JoinColumn(name="account_to")
    private Account accountTo;

    public Transfer() {
    }

    public Transfer(Account accountFrom) {
        this.accountFrom = accountFrom;
    }
    
}

