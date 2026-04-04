package com.vaishnavv.finance_dashboard.controller;

import com.vaishnavv.finance_dashboard.model.FinancialRecord;
import com.vaishnavv.finance_dashboard.service.FinancialRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
public class FinancialRecordController {

    private final FinancialRecordService financialRecordService;

    public FinancialRecordController(FinancialRecordService financialRecordService) {
        this.financialRecordService = financialRecordService;
    }

    @PostMapping("/{userId}")
    public FinancialRecord createRecord(@PathVariable Long userId,
                                        @RequestBody FinancialRecord financialRecord) {
        return financialRecordService.createRecord(userId, financialRecord);
    }

    @GetMapping
    public List<FinancialRecord> getAllRecords() {
        return financialRecordService.getAllRecords();
    }

    @GetMapping("/user/{userId}")
    public List<FinancialRecord> getAllRecordsByUser(@PathVariable Long userId) {
        return financialRecordService.getRecordsByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public String deleteRecord(@PathVariable Long id){
        financialRecordService.deleteRecord(id);
        return "Record deleted successfully";
    }
}
