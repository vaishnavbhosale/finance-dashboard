package com.vaishnavv.finance_dashboard.service;

import com.vaishnavv.finance_dashboard.model.FinancialRecord;
import com.vaishnavv.finance_dashboard.model.User;
import com.vaishnavv.finance_dashboard.repository.FinancialRecordRepository;
import com.vaishnavv.finance_dashboard.repository.UserRepository;
import org.springframework.stereotype.Service;

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


}
