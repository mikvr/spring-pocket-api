package com.rnd.corp.springpocketapi.repository.finance;

import java.util.List;
import java.util.Optional;

import com.rnd.corp.springpocketapi.domain.finance.MonthlyTransaction;
import com.rnd.corp.springpocketapi.domain.finance.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findById(int id);

    List<Transaction> findAllByMtId(MonthlyTransaction id);
}
