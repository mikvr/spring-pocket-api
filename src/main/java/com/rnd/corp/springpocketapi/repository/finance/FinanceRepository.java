package com.rnd.corp.springpocketapi.repository.finance;

import java.util.Optional;

import com.rnd.corp.springpocketapi.domain.finance.Finance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceRepository extends JpaRepository<Finance, Long> {

    Optional<Finance> findByUserId(String userId);
}
