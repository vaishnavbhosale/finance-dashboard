package com.vaishnavv.finance_dashboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Data

public class FinancialRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Amount is required")
    private double  amount;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Type (INCOME/EXPENSE) is required")
    private FinanceType type;


    @NotBlank(message = "Category is required")
   private String category ;

    @NotNull(message = "Date is required")
    private LocalDate date;

    private String note;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    //@com.fasterxml.jackson.annotation.JsonIgnore // <--- ADD THIS LINE



}
