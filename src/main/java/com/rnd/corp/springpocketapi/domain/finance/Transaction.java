package com.rnd.corp.springpocketapi.domain.finance;

import java.time.Instant;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.rnd.corp.springpocketapi.domain.Label;
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
@Table(name = "transaction")
public class  Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "label_id")
    private Label label;
    private float amount;
    private int qty;
    private float unitPrice;
    private Instant date;

    @Column(name = "date_mt")
    private Instant dateMt;

    @Column(name = "finance_fid")
    private int financeId;

    @MapsId("id")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({
        @JoinColumn(name = "date_mt", referencedColumnName = "date"),
        @JoinColumn(name = "finance_fid", referencedColumnName = "finance_id")
    })
    private MonthlyTransaction mtId;

    public void updateAssociatedMonthlyTransaction(@NotNull MonthlyTransaction mTransaction) {
        this.mtId = mTransaction;
        this.dateMt = mTransaction.getId().getDate();
        this.financeId = mTransaction.getId().getFinanceId();
    }

}
