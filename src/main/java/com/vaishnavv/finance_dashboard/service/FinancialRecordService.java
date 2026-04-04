package com.vaishnavv.finance_dashboard.service;

import com.vaishnavv.finance_dashboard.model.FinanceType;
import com.vaishnavv.finance_dashboard.model.FinancialRecord;
import com.vaishnavv.finance_dashboard.model.User;
import com.vaishnavv.finance_dashboard.repository.FinancialRecordRepository;
import com.vaishnavv.finance_dashboard.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FinancialRecordService {

    private final FinancialRecordRepository financialRecordRepository;

    private final UserRepository userRepository;

    public FinancialRecordService(FinancialRecordRepository financialRecordRepository , UserRepository userRepository) {
        this.financialRecordRepository = financialRecordRepository;
        this.userRepository = userRepository;
    }

    public FinancialRecord createRecord(Long userId, FinancialRecord record) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        record.setUser(user);
        return financialRecordRepository.save(record);
    }

    public void deleteRecord(Long id){
        if(!financialRecordRepository.existsById(id)){
            throw new RuntimeException("Record not found");
        }
        financialRecordRepository.deleteById(id);
    }

    public List<FinancialRecord> getRecordsByUserId(Long userId){
        return financialRecordRepository.findByUserId(userId);
    }

    public List<FinancialRecord> getAllRecords() {
        return financialRecordRepository.findAll();
    }

    public List<FinancialRecord> getFilteredRecords(FinanceType type, String category, LocalDate date) {
        if (type != null) {
            return financialRecordRepository.findByType(type);
        }

        else if (category != null && !category.isEmpty()) {
            return financialRecordRepository.findByCategory(category);
        }
        else if (date != null) {
            return financialRecordRepository.findByDate(date);
        }
        else {
            return financialRecordRepository.findAll();
        }
    }


}
