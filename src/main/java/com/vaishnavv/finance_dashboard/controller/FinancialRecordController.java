package com.vaishnavv.finance_dashboard.controller;

import com.vaishnavv.finance_dashboard.model.FinanceType;
import com.vaishnavv.finance_dashboard.model.FinancialRecord;
import com.vaishnavv.finance_dashboard.service.FinancialRecordService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/records")
public class FinancialRecordController {

    private final FinancialRecordService financialRecordService;

    public FinancialRecordController(FinancialRecordService financialRecordService) {
        this.financialRecordService = financialRecordService;
    }

    // 🔥 CREATE (ADMIN)
    @PostMapping("/{userId}")
    public FinancialRecord createRecord(@PathVariable Long userId,
                                        @RequestBody FinancialRecord financialRecord) {
        return financialRecordService.createRecord(userId, financialRecord);
    }

    // 🔥 GET (ANALYST + ADMIN)
    @GetMapping
    public List<FinancialRecord> getRecords(
            @RequestParam Long userId,
            @RequestParam(required = false) FinanceType type,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) LocalDate date) {

        return financialRecordService.getFilteredRecords(userId, type, category, date);
    }

    // 🔥 GET BY USER
    @GetMapping("/user/{userId}")
    public List<FinancialRecord> getAllRecordsByUser(@PathVariable Long userId) {
        return financialRecordService.getRecordsByUserId(userId);
    }

    // 🔥 UPDATE (ADMIN)
    @PutMapping("/{userId}/{id}")
    public FinancialRecord updateRecord(@PathVariable Long userId,
                                        @PathVariable Long id,
                                        @RequestBody FinancialRecord financialRecord) {
        return financialRecordService.updateRecord(userId, id, financialRecord);
    }

    // 🔥 DELETE (ADMIN)
    @DeleteMapping("/{userId}/{id}")
    public String deleteRecord(@PathVariable Long userId,
                               @PathVariable Long id) {
        financialRecordService.deleteRecord(userId, id);
        return "Record deleted successfully";
    }
}