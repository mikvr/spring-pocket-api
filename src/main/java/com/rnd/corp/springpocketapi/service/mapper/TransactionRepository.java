package com.rnd.corp.springpocketapi.service.mapper;

import java.util.Optional;
import javax.swing.text.html.Option;

import com.rnd.corp.springpocketapi.domain.finance.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findById(int id);
}
