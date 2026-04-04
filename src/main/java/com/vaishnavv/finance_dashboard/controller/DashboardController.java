package com.vaishnavv.finance_dashboard.controller;

import com.vaishnavv.finance_dashboard.dto.DashboardSummary;
import com.vaishnavv.finance_dashboard.service.FinancialRecordService;
import org.springframework.web.bind.annotation.*;

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
