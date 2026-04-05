package com.vaishnavv.finance_dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardSummary {

    private double totalIncome;
    private double totalExpense;
    private double balance;

}
