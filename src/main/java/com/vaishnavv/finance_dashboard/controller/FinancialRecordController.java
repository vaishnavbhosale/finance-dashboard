package com.vaishnavv.finance_dashboard.controller;

import com.vaishnavv.finance_dashboard.dto.DashboardSummary;
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

    @PostMapping("/{userId}")
    public FinancialRecord createRecord(@PathVariable Long userId,
                                        @RequestBody FinancialRecord financialRecord) {
        return financialRecordService.createRecord(userId, financialRecord);
    }

    @GetMapping
    public List<FinancialRecord> getRecords(
            @RequestParam(required = false) FinanceType type,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) LocalDate date) {

        return financialRecordService.getFilteredRecords(type, category, date);
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

    @RestController
    @RequestMapping("/dashboard")
    public class DashboardController {

        private final FinancialRecordService financialRecordService;

        public DashboardController(FinancialRecordService financialRecordService) {
            this.financialRecordService = financialRecordService;
        }

        @GetMapping("/summary")
        public DashboardSummary getSummary() {
            return financialRecordService.getDashboardSummary();
        }
    }

}
