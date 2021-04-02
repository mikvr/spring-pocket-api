package com.rnd.corp.springpocketapi.domain.finance;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.rnd.corp.springpocketapi.domain.MonthlyTransactionId;
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
@Table(name = "monthly_transaction")
public class MonthlyTransaction {

    @EmbeddedId
    private MonthlyTransactionId id;

    private float balance;
    private float income;
    private float outcome;

    @Column(name = "finance_id", insertable = false, updatable = false)
    private int financeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "finance_id", insertable = false, updatable = false)
    private Finance finance;

    public MonthlyTransaction(MonthlyTransactionId id, float amount) {
        this.id = id;
        this.balance = amount;
        if (balance >= 0) {
            this.income = balance;
            this.outcome = 0.00f;
        } else {
            this.income = 0.00f;
            this.outcome = -amount;
        }
    }

    public void updateState(final float amount) {
        this.balance += amount;
        if (amount >= 0) {
            this.income +=  amount;
        } else {
            this.outcome += -amount;
        }
        this.finance.updateState(amount);
    }
}
