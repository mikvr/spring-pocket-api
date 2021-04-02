package com.rnd.corp.springpocketapi.domain.finance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "finance")
public class Finance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private float balance;
    private float income;
    private float outcome;
    private String userId;


    public void updateState(final float amount) {
        this.balance += amount;
        if (amount >= 0) {
            this.income +=  amount;
        } else {
            this.outcome += -amount;
        }
    }
}
