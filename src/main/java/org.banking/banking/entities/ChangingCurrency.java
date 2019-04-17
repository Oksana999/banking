package org.banking.banking.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
@Getter
@Setter
public class ChangingCurrency extends Transaction{
    @ManyToOne
    @JoinColumn(name="account_to_change")
    private Account accountToChange;
    @ManyToOne
    @JoinColumn(name="account_from_change")
    private Account accountFromChange;


}
