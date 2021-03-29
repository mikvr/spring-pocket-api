package com.rnd.corp.springpocketapi.repository.finance;

import java.util.Optional;

import com.rnd.corp.springpocketapi.domain.MonthlyTransactionId;
import com.rnd.corp.springpocketapi.domain.finance.MonthlyTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyTransactionRepository extends JpaRepository<MonthlyTransaction, Long> {

    Optional<MonthlyTransaction> findById(MonthlyTransactionId id);
}
