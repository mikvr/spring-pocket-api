package com.rnd.corp.springpocketapi.domain.finance;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float balance;
    private float income;
    private float outcome;
    private String userId;

    @OneToMany
    @JoinTable(name = "finance_archive",
    joinColumns = @JoinColumn(name = "id_finance"),
    inverseJoinColumns = @JoinColumn(name = "id_archive"))
    private Set<Archive> archives = new HashSet<>();

}
