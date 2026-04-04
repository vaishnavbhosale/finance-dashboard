package com.vaishnavv.finance_dashboard.repository;


import com.vaishnavv.finance_dashboard.model.FinanceType;
import com.vaishnavv.finance_dashboard.model.FinancialRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {
    List<FinancialRecord> findByUserId(Long userId);
    List<FinancialRecord> findByType(FinanceType type);
    List<FinancialRecord> findByCategory(String category);
    List<FinancialRecord> findByDate(LocalDate date);
}

