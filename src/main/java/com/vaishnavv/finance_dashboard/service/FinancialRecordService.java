package com.vaishnavv.finance_dashboard.service;

import com.vaishnavv.finance_dashboard.ExceptionHandler.ResourceNotFound;
import com.vaishnavv.finance_dashboard.dto.DashboardSummary;
import com.vaishnavv.finance_dashboard.model.FinanceType;
import com.vaishnavv.finance_dashboard.model.FinancialRecord;
import com.vaishnavv.finance_dashboard.model.Role;
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

    public FinancialRecordService(FinancialRecordRepository financialRecordRepository,
                                  UserRepository userRepository) {
        this.financialRecordRepository = financialRecordRepository;
        this.userRepository = userRepository;
    }

    // helper (simple + reusable)
    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User not found"));
    }

    // CREATE > ADMIN only
    public FinancialRecord createRecord(Long userId, FinancialRecord record) {

        User user = getUser(userId);

        if (user.getRole() != Role.ADMIN) {
            throw new ResourceNotFound("Only ADMIN can create records");
        }

        record.setUser(user);
        return financialRecordRepository.save(record);
    }

    // DELETE > ADMIN only
    public void deleteRecord(Long userId, Long id) {

        User user = getUser(userId);

        if (user.getRole() != Role.ADMIN) {
            throw new ResourceNotFound("Only ADMIN can delete records");
        }

        if (!financialRecordRepository.existsById(id)) {
            throw new ResourceNotFound("Record not found");
        }

        financialRecordRepository.deleteById(id);
    }

    // UPDATE > ADMIN only
    public FinancialRecord updateRecord(Long userId, Long id, FinancialRecord updatedRecord) {

        User user = getUser(userId);

        if (user.getRole() != Role.ADMIN) {
            throw new ResourceNotFound("Only ADMIN can update records");
        }

        FinancialRecord existing = financialRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Record not found"));

        existing.setAmount(updatedRecord.getAmount());
        existing.setType(updatedRecord.getType());
        existing.setCategory(updatedRecord.getCategory());
        existing.setDate(updatedRecord.getDate());
        existing.setNote(updatedRecord.getNote());

        return financialRecordRepository.save(existing);
    }

    //  GET RECORDS > ANALYST + ADMIN
    public List<FinancialRecord> getFilteredRecords(Long userId,
                                                    FinanceType type,
                                                    String category,
                                                    LocalDate date) {

        User user = getUser(userId);

        if (user.getRole() == Role.USER) {
            throw new ResourceNotFound("VIEWER cannot access records");
        }

        if (type != null) {
            return financialRecordRepository.findByType(type);
        } else if (category != null && !category.isBlank()) {
            return financialRecordRepository.findByCategory(category);
        } else if (date != null) {
            return financialRecordRepository.findByDate(date);
        } else {
            return financialRecordRepository.findAll();
        }
    }

    //  GET BY USER > ANALYST + ADMIN
    public List<FinancialRecord> getRecordsByUserId(Long userId) {

        User user = getUser(userId);

        if (user.getRole() == Role.USER) {
            throw new ResourceNotFound("VIEWER cannot access records");
        }

        return financialRecordRepository.findByUserId(userId);
    }

    //  DASHBOARD > ALL
    public DashboardSummary getDashboardSummary() {

        List<FinancialRecord> records = financialRecordRepository.findAll();

        double totalIncome = 0;
        double totalExpense = 0;

        for (FinancialRecord record : records) {
            if (record.getType() == FinanceType.INCOME) {
                totalIncome += record.getAmount();
            } else {
                totalExpense += record.getAmount();
            }
        }

        return new DashboardSummary(
                totalIncome,
                totalExpense,
                totalIncome - totalExpense
        );
    }
}