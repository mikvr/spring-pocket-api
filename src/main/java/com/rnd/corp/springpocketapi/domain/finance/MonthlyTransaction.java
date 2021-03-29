package com.rnd.corp.springpocketapi.domain.finance;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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

}
