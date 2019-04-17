package org.banking.banking.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
@Getter
@Setter
@MappedSuperclass
public abstract class Transaction {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private LocalDateTime dateTime;
    @Column
    private double value;
}
