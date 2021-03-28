package com.rnd.corp.springpocketapi.domain.finance;

import java.time.Instant;
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
@Table(name = "archive")
public class Archive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float balance;
    private float income;
    private float outcome;
    private Instant date;
}
