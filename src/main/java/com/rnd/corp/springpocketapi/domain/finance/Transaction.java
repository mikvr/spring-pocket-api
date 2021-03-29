package com.rnd.corp.springpocketapi.domain.finance;

import java.time.Instant;
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
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "label_id")
    private Label label;
    private float amount;
    private int qty;
    private float unitPrice;
    private Instant date;

    @MapsId("id")
    @JoinColumns({
        @JoinColumn(name = "date_mt", referencedColumnName = "date"),
        @JoinColumn(name = "finance_fid", referencedColumnName = "finance_id")
    })
    @ManyToOne
    private MonthlyTransaction mtId;

}
